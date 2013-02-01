package org.bukkit.inventory;

public interface AnvilInventory extends Inventory {
    //TODO Are the javadocs OK

    /**
     * Gets the base item to combine on the anvil.
     *
     * @return the item to combine on the anvil
     */
    ItemStack getBaseItem();

    /**
     * Sets the base item to combine on the anvil.
     *
     * @param item the new item
     */
    void setBaseItem(ItemStack item);

    /**
     * Gets the item to be combined with on the anvil.
     *
     * @return the item to be combined with on the anvil
     */
    ItemStack getCombiningItem();

    /**
     * Sets the item to be combined with on the anvil.
     *
     * @param item the new item
     */
    void setCombiningItem(ItemStack item);

    /**
     * Gets the item resulting from the combination of the items on the anvil.
     *
     * @return the resultant item
     */
    ItemStack getResult();

    /**
     * Sets the item that will result from the combination of the items on the anvil.
     *
     * @param item the new resultant item
     */
    void setResult(ItemStack item);
}
