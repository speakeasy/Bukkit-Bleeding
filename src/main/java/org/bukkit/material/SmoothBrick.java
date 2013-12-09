package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.SmoothBrickType;

/**
 * Represents the different types of smooth bricks.
 */
public class SmoothBrick extends TexturedMaterial {

    private static final List<Material> textures = new ArrayList<Material>();
    static {
        textures.add(Material.STONE);
        textures.add(Material.MOSSY_COBBLESTONE);
        textures.add(Material.COBBLESTONE);
        textures.add(Material.SMOOTH_BRICK);
    }

    public SmoothBrick() {
        super(Material.SMOOTH_BRICK);
    }

    public SmoothBrick(SmoothBrickType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SmoothBrick(final int type) {
        super(type);
    }

    public SmoothBrick(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SmoothBrick(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SmoothBrick(final Material type, final byte data) {
        super(type, data);
    }

    /**
    *
    * @deprecated Use {@link #getType()} instead.
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
    * @deprecated Use {@link #setType(SmoothBrickType)} instead.
    */
   @Deprecated
   public void setMaterial(Material material) {
       if (getTextures().contains(material)) {
           setTextureIndex(getTextures().indexOf(material));
       } else {
           setTextureIndex(0x0);
       }
   }

    @Override
    @Deprecated
    public List<Material> getTextures() {
        return textures;
    }

    public SmoothBrickType getType() {
        switch (getData()) {
            case 0x0:
                return SmoothBrickType.SMOOTH;
            case 0x1:
                return SmoothBrickType.MOSSY;
            case 0x2:
                return SmoothBrickType.CRACKED;
            case 0x3:
                return SmoothBrickType.CHISELED;
            default:
                return null;
        }
    }

    public void setType(SmoothBrickType type) {
        switch (type) {
            case SMOOTH:
                setData((byte) 0x0);
                return;
            case MOSSY:
                setData((byte) 0x1);
                return;
            case CRACKED:
                setData((byte) 0x2);
                return;
            case CHISELED:
                setData((byte) 0x3);
                return;
        }
    }

    @Override
    public SmoothBrick clone() {
        return (SmoothBrick) super.clone();
    }
}
