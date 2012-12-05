package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * This event is fired when the player is leaving a bed.
 */
public class PlayerBedLeaveEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Block bed;
    private final boolean spawnChange;

    public PlayerBedLeaveEvent(final Player who, final Block bed, final boolean spawnChange) {
        super(who);
        this.bed = bed;
        this.spawnChange = spawnChange;
    }

    /**
     * Returns the bed block involved in this event.
     *
     * @return the bed block involved in this event
     */
    public Block getBed() {
        return bed;
    }

    /**
     * Indicates if the player spawn location will change to {@link #getBed()} after this event is processed.
     *
     * @return true if player spawn location will change; otherwise false
     */
    public boolean isSpawnChange() {
        return spawnChange;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
