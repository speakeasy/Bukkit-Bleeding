package org.bukkit.scoreboard;

/**
 * An objective on a Scoreboard
 */
public interface Objective {
    /**
     * Criteria names which trigger an objective to be modified by actions in-game
     */
    class Criteria {
        public static final String HEALTH;
        public static final String PLAYER_KILLS;
        public static final String TOTAL_KILLS;
        public static final String DEATHS;

        static {
            HEALTH="health";
            PLAYER_KILLS="playerKillCount";
            TOTAL_KILLS="totalKillCount";
            DEATHS="deathCount";
        }
    }

    /**
     * Gets the name of this Objective
     *
     * @return Objective name
     */
    String getName();

    /**
     * Gets the name displayed to players for this objective
     *
     * @return Objective display name
     */
    String getDisplayName();

    /**
     * Sets the name displayed to players for this objective<br/>
     * Limit of 32 characters. Invalid names will throw IllegalArgumentException
     *
     * @param displayName Display name to set
     */
    void setDisplayName(String displayName);

    /**
     * Gets the criteria this objective tracks
     *
     * @return Objective criteria
     */
    String getCriteria();

    /**
     * Gets if the objective's scores can be modified by user or plugin input
     *
     * @return true if scores are modifiable
     */
    boolean isModifiable();

    /**
     * Gets the Scoreboard to which this Objective is attached
     *
     * @return Owning scoreboard
     */
    Scoreboard getScoreboard();
}
