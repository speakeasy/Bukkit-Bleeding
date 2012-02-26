package org.bukkit.entity;

public interface ProjectileShooter {
    /**
     * Launches a {@link Projectile} from the entity.
     *
     * @param projectile Class of the projectile to launch
     *
     * @return The launched projectile.
     */
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile);
}
