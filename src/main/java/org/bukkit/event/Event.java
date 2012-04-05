package org.bukkit.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * Represents an event
 */
public abstract class Event {
    private String name;

    /**
     * @return Name of this event
     */
    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public abstract HandlerList getHandlers();

    public enum Result {

        /**
         * Deny the event.
         * Depending on the event, the action indicated by the event will either not take place or will be reverted.
         * Some actions may not be denied.
         */
        DENY,
        /**
         * Neither deny nor allow the event.
         * The server will proceed with its normal handling.
         */
        DEFAULT,
        /**
         * Allow / Force the event.
         * The action indicated by the event will take place if possible, even if the server would not normally allow the action.
         * Some actions may not be allowed.
         */
        ALLOW;
    }

    public interface PlayerEvent {
        /**
         * Returns the player involved in this event
         *
         * @return Player involved in this event
         */
        Player getPlayer();
    }

    public interface EntityEvent {
        /**
         * Returns the entity involved in this event
         *
         * @return Entity involved in this event
         */
        Entity getEntity();

        /**
         * Gets the EntityType of the Entity involved in this event.
         *
         * @return EntityType of the Entity involved in this event
         */
        EntityType getEntityType();
    }

    public interface BlockEvent {
        /**
         * Gets the block involved in this event
         *
         * @return Block involved in this event
         */
        Block getBlock();
    }
}
