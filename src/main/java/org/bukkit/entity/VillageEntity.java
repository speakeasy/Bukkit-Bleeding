package org.bukkit.entity;

import org.bukkit.village.Village;

/**
 * Represents an Entity that has a Village
 */
public interface VillageEntity extends Creature {
    /**
     * Get the village of this Entity.
     *
     * @return Current village.
     */
    public Village getVillage();
}
