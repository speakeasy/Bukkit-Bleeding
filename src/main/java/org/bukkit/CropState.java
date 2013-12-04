package org.bukkit;

/**
 * Represents the different growth states of crops
 */
public enum CropState {

    /**
     * State when first seeded
     */
    SEEDED,
    /**
     * First growth stage
     */
    GERMINATED,
    /**
     * Second growth stage
     */
    VERY_SMALL,
    /**
     * Third growth stage
     */
    SMALL,
    /**
     * Fourth growth stage
     */
    MEDIUM,
    /**
     * Fifth growth stage
     */
    TALL,
    /**
     * Almost ripe stage
     */
    VERY_TALL,
    /**
     * Ripe stage
     */
    RIPE;
}
