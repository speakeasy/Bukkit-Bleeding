package org.bukkit.inventory.meta;

import org.bukkit.Material;

/**
 * This class is used to make new ItemMeta objects
 */
public final class ItemMetaFactory {

    private ItemMetaFactory() {}

    /**
     * This method creates an ItemMeta for the specified Material
     * @param material the material to derive the new ItemMeta from
     * @return a newly created ItemMeta
     */
    public static ItemMeta getItemMetaFor(final Material material) {
        switch (material) {
        default:
            return new EmptyItemMeta();
        }
    }

    /**
     * This method checks equality between two ItemMeta.
     * @param meta1 The first meta to check
     * @param meta2 The second meta to check
     * @return true if both are null, if one is null and the other represents an empty meta, or meta1.equals(meta2)
     */
    public static boolean equals(final ItemMeta meta1, final ItemMeta meta2) {
        if (meta1 == meta2) {
            return true;
        }
        if (meta1 == null) {
            return meta2 instanceof EmptyItemMeta;
        }
        if (meta2 == null) {
            return meta1 instanceof EmptyItemMeta;
        }
        return meta1.equals(meta2);
    }
}
