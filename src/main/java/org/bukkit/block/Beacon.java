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
     * Set the PotionEffects provided by this beacon while it is active.
     * <p>
     * The duration parameter for the provided effects is ignored.
     *
     * @param newEffects PotionEffects to provide to players
     */
    public void setEffects(Collection<PotionEffect> newEffects);

    /**
     * Remove any plugin-provided effects on this beacon and reset them to the
     * default.
     *
     * @return the new effects the beacon is providing
     */
    public Collection<PotionEffect> resetEffects();

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
     * Get the radius for which nearby players will be given the effects.
     * <p>
     * If the beacon pyramid is completely broken, this will return 0.
     *
     * @return the radius to check for players
     */
    public double getRadius();

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
