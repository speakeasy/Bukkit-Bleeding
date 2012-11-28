package org.bukkit.entity;

/**
 * Represents an entity that will be removed after a specified number of ticks.
 */
public interface Expirable {
    
    /**
     * Gets the age timer that determines expiration.
     * 
     * @return tick count entity uses to determine expiration
     */
    public int getAge();
    
    /**
     * Sets the age timer that determines expiration.
     * 
     * @param age tick count entity will use to determine expiration
     */
    public void setAge(int age);
    
    /**
     * Gets the maximum age before removal of this entity.
     * 
     * @return age in ticks when entity should be removed
     */
    public int getExpiration();
    
    /**
     * Sets the maximum age at which to remove this entity.
     * 
     * @param expiration age in ticks when entity should be removed
     */
    public void setExpiration(int expiration);

}
