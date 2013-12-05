package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents all 6 types of {@link TreeSpecies}.
 */
public class Sapling extends MaterialData {
    public Sapling() {
        super(Material.SAPLING);
    }

    public Sapling(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sapling(final int type) {
        super(type);
    }

    public Sapling(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sapling(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Sapling(final Material type, final byte data) {
        super(type, data);
    }

    public TreeSpecies getSpecies() {
        switch (getData() & 0x7) {
            case 0x0:
                return TreeSpecies.GENERIC;
            case 0x1:
                return TreeSpecies.REDWOOD;
            case 0x2:
                return TreeSpecies.BIRCH;
            case 0x3:
                return TreeSpecies.JUNGLE;
            case 0x4:
                return TreeSpecies.ACACIA;
            case 0x5:
                return TreeSpecies.DARK_OAK;
            default:
                return null;
        }
    }

    public void setSpecies(TreeSpecies species) {
        switch (species) {
            case GENERIC:
                setData((byte) (getData() & 0x8 | 0));
                break;
            case REDWOOD:
                setData((byte) (getData() & 0x8 | 1));
                break;
            case BIRCH:
                setData((byte) (getData() & 0x8 | 2));
                break;
            case JUNGLE:
                setData((byte) (getData() & 0x8 | 3));
                break;
            case ACACIA:
                setData((byte) (getData() & 0x8 | 4));
                break;
            case DARK_OAK:
                setData((byte) (getData() & 0x8 | 5));
                break;
        }
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public Sapling clone() {
        return (Sapling) super.clone();
    }
}
