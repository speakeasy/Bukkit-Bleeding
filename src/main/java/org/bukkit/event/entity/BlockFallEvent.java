package org.bukkit.event.entity;

import org.bukkit.block.BlockState;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Triggered when a falling block lands on the ground. If cancelled, the block will be lost forever.
 */
public class BlockFallEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Item dropItem;
    private BlockState state;
    private boolean cancelled;

    public BlockFallEvent(FallingBlock block, Item drop, BlockState before) {
        super(block);
        this.dropItem = drop;
        this.state = before;
    }

    /**
     * Checks if the block managed to find space to land safely. If it doesn't
     * find space, it drops as an item rather than as a block.
     * @return True if there was space.
     */
    public boolean couldLandSafely() {
        return dropItem != null;
    }

    /**
     * Get the item entity that has dropped as a result of the block falling. It will be null
     * if there was enough space for the block to land properly.
     * @return The item entity or null
     */
    public Item getDrop() {
        return dropItem;
    }

    /**
     * Get the block that's falling.
     * @return The falling block
     */
    public FallingBlock getBlock() {
        return (FallingBlock) getEntity();
    }

    /**
     * Get the state of the block at the location that this block has fallen, prior to this block having fallen.
     * If there was space to fall properly, the block is no longer in this state.
     * @return The block that was there before this one fell.
     */
    public BlockState getTargetBlock() {
        return state;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
