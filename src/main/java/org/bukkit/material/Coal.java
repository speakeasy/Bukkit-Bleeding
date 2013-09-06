package org.bukkit.material;

import org.bukkit.CoalType;
import org.bukkit.Material;

/**
 * Represents the different types of coals.
 */
public class Coal extends MaterialData {
    public Coal() {
        super(Material.COAL);
    }

    public Coal(CoalType type) {
        this();
        setType(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Coal(final int type) {
        super(type);
    }

    public Coal(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Coal(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Coal(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this coal
     *
     * @return CoalType of this coal
     */
    public CoalType getType() {
        switch (getData()) {
            case 0x0:
                return CoalType.COAL;
            case 0x1:
                return CoalType.CHARCOAL;
            default:
                return null;
        }
    }

    /**
     * Sets the type of this coal
     *
     * @param type New type of this coal
     */
    public void setType(CoalType type) {
        switch (type) {
            case COAL:
                setData((byte) 0x0);
                return;
            case CHARCOAL:
                setData((byte) 0x1);
                return;
        }
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Coal clone() {
        return (Coal) super.clone();
    }
}
