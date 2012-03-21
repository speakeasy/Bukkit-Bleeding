package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * Represents a leaf block
 */
public class Leaves extends Tree {
    private static final byte DECAYING_BIT = 0x8;
    private static final byte PERMANENT_BIT = 0x4;

    public Leaves() {
        super(Material.LEAVES);
    }

    public Leaves(TreeSpecies species) {
        super(Material.LEAVES, species);
    }

    public Leaves(final int type) {
        super(type);
    }

    public Leaves(final Material type) {
        super(type);
    }

    public Leaves(final int type, final byte data) {
        super(type, data);
    }

    public Leaves(final Material type, final byte data) {
        super(type, data);
    }

    public Leaves(byte data) {
        super(Material.LEAVES, data);
    }

    /**
     * @return True if the leaves are permanent and will never decay.
     */
    public boolean isPermanent() {
        return (getData() & PERMANENT_BIT) > 0;
    }

    /**
     * @param permanent Whether the leaves should be permanent and never decay.
     */
    public void setPermanent(boolean permanent) {
        byte data = getData();
        if (permanent) {
            data |= PERMANENT_BIT;
        } else {
            data &= ~PERMANENT_BIT;
        }
        setData(data);
    }

    /**
     * @return True if the leaves are marked to be checked for decay.
     */
    public boolean isReadyForDecay() {
        return (getData() & DECAYING_BIT) > 0;
    }

    /**
     * @param ready Whether the leaves should be checked for decay.
     */
    public void setReadyForDecay(boolean ready) {
        byte data = getData();
        if (ready) {
            data |= DECAYING_BIT;
        } else {
            data &= ~DECAYING_BIT;
        }
        setData(data);
    }

    @Override
    public String toString() {
        return super.toString() + (isPermanent() ? " (permanent)" : (isReadyForDecay() ? " (decaying)" : ""));
    }

    @Override
    public Leaves clone() {
        return (Leaves) super.clone();
    }
}
