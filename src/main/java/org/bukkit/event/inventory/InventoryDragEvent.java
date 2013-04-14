package org.bukkit.event.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * This event is called when the player drags an item in their cursor across
 * the inventory. The event is called even when only one slot is selected
 * (despite not rendering on the client) for technical reasons.
 */
public class InventoryDragEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private ItemStack newCursor;
    private List<DraggingSlot> slots;

    /**
     * A holder class for the slots contained in this InventoryDragEvent.
     */
    public class DraggingSlot {
        private ItemStack item;
        private ItemStack result;
        private SlotType slotType;
        private int whichSlot;
        private int rawSlot;

        public DraggingSlot(int rawSlot, int amount) {
            InventoryView view = getView();
            item = view.getItem(rawSlot);
            result = view.getCursor();
            if (result != null) {
                result = result.clone();
                result.setAmount(item.getAmount() + amount);
            }
            slotType = view.getSlotType(rawSlot);
            whichSlot = view.convertSlot(rawSlot);
            this.rawSlot = rawSlot;
        }

        /**
         * Gets the item in the dragged-over slot.
         * @return The item
         */
        public ItemStack getItem() {
            return item.clone();
        }

        /**
         * Gets the result item in the dragged slot after the drag is done.
         * <p>
         * Changes to this item stack will be reflected in the inventory.
         * @return The result item
         */
        public ItemStack getResult() {
            return result;
        }

        /**
         * Sets the result item in the dragged slot.
         * @param result The result item
         */
        public void setResult(ItemStack result) {
            this.result = result;
        }

        /**
         * Gets the SlotType of the dragged slot.
         * @return The slot type
         */
        public SlotType getSlotType() {
            return slotType;
        }

        /**
         * The slot number that was clicked, ready for passing to
         * {@link Inventory#getItem(int)}.
         * <p>
         * Note that there may be two slots with the same slot number, since
         * a view links two different inventories.
         * @return The slot number.
         */
        public int getSlot() {
            return whichSlot;
        }

        /**
         * <p>The raw slot number, which is unique for the view.</p>
         * @return The slot number.
         */
        public int getRawSlot() {
            return rawSlot;
        }
    }

    public InventoryDragEvent(InventoryView what, ItemStack newCursor, boolean right, Map<Integer, Integer> slots) {
        super(what, right ? InventoryAction.DRAG_RIGHT : InventoryAction.DRAG_LEFT);
        this.cancelled = false;
        this.newCursor = newCursor;
        this.slots = new ArrayList<DraggingSlot>();
        for (Map.Entry<Integer, Integer> slot : slots.entrySet()) {
            this.slots.add(new DraggingSlot(slot.getKey(), slot.getValue()));
        }
    }

    /**
     * Get the slots to be changed in this InventoryDragEvent.
     * @return list of DraggingSlots
     */
    public List<DraggingSlot> getSlots() {
        return Collections.unmodifiableList(slots);
    }

    /**
     * Set the slots to be changed in this drag event.
     * @param newslots list of slots to use
     */
    public void setSlots(List<DraggingSlot> newslots) {
        this.slots = new ArrayList<DraggingSlot>(newslots);
    }

    /**
     * Get the result cursor after the drag is done.
     * <p>
     * Changing this item stack changes the cursor item. Note that changing
     * the affected "dragged" slots does not update this item stack to
     * reflect the changes you've made.
     * <p>
     * To get the cursor item before the drag begins, use
     * {@link #getView()} and then {@link InventoryView#getCursor()}.
     * @return the cursor ItemStack
     */
    public ItemStack getNewCursor() {
        return newCursor;
    }

    /**
     * Sets the result cursor after the drag is done.
     * @param newCursor - the new cursor itemstack
     */
    public void setNewCursor(ItemStack newCursor) {
        this.newCursor = newCursor;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
