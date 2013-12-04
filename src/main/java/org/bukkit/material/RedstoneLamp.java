package org.bukkit.material;

import org.bukkit.Material;

public class RedstoneLamp extends MaterialData implements Redstone {
    public RedstoneLamp() {
        super(Material.REDSTONE_LAMP_ON);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RedstoneLamp(final int type) {
        super(type);
    }

    public RedstoneLamp(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RedstoneLamp(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public RedstoneLamp(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return getItemType() == Material.REDSTONE_LAMP_ON;
    }

    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public RedstoneLamp clone() {
        return (RedstoneLamp) super.clone();
    }
}
