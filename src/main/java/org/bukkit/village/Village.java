package org.bukkit.village;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Villager;

/**
 * Represents a village
 */
public interface Village {
    /**
     * Get if the Village is abandoned
     * <p />
     * A Village is abandoned when it has no doors
     *
     * @return True if Village is abandoned
     */
    public boolean isAbandoned();

    /**
     * Will set the state of this Village to abandoned, effectively removing the Village
     * <p />
     * This will not, however, prevent new Villages from appearing in the same location
     */
    public void abandon();

    /**
     * Get the current count of population
     * <p />
     * Population is determined by how many Villagers are in the vicinity of the Village
     *
     * @return The amount of Villagers in the village
     */
    public int getPopulation();

    /**
     * Get the village center
     *
     * @return Location of the center of the village
     */
    public Location getCenter();

    /**
     * Get the size of the village
     * <p />
     * The size is the block radius around the village center
     *
     * @return the size of the village
     */
    public int getSize();

    /**
     * Populates a new list of VillageDoors
     *
     * @return A list of VillageDoors
     */
    public List<VillageDoor> getDoors();

    /**
     * Gets the amount of doors this village has, each door increases the amount of villagers a village can have
     *
     * @return the amount of doors in this village
     */
    public int getDoorCount();

    /**
     * Populates a new list of VillageAggressors
     *
     * @return A list of VillageAggressors
     */
    public List<VillageAggressor> getAggressors();

    /**
     * Get the amount of aggressors for the village
     *
     * @return A count of the current Aggressors
     */
    public int getAggressorCount();

    /**
     * Spawns a new IronGolem in the village center
     *
     * @return The golem spawned
     */
    public IronGolem spawnGolem();

    /**
     * Spawns a new villager in the village center
     *
     * @return The villager spawned
     */
    public Villager spawnVillager();

    /**
     * Spawns a new villager in the village center
     *
     * @param child True to spawn a
     * @return The villager spawned
     */
    public Villager spawnVillager(boolean child);

    /**
     * Get if this village is the village currently under siege
     *
     * @return True if under siege
     */
    public boolean isUnderSiege();

    /**
     * Return the world the village is in
     *
     * @return The world
     */
    public World getWorld();
}
