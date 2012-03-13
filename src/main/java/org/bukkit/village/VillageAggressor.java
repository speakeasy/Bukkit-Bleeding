package org.bukkit.village;

import org.bukkit.entity.LivingEntity;

public interface VillageAggressor {
    /**
     * Get the entity attacking the village
     *
     * @return LivingEntity
     */
    public LivingEntity getEntity();

    /**
     * Get the village being attacked
     *
     * @return Village being attacked
     */
    public Village getVillage();

    /*
     * How long this entity is going to be aggressive against the village for
     */
    public int getAggressionTicks();

    /**
     * Value between 0 and 300
     */
    public void setAggressionTicks(int ticks);
}
