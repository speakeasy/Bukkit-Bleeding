package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a bed.
 */
public class Bed extends MaterialData implements Directional {
    private static final byte DIR_MASK = 0x7;
    private static final byte SOUTH_BIT = 0x3;
    private static final byte EAST_BIT = 0x2;
    private static final byte NORTH_BIT = 0x1;
    private static final byte WEST_BIT = 0x0;
    private static final byte HEAD_BIT = 0x8;

    /**
     * Default constructor for a bed.
     */
    public Bed() {
        super(Material.BED_BLOCK);
    }

    /**
     * Instantiate a bed facing in a particular direction.
     *
     * @param direction the direction the bed's head is facing
     */
    public Bed(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    public Bed(final int type) {
        super(type);
    }

    public Bed(final Material type) {
        super(type);
    }

    public Bed(final int type, final byte data) {
        super(type, data);
    }

    public Bed(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Determine if this block represents the head of the bed
     *
     * @return true if this is the head of the bed, false if it is the foot
     */
    public boolean isHeadOfBed() {
        return (getData() & HEAD_BIT) == HEAD_BIT;
    }

    /**
     * Configure this to be either the head or the foot of the bed
     *
     * @param isHeadOfBed True to make it the head.
     */
    public void setHeadOfBed(boolean isHeadOfBed) {
        setData((byte) (isHeadOfBed ? (getData() | HEAD_BIT) : (getData() & ~HEAD_BIT)));
    }

    /**
     * Set which direction the head of the bed is facing. Note that this will
     * only affect one of the two blocks the bed is made of.
     */
    public void setFacingDirection(BlockFace face) {
        byte data;

        switch (face) {
        case WEST:
            data = WEST_BIT;
            break;

        case NORTH:
            data = NORTH_BIT;
            break;

        case EAST:
            data = EAST_BIT;
            break;

        case SOUTH:
        default:
            data = SOUTH_BIT;
        }

        if (isHeadOfBed()) {
            data |= HEAD_BIT;
        }

        setData(data);
    }

    /**
     * Get the direction that this bed's head is facing toward
     *
     * @return the direction the head of the bed is facing
     */
    public BlockFace getFacing() {
        byte data = (byte) (getData() & DIR_MASK);

        switch (data) {
        case WEST_BIT:
            return BlockFace.WEST;

        case NORTH_BIT:
            return BlockFace.NORTH;

        case EAST_BIT:
            return BlockFace.EAST;

        case SOUTH_BIT:
        default:
            return BlockFace.SOUTH;
        }
    }

    @Override
    public String toString() {
        return (isHeadOfBed() ? "HEAD" : "FOOT") + " of " + super.toString() + " facing " + getFacing();
    }

    @Override
    public Bed clone() {
        return (Bed) super.clone();
    }
}
