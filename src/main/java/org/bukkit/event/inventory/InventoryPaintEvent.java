package org.bukkit.event.inventory;

import java.util.Map;
import java.util.Set;

import org.bukkit.event.Cancellable;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryPaintEvent extends InventoryEvent implements Cancellable {
    /**
     * The keys are slot numbers in the InventoryView.
     * The values are how many items to put in each slot.
     */
    private Map<Integer, Integer> slots;
    private int amount;
    private boolean right;
    private boolean cancelled;
    private ItemStack cursor;

    public InventoryPaintEvent(InventoryView what, ItemStack itemstack1, boolean right, Map<Integer, Integer> slots) {
        super(what);
        this.right = right;
        this.slots = slots;
        this.cancelled = false;
        if (right) {
            amount = 1;
        } else {
            amount = (int) (what.getCursor().getAmount() / slots.size());
        }
    }

    /**
     * Gets the new cursor after the event.
     * @return the current item
     */
    public ItemStack getCursor() {
        return getView().getCursor();
    }

    /**
     * Get a Map of all the slots involved in this InventoryPaintEvent.
     * @return map from InventoryView slot number to number of items to put in
     */
    public Map<Integer, Integer> getSlots() {
        return slots;
    }

    public void addSlot(boolean recalculate) {

    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
