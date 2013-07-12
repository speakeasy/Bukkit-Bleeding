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
     * <p>
     * The duration parameter for these effects is meaningless.
     *
     * @return PotionEffects to provide to players
     */
    public Collection<PotionEffect> getEffects();

    /**
     * Get the PotionEffects that were last set on this beacon by a non-plugin
     * method.
     *
     * @return PotionEffects that would be provided if effects were reset
     */
    public Collection<PotionEffect> getDefaultEffects();

    /**
     * Set the PotionEffects provided by this beacon while it is active.
     * <p>
     * The duration and ambient parameters for the provided effects are
     * ignored.
     *
     * @param newEffects PotionEffects to provide to players
     * @see #resetEffects()
     */
    public void setEffects(Collection<PotionEffect> newEffects);

    /**
     * Revert the effects this beacon is providing to the effects returned by
     * {@link #getDefaultEffects()}.
     *
     * @see #setEffects(Collection)
     */
    public void resetEffects();

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
     * Check if a plugin has overridden the activation logic of the beacon.
     *
     * @return if setActive() has been called
     */
    public boolean isActivationOverridden();

    /**
     * Override the normal activation logic of this beacon to the given
     * activation state.
     * <p>
     * Even if you specify a true override, the beacon will continue to be
     * inactive if no effects are set.
     * <p>
     * Even if you specify a true override, the beacon will continue to be
     * inactive if there is no pyramid and the radius is not overridden.
     *
     * @param active new activation state
     */
    public void setActive(boolean active);

    /**
     * Clear the activation logic override.
     */
    public void resetActive();

    /**
     * Get the radius for which nearby players will be given the effects.
     * <p>
     * If the beacon pyramid is completely broken, this will return 0.
     *
     * @return the radius to check for players
     */
    public double getRadius();

    /**
     * Get the radius that this beacon would be providing effects based on its
     * pyramid size. This is only different from {@link #getRadius()} if a
     * plugin has changed the radius.
     *
     * @return the default radius to check for players
     */
    public double getDefaultRadius();

    /**
     * Override the radius for which nearby players will be given the effects
     * of this beacon.
     *
     * @param radius new radius
     * @see #resetRadius()
     */
    public void setRadius(double radius);

    /**
     * Reset the radius for which nearby players will be given the effects of
     * this beacon to the radius determined solely by the pyramid size as
     * returned by {@link #getDefaultRadius()}.
     *
     * @see #getDefaultRadius()
     */
    public void resetRadius();

    /**
     * Return whether this beacon can "see the sky", using an
     * implementation-defined definition which is the same as the one which
     * affects {@link #isActive()}. If it cannot, it will not be providing
     * effects.
     * <p>
     * This can be used to help determine for which reason the beacon is not
     * providing effects.
     *
     * @return if the beacon can "see the sky"
     */
    public boolean canSeeSky();

    /**
     * Get the size of the pyramid of blocks below this beacon.
     * <p>
     * This has the same effect as calling getPyramidSize(false) - the
     * previously calculated value is used.
     *
     * @return number of layers in the pyramid
     */
    public int getPyramidSize();

    /**
     * Get the size of the pyramid of blocks below this beacon.
     * <p>
     * If calculate is false, the previously calculated value is used.
     * <p>
     * If calculate is true, the pyramid size will be calculated immediately
     * with a maximum size of 4.
     *
     * @param calculate force recalculation right now
     * @return number of layers in the pyramid
     */
    public int getPyramidSize(boolean calculate);

    /**
     * Get the size of the pyramid of blocks below this beacon.
     * <p>
     * If calculate is false, the previously calculated value is used, and the
     * maximum parameter is ignored.
     * <p>
     * It is not guaranteed that the beacon will "remember" the count of any
     * number of layers greater than 4 for subsequent calls to
     * {@link #getPyramidSize()}.
     * <p>
     * Calling this method with a maximum height less than 4 may have
     * unexpected results.
     *
     * @param calculate force recalculation right now
     * @param maximum highest number of layers to check
     * @return number of layers in the pyramid
     */
    public int getPyramidSize(boolean calculate, int maximum);
}
