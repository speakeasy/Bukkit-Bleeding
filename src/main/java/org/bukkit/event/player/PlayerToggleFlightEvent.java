package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * @author zml2008
 */
public class PlayerToggleFlightEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final boolean newValue;
    private boolean cancelled;

    public PlayerToggleFlightEvent(final Player who, final boolean newValue) {
        super(who);
        this.newValue = newValue;
    }

    public boolean getNewValue() {
        return newValue;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
