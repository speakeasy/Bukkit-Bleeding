package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.MonsterEggType;

/**
 * Represents the different types of monster eggs
 */
public class MonsterEggs extends TexturedMaterial {

    private static final List<Material> textures = new ArrayList<Material>();
    static {
        textures.add(Material.STONE);
        textures.add(Material.COBBLESTONE);
        textures.add(Material.SMOOTH_BRICK);
    }

    public MonsterEggs() {
        super(Material.MONSTER_EGGS);
    }

    public MonsterEggs(MonsterEggType type) {
        this();
        setAppearance(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public MonsterEggs(final int type) {
        super(type);
    }

    public MonsterEggs(final Material type) {
        super((textures.contains(type)) ? Material.MONSTER_EGGS : type);
        if (textures.contains(type)) {
            setMaterial(type);
        }
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public MonsterEggs(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public MonsterEggs(final Material type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Does not work for all types
     */
    @Override
    @Deprecated
    public List<Material> getTextures() {
        return textures;
    }

    /**
     *
     * @deprecated Does not work for all types. Use {@link #getAppearance()} instead.
     */
    @Deprecated
    public Material getMaterial() {
        int n = getTextureIndex();
        if (n > getTextures().size() - 1) {
            n = 0;
        }

        return getTextures().get(n);
    }

    /**
     * Sets the material this block is made of
     *
     * @param material
     *            New material of this block
     *
     * @deprecated Does not work for all types. Use {@link #setAppearance(MonsterEggType)} instead.
     */
    @Deprecated
    public void setMaterial(Material material) {
        if (getTextures().contains(material)) {
            setTextureIndex(getTextures().indexOf(material));
        } else {
            setTextureIndex(0x0);
        }
    }

    public MonsterEggType getAppearance() {
        switch (getData()) {
            case 0x0:
                return MonsterEggType.STONE;
            case 0x1:
                return MonsterEggType.COBBLESTONE;
            case 0x2:
                return MonsterEggType.STONE_BRICK;
            case 0x3:
                return MonsterEggType.MOSSY_STONE_BRICK;
            case 0x4:
                return MonsterEggType.CRACKED_STONE_BRICK;
            case 0x5:
                return MonsterEggType.CHISELED_STONE_BRICK;
            default:
                return null;
        }
    }

    public void setAppearance(MonsterEggType type) {
        switch (type) {
            case STONE:
                setData((byte) 0x0);
                return;
            case COBBLESTONE:
                setData((byte) 0x1);
                return;
            case STONE_BRICK:
                setData((byte) 0x2);
                return;
            case MOSSY_STONE_BRICK:
                setData((byte) 0x3);
                return;
            case CRACKED_STONE_BRICK:
                setData((byte) 0x4);
                return;
            case CHISELED_STONE_BRICK:
                setData((byte) 0x5);
                return;
        }
    }

    @Override
    public MonsterEggs clone() {
        return (MonsterEggs) super.clone();
    }
}
