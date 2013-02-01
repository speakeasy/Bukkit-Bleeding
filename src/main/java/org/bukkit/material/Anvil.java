package org.bukkit.material;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an Anvil.
 */
public class Anvil extends MaterialData {
    /**
     * Damage values for use with anvils.
     */
    public enum Damage {
        /**
         * Represents an undamaged anvil.
         */
        INTACT((byte) 0),
        /**
         * Represents an anvil with slight damage.
         */
        SLIGHTLY_DAMAGED((byte) 1),
        /**
         * Represents an anvil close to breaking.
         */
        VERY_DAMAGED((byte) 2);

        private final byte dataValue;
        private static final Map<Byte, Damage> fromDamage;

        Damage(byte dataValue) {
            this.dataValue = dataValue;
        }

        /**
         * @return the damage value used in an {@link org.bukkit.inventory.ItemStack} or {@link org.bukkit.block.Block}
         */
        public byte getDataValue() {
            return dataValue;
        }

        /**
         * Gets the Damage of an anvil corresponding to a specific {@link org.bukkit.inventory.ItemStack} or
         * {@link org.bukkit.block.Block} data value.
         *
         * @param data data value
         * @return the corresponding Damage value
         */
        public static Damage fromDataValue(byte data) {
            return fromDamage.get(data);
        }

        static {
            final Damage[] values = values();
            fromDamage = new HashMap<Byte, Damage>(values.length, 1);
            for (Damage value : values) {
                fromDamage.put(value.dataValue, value);
            }
        }
    }

    public Anvil() {
        super(Material.ANVIL);
    }

    public Anvil(Damage damage) {
        this();
        setDamage(damage);
    }

    public Anvil(final int type) {
        super(type);
    }

    public Anvil(final Material type) {
        super(type);
    }

    public Anvil(final int type, final byte data) {
        super(type, data);
    }

    public Anvil(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the damage level of this anvil.
     *
     * @return the {@link Damage}
     */
    public Damage getState() {
        return Damage.fromDataValue(getData());
    }

    /**
     * Sets the damage level of this anvil.
     *
     * @param damage the new damage level for this anvil
     */
    public void setDamage(Damage damage) {
        setData(damage.getDataValue());
    }

    @Override
    public String toString() {
        return getState() + " " + super.toString();
    }

    @Override
    public Anvil clone() {
        return (Anvil) super.clone();
    }
}
