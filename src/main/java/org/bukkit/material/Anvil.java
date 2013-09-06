package org.bukkit.material;

import org.bukkit.AnvilDamageState;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Anvil extends MaterialData implements Directional {
    private static final byte DIRECTION_BIT = 0x1;
    private static final byte DAMAGED_BIT = 0x4;
    private static final byte VERY_DAMAGED_BIT = 0x8;

    public Anvil() {
        super(Material.ANVIL);
    }

    public Anvil(AnvilDamageState state) {
        this();
        setState(state);
    }

    public Anvil(AnvilDamageState state, BlockFace direction) {
        this();
        setState(state);
        setFacingDirection(direction);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Anvil(final int type) {
        super(type);
    }

    public Anvil(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Anvil(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Anvil(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        byte data = getData();

        switch (face) {
            case WEST:
            case EAST:
                data |= DIRECTION_BIT;
                break;

            case NORTH:
            case SOUTH:
            default:
                data &= ~DIRECTION_BIT;
        }

        setData(data);
    }

    @Override
    public BlockFace getFacing() {
        switch (getData() & DIRECTION_BIT) {
            case 0x0:
            default:
                return BlockFace.WEST;
            case 0x1:
                return BlockFace.NORTH;
        }
    }

    public AnvilDamageState getState() {
        byte data = getData();

        if ((data & DAMAGED_BIT) == 1) {
            return AnvilDamageState.SLIGHTLY_DAMAGED;
        }
        else if ((data & VERY_DAMAGED_BIT) == 1) {
            return AnvilDamageState.VERY_DAMAGED;
        }
        else {
            return AnvilDamageState.NOT_DAMAGED;
        }
    }

    public void setState(AnvilDamageState state) {
        byte data = getData();

        switch (state) {
            case NOT_DAMAGED:
                data &= ~DAMAGED_BIT;
                data &= ~VERY_DAMAGED_BIT;
                break;

            case SLIGHTLY_DAMAGED:
                data |= DAMAGED_BIT;
                data &= ~VERY_DAMAGED_BIT;
                break;

            case VERY_DAMAGED:
                data &= ~DAMAGED_BIT;
                data |= VERY_DAMAGED_BIT;
                break;
        }
    }

    @Override
    public String toString() {
        return getState() + " " + super.toString();
    }

    @Override
    public Anvil clone() {
        return (Anvil) super.clone();
    }
}
