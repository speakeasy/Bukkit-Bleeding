package org.bukkit.scoreboard;

import java.util.Set;

import org.bukkit.OfflinePlayer;

/**
 * A team on a Scoreboard
 */
public interface Team {

    /**
     * Gets the name of this Team
     *
     * @return Objective name
     */
    String getName();

    /**
     * Gets the name displayed to players for this team
     *
     * @return Team display name
     */
    String getDisplayName();

    /**
     * Sets the name displayed to players for this team<br/>
     * Limit of 32 characters. Invalid names will throw IllegalArgumentException
     *
     * @param displayName New display name
     */
    void setDisplayName(String displayName);

    /**
     * Gets the prefix displayed to players for this team
     *
     * @return Team prefix
     */
    String getPrefix();

    /**
     * Sets the prefix displayed to players for this team<br/>
     * Limit of 16 characters. Invalid names will throw IllegalArgumentException
     *
     * @param prefix New prefix
     */
    void setPrefix(String prefix);

    /**
     * Gets the suffix displayed to players for this team
     *
     * @return Team prefix
     */
    String getSuffix();

    /**
     * Sets the suffix displayed to players for this team<br/>
     * Limit of 16 characters. Invalid names will throw IllegalArgumentException
     *
     * @param suffix New suffix
     */
    void setSuffix(String suffix);

    /**
     * Gets the team friendly fire state
     *
     * @return true if friendly fire is enabled
     */
    boolean allowFriendlyFire();

    /**
     * Sets the team friendly fire state
     *
     * @param enabled true if friendly fire is to be allowed
     */
    void setAllowFriendlyFire(boolean enabled);

    /**
     * Gets the team ability to see invisible teammates
     *
     * @return true if team members can see invisible members
     */
    boolean canSeeFriendlyInvisibles();

    /**
     * Sets the team ability to see invisible teammates
     *
     * @param enabled true if invisible teammates are to be visible
     */
    void setCanSeeFriendlyInvisibles(boolean enabled);

    /**
     * Gets the Set of players on the team
     *
     * @return players on the team
     */
    Set<OfflinePlayer> getPlayers();

    /**
     * Gets the size of the team
     *
     * @return number of players on the team
     */
    int getSize();

    /**
     * Gets the Scoreboard to which this Team is attached
     *
     * @return Owning scoreboard
     */
    Scoreboard getScoreboard();
}
