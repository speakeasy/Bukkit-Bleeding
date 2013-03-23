package org.bukkit.scoreboard;

import java.util.Map;
import java.util.Set;

import org.bukkit.OfflinePlayer;

import com.google.common.collect.Maps;

/**
 * A scoreboard
 */
public interface Scoreboard {

    /**
     * Locations for displaying objectives to the player
     */
    enum DisplaySlot {
        BELOW_NAME("belowName"),
        PLAYER_LIST("list"),
        SIDEBAR("sidebar");

        private final String commandString;
        private final static Map<String, DisplaySlot> BY_NAME = Maps.newHashMap();

        private DisplaySlot(String commandString) {
            this.commandString = commandString;
        }

        static {
            for (DisplaySlot displaySlot : DisplaySlot.values()) {
                DisplaySlot.BY_NAME.put(displaySlot.getCommandName(), displaySlot);
            }
        }

        /**
         * Gets a DisplaySlot by name used in the scoreboard command
         *
         * @param commandName Command name
         * @return DisplaySlot matching the name, or null if no match
         */
        public static DisplaySlot getDisplaySlot(String commandName) {
            return DisplaySlot.BY_NAME.get(commandName);
        }

        /**
         * Gets the name of this DisplaySlot as used by the scoreboard command
         *
         * @return command name
         */
        public String getCommandName() {
            return this.commandString;
        }
    }

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @return The registered Objective
     */
    Objective registerObjective(String name, String criteria);

    /**
     * Gets an Objective on this Scoreboard by name
     *
     * @param name Name of the Objective
     * @return the Objective or null if it does not exist
     */
    Objective getObjective(String name);

    /**
     * Gets all Objectives of a Criteria on the Scoreboard
     *
     * @param criteria Criteria to search by
     * @return an immutable set of Objectives using the specified Criteria
     */
    Set<Objective> getObjectivesByCriteria(String criteria);

    /**
     * Gets all Objectives on this Scoreboard
     *
     * @return An immutable set of all Objectives on this Scoreboard
     */
    Set<Objective> getObjectives();

    /**
     * Unregisters an Objective from this Scoreboard
     *
     * @param objective Objective to unregister
     */
    void unregisterObjective(Objective objective);

    /**
     * Sets which Objective is displayed in a DisplaySlot on this Scoreboard
     *
     * @param slot DisplaySlot to change
     * @param objective Objective to display or null to display nothing in the specified DisplaySlot
     */
    void setDisplaySlot(DisplaySlot slot, Objective objective);

    /**
     * Gets the Objective currently displayed in a DisplaySlot on this Scoreboard
     *
     * @param slot The DisplaySlot
     * @return The Objective currently displayed or null if nothing is displayed in that DisplaySlot
     */
    Objective getDisplaySlot(DisplaySlot slot);

    /**
     * Gets a player's Score for an Objective on this Scoreboard
     *
     * @param objective Objective for the Score
     * @param player Player for the Score
     * @return Score tracking the Objective and player specified
     */
    Score getScore(Objective objective, OfflinePlayer player);

    /**
     * Gets all scores for a player on this Scoreboard
     *
     * @param player Player whose scores are being retrieved
     * @return immutable set of all scores tracked for the player
     */
    Set<Score> getScores(OfflinePlayer player);

    /**
     * Removes all scores for a player on this Scoreboard
     *
     * @param player Player who loses all scores
     */
    void resetScores(OfflinePlayer player);

    /**
     * Gets a player's Team on this Scoreboard
     *
     * @param player Player to search by
     * @return The player's Team or null if the player is not on a team
     */
    Team getPlayerTeam(OfflinePlayer player);

    /**
     * Sets a player's Team on this Scoreboard
     *
     * @param player Player to change Team membership
     * @param team Team for the player to join, null for no team
     */
    void setPlayerTeam(OfflinePlayer player, Team team);

    /**
     * Gets a Team by name on this Scoreboard
     *
     * @param teamName Team name
     * @return the matching Team or null if no matches
     */
    Team getTeam(String teamName);

    /**
     * Gets all teams on this Scoreboard
     *
     * @return an immutable set of Teams
     */
    Set<Team> getTeams();

    /**
     * Registers a Team on this Scoreboard
     * If the name already exists on this board, an IllegalArgumentException is thrown
     *
     * @param name Team name
     * @return registered Team
     */
    Team registerTeam(String name);

    /**
     * Unregisters a Team from this Scoreboard
     *
     * @param team Team to unregister
     */
    void unregisterTeam(Team team);

    /**
     * Gets all players tracked by this Scoreboard
     *
     * @return immutable set of all tracked players
     */
    Set<OfflinePlayer> getPlayers();
}
