package org.bukkit.event.inventory;

import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

public class InventoryHotbarEvent extends InventoryClickEvent {
    private int hotbarKey;

    public InventoryHotbarEvent(InventoryView what, SlotType type, int slot, int key) {
        super(what, type, slot, ClickAction.NUMBER_KEY);
        this.hotbarKey = key;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the offset
     * into the InventoryView of the appropriate slot on the hotbar.
     * @return a raw slot index
     */
    // TODO test
    public int getHotbarSlot() {
        return hotbarKey + getView().getTopInventory().getSize() + 27;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the index
     * of the pressed key (0-8).
     * @return the number on the key minus 1 (range 0-8)
     */
    public int getHotbarButton() {
        return hotbarKey;
    }
}
