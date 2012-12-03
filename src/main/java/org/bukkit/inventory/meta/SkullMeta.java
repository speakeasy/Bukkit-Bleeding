package org.bukkit.inventory.meta;

public interface SkullMeta extends ItemMeta {
    /**
     * Gets the owner of the skull
     * @return the owner if the skull
     */
    String getOwner();

    /**
     * Checks to see if the skull has an owner
     * @return true if the skull has an owner
     */
    boolean hasOwner();

    /**
     * Sets the owner of the skull
     * @param owner the new owner of the skull
     * @return true if the owner was successfully set
     */
    boolean setOwner(String owner);
}
