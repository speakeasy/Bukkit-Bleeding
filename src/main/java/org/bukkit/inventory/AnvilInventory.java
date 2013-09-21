package org.bukkit.inventory;

public interface AnvilInventory extends Inventory {
    /**
     * Gets the cost of the current repair.
     *
     * @return the cost of the current repair
     */
    public int getRepairCost();

    /**
     * Sets the cost of the current repair.
     *
     * @param cost the cost of the current repair
     */
    public void setRepairCost(int cost);

    /**
     * Gets the name of the of the result item, or null if there currently
     * isn't an item in the result slot.
     *
     * @return the name of the result item
     */
    public String getNewName();

    /**
     * Sets the name of the result item, and updates the experience cost for
     * the new name.
     *
     * @param name the new name of the result
     */
    public void setNewName(String name);
}
