package org.bukkit.entity;

import org.bukkit.Material;

/**
 * Represents a falling block.
 */
public interface FallingBlock extends Entity {
    /**
     * Get the type of block this is.
     * @return The type of block.
     */
    Material getBlockType();
    /**
     * Set the type of block this is.
     * @param material The new block type (must be one of SAND, GRAVEL, or DRAGON_EGG).
     */
    void setBlockType(Material material);
}
