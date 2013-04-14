package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryActionEvent extends InventoryEvent {
    protected final ClickAction action;

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

    public InventoryActionEvent(InventoryView transaction, ClickAction action) {
        super(transaction);
        this.action = action;
    }

    /**
     * Get the current item on the cursor.
     * @return The cursor item
     */
    public ItemStack getCursor() {
        return getView().getCursor();
    }

    public ClickAction getAction() {
        return action;
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
}
