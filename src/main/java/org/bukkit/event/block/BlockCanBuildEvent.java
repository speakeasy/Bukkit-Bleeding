package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Called when we try to place a block, to see if we can build it here or not
 * <p>
 * Note:
 * <ul>
 * <li>The Block returned by getBlock() is the block we are trying to place
 * on, not the block we are trying to place.</li>
 * <li>If you want to figure out what is being placed, use
 * {@link #getMaterial()} or {@link #getMaterialId()} instead.</li>
 * </ul>
 */
public class BlockCanBuildEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private boolean buildable;
    private final int material;
    private final Entity entity;

    @Deprecated
    public BlockCanBuildEvent(final Block block, final int id, final boolean canBuild) {
        this(block, id, canBuild, null);
    }

    public BlockCanBuildEvent(final Block block, final int id, final boolean canBuild, final Entity entity) {
        super(block);
        this.buildable = canBuild;
        this.material = id;
        this.entity = entity;
    }

    /**
     * Gets whether or not the block can be built here.
     * By default, returns Minecraft's answer on whether the block can be
     * built here or not.
     *
     * @return boolean whether or not the block can be built
     */
    public boolean isBuildable() {
        return buildable;
    }

    /**
     * Sets whether the block can be built here or not.
     *
     * @param buildable true if you want to allow the block to be built here
     *                  despite Minecraft's default behaviour
     */
    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    /**
     * Gets the Material that we are trying to place.
     *
     * @return The Material that we are trying to place
     */
    public Material getMaterial() {
        return Material.getMaterial(material);
    }

    /**
     * Gets the Material ID for the Material that we are trying to place.
     *
     * @return The Material ID for the Material that we are trying to place
     */
    public int getMaterialId() {
        return material;
    }

    /**
     * Gets the Entity trying to place the block.
     *
     * @return The Entity trying to place the block or null if no entity is
     *         involved.
     */
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
