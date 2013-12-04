package org.bukkit.material;

import org.bukkit.CropState;
import org.bukkit.Material;

/**
 * Represents the different types of crops.
 */
public class Crops extends MaterialData {
    public Crops() {
        super(Material.CROPS);
    }

    public Crops(CropState state) {
        this();
        setState(state);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Crops(final int type) {
        super(type);
    }

    public Crops(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Crops(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Crops(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current growth state of this crop
     *
     * @return CropState of this crop
     */
    public CropState getState() {
        switch (getData()) {
            case 0x0:
                return CropState.SEEDED;
            case 0x1:
                return CropState.GERMINATED;
            case 0x2:
                return CropState.VERY_SMALL;
            case 0x3:
                return CropState.SMALL;
            case 0x4:
                return CropState.MEDIUM;
            case 0x5:
                return CropState.TALL;
            case 0x6:
                return CropState.VERY_TALL;
            case 0x7:
                return CropState.RIPE;
            default:
                return null;
        }
    }

    /**
     * Sets the growth state of this crop
     *
     * @param state New growth state of this crop
     */
    public void setState(CropState state) {
        switch (state) {
            case SEEDED:
                setData((byte) 0x0);
                return;
            case GERMINATED:
                setData((byte) 0x1);
                return;
            case VERY_SMALL:
                setData((byte) 0x2);
                return;
            case SMALL:
                setData((byte) 0x3);
                return;
            case MEDIUM:
                setData((byte) 0x4);
                return;
            case TALL:
                setData((byte) 0x5);
                return;
            case VERY_TALL:
                setData((byte) 0x6);
                return;
            case RIPE:
                setData((byte) 0x7);
                return;
        }
    }

    @Override
    public String toString() {
        return getState() + " " + super.toString();
    }

    @Override
    public Crops clone() {
        return (Crops) super.clone();
    }
}
