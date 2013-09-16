package org.bukkit.inventory.meta;

import org.bukkit.Material;
import org.bukkit.SkullType;

/**
 * Represents a skull ({@link Material#SKULL_ITEM}) that can have an owner.
 */
public interface SkullMeta extends ItemMeta {

    /**
     * Gets the owner of the skull.
     *
     * @return the owner of the skull
     */
    String getOwner();

    /**
     * Checks to see if the skull has an owner.
     *
     * @return true if the skull has an owner
     */
    boolean hasOwner();

    /**
     * Sets the owner of the skull.
     * Plugins should check that hasOwner() returns true before calling this plugin.
     *
     * @param owner the new owner of the skull
     * @return true if the owner was successfully set
     */
    boolean setOwner(String owner);

    /**
     * Gets the type of the skull.
     *
     * @return the type of the skull
     */
    SkullType getType();

    /**
     * Sets the type of the skull.
     *
     * @return true if the type of the skull was successfully set
     */
    boolean setType(SkullType type);

    SkullMeta clone();
}
