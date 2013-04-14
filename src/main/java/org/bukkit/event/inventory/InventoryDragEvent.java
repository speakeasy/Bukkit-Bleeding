package org.bukkit.event.inventory;

import java.util.Collections;
import java.util.Set;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ImmutableSet;

/**
 * This event is called when the player drags an item in their cursor across
 * the inventory. The event is called even when only one slot is selected
 * (despite not rendering on the client) for technical reasons.
 */
public class InventoryDragEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private ItemStack newCursor;
    private Set<Integer> rawSlots;
    private Set<Integer> containerSlots;

    public InventoryDragEvent(InventoryView what, ItemStack newCursor, boolean right, Set<Integer> slots) {
        super(what, right ? InventoryAction.DRAG_RIGHT : InventoryAction.DRAG_LEFT);
        this.cancelled = false;
        this.newCursor = newCursor;
        this.rawSlots = Collections.unmodifiableSet(slots);
        ImmutableSet.Builder<Integer> b = ImmutableSet.builder();
        for (Integer slot : slots) {
            b.add(what.convertSlot(slot));
        }
        this.containerSlots = b.build();
    }

    /**
     * Get the slots to be changed in this InventoryDragEvent.
     * @return list of raw slot ids, suitable for InventoryView.getItem()
     */
    public Set<Integer> getRawSlots() {
        return rawSlots;
    }

    /**
     * Get the slots to be changed in this InventoryDragEvent.
     * @return list of converted slot ids, suitable for Container.getItem()
     */
    public Set<Integer> getContainerSlots() {
        return containerSlots;
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
