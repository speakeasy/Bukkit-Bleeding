package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Rotateable extends MaterialData implements Directional {
    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Rotateable(final int type) {
        super(type);
    }

    public Rotateable(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Rotateable(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Rotateable(final Material type, final byte data) {
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
}
