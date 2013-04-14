package org.bukkit.event.inventory;

import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.entity.HumanEntity;
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
public class InventoryClickEvent extends InventoryEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private SlotType slot_type;
    private Result result;
    private int whichSlot;
    private int rawSlot;
    private ItemStack current = null;
    private ClickAction action;
    private int hotbarKey = -1;

    public enum ClickAction {
        /**
         * The left (or primary) mouse button.
         */
        LEFT,
        /**
         * Holding shift while pressing the left mouse button.
         */
        SHIFT_LEFT,
        /**
         * The right mouse button.
         */
        RIGHT,
        /**
         * Holding shift while pressing the right mouse button.
         */
        SHIFT_RIGHT,
        /**
         * The middle mouse button, or a "scrollwheel click".
         */
        MIDDLE,
        /**
         * @see InventoryPaintEvent
         * Selecting slots for a left-click paint event.
         * If cancelled, this slot will be dropped from the subsequent event.
         */
        DRAG_LEFT,
        /**
         * @see InventoryPaintEvent
         * Selecting slots for a right-click paint event.
         * If cancelled, this slot will be dropped from the subsequent event.
         */
        DRAG_RIGHT,
        /**
         * One of the number keys 1-9, correspond to slots on the hotbar.
         */
        NUMBER_KEY,
        /**
         * Pressing the left mouse button twice in quick succession.
         */
        DOUBLE_CLICK,
        /**
         * The "Drop" key (defaults to Q).
         */
        DROP,
        /**
         * Holding Ctrl while pressing the "Drop" key (defaults to Q).
         */
        CONTROL_DROP,
        /**
         * Any action done with the Creative inventory open.
         */
        CREATIVE,
        /**
         * A type of inventory manipulation not yet recognized by Bukkit.
         * This is only for transitional purposes on a new Minecraft update.
         * <p>
         * Any ClickAction.OTHER is called on a best-effort basis.
         */
        OTHER,
        ;
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, ClickAction action) {
        super(what);
        this.slot_type = type;
        this.action = action;
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
     * Get the current item on the cursor.
     * @return The cursor item
     */
    public ItemStack getCursor() {
        return getView().getCursor();
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
     * @return True if the click action is with the right mouse button.
     */
    public boolean isRightClick() {
        return (action == ClickAction.RIGHT) || (action == ClickAction.SHIFT_RIGHT) || (action == ClickAction.DRAG_RIGHT);
    }

    /**
     * @return True if the click action is with the left mouse button.
     */
    public boolean isLeftClick() {
        return (action == ClickAction.LEFT) || (action == ClickAction.SHIFT_LEFT) || (action == ClickAction.DRAG_LEFT) || (action == ClickAction.DOUBLE_CLICK) || (action == ClickAction.CREATIVE);
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
     * Get the player who performed the click.
     * @return The clicking player.
     */
    public HumanEntity getWhoClicked() {
        return getView().getPlayer();
    }

    /**
     * Set the item on the cursor.
     * @param what The new cursor item.
     */
    public void setCursor(ItemStack what) {
        getView().setCursor(what);
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
