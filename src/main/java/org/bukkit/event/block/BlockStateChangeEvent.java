package org.bukkit.event.block;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockView;
import org.bukkit.block.SimpleBlockView;
import org.bukkit.event.Cancellable;

/**
 * Called when a block changes state.<br>
 * Fluid flow, block fade, and block growth are examples.
 */
public abstract class BlockStateChangeEvent extends BlockEvent implements Cancellable {
    private final BlockView newState;
    private boolean cancelled = false;

    /**
     * @deprecated This constructor is provided for compatibility reasons. It
     *          will make an immutable version of the provided BlockState. If
     *          mutability is desired, the BlockState should be explicitly
     *          downcasted for use in the other constructor.
     */
    @Deprecated
    public BlockStateChangeEvent(final Block block, final BlockState newState) {
        super(block);
        Validate.notNull(newState, "Cannot use null state");
        this.newState = new SimpleBlockView(newState);
    }

    public BlockStateChangeEvent(final Block block, final BlockView newState) {
        super(block);
        Validate.notNull(newState, "Cannot use null state");
        this.newState = newState;
    }

    /**
     * Gets the new state of the block that will change.
     *
     * @deprecated The block state returned here is a new copy from
     *          the block provided in the underlying BlockView. The
     *          'copied' behavior should not be relied on, as it may
     *          change at some time in the future.
     * @return The block state of the changing block
     */
    @Deprecated
    public BlockState getNewState() {
        final BlockState copiedState = newState.getBlock().getState();
        copiedState.setType(newState.getType());
        copiedState.setRawData(newState.getRawData());
        return copiedState;
    }

    /**
     * Gets the post-change state. The value returned here may,
     * or may not be mutable.
     * @return a BlockView representing this StateChangeEvent
     */
    public BlockView getChangedState() {
        return newState;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
