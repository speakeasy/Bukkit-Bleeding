package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryCreativeEvent extends InventoryClickEvent {
    private static final HandlerList handlers = new HandlerList();
    private ItemStack item;
    private boolean creative;

    public InventoryCreativeEvent(InventoryView what, SlotType type, int slot, ItemStack newItem, boolean inCreative) {
        super(what, type, slot, ClickAction.CREATIVE);
        this.item = newItem;
        this.creative = inCreative;
        result = inCreative ? Result.DEFAULT : Result.DENY;
    }

    /**
     * Get the ItemStack this slot is being set to.
     * @return the new ItemStack
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Sets the item the slot is being set to.
     * @param newItem the new ItemStack
     */
    public void setItem(ItemStack newItem) {
        item = newItem;
    }

    /**
     * The client doesn't tell the server what the cursor is, so we use
     * what's about to be set as a good substitute.
     */
    @Override
    public ItemStack getCursor() {
        return item;
    }

    @Override
    public boolean isCancelled() {
        if (result == Result.ALLOW) {
            return false;
        } else if (result == Result.DENY) {
            return true;
        } else {
            return !creative;
        }
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
