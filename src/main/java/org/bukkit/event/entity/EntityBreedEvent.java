package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when two parent entities breed to create a child entity.
 * getEntity returns the child, while getParents returns the parents
 *
 */
public class EntityBreedEvent extends EntityEvent implements Cancellable {
    private final static HandlerList handlers = new HandlerList();
    private final Entity[] parents;
    private boolean cancel;
    
    public EntityBreedEvent(Entity entity, Entity[] parents) {
        super(entity);
        this.parents = parents;
    }
    
    /**
     * Gets the parent entities of the child.  Will always be two entities
     * @return the parent entities
     */
    public Entity[] getParents() {
        return parents;
    }
    
    public boolean isCancelled() {
        return cancel;
    }
    
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
