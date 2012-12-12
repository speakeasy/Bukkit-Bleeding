package org.bukkit.inventory;

/**
 * Represents some type of crafting recipe.
 */
public interface Recipe {
    enum RecipeType {
        /**
         * A simple recipe in which the placement of ingredients matters.
         */
        SHAPED,
        /**
         * A simple recipe in which the placement of ingredients is irrelevant.
         */
        SHAPELESS,
        /**
         * A simple furnace recipe.
         */
        FURNACE,
        /**
         * A recipe for dying armor.
         */
        ARMOR_DYE,
        /**
         * A recipe for copying a map.
         */
        MAP_COPY,
        /**
         * A recipe for extending a map.
         */
        MAP_EXTEND,
        /**
         * A recipe for repairing tools.
         */
        REPAIR
    };

    /**
     * Get the result of this recipe.
     *
     * @return The result stack
     */
    ItemStack getResult();

    /**
     * Get the type of this recipe.
     * @return A recipe type.
     */
    RecipeType getType();
}
