package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an item is about to merge with another. Either item could cease to exist after event processing.
 */
public class ItemMergeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Item merger;
    private boolean cancelled;

    public ItemMergeEvent(final Item mergee, final Item merger) {
        super(mergee);
        this.merger = merger;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public Item getEntity() {
        return (Item) entity;
    }

    /**
     * Returns the item that {@link #getEntity()} found close enough to merge with.
     *
     * @return Item found close enough to merge with
     */
    public Item getMerger() {
        return merger;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
