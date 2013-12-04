package org.bukkit.material;

import org.bukkit.Material;

public class Jukebox extends MaterialData implements Redstone {
    public Jukebox() {
        super(Material.JUKEBOX);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Jukebox(final int type) {
        super(type);
    }

    public Jukebox(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Jukebox(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Jukebox(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Get the disc in the jukebox
     *
     * @return the Material for the disc currently in the jukebox or null if empty
     */
    public Material getDisc() {
        switch (getData()) {
            case 1:
                return Material.GOLD_RECORD;
            case 2:
                return Material.GREEN_RECORD;
            case 3:
                return Material.RECORD_3;
            case 4:
                return Material.RECORD_4;
            case 5:
                return Material.RECORD_5;
            case 6:
                return Material.RECORD_6;
            case 7:
                return Material.RECORD_7;
            case 8:
                return Material.RECORD_8;
            case 9:
                return Material.RECORD_9;
            case 10:
                return Material.RECORD_10;
            case 11:
                return Material.RECORD_11;
            case 12:
                return Material.RECORD_12;
            default:
                return null;
        }
    }

    /**
     * Set the contents of the jukebox
     *
     * @param material Material of the disc to put in the jukebox.
     */
    public void setDisc(Material material) {
        switch (material) {
            case GOLD_RECORD:
                setData((byte) 0x1);
                return;
            case GREEN_RECORD:
                setData((byte) 0x2);
                return;
            case RECORD_3:
                setData((byte) 0x3);
                return;
            case RECORD_4:
                setData((byte) 0x4);
                return;
            case RECORD_5:
                setData((byte) 0x5);
                return;
            case RECORD_6:
                setData((byte) 0x6);
                return;
            case RECORD_7:
                setData((byte) 0x7);
                return;
            case RECORD_8:
                setData((byte) 0x8);
                return;
            case RECORD_9:
                setData((byte) 0x9);
                return;
            case RECORD_10:
                setData((byte) 0xA);
                return;
            case RECORD_11:
                setData((byte) 0xB);
                return;
            case RECORD_12:
                setData((byte) 0xC);
                return;
        }
    }

    /**
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return true if powered, otherwise false
     */
    public boolean isPowered() {
        return getData() > 0x0;
    }

    @Override
    public String toString() {
        return super.toString() + " with disc " + getDisc();
    }

    @Override
    public Jukebox clone() {
        return (Jukebox) super.clone();
    }

}
