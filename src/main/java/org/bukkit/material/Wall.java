package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.QuartzType;
import org.bukkit.WallType;
import org.bukkit.block.BlockFace;

/**
 * Represents the different types of walls.
 */
public class Wall extends MaterialData {
    public Wall() {
        super(Material.COBBLE_WALL);
    }

    public Wall(WallType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final int type) {
        super(type);
    }

    public Wall(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Wall(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Get the type of this wall
     *
     * @return WallType of this wall
     */
    public WallType getType() {
        switch (getData()) {
            case 0x0:
                return WallType.COBBLESTONE;
            case 0x1:
                return WallType.MOSSY_COBBLESTONE;
            default:
                return null;
        }
    }

    /**
     * Set the type of this wall
     *
     * @param type New type of this wall
     */
    public void setType(WallType type) {
        switch (type) {
            case COBBLESTONE:
                setData((byte) 0x0);
                return;
            case MOSSY_COBBLESTONE:
                setData((byte) 0x1);
                return;
        }
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Wall clone() {
        return (Wall) super.clone();
    }
}
