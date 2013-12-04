package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;

/**
 * Represents dye
 */
public class Dye extends MaterialData implements Colorable {
    public Dye() {
        super(Material.INK_SACK);
    }

    public Dye(DyeColor color) {
        this();
        setColor(color);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Dye(final int type) {
        super(type);
    }

    public Dye(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Dye(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Dye(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current color of this dye
     *
     * @return DyeColor of this dye
     */
    public DyeColor getColor() {
        switch (getData()) {
            case 0x0:
                return DyeColor.BLACK;
            case 0x1:
                return DyeColor.RED;
            case 0x2:
                return DyeColor.GREEN;
            case 0x3:
                return DyeColor.BROWN;
            case 0x4:
                return DyeColor.BLUE;
            case 0x5:
                return DyeColor.PURPLE;
            case 0x6:
                return DyeColor.CYAN;
            case 0x7:
                return DyeColor.SILVER;
            case 0x8:
                return DyeColor.GRAY;
            case 0x9:
                return DyeColor.PINK;
            case 0xA:
                return DyeColor.LIME;
            case 0xB:
                return DyeColor.YELLOW;
            case 0xC:
                return DyeColor.LIGHT_BLUE;
            case 0xD:
                return DyeColor.MAGENTA;
            case 0xE:
                return DyeColor.ORANGE;
            case 0xF:
                return DyeColor.WHITE;
            default:
                return null;
        }
    }

    /**
     * Sets the color of this dye
     *
     * @param color New color of this dye
     */
    public void setColor(DyeColor color) {
        switch (color) {
            case BLACK:
                setData((byte) 0x0);
                return;
            case RED:
                setData((byte) 0x1);
                return;
            case GREEN:
                setData((byte) 0x2);
                return;
            case BROWN:
                setData((byte) 0x3);
                return;
            case BLUE:
                setData((byte) 0x4);
                return;
            case PURPLE:
                setData((byte) 0x5);
                return;
            case CYAN:
                setData((byte) 0x6);
                return;
            case SILVER:
                setData((byte) 0x7);
                return;
            case GRAY:
                setData((byte) 0x8);
                return;
            case PINK:
                setData((byte) 0x9);
                return;
            case LIME:
                setData((byte) 0xA);
                return;
            case YELLOW:
                setData((byte) 0xB);
                return;
            case LIGHT_BLUE:
                setData((byte) 0xC);
                return;
            case MAGENTA:
                setData((byte) 0xD);
                return;
            case ORANGE:
                setData((byte) 0xE);
                return;
            case WHITE:
                setData((byte) 0xF);
                return;
        }
    }

    @Override
    public String toString() {
        return getColor() + " DYE(" + getData() + ")";
    }

    @Override
    public Dye clone() {
        return (Dye) super.clone();
    }
}
