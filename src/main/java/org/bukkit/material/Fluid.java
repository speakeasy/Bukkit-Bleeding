package org.bukkit.material;

import org.bukkit.FluidLevel;
import org.bukkit.Material;

public class Fluid extends MaterialData {

    public Fluid() {
        super(Material.WATER);
    }

    public Fluid(FluidLevel level) {
        this();
        setLevel(level);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Fluid(final int type) {
        super(type);
    }

    public Fluid(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Fluid(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Fluid(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current level of this fluid
     *
     * @return FluidLevel of this fluid
     */
    public FluidLevel getLevel() {
        switch (getData()) {
            case 0x0:
                return FluidLevel.HIGHEST;
            case 0x1:
                return FluidLevel.HIGHER;
            case 0x2:
                return FluidLevel.HIGH;
            case 0x3:
                return FluidLevel.MEDIUM_HIGH;
            case 0x4:
                return FluidLevel.MEDIUM_LOW;
            case 0x5:
                return FluidLevel.LOW;
            case 0x6:
                return FluidLevel.LOWER;
            case 0x7:
                return FluidLevel.LOWEST;
            default:
                return null;
        }
    }

    /**
     * Sets the level of this fluid
     *
     * @param level New level of this fluid
     */
    public void setLevel(FluidLevel level) {
        switch (level) {
            case HIGHEST:
                setData((byte) 0x0);
                return;
            case HIGHER:
                setData((byte) 0x1);
                return;
            case HIGH:
                setData((byte) 0x2);
                return;
            case MEDIUM_HIGH:
                setData((byte) 0x3);
                return;
            case MEDIUM_LOW:
                setData((byte) 0x4);
                return;
            case LOW:
                setData((byte) 0x5);
                return;
            case LOWER:
                setData((byte) 0x6);
                return;
            case LOWEST:
                setData((byte) 0x7);
                return;
        }
    }

    /**
     * Set if this liquid is falling (spreading downward).
     *
     * @param isFalling true if the fluid is falling, false otherwise
     */
    public void setFalling(boolean isFalling) {
        int dat = getData() & 0x7;
        if (isFalling) {
            dat |= 0x8;
        }
        setData((byte) dat);
    }

    /**
     * Test if fluid is falling (spreading downward).
     *
     * @return true if fluid is falling, false otherwise
     */
    public boolean isFalling() {
        return ((getData() & 0x8) != 0);
    }

    @Override
    public String toString() {
        return getLevel() + " " + super.toString();
    }

    @Override
    public Fluid clone() {
        return (Fluid) super.clone();
    }

}
