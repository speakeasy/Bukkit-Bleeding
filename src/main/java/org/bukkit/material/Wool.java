package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;

/**
 * Represents a Wool/Cloth block
 */
public class Wool extends MaterialData implements Colorable {
    public Wool() {
        super(Material.WOOL);
    }

    public Wool(DyeColor color) {
        this();
        setColor(color);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wool(final int type) {
        super(type);
    }

    public Wool(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wool(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wool(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current color of this wool
     *
     * @return DyeColor of this wool
     */
    public DyeColor getColor() {
        switch (getData()) {
            case 0x0:
                return DyeColor.WHITE;
            case 0x1:
                return DyeColor.ORANGE;
            case 0x2:
                return DyeColor.MAGENTA;
            case 0x3:
                return DyeColor.LIGHT_BLUE;
            case 0x4:
                return DyeColor.YELLOW;
            case 0x5:
                return DyeColor.LIME;
            case 0x6:
                return DyeColor.PINK;
            case 0x7:
                return DyeColor.GRAY;
            case 0x8:
                return DyeColor.SILVER;
            case 0x9:
                return DyeColor.CYAN;
            case 0xA:
                return DyeColor.PURPLE;
            case 0xB:
                return DyeColor.BLUE;
            case 0xC:
                return DyeColor.BROWN;
            case 0xD:
                return DyeColor.GREEN;
            case 0xE:
                return DyeColor.RED;
            case 0xF:
                return DyeColor.BLACK;
            default:
                return null;
        }
    }

    /**
     * Sets the color of this wool
     *
     * @param color New color of this wool
     */
    public void setColor(DyeColor color) {
        switch (color) {
            case WHITE:
                setData((byte) 0x0);
                return;
            case ORANGE:
                setData((byte) 0x1);
                return;
            case MAGENTA:
                setData((byte) 0x2);
                return;
            case LIGHT_BLUE:
                setData((byte) 0x3);
                return;
            case YELLOW:
                setData((byte) 0x4);
                return;
            case LIME:
                setData((byte) 0x5);
                return;
            case PINK:
                setData((byte) 0x6);
                return;
            case GRAY:
                setData((byte) 0x7);
                return;
            case SILVER:
                setData((byte) 0x8);
                return;
            case CYAN:
                setData((byte) 0x9);
                return;
            case PURPLE:
                setData((byte) 0xA);
                return;
            case BLUE:
                setData((byte) 0xB);
                return;
            case BROWN:
                setData((byte) 0xC);
                return;
            case GREEN:
                setData((byte) 0xD);
                return;
            case RED:
                setData((byte) 0xE);
                return;
            case BLACK:
                setData((byte) 0xF);
                return;
        }
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }

    @Override
    public Wool clone() {
        return (Wool) super.clone();
    }
}
