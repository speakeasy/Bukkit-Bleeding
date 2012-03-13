package org.bukkit.village;

import java.util.List;
import org.bukkit.Location;

/**
 * Represents a worlds village manager
 */
public interface VillageManager {
    /**
     * Get the list of villages in this world
     * <p />
     * This list is a new copy of the villages, and changes will not effect the list
     *
     * @return List of villages in the world
     */
    public List<Village> getVillages();

    /**
     * Gets the closest village within 16 blocks
     * <p />
     * Will return null if no village is found
     *
     * @param location Location to search from
     * @return Closest village to location, can be null
     */
    public Village getVillage(Location location);

    /**
     * Gets the closest village within a specified range
     * <p />
     * Will return null if no village is found
     *
     * @param location Location to search from
     * @param range Range of blocks to search, between 1 and 64
     * @return Closest village to location, can be null
     */
    public Village getVillage(Location location, int range);

    /**
     * Get the village under siege
     * <p />
     * Will return null if there is no siege
     *
     * @return The current village under siege
     */
    public Village getVillageUnderSiege();
}
