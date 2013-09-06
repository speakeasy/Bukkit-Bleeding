package org.bukkit.material;

import org.bukkit.Material;

public class DaylightSensor extends MaterialData implements Redstone {
    public DaylightSensor() {
        super(Material.DAYLIGHT_DETECTOR);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public DaylightSensor(final int type) {
        super(type);
    }

    public DaylightSensor(final Material type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public DaylightSensor(final int type, final byte data) {
        super(type, data);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public DaylightSensor(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return true if powered, otherwise false
     */
    public boolean isPowered() {
        return getData() > 0;
    }

    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public DaylightSensor clone() {
        return (DaylightSensor) super.clone();
    }
}
