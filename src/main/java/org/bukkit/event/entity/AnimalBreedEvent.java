package org.bukkit.event.entity;

import org.bukkit.entity.Animals;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class AnimalBreedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Animals parent;
    private final Animals child;
    private boolean cancelled;

    public AnimalBreedEvent(Animals parent, Animals parent2, Animals child) {
        super(parent);
        this.parent = parent2;
        this.child = child;
    }

    @Override
    public Animals getEntity() {
        return (Animals) entity;
    }

    /**
     * Return the other parent of the child
     *
     * @return Animal parent
     */
    public Animals getOtherParent() {
        return parent;
    }

    /**
     * Return the Animal spawned by this event
     *
     * @return Animal child
     */
    public Animals getChild() {
        return child;
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
