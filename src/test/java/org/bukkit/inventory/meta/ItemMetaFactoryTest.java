package org.bukkit.inventory.meta;

import static junit.framework.Assert.*;

import org.bukkit.Material;
import org.junit.Test;

public class ItemMetaFactoryTest {
    @Test
    public void testMetaApplicable() {
        for (Material material : Material.values()) {
            assertTrue(material.toString(), ItemMetaFactory.getItemMetaFor(material).isApplicableTo(material));
        }
    }
}
