package org.bukkit.event.inventory;

import org.apache.commons.lang.Validate;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;


public class AnvilUpdateEvent extends InventoryEvent {
    private static HandlerList handlers = new HandlerList();
    private final UpdateCause reason;
    private boolean costChanged;
    private String name;
    private int exp;

    public AnvilUpdateEvent(InventoryView transaction, int exp, String name, UpdateCause reason) {
        super(transaction);
        this.exp = exp;
        this.name = name;
        this.reason = reason;
    }

    @Override
    public AnvilInventory getInventory() {
        return (AnvilInventory) super.getInventory();
    }

    /**
     * Gets the name that will be applied to the ItemStack in the result
     * slot.
     *
     * @return the name that will be applied to the result ItemStack
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name that will be applied to the ItemStack in the result
     * slot. The text typed into the textbox cannot be changed, so changing
     * this value will only change the name applied to the item in slot 2.
     * <p>
     * If this value is changed and the experience cost has not been changed,
     * then the repair cost will be set to the 'vanilla' cost of renaming the
     * item to have the new name.
     *
     * @param newName the new name for the item in the result slot
     */
    public void setName(String newName) {
        Validate.isTrue(newName != null && !newName.isEmpty(), "New name may not be null or empty!");
        this.name = newName;
    }

    /**
     * Gets whether the experience cost has been changed by a plugin.
     *
     * @return whether the experience cost has been changed by a plugin
     */
    public boolean isExperienceCostChanged() {
        return costChanged;
    }

    /**
     * Gets the cost to enchant the item(in experience levels) contained
     * within the AnvilInventory associated with this event.
     *
     * @return the cost to enchant the item
     */
    public int getExperienceCost() {
        return exp;
    }

    /**
     * Sets the cost(in experience levels) to enchant the item contained
     * within the AnvilInventory associated with this event.
     * <p>
     * Any valid invocation of this method will change {@link
     * #isExperienceCostChanged()} to return true, regardless of whether or
     * not the experience cost has truly changed.
     *
     * @param exp the cost to enchant the item
     */
    public void setExperienceCost(int exp) {
        Validate.isTrue(exp > 0, "Exp must be greater than 0!");
        costChanged = true;
        this.exp = exp;
    }

    /**
     * Gets the cause of this AnvilUpdate.
     *
     * @return the cause of the event being fired
     */
    public UpdateCause getUpdateCause() {
        return reason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum UpdateCause {
        NAME_CHANGE,
        INVENTORY_CHANGE,
        ;
    }
}
