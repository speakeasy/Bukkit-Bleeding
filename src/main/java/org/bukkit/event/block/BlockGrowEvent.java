package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockView;
import org.bukkit.event.HandlerList;

/**
 * Called when a block grows naturally in the world.
 * <p />
 * Examples:
 * <ul>
 * <li>Wheat</li>
 * <li>Sugar Cane</li>
 * <li>Cactus</li>
 * <li>Watermelon</li>
 * <li>Pumpkin</li>
 * </ul>
 * <p />
 * If a Block Grow event is cancelled, the block will not grow.
 */
public class BlockGrowEvent extends BlockStateChangeEvent {
    private static final HandlerList handlers = new HandlerList();

    /**
     * @deprecated This constructor is provided for compatibility. It will
     *          create an immutable version from the provided block state.
     *          For mutability, the BlockState should be downcast.
     */
    @Deprecated
    public BlockGrowEvent(final Block block, final BlockState newState) {
        super(block, newState);
    }

    public BlockGrowEvent(final Block block, final BlockView newState) {
        super(block, newState);
    }

    /**
     * Gets the state of the block where it will form or spread to.
     *
     * @deprecated The block state returned here is a new copy from
     *          the block provided in the underlying BlockView. The
     *          'copied' behavior should not be relied on, as it may
     *          change at some time in the future.
     * @return The block state for this events block
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
