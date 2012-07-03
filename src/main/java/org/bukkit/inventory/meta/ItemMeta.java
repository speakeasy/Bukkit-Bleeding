package org.bukkit.inventory.meta;

import org.bukkit.Material;

/**
 * This interface facilitates the extra information that can be put on items.
 */
public interface ItemMeta extends Cloneable {

    public ItemMeta clone();

    public boolean isApplicableTo(Material material);
}
