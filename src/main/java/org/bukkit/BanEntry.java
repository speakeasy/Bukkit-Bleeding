package org.bukkit;

import java.util.Date;

/**
 * A single entry from a ban list
 */
public interface BanEntry {
    /**
     * Gets the banned name or IP address
     *
     * @return Name or IP address banned
     */
    String getName();

    /**
     * Gets the Date of the ban's creation
     *
     * @return Date of ban creation
     */
    Date getCreated();

    /**
     * Sets the Date of the ban's creation<br />
     * Use {@link #save() save()} to save the entry
     *
     * @param created Date of the ban's creation
     */
    void setCreated(Date created);

    /**
     * Gets the source of the ban
     *
     * @return Source of the ban
     */
    String getSource();

    /**
     * Sets the source of the ban<br />
     * Use {@link #save() save()} to save the entry
     *
     * @param source Source of the ban
     */
    void setSource(String source);

    /**
     * Gets when the ban expires, or null for no expiration
     *
     * @return Date of expiration or null for no expiration
     */
    Date getExpires();

    /**
     * Sets when the ban expires, using null for no expiration.<br />
     * Use {@link #save() save()} to save the entry
     *
     * @param expires Date of expiration or null for no expiration
     */
    void setExpires(Date expires);

    /**
     * Gets the ban reason
     *
     * @return Ban reason
     */
    String getReason();

    /**
     * Sets the ban reason<br />
     * Use {@link #save() save()} to save the entry
     *
     * @param reason Ban reason
     */
    void setReason(String reason);

    /**
     * Save the ban entry<br />
     * If the entry had been removed with an unban, this action will re-add it
     */
    void save();

}
