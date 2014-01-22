package org.bukkit.event.player;

import org.bukkit.QuitReason;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player leaves a server
 */
public class PlayerQuitEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String quitMessage;
    private QuitReason quitReason;

    @Deprecated
    public PlayerQuitEvent(final Player who, final String quitMessage) {
        this(who, quitMessage, QuitReason.UNKNOWN);
    }

    public PlayerQuitEvent(final Player who, final String quitMessage, QuitReason quitReason) {
        super(who);
        this.quitMessage = quitMessage;
        this.quitReason = quitReason;
    }

    /**
     * Gets the quit message to send to all online players
     *
     * @return string quit message
     */
    public String getQuitMessage() {
        return quitMessage;
    }

    /**
     * Sets the quit message to send to all online players
     *
     * @param quitMessage quit message
     */
    public void setQuitMessage(String quitMessage) {
        this.quitMessage = quitMessage;
    }

    /**
     * Gets the reason the player left the server
     *
     * @return QuitReason quit reason
     */
    public QuitReason getQuitReason() {
        return quitReason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
