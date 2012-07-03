package org.bukkit.inventory.meta;

import org.bukkit.Material;

final class EmptyItemMeta extends SimpleItemMeta {

    EmptyItemMeta() {}

    @Override
    public SimpleItemMeta clone() {
        return new EmptyItemMeta();
    }

    public boolean isApplicableTo(Material material) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof EmptyItemMeta;
    }
}
