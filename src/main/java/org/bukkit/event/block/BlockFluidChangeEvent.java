package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockView;
import org.bukkit.event.HandlerList;

/**
 * This event is fired when a fluid changes level, as opposed to
 * {@link BlockFromToEvent} where a new fluid block is created.
 */
public class BlockFluidChangeEvent extends BlockStateChangeEvent {
    private static final HandlerList handlers = new HandlerList();

    public BlockFluidChangeEvent(Block block, BlockView newState) {
        super(block, newState);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
