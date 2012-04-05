package org.bukkit.event.painting;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Painting;
import org.bukkit.event.Event;

/**
 * Triggered when a painting is removed by an entity
 */
public class PaintingBreakByEntityEvent extends PaintingBreakEvent implements Event.EntityEvent {
    private final Entity remover;

    public PaintingBreakByEntityEvent(final Painting painting, final Entity remover) {
        super(painting, RemoveCause.ENTITY);
        this.remover = remover;
    }

    /**
     * Gets the entity that removed the painting
     *
     * @return the entity that removed the painting.
     */
    public Entity getRemover() {
        return remover;
    }

    public Entity getEntity() {
        return remover;
    }

    public EntityType getEntityType() {
        return remover.getType();
    }
}
