package org.bukkit.inventory.meta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public interface PotionMeta extends ItemMeta {
    /**
     * Checks for the presence of custom potion effects
     * @return true if custom potion effects are applied
     */
    boolean hasCustomEffects();

    /**
     * Gets an immutable list containing all custom potion effects applied to this potion
     * @return the immutable list of custom potion effects
     */
    List<PotionEffect> getCustomEffects();

    /**
     * Adds a custom potion effect to this potion
     * @param effect the potion effect to add
     * @return true if the potion meta changed as a result of this call
     */
    boolean addCustomEffect(PotionEffect effect);

    /**
     * Removes a custom potion effect from this potion
     * @param type the potion effect type to remove
     * @return true if the potion meta changed as a result of this call
     */
    boolean removeCustomEffect(PotionEffectType type);

    /**
     * Moves a potion effect to the top of the potion effect list.
     * This causes the client to display the potion effect in the potion's name.
     * @param type the potion effect type to move
     * @return true if the potion meta changed as a result of this call
     */
    boolean setMainEffect(PotionEffectType type);

    /**
     * Removes all custom potion effects from this potion
     * @return true if the potion meta changed as a result of this call
     */
    boolean clearCustomEffects();
}
