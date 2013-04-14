package org.bukkit.event.inventory;

import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

/**
 * This event is called when a player clicks in an inventory.
 * The Result enum is used to allow forcing something similar to vanilla
 * behavior in cases where vanilla would disallow the action.
 * <p>
 * The ClickAction enum describes the various types of ways a player can
 * click on an inventory, including an OTHER value for methods not yet
 * recognized by Bukkit.
 * <p>
 * This event is called with ClickAction.DRAG_* for the selection of
 * Individual slots. To disallow the drag as a whole, listen to
 * {@link InventoryDragEvent}.
 */
public class InventoryClickEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private SlotType slot_type;
    protected Result result;
    private int whichSlot;
    private int rawSlot;
    private ItemStack current = null;
    private int hotbarKey = -1;

    @Deprecated
    public InventoryClickEvent(InventoryView what, SlotType type, int slot, boolean right, boolean shift) {
        this(what, type, slot, right ? (shift ? InventoryAction.SHIFT_RIGHT : InventoryAction.RIGHT) : (shift ? InventoryAction.SHIFT_LEFT : InventoryAction.LEFT));
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, InventoryAction action) {
        super(what, action);
        this.slot_type = type;
        this.result = Result.DEFAULT;
        this.rawSlot = slot;
        this.whichSlot = what.convertSlot(slot);
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, int key) {
        this(what, type, slot, InventoryAction.NUMBER_KEY);
        this.hotbarKey = key;
    }

    /**
     * Get the type of slot that was clicked.
     * @return The slot type.
     */
    public SlotType getSlotType() {
        return slot_type;
    }

    /**
     * Get the current item in the clicked slot.
     * @return The slot item.
     */
    public ItemStack getCurrentItem() {
        if (slot_type == SlotType.OUTSIDE) {
            return current;
        }
        return getView().getItem(rawSlot);
    }

    /**
     * Set the result of the InventoryClickEvent.
     *
     * @param newResult new {@link Result}
     */
    public void setResult(Result newResult) {
        result = newResult;
    }

    /**
     * Get the {@link Result} of the event.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Set the current item in the slot.
     * @param what The new slot item.
     */
    public void setCurrentItem(ItemStack what) {
        if (slot_type == SlotType.OUTSIDE) {
            current = what;
        }
        else getView().setItem(rawSlot, what);
    }

    /**
     * Returns whether this event will be cancelled. If the result is
     * DEFAULT, a guess is made as to whether vanilla will allow the click.
     * @return whether the event should be considered cancelled
     */
    public boolean isCancelled() {
        if (result == Result.ALLOW) {
            return false;
        } else if (result == Result.DENY) {
            return true;
        } else {
            // Guess whether vanilla will deny it (this only applies for middle clicks, because InventoryCreativeEvent overrides it)
            if (isCreativeAction()) {
                return getView().getPlayer().getGameMode() != GameMode.CREATIVE;
            }
            return false;
        }
    }

    /**
     * Sets the result of this event in a manner consistent with
     * isCancelled(). It is preferred to use
     * {@link InventoryClickEvent#setResult(Result)} instead, however.
     *
     * @param toCancel result is DENY if true, DEFAULT if false, and ALLOW if
     *    false and this ClickEvent requires creative
     */
    public void setCancelled(boolean toCancel) {
        result = toCancel ? Result.DENY : (isCreativeAction() ? Result.ALLOW : Result.DEFAULT);
    }

    /**
     * The slot number that was clicked, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     * @return The slot number.
     */
    public int getSlot() {
        return whichSlot;
    }

    /**
     * The raw slot number clicked, ready for passing to
     * {@link InventoryView#getItem(int)} This slot number is unique for the
     * view.
     * @return The slot number.
     */
    public int getRawSlot() {
        return rawSlot;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the offset
     * into the InventoryView of the appropriate slot on the hotbar.
     * @return a raw slot index
     */
    public int getHotbarSlot() {
        if (hotbarKey == -1) return -1;
        return hotbarKey + getView().getTopInventory().getSize() + 28;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the index
     * of the pressed key (0-8).
     * @return the number on the key minus 1 (range 0-8); or -1 if not
     *     a NUMBER_KEY action
     */
    public int getHotbarButton() {
        return hotbarKey;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
