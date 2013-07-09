package org.bukkit.block;

import java.util.Collection;

import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.potion.PotionEffect;

/**
 * Represents a beacon.
 */
public interface Beacon extends BlockState, InventoryHolder {
    /**
     * Get the BeaconInventory.
     */
    public BeaconInventory getInventory();

    /**
     * Get the PotionEffects that are provided by this beacon while it is
     * active.
     *
     * @return PotionEffects to provide to players
     */
    public Collection<PotionEffect> getEffects();

    /**
     * Set the PotionEffects provided by this beacon while it is active.
     *
     * @param newEffects PotionEffects to provide to players
     */
    public void setEffects(Collection<PotionEffect> newEffects);

    /**
     * Whether this beacon is actively providing PotionEffects to players.
     * <p>
     * This value is guaranteed to be updated right before the effects are
     * provided.
     *
     * @return if the beacon provided effects in the last cycle
     */
    public boolean isActive();

    /**
     * Override the normal activation logic of the beacon.
     * <p>
     * Even if you setOverride(true), the beacon will remain inactive if no
     * effects are set for it.
     *
     * @param active new activation state
     * @see #clearOverride()
     */
    public void setOverride(boolean active);

    /**
     * Stop overriding the activation logic of the beacon.
     *
     * @see #setOverride(boolean)
     */
    public void clearOverride();

    /**
     * Get the radius for which nearby players will be given the effects.
     * <p>
     * If the beacon pyramid is completely broken, this will return 0.
     *
     * @return the radius to check for players
     */
    public double getRadius();

    /**
     * Provide all effects in {@link #getEffects()} to nearby players
     * immediately, regardless of whether the beacon is active.
     */
    public boolean provideEffectsNow();
}
