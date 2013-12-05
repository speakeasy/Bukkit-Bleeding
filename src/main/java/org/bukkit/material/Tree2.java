package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;

/**
 * Represents two types of logs: {@link TreeSpecies#ACACIA} and {@link TreeSpecies#DARK_OAK}.
 * </p>
 * The other types of logs are represented by {@link Tree}.
 */
public class Tree2 extends SimpleTree {
    public Tree2() {
        super(Material.LOG_2);
    }

    public Tree2(TreeSpecies species) {
        this();
        setSpecies(species);
    }

    public Tree2(TreeSpecies species, BlockFace dir) {
        this();
        setSpecies(species);
        setFacingDirection(dir);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Tree2(final int type) {
        super(type);
    }

    public Tree2(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Tree2(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public Tree2(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public Tree2 clone() {
        return (Tree2) super.clone();
    }
}
