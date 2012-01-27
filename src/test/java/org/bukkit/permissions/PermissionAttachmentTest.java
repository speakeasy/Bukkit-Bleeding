package org.bukkit.permissions;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.bukkit.permissions.util.FakePlugin;
import org.junit.Test;

public class PermissionAttachmentTest extends PermissionTest {

    @Test
    /**
     * Test setting and unsetting of single permissions at the attachment
     */
    public void testPermissionAttachment_setUnsetPermission() {

        PermissibleBase base = new PermissibleBase(null);

        FakePlugin plugin = new FakePlugin();
        plugin.setEnabled(true);

        PermissionAttachment attachment = base.addAttachment(plugin);

        String permissionName = "permission";
        Permission permission = new Permission(permissionName);

        Assert.assertFalse("isPermissionSet should return false for unset string permission", base.isPermissionSet(permissionName));
        Assert.assertFalse("isPermissionSet should return false for unset permission", base.isPermissionSet(permission));
        Assert.assertFalse("hasPermission should return false for unset string permission", base.hasPermission(permissionName));
        Assert.assertFalse("hasPermission should return false for unset permission", base.hasPermission(permission));

        // set a permission on the attachment, it should be reflected at the base
        attachment.setPermission(permission, false);

        Assert.assertTrue("isPermissionSet should return true for set string permission", base.isPermissionSet(permissionName));
        Assert.assertTrue("isPermissionSet should return true for set permission", base.isPermissionSet(permission));
        Assert.assertFalse("hasPermission should return false for set string permission", base.hasPermission(permissionName));
        Assert.assertFalse("hasPermission should return false for set permission", base.hasPermission(permission));

        // set a permission on the attachment, it should be reflected at the base
        attachment.setPermission(permission, true);

        Assert.assertTrue("isPermissionSet should return true for set string permission", base.isPermissionSet(permissionName));
        Assert.assertTrue("isPermissionSet should return true for set permission", base.isPermissionSet(permission));
        Assert.assertTrue("hasPermission should return true for set string permission", base.hasPermission(permissionName));
        Assert.assertTrue("hasPermission should return true for set permission", base.hasPermission(permission));

        // unset a permission on the attachment, it should be reflected at the base
        attachment.unsetPermission(permission);

        Assert.assertFalse("isPermissionSet should return false for unset string permission", base.isPermissionSet(permissionName));
        Assert.assertFalse("isPermissionSet should return false for unset permission", base.isPermissionSet(permission));
        Assert.assertFalse("hasPermission should return false for unset string permission", base.hasPermission(permissionName));
        Assert.assertFalse("hasPermission should return false for unset permission", base.hasPermission(permission));

        // set a permission by name on the attachment, it should be reflected at the base
        attachment = base.addAttachment(plugin);
        attachment.setPermission(permissionName, true);

        Assert.assertTrue("isPermissionSet should return true for set string permission", base.isPermissionSet(permissionName));
        Assert.assertTrue("isPermissionSet should return true for set permission", base.isPermissionSet(permission));
        Assert.assertTrue("hasPermission should return true for set string permission", base.hasPermission(permissionName));
        Assert.assertTrue("hasPermission should return true for set permission", base.hasPermission(permission));

        // unset a permission by name on the attachment, it should be reflected at the base
        attachment.unsetPermission(permissionName);

        Assert.assertFalse("isPermissionSet should return true for set string permission", base.isPermissionSet(permissionName));
        Assert.assertFalse("isPermissionSet should return true for set permission", base.isPermissionSet(permission));
        Assert.assertFalse("hasPermission should return true for set string permission", base.hasPermission(permissionName));
        Assert.assertFalse("hasPermission should return true for set permission", base.hasPermission(permission));
    }

    @Test
    /**
     * Test setting/unsetting of multiple permissions at the attachment
     */
    public void testPermissionAttachment_setUnsetPermissions() {

        PermissibleBase base = new PermissibleBase(null);

        FakePlugin plugin = new FakePlugin();
        plugin.setEnabled(true);

        PermissionAttachment attachment = base.addAttachment(plugin);

        String permission1Name = "permission1";
        String permission2Name = "permission2";
        Permission permission1 = new Permission(permission1Name);
        Permission permission2 = new Permission(permission2Name);

        isSet(base, false, permission1Name, permission2Name);
        isSet(base, false, permission1, permission2);
        hasPerm(base, false, permission1Name, permission2Name);
        hasPerm(base, false, permission1, permission2);

        // Prepare parameter maps for bulk setting of permissions
        Map<String, Boolean> permsMapByName = new HashMap<String, Boolean>();
        permsMapByName.put(permission1Name, true);
        permsMapByName.put(permission2Name, false);

        Map<Permission, Boolean> permsMap = new HashMap<Permission, Boolean>();
        permsMap.put(permission1, false);
        permsMap.put(permission2, true);

        // setting permissions with different values by name
        attachment.setPermissionsByName(permsMapByName, false);

        isSet(base, true, permission1Name, permission2Name);
        isSet(base, true, permission1, permission2);
        hasPerm(base, true, permission1Name);
        hasPerm(base, true, permission1);
        hasPerm(base, false, permission2Name);
        hasPerm(base, false, permission2);

        // Unsetting permissions by Permission objects
        attachment.unsetPermissions(permsMap.keySet());

        isSet(base, false, permission1Name, permission2Name);
        isSet(base, false, permission1, permission2);
        hasPerm(base, false, permission1Name, permission2Name);
        hasPerm(base, false, permission1, permission2);

        // setting permissions with different values by Permission object
        attachment.setPermissions(permsMap, false); 

        isSet(base, true, permission1Name, permission2Name);
        isSet(base, true, permission1, permission2);
        hasPerm(base, false, permission1Name);
        hasPerm(base, false, permission1);
        hasPerm(base, true, permission2Name);
        hasPerm(base, true, permission2);

        // Unsetting permissions by Permission names
        attachment.unsetPermissionsByName(permsMapByName.keySet());

        isSet(base, false, permission1Name, permission2Name);
        isSet(base, false, permission1, permission2);
        hasPerm(base, false, permission1Name, permission2Name);
        hasPerm(base, false, permission1, permission2);
    }

}
