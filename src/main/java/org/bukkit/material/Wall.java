package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

/**
 * Represents the different types of walls.
 */
public class Wall extends TexturedMaterial {
    private static final List<Material> textures = new ArrayList<Material>();
    static {
        textures.add(Material.COBBLESTONE);
        textures.add(Material.MOSSY_COBBLESTONE);
    }

    public Wall() {
        super(Material.COBBLE_WALL);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final int type) {
        super(type);
    }

    public Wall(final Material type) {
        super((textures.contains(type)) ? Material.COBBLE_WALL : type);
        if (textures.contains(type)) {
            setMaterial(type);
        }
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return textures;
    }

    @Override
    public Wall clone() {
        return (Wall) super.clone();
    }

}
