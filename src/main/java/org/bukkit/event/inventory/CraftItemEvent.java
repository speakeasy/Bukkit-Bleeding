package org.bukkit.event.inventory;

import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;

public class CraftItemEvent extends InventoryClickEvent {
    private Recipe recipe;

    @Deprecated
    public CraftItemEvent(Recipe recipe, InventoryView what, SlotType type, int slot, boolean right, boolean shift) {
        this(recipe, what, type, slot, right ? (shift ? InventoryAction.SHIFT_RIGHT : InventoryAction.RIGHT) : (shift ? InventoryAction.SHIFT_LEFT : InventoryAction.LEFT));
    }

    public CraftItemEvent(Recipe recipe, InventoryView what, SlotType type, int slot, InventoryAction action) {
        super(what, type, slot, action);
        this.recipe = recipe;
    }

    public CraftItemEvent(Recipe recipe, InventoryView what, SlotType type, int slot, int key) {
        super(what, type, slot, key);
        this.recipe = recipe;
    }

    /**
     * @return A copy of the current recipe on the crafting matrix.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public CraftingInventory getInventory() {
        return (CraftingInventory) super.getInventory();
    }
}
