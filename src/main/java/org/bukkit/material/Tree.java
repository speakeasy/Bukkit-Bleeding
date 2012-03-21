package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents the different types of Trees.
 */
public class Tree extends MaterialData {
    private static final byte SPECIES_BIT = 0x3;

    public Tree() {
        super(Material.LOG);
    }

    public Tree(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    public Tree(final int type) {
        super(type);
    }

    public Tree(final Material type) {
        super(type);
    }

    public Tree(final Material type, TreeSpecies species) {
        super(type);
        setSpecies(species);
    }

    public Tree(final int type, final byte data) {
        super(type, data);
    }

    public Tree(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current species of this tree
     *
     * @return TreeSpecies of this tree
     */
    public TreeSpecies getSpecies() {
        return TreeSpecies.getByData((byte) (getData() & SPECIES_BIT));
    }

    /**
     * Sets the species of this tree
     *
     * @param species New species of this tree
     */
    public void setSpecies(TreeSpecies species) {
        byte data = (byte) (getData() &~ SPECIES_BIT);
        setData((byte) (species.getData() | data));
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public Tree clone() {
        return (Tree) super.clone();
    }
}
