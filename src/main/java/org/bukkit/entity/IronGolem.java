package org.bukkit.entity;

/**
 * An iron Golem that protects Villages.
 */
public interface IronGolem extends Golem {

    /**
     * Gets whether this iron golem was built by a player.
     * 
     * @return Whether this iron golem was built by a player
     */
    public boolean isPlayerCreated();
}
