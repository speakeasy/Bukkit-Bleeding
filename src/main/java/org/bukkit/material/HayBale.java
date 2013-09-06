package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class HayBale extends MaterialData implements Directional {
    public HayBale() {
        super(Material.HAY_BLOCK);
    }

    public HayBale(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public HayBale(final int type) {
        super(type);
    }

    public HayBale(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public HayBale(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public HayBale(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        int dat;
        switch (face) {
            case UP:
            case DOWN:
            default:
                dat = 0;
                break;
            case WEST:
            case EAST:
                dat = 1;
                break;
            case NORTH:
            case SOUTH:
                dat = 2;
                break;
            case SELF:
                dat = 3;
                break;
        }
        setData((byte) ((getData() & 0x3) | (dat << 2)));
    }

    /**
     * Get direction of the hay
     *
     * @return BlockFace.TOP for upright (default), BlockFace.NORTH (east-west), BlockFace.WEST (north-sout), BlockFace.SELF (directionless)
     */
    @Override
    public BlockFace getFacing() {
        switch ((getData() >> 2) & 0x3) {
            case 0: // Up-down
            default:
                return BlockFace.UP;
            case 1: // North-south
                return BlockFace.WEST;
            case 2: // East-west
                return BlockFace.NORTH;
            case 3: // Directionless (bark on all sides)
                return BlockFace.SELF;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing();
    }

    @Override
    public HayBale clone() {
        return (HayBale) super.clone();
    }
}
