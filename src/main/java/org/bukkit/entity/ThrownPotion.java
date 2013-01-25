package org.bukkit.entity;

import java.util.Collection;

import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

/**
 * Represents a thrown potion bottle
 */
public interface ThrownPotion extends Projectile {
    /**
     * @deprecated use the itemmeta methods
     *
     * Returns the effects that are applied by this potion.
     * @return The potion effects
     */
    public Collection<PotionEffect> getEffects();

    /**
     * Returns the PotionMeta for the attached potion
     *
     * @return
     */
    public PotionMeta getItemMeta();

    /**
     * Sets the PotionMeta for the attached potion
     */
    public boolean setItemMeta(PotionMeta meta);
}
