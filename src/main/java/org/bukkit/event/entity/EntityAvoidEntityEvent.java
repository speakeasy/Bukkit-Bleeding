package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a creature wants to avoid another entity.
 * This event fires after most checks of avoidance feasibility have confirmed it possible to avoid,
 *   but this event does not signal that the entity is definitely going to move, merely its intent.
 */
public class EntityAvoidEntityEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Entity avoided;

    public EntityAvoidEntityEvent(final Entity entity, final Entity avoided) {
        super(entity);
        this.avoided = avoided;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Get the entity that is being avoided.
     *
     * @return The entity
     */
    public Entity getAvoided() {
        return avoided;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
