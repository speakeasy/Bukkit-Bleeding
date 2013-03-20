package org.bukkit.scoreboard;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * An objective on a Scoreboard
 */
public interface Objective {

    /**
     * Objective criteria define if and how an objective's scores are updated
     */
    enum Criteria {
        HEALTH("health", true),
        PLAYER_KILLS("playerKillCount"),
        TOTAL_KILLS("totalKillCount"),
        DEATHS("deathCount"),
        CUSTOM("dummy");

        private final String commandName;
        private final boolean readOnly;
        private final static Map<String, Criteria> BY_NAME = Maps.newHashMap();

        private Criteria(String commandName) {
            this(commandName, false);
        }

        private Criteria(String commandName, boolean readOnly) {
            this.commandName = commandName;
            this.readOnly = readOnly;
        }

        static {
            for (Criteria criteria : Criteria.values()) {
                Criteria.BY_NAME.put(criteria.getCommandName(), criteria);
            }
        }

        /**
         * Gets a Criteria by the name used in the command
         *
         * @param commandName Command name
         * @return Criteria matching name or null if no match
         */
        public static Criteria getCriteria(String commandName) {
            return Criteria.BY_NAME.get(commandName);
        }

        /**
         * Gets if the Criteria is read-only.
         * Read-only criteria cannot be modified except by the server
         *
         * @return true if read-only
         */
        public boolean isReadOnly() {
            return this.readOnly;
        }

        /**
         * Gets the name used in the /scoreboard command
         *
         * @return the command name
         */
        public String getCommandName() {
            return this.commandName;
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
    Criteria getCriteria();

    /**
     * Gets the Scoreboard to which this Objective is attached
     *
     * @return Owning scoreboard
     */
    Scoreboard getScoreboard();
}
