package org.bukkit.permissions;

import junit.framework.Assert;

import org.bukkit.permissions.util.FakePlugin;
import org.junit.Test;

public class PermissionAttachmentTest extends PermissionTest {

    @Test
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
}
