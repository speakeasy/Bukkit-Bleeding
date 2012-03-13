package org.bukkit.village;

import org.bukkit.Location;

/**
 * Represents a door belonging to a village
 * <p />
 * A village door is the bottom half of a door
 */
public interface VillageDoor {
    /**
     * Get the location of the VillageDoor
     *
     * @return location of the VillageDoor
     */
    public Location getLocation();

    /**
     * Get the village of the VillageDoor
     *
     * @return village of this door
     */
    public Village getVillage();

    /**
     * Gets the age in ticks
     *
     * @return age of the VillageDoor
     */
    public int getAge();

    /**
     * Set the age (in ticks) of the VillageDoor
     *
     * @param age Age in ticks of the VillageDoor
     */
    public void setAge(int age);
}
