package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.QuartzType;
import org.bukkit.block.BlockFace;

public class QuartzBlock extends MaterialData implements Directional {
    public QuartzBlock() {
        super(Material.QUARTZ_BLOCK);
    }

    public QuartzBlock(QuartzType type) {
        this();
        setType(type);
    }

    public QuartzBlock(QuartzType type, BlockFace dir) {
        this();
        setType(type);
        setFacingDirection(dir);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public QuartzBlock(final int type) {
        super(type);
    }

    public QuartzBlock(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public QuartzBlock(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public QuartzBlock(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Get the type of this quartz
     *
     * @return QuartzType of this quartz
     */
    public QuartzType getType() {
        switch (getData()) {
            case 0:
                return QuartzType.SMOOTH;
            case 1:
                return QuartzType.CHISELED;
            default:
                return QuartzType.PILLAR;
        }
    }

    /**
     * Set the type of this quartz
     *
     * @param type New type of this quartz
     */
    public void setType(QuartzType type) {
        switch (type) {
            case SMOOTH:
                setData((byte) 0x0);
                return;
            case CHISELED:
                setData((byte) 0x1);
                return;
            case PILLAR:
                setData((byte) 0x2);
                return;
        }
    }

    /**
     * Get direction of the quartz
     *
     * @return BlockFace.TOP for upright (default), BlockFace.NORTH (east-west), BlockFace.WEST (north-south), BlockFace.SELF (directionless)
     */
    @Override
    public BlockFace getFacing() {
        switch (getData()) {
            case 2:
                return BlockFace.UP;
            case 3:
                return BlockFace.WEST;
            case 4:
                return BlockFace.NORTH;
            default:
                return BlockFace.SELF;
        }
    }

    /**
     * Set the direction of this quartz
     *
     * @param dir direction end of quartz (BlockFace.SELF for no direction)
     */
    @Override
    public void setFacingDirection(BlockFace dir) {
        switch (dir) {
            case UP:
            case DOWN:
                setData((byte) 0x2);
                return;
            case WEST:
            case EAST:
                setData((byte) 0x3);
                return;
            case NORTH:
            case SOUTH:
                setData((byte) 0x4);
                return;
            default:
                setData((byte) 0x0);
                return;
        }
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public QuartzBlock clone() {
        return (QuartzBlock) super.clone();
    }
}
