package org.bukkit.inventory.meta;

public interface SkullMeta extends ItemMeta {
    String getOwner();

    boolean setOwner(String owner);
}
