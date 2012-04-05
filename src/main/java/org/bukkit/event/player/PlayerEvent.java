package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Represents a player related event
 */
public abstract class PlayerEvent extends Event implements Event.PlayerEvent {
    protected Player player;

    public PlayerEvent(final Player who) {
        player = who;
    }

    public final Player getPlayer() {
        return player;
    }
}
