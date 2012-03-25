package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.bukkit.Material;

/**
 * Represents a button
 */
public class Button extends SimpleAttachableMaterialData implements Redstone {
    private static final byte WEST_BIT = 0x4;
    private static final byte EAST_BIT = 0x3;
    private static final byte SOUTH_BIT = 0x2;
    private static final byte NORTH_BIT = 0x1;
    private static final byte DIR_MASK = 0x7;
    private static final byte POWER_BIT = 0x8;

    public Button() {
        super(Material.STONE_BUTTON);
    }

    public Button(final int type) {
        super(type);
    }

    public Button(final Material type) {
        super(type);
    }

    public Button(final int type, final byte data) {
        super(type, data);
    }

    public Button(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return true if powered, otherwise false
     */
    public boolean isPowered() {
        return (getData() & POWER_BIT) == POWER_BIT;
    }

    /**
     * Sets the current state of this button
     *
     * @param bool
     *            whether or not the button is powered
     */
    public void setPowered(boolean bool) {
        setData((byte) (bool ? (getData() | POWER_BIT) : (getData() & ~POWER_BIT)));
    }

    /**
     * Gets the face that this block is attached on
     *
     * @return BlockFace attached to
     */
    public BlockFace getAttachedFace() {
        byte data = (byte) (getData() & DIR_MASK);

        switch (data) {
        case NORTH_BIT:
            return BlockFace.NORTH;

        case SOUTH_BIT:
            return BlockFace.SOUTH;

        case EAST_BIT:
            return BlockFace.EAST;

        case WEST_BIT:
            return BlockFace.WEST;
        }

        return null;
    }

    /**
     * Sets the direction this button is pointing toward
     */
    public void setFacingDirection(BlockFace face) {
        byte data = (byte) (getData() & POWER_BIT);

        switch (face) {
        case SOUTH:
        default:
            data |= NORTH_BIT;
            break;

        case NORTH:
            data |= SOUTH_BIT;
            break;

        case WEST:
            data |= EAST_BIT;
            break;

        case EAST:
            data |= WEST_BIT;
            break;
        }

        setData(data);
    }

    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public Button clone() {
        return (Button) super.clone();
    }
}
