package org.bukkit.material;

import org.bukkit.CauldronState;
import org.bukkit.Material;

/**
 * Represents a cauldron
 */
public class Cauldron extends MaterialData {
    private static final int CAULDRON_FULL = 3;
    private static final int CAULDRON_EMPTY = 0;

    public Cauldron() {
        super(Material.CAULDRON);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Cauldron(int type, byte data){
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Cauldron(byte data) {
        super(Material.CAULDRON, data);
    }

    /**
     * Check if the cauldron is full.
     *
     * @return True if it is full.
     */
    public boolean isFull() {
        return getData() >= CAULDRON_FULL;
    }

    /**
     * Check if the cauldron is empty.
     *
     * @return True if it is empty.
     */
    public boolean isEmpty() {
        return getData() <= CAULDRON_EMPTY;
    }

    public CauldronState getState() {
        switch (getData()) {
            case 0x0:
                return CauldronState.EMPTY;
            case 0x1:
                return CauldronState.ONE_THIRD_FULL;
            case 0x2:
                return CauldronState.TWO_THIRDS_FULL;
            case 0x3:
                return CauldronState.FULL;
            default:
                return null;
        }
    }

    public void setState(CauldronState state) {
        switch (state) {
            case EMPTY:
                setData((byte) 0x0);
                return;
            case ONE_THIRD_FULL:
                setData((byte) 0x1);
                return;
            case TWO_THIRDS_FULL:
                setData((byte) 0x2);
                return;
            case FULL:
                setData((byte) 0x3);
                return;
        }
    }

    @Override
    public String toString() {
        return (isEmpty() ? "EMPTY" : (isFull() ? "FULL" : getData() + "/3 FULL")) + " CAULDRON";
    }

    @Override
    public Cauldron clone() {
        return (Cauldron) super.clone();
    }
}
