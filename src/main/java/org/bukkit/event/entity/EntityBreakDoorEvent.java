package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an {@link Entity} breaks a door
 * <p />
 * Canceling the event will cause the event to be delayed
 */
public class EntityBreakDoorEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Block target;
    private boolean cancelled = false;

    public EntityBreakDoorEvent(final Entity entity, final Block targetBlock) {
        super(entity);
        target = targetBlock;
    }

    /**
     * Get the {@link Block} the {@link Entity} is targeting
     *
     * @return the block
     */
    public Block getTargetBlock() {
        return target;
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
