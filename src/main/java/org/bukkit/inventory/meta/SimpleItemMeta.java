package org.bukkit.inventory.meta;

/**
 * This class is the internal implementation base. It should not be used.
 */
public abstract class SimpleItemMeta implements ItemMeta {

    /**
     * Package private constructor to maintain control over implementations
     */
    SimpleItemMeta() {}

    /*
     * This implementation assumes all sub-classes are final, and can recreate themselves.
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public abstract SimpleItemMeta clone();

}
