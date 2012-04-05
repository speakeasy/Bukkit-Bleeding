package org.bukkit.event.entity;

import org.bukkit.entity.Animals;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class AnimalBreedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Animals[] parents;
    private boolean cancelled;

    public AnimalBreedEvent(Animals parent, Animals parent2, Animals child) {
        super(child);
        this.parents = new Animals[] { parent, parent2 };
    }

    /**
     * Gets the child entity
     * 
     * @return child entity
     */
    @Override
    public Animals getEntity() {
        return (Animals) entity;
    }
    
    /**
     * Gets the parent entities.  The array will always have a length of two
     * 
     * @return parent entities
     */
    public Animals[] getParents() {
        return parents;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
