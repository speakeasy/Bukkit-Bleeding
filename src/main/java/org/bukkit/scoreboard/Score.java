package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;

/**
 * An individual score for a player and Objective on a Scoreboard
 */
public interface Score {

    /**
     * Gets the OfflinePlayer being tracked by this Score
     *
     * @return this Score's tracked player
     */
    OfflinePlayer getPlayer();

    /**
     * Gets the Objective being tracked by this Score
     *
     * @return this Score's tracked objective
     */
    Objective getObjective();

    /**
     * Gets the current score
     *
     * @return the current score
     */
    int getScore();

    /**
     * Sets the current score.
     *
     * @param score New score
     */
    void setScore(int score);
}
