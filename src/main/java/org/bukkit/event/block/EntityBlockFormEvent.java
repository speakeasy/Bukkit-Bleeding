package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

/**
 * Called when a block is formed by entities.
 * <p />
 * Examples:
 * <ul>
 * <li>Snow formed by a {@link org.bukkit.entity.Snowman}.</li>
 * </ul>
 */
public class EntityBlockFormEvent extends BlockFormEvent implements Event.EntityEvent {
    private final Entity entity;

    public EntityBlockFormEvent(final Entity entity, final Block block, final BlockState blockstate) {
        super(block, blockstate);

        this.entity = entity;
    }

    /**
     * Get the entity that formed the block.
     *
     * @return Entity involved in event
     */
    public Entity getEntity() {
        return entity;
    }

    public EntityType getEntityType() {
        return getEntity().getType();
    }
}