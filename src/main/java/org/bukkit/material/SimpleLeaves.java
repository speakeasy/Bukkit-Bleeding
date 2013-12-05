package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

public class SimpleLeaves extends MaterialData {

    public SimpleLeaves(Material type, TreeSpecies species) {
        super(type);
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SimpleLeaves(final int type) {
        super(type);
    }

    public SimpleLeaves(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SimpleLeaves(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public SimpleLeaves(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this leaf
     *
     * @return TreeSpecies of this leaf
     */
    public TreeSpecies getSpecies() {
        Material type = getItemType();

        switch (getData() & 0x3) {
            case 0x0:
                return type == Material.LEAVES ? TreeSpecies.GENERIC : (type == Material.LEAVES_2 ? TreeSpecies.ACACIA : null);
            case 0x1:
                return type == Material.LEAVES ? TreeSpecies.REDWOOD : (type == Material.LEAVES_2 ? TreeSpecies.DARK_OAK : null);
            case 0x2:
                return type == Material.LEAVES ? TreeSpecies.BIRCH : null ;
            case 0x3:
                return type == Material.LEAVES ? TreeSpecies.JUNGLE : null;
            default:
                return null;
        }
    }

    /**
     * Sets the species of this leaf
     *
     * @param species New species of this leaf
     * @throws IllegalArgumentException if the given {@link TreeSpecies} is not supported by the underlying Material
     */
    public void setSpecies(TreeSpecies species) throws IllegalArgumentException {
        Material type = getItemType();

        if (type == Material.LEAVES) {
            switch (species) {
                case GENERIC:
                    setData((byte) (getData() & 0xC | 0));
                    return;
                case REDWOOD:
                    setData((byte) (getData() & 0xC | 1));
                    return;
                case BIRCH:
                    setData((byte) (getData() & 0xC | 2));
                    return;
                case JUNGLE:
                    setData((byte) (getData() & 0xC | 3));
                    return;
                default:
                    throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + type);
            }
        } else if (type == Material.LEAVES_2) {
            switch (species) {
                case ACACIA:
                    setData((byte) (getData() & 0xC | 0));
                    return;
                case DARK_OAK:
                    setData((byte) (getData() & 0xC | 1));
                    return;
                default:
                    throw new IllegalArgumentException("TreeSpecies " + species + " is not supported by " + type);
            }
        }
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public SimpleLeaves clone() {
        return (SimpleLeaves) super.clone();
    }
}
