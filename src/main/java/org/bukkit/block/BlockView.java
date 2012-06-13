package org.bukkit.block;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 * Represents a captured state of a block.
 * Unlike Block, which only one object can exist per coordinate, BlockView can
 * exist multiple times for any given Block. Note that another plugin may change
 * the state of the block and you will not know, or they may change the block to
 * another type entirely, causing your BlockState to become invalid.
 */
public interface BlockView {

    /**
     * Gets the block represented by this BlockState
     *
     * @return Block that this BlockState represents
     */
    Block getBlock();

    /**
     * Gets the type of this block
     *
     * @return block type
     */
    Material getType();

    /**
     * Gets the type-id of this block
     *
     * @return block type-id
     */
    int getTypeId();

    /**
     * Gets the light level between 0-15
     *
     * @return light level
     */
    byte getLightLevel();

    /**
     * Gets the world which contains this Block
     *
     * @return World containing this block
     */
    World getWorld();

    /**
     * Gets the x-coordinate of this block
     *
     * @return x-coordinate
     */
    int getX();

    /**
     * Gets the y-coordinate of this block
     *
     * @return y-coordinate
     */
    int getY();

    /**
     * Gets the z-coordinate of this block
     *
     * @return z-coordinate
     */
    int getZ();

    /**
     * Gets the location of this block
     *
     * @return location
     */
    Location getLocation();

    /**
     * Gets the chunk which contains this block
     *
     * @return Containing Chunk
     */
    Chunk getChunk();

    /**
     * @return The data as a raw byte.
     */
    byte getRawData();
}
