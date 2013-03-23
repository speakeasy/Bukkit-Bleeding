package org.bukkit.scoreboard;

import java.util.Set;

import org.bukkit.OfflinePlayer;

/**
 * A scoreboard
 */
public interface Scoreboard {

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @return The registered Objective
     * @throws IllegalArgumentException if name is null
     * @throws IllegalArgumentException if criteria is null
     * @throws IllegalArgumentException if an objective by that name already exists
     */
    Objective registerNewObjective(String name, String criteria) throws IllegalArgumentException;

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
     * Gets the Objective currently displayed in a DisplaySlot on this Scoreboard
     *
     * @param slot The DisplaySlot
     * @return The Objective currently displayed or null if nothing is displayed in that DisplaySlot
     * @throws IllegalArgumentException if slot is null
     */
    Objective getObjective(DisplaySlot slot) throws IllegalArgumentException;

    /**
     * Gets all scores for a player on this Scoreboard
     *
     * @param player Player whose scores are being retrieved
     * @return immutable set of all scores tracked for the player
     * @throws IllegalArgumentException if player is null
     */
    Set<Score> getScores(OfflinePlayer player) throws IllegalArgumentException;

    /**
     * Removes all scores for a player on this Scoreboard
     *
     * @param player Player who loses all scores
     * @throws IllegalArgumentException if player is null
     */
    void resetScores(OfflinePlayer player) throws IllegalArgumentException;

    /**
     * Gets a player's Team on this Scoreboard
     *
     * @param player Player to search by
     * @return The player's Team or null if the player is not on a team
     */
    Team getPlayerTeam(OfflinePlayer player);

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
     *
     * @param name Team name
     * @return registered Team
     * @throws IllegalArgumentException if name is null
     * @throws IllegalArgumentException if team by that name already exists
     */
    Team registerNewTeam(String name) throws IllegalArgumentException;

    /**
     * Gets all players tracked by this Scoreboard
     *
     * @return immutable set of all tracked players
     */
    Set<OfflinePlayer> getPlayers();

    /**
     * Clears any objective in the specified slot.
     *
     * @param slot the slot to remove objectives
     * @throws IllegalArgumentException if slot is null
     */
    void clearSlot(DisplaySlot slot) throws IllegalArgumentException;
}
