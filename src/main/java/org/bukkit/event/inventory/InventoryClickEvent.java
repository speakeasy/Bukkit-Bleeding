package org.bukkit.event.inventory;

import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

/**
 * This event is fired when a player clicks in an inventory.
 * The Result enum is used to allow forcing something similar to vanilla
 * behavior in cases where vanilla would disallow the action.
 * <p>
 * The ClickAction enum describes the various types of ways a player can
 * click on an inventory, including an OTHER value for methods not yet
 * recognized by Bukkit.
 */
public class InventoryClickEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private SlotType slot_type;
    private Result result;
    private int whichSlot;
    private int rawSlot;
    private ItemStack current = null;
    private int hotbarKey = -1;

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, ClickAction action) {
        super(what, action);
        this.slot_type = type;
        this.result = Result.DEFAULT;
        this.rawSlot = slot;
        this.whichSlot = what.convertSlot(slot);
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, int key) {
        this(what, type, slot, ClickAction.NUMBER_KEY);
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
     * Shift and Ctrl can be combined with some actions as a modifier.
     * @return True if the action uses Shift or Ctrl.
     */
    public boolean isShiftClick() {
        return (action == ClickAction.SHIFT_LEFT) || (action == ClickAction.SHIFT_RIGHT) || (action == ClickAction.CONTROL_DROP);
    }

    /**
     * Some click events are only permitted in Creative mode.
     * @return True if this action normally requires Creative mode
     */
    public boolean isCreativeAction() {
        return (action == ClickAction.MIDDLE) || (action == ClickAction.CREATIVE);
    }

    /**
     * Set the result of the InventoryClickEvent.
     * <p>
     * <b>WARNING</b>: Result.ALLOW deviates from vanilla behavior. For
     * details, see the net.minecraft.server.PlayerConnection class from your
     * Bukkit implementation. It is recommended to use Result.DEFAULT in most
     * cases.
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
            // Guess whether vanilla will deny it
            if (isCreativeAction()) {
                return getView().getPlayer().getGameMode() != GameMode.CREATIVE;
            }
            return false;
        }
    }

    /**
     * Sets the result of this event in a manner consistent with isCancelled().
     * @param toCancel result is DENY if true, DEFAULT if false, and ALLOW if
     *    false and this ClickEvent requires creative
     */
    public void setCancelled(boolean toCancel) {
        result = toCancel ? Result.DENY : (isCreativeAction() ? Result.ALLOW : Result.DEFAULT);
    }

    /**
     * The slot number that was clicked, ready for passing to {@link Inventory#getItem(int)}. Note
     * that there may be two slots with the same slot number, since a view links two different inventories.
     * @return The slot number.
     */
    public int getSlot() {
        return whichSlot;
    }

    /**
     * The raw slot number, which is unique for the view.
     * @return The slot number.
     */
    public int getRawSlot() {
        return rawSlot;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return which number
     * key was pressed.
     * @return the slot number, 0-8, or -1 if action is not NUMBER_KEY
     */
    public int getHotbarKey() {
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
