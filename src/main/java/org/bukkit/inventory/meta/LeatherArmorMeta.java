package org.bukkit.inventory.meta;

import org.bukkit.Color;

public interface LeatherArmorMeta extends ItemMeta {

    /**
     * Gets the color of the armor
     * 
     * @return the color of the armor
     */
    Color getColor();

    /**
     * Sets the color of the armor
     * 
     * @param color the color to set
     */
    void setColor(Color color);
}
