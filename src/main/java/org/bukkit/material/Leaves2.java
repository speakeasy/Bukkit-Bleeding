package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents two types of leaves: {@link TreeSpecies#ACACIA} and {@link TreeSpecies#DARK_OAK}.
 * </p>
 * The other types of leaves are represented by {@link Leaves2}.
 */
public class Leaves2 extends SimpleLeaves {
    public Leaves2() {
        super(Material.LEAVES_2);
    }

    public Leaves2(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final int type) {
        super(type);
    }

    public Leaves2(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Leaves2(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public Leaves2 clone() {
        return (Leaves2) super.clone();
    }
}
