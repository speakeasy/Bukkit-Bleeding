package org.bukkit.block;

import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 * This implementation of {@link BlockView} is immutable.
 * The material, light, data, and location will not change.
 */
public final class SimpleBlockView implements BlockView {
    private final World world;
    private final int x;
    private final int y;
    private final int z;
    private final int material;
    private final byte data;
    private final byte light;

    public SimpleBlockView(final Block block) {
        Validate.notNull(block);
        world = block.getWorld();
        x = block.getX();
        y = block.getY();
        z = block.getZ();
        material = block.getTypeId();
        data = block.getData();
        light = block.getLightLevel();
    }

    public SimpleBlockView(final Block block, final int id, final byte blockData) {
        Validate.notNull(block);
        Validate.notNull(Material.getMaterial(id), "Must be valid material");
        world = block.getWorld();
        x = block.getX();
        y = block.getY();
        z = block.getZ();
        material = id;
        data = blockData;
        light = block.getLightLevel();
    }

    public SimpleBlockView(final BlockState state) {
        Validate.notNull(state);
        world = state.getWorld();
        x = state.getX();
        y = state.getY();
        z = state.getZ();
        material = state.getTypeId();
        data = state.getRawData();
        light = state.getLightLevel();
    }

    public SimpleBlockView(
            final World world,
            final int x, final int y, final int z,
            final Material material,
            final byte data, final byte light) {
        Validate.notNull(world, "World cannot be null");
        Validate.notNull(material, "Material cannot be null");
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = material.getId();
        this.data = data;
        this.light = light;
    }

    public SimpleBlockView(
            final World world,
            final int x, final int y, final int z,
            final int id,
            final byte data, final byte light) {
        Validate.notNull(world);
        Validate.notNull(Material.getMaterial(id), "Must be valid material");
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = id;
        this.data = data;
        this.light = light;
    }

    public Block getBlock() {
        return world.getBlockAt(x, y, z);
    }

    public Material getType() {
        return Material.getMaterial(material);
    }

    public int getTypeId() {
        return material;
    }

    public byte getLightLevel() {
        return light;
    }

    public World getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Location getLocation() {
        return new Location(world, x, y, z);
    }

    public Chunk getChunk() {
        return world.getChunkAt(x, z);
    }

    public byte getRawData() {
        return data;
    }
}
