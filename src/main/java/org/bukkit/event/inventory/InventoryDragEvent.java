package org.bukkit.event.inventory;

import java.util.Map;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryDragEvent extends InventoryActionEvent implements Cancellable {
    /**
     * The keys are slot numbers in the InventoryView.
     * The values are how many items to put in each slot.
     */
    private Map<Integer, Integer> slots;
    private boolean cancelled;
    private ItemStack cursor;

    public InventoryDragEvent(InventoryView what, ItemStack itemstack1, boolean right, Map<Integer, Integer> slots) {
        super(what, right ? ClickAction.DRAG_RIGHT : ClickAction.DRAG_LEFT);
        this.slots = slots;
        this.cancelled = false;
    }

    /**
     * Get a Map of all the slots involved in this InventoryPaintEvent.
     * @return map from InventoryView slot number to number of items to put in
     */
    public Map<Integer, Integer> getSlots() {
        return slots;
    }

    /**
     * Get the result cursor after the painting is done.
     * <p>
     * Changing this item stack changes the cursor item. Note that changing
     * the affected "painted" slots does not update this item stack to
     * reflect the changes you've made.
     * <p>
     * To get the cursor item before the painting begins, use
     * {@link #getView()} and then {@link InventoryView#getCursor()}.
     * @return the cursor ItemStack
     */
    public ItemStack getNewCursor() {
        return cursor;
    }

    /**
     * Sets the result cursor after the painting is done.
     * @param newCursor - the new cursor itemstack
     */
    public void setNewCursor(ItemStack newCursor) {
        cursor = newCursor;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
