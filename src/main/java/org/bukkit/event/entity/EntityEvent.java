package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

/**
 * Represents an Entity-related event
 */
public abstract class EntityEvent extends Event implements Event.EntityEvent {
    protected Entity entity;

    public EntityEvent(final Entity what) {
        entity = what;
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityType getEntityType() {
        return entity.getType();
    }
}
