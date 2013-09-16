package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.TreeSpecies;

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
        switch (getData()) {
            case 0x0:
            default:
                return TreeSpecies.GENERIC;

            case 0x1:
                return TreeSpecies.REDWOOD;

            case 0x2:
                return TreeSpecies.BIRCH;

            case 0x3:
                return TreeSpecies.JUNGLE;
        }
    }

    public void setSpecies(TreeSpecies species) {
        switch (species) {
            case GENERIC:
            default:
                setData((byte) 0x0);
                break;

            case REDWOOD:
                setData((byte) 0x1);
                break;

            case BIRCH:
                setData((byte) 0x2);
                break;

            case JUNGLE:
                setData((byte) 0x3);
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " of species " + getSpecies();
    }

    @Override
    public Sapling clone() {
        return (Sapling) super.clone();
    }
}
