package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called after the fact the block is placed, allowing notification of the final
 * state of the block.
 */
public class BlockPostPlaceEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();

    private Player player;

    public BlockPostPlaceEvent(final Block block, final Player player) {
        super(block);

        this.player = player;
    }

    /**
     * Gets the player who placed the block involved in this event.
     *
     * @return player that placed the block involved in this event
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
