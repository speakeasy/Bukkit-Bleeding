package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Diode extends MaterialData implements Directional {
    private static final byte NORTH_BIT = 0x3;
    private static final byte WEST_BIT = 0x2;
    private static final byte SOUTH_BIT = 0x1;
    private static final byte EAST_BIT = 0x0;
    private static final byte DELAY_SHIFT = 2;
    private static final byte DIR_MASK = 0x3;
    private static final byte MIN_DELAY = 1;
    private static final byte MAX_DELAY = 4;

    public Diode() {
        super(Material.DIODE_BLOCK_ON);
    }

    public Diode(int type) {
        super(type);
    }

    public Diode(Material type) {
        super(type);
    }

    public Diode(int type, byte data) {
        super(type, data);
    }

    public Diode(Material type, byte data) {
        super(type, data);
    }

    /**
     * Sets the delay of the repeater
     *
     * @param delay
     *            The new delay (1-4)
     */
    public void setDelay(int delay) {
        if (delay > MAX_DELAY) {
            delay = MAX_DELAY;
        }
        if (delay < MIN_DELAY) {
            delay = MIN_DELAY;
        }
        byte newData = (byte) (getData() & DIR_MASK);

        setData((byte) (newData | ((delay - MIN_DELAY) << DELAY_SHIFT)));
    }

    /**
     * Gets the delay of the repeater in ticks
     *
     * @return The delay (1-4)
     */
    public int getDelay() {
        return (getData() >> DELAY_SHIFT) + MIN_DELAY;
    }

    public void setFacingDirection(BlockFace face) {
        int delay = getDelay();
        byte data;

        switch (face) {
        case SOUTH:
            data = SOUTH_BIT;
            break;

        case WEST:
            data = WEST_BIT;
            break;

        case NORTH:
            data = NORTH_BIT;
            break;

        case EAST:
        default:
            data = EAST_BIT;
        }

        setData(data);
        setDelay(delay);
    }

    public BlockFace getFacing() {
        byte data = (byte) (getData() & DIR_MASK);

        switch (data) {
        case EAST_BIT:
        default:
            return BlockFace.EAST;

        case SOUTH_BIT:
            return BlockFace.SOUTH;

        case WEST_BIT:
            return BlockFace.WEST;

        case NORTH_BIT:
            return BlockFace.NORTH;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing() + " with " + getDelay() + " ticks delay";
    }

    @Override
    public Diode clone() {
        return (Diode) super.clone();
    }
}
