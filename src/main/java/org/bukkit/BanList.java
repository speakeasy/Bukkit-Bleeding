package org.bukkit;

import java.util.Date;
import java.util.Set;

/**
 * A ban list, containing bans of type {@link org.bukkit.BanList.Type}
 */
public interface BanList {
    /**
     * Gets a {@link BanEntry} by lookup
     *
     * @param name Entry to search for
     * @return BanEntry for the submitted query, or null if none exists
     */
    public BanEntry getBanEntry(String name);

    /**
     * Add a ban to the ban list<br />
     * Will overwrite previous ban if one exists
     *
     * @param name Entry name
     * @param source Source of the ban. Set null for default
     * @param expires Expiration Date of the ban. Set null for no expiration
     * @param reason Reason for the ban. Set null for default
     * @return BanEntry for the ban
     */
    public BanEntry addBan(String name, String source, Date expires, String reason);

    /**
     * Gets a set containing every {@link BanEntry} in the BanList
     *
     * @return an immutable set containing every BanEntry tracked by the BanList
     */
    public Set<BanEntry> getBanEntries();

    /**
     * Gets if a {@link BanEntry} exists for the name
     *
     * @param name Entry to search for
     * @return true if a {@link BanEntry} exists for the name
     */
    public boolean isBanned(String name);

    /**
     * Removes the specified entry from the list
     *
     * @param name Name of entry to unban
     */
    public void unban(String name);

    /**
     * Available types of {@link BanList}
     */
    public enum Type {
        /**
         * Banned usernames
         */
        PLAYER,
        /**
         * Banned IP addresses
         */
        IP;
    }

}
