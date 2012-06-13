package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockView;
import org.bukkit.entity.Entity;

/**
 * Called when a block is formed by entities.
 * <p />
 * Examples:
 * <ul>
 * <li>Snow formed by a {@link org.bukkit.entity.Snowman}.</li>
 * </ul>
 */
public class EntityBlockFormEvent extends BlockFormEvent {
    private final Entity entity;

    /**
     * @deprecated This constructor is provided for compatibility. It will
     *          create an immutable version from the provided block state.
     *          For mutability, the BlockState should be downcast.
     */
    @Deprecated
    public EntityBlockFormEvent(final Entity entity, final Block block, final BlockState newState) {
        super(block, newState);
        this.entity = entity;
    }

    public EntityBlockFormEvent(final Entity entity, final Block block, final BlockView newState) {
        super(block, newState);
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
}