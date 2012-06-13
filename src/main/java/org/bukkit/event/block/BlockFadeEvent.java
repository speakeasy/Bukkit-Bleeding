package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockView;
import org.bukkit.event.HandlerList;

/**
 * Called when a block fades, melts or disappears based on world conditions
 * <p />
 * Examples:
 * <ul>
 * <li>Snow melting due to being near a light source.</li>
 * <li>Ice melting due to being near a light source.</li>
 * </ul>
 * <p />
 * If a Block Fade event is cancelled, the block will not fade, melt or disappear.
 */
public class BlockFadeEvent extends BlockStateChangeEvent {
    private static final HandlerList handlers = new HandlerList();

    /**
     * @deprecated This constructor is provided for compatibility. It will
     *          create an immutable version from the provided block state.
     *          For mutability, the BlockState should be downcast.
     */
    @Deprecated
    public BlockFadeEvent(final Block block, final BlockState newState) {
        super(block, newState);
    }

    public BlockFadeEvent(final Block block, final BlockView newState) {
        super(block, newState);
    }

    /**
     * Gets the state of the block that will be fading, melting or disappearing.
     *
     * @deprecated The block state returned here is a new copy from
     *          the block provided in the underlying BlockView. The
     *          'copied' behavior should not be relied on, as it may
     *          change at some time in the future.
     * @return The block state of the block that will be fading, melting or disappearing
     */
    @Override
    @Deprecated
    public BlockState getNewState() {
        return super.getNewState();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
