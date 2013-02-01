package org.bukkit.block;

import org.bukkit.potion.PotionEffectType;

/**
 * Represents a Beacon.
 */
public interface Beacon extends BlockState {
    /**
     * Gets the height of the pyramid that this beacon rests on.
     *
     * @return the pyramid height
     */
    int getPyramidHeight();

    /**
     * Gets the primary effect given by this beacon.
     *
     * @return the effect
     */
    PotionEffectType getPrimaryEffect();

    /**
     * Sets the primary effect given by this beacon.
     *
     * @param effect the new effect.
     *               Must be one of SPEED, FAST_DIGGING, DAMAGE_RESISTANCE,
     *               JUMP, INCREASE_DAMAGE, or REGENERATION
     */
    void setPrimaryEffect(PotionEffectType effect);

    /**
     * Gets the secondary effect given by this beacon.
     *
     * @return the effect
     */
    PotionEffectType getSecondaryEffect();

    /**
     * Sets the secondary effect given by this beacon.
     *
     * @param effect the new effect. Has the same constraints as the primary effect.
     * @see #setPrimaryEffect(org.bukkit.potion.PotionEffectType)
     */
    void setSecondaryEffect(PotionEffectType effect);
}
