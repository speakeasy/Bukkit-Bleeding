package org.bukkit.inventory.meta;

import org.bukkit.Colour;

public interface LeatherArmorMeta extends ItemMeta {
    Colour getColour();
    void setColour(Colour colour);
}
