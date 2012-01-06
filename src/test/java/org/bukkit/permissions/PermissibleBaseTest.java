package org.bukkit.permissions;

import java.util.Set;

import junit.framework.Assert;

import org.bukkit.permissions.util.FakePlugin;
import org.junit.Test;

public class PermissibleBaseTest extends PermissionTest {

    @Test
    public void testPermissibleBase_isOp() {

        PermissibleBase base;

        // without serveroperator object
        base = new PermissibleBase(null);

        try {
            base.setOp(true);
            Assert.fail("Setting OP status should cause an exception");
        } catch(Exception e) {}

        try {
            base.setOp(false);
            Assert.fail("Setting OP status should cause an exception");
        } catch(Exception e) {}

        // with ServerOperator object without op rights
        ServerOperator op = createServerOperator(false);
        base = new PermissibleBase(op);

        Assert.assertFalse("Server operator should not have op rights yet", op.isOp());
        Assert.assertFalse("PermissibleBase should not have op rights yet", base.isOp());

        // give op rights through PermissibleBase
        base.setOp(true);

        Assert.assertTrue("Server operator should have op rights now", op.isOp());
        Assert.assertTrue("PermissibleBase should have op rights now", base.isOp());

        // retract op rights through ServerOperator
        op.setOp(false);

        Assert.assertFalse("Server operator should no longer have op rights", op.isOp());
        Assert.assertFalse("PermissibleBase should no longer have op rights", base.isOp());
    }

    @Test
    public void testPermissibleBase_isPermissionSet() {

        PermissibleBase base;

        // without serveroperator object
        base = new PermissibleBase(null);

        // null permissions should fail
        String permissionName = null;
        Permission permission = null;

        try {
            base.isPermissionSet(permissionName);
            Assert.fail("isPermissionSet should cause an exception with null string permission");
        } catch(Exception e) {}

        try {
            base.isPermissionSet(permission);
            Assert.fail("isPermissionSet should cause an exception with null permission");
        } catch(Exception e) {}

        // unset permissions should return false
        permissionName = "unsetPermission";
        permission = new Permission(permissionName);
        Assert.assertFalse("isPermissionSet should return false for unset string permission", base.isPermissionSet(permissionName));
        Assert.assertFalse("isPermissionSet should return false for unset permission", base.isPermissionSet(permission));

        FakePlugin plugin = new FakePlugin();
        plugin.setEnabled(true);

        // set permissions should return true
        PermissionAttachment attachment = base.addAttachment(plugin, "setPermission", false);

        permissionName = "setPermission";
        permission = new Permission(permissionName);

        Assert.assertTrue("isPermissionSet should return true for set string permission", base.isPermissionSet(permissionName));
        Assert.assertTrue("isPermissionSet should return true for set permission", base.isPermissionSet(permission));

        // removed permissions should return false again
        base.removeAttachment(attachment);

        Assert.assertFalse("isPermissionSet should return false again for removed string permission", base.isPermissionSet(permissionName));
        Assert.assertFalse("isPermissionSet should return false again for removed permission", base.isPermissionSet(permission));

        // default permissions for null serveroperator object
        base = new PermissibleBase(null);

        Assert.assertTrue("isPermissionSet for non-OP should return true for default true permission", base.isPermissionSet(PERM_DEFAULT_TRUE));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default op permission", base.isPermissionSet(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default false permission", base.isPermissionSet(PERM_DEFAULT_FALSE));
        Assert.assertTrue("isPermissionSet for non-OP should return true for default not-op permission", base.isPermissionSet(PERM_DEFAULT_NOT_OP));

        // default permissions set for non OP
        base = new PermissibleBase(createServerOperator(false));

        Assert.assertTrue("isPermissionSet for non-OP should return true for default true permission", base.isPermissionSet(PERM_DEFAULT_TRUE));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default op permission", base.isPermissionSet(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default false permission", base.isPermissionSet(PERM_DEFAULT_FALSE));
        Assert.assertTrue("isPermissionSet for non-OP should return true for default not-op permission", base.isPermissionSet(PERM_DEFAULT_NOT_OP));

        // default permissions set for OP
        base = new PermissibleBase(createServerOperator(true));

        Assert.assertTrue("isPermissionSet for OP should return true for default true permission", base.isPermissionSet(PERM_DEFAULT_TRUE));
        Assert.assertTrue("isPermissionSet for OP should return true for default op permission", base.isPermissionSet(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for OP should return false for default false permission", base.isPermissionSet(PERM_DEFAULT_FALSE));
        Assert.assertFalse("isPermissionSet for OP should return false for default not-op permission", base.isPermissionSet(PERM_DEFAULT_NOT_OP));

    }

    @Test
    public void testPermissibleBase_hasPermissionDefaults() {
        // default permissions for null serveroperator object
        PermissibleBase base = new PermissibleBase(null);

        Assert.assertTrue("isPermissionSet for non-OP should return true for default true permission", base.hasPermission(PERM_DEFAULT_TRUE));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default op permission", base.hasPermission(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default false permission", base.hasPermission(PERM_DEFAULT_FALSE));
        Assert.assertTrue("isPermissionSet for non-OP should return true for default not-op permission", base.hasPermission(PERM_DEFAULT_NOT_OP));

        // default permissions set for OP
        base = new PermissibleBase(createServerOperator(true));

        Assert.assertTrue("isPermissionSet for OP should return true for default true permission", base.hasPermission(PERM_DEFAULT_TRUE));
        Assert.assertTrue("isPermissionSet for OP should return true for default op permission", base.hasPermission(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for OP should return false for default false permission", base.hasPermission(PERM_DEFAULT_FALSE));
        Assert.assertFalse("isPermissionSet for OP should return false for default not-op permission", base.hasPermission(PERM_DEFAULT_NOT_OP));

        // default permissions set for non OP
        base = new PermissibleBase(createServerOperator(false));

        Assert.assertTrue("isPermissionSet for non-OP should return true for default true permission", base.hasPermission(PERM_DEFAULT_TRUE));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default op permission", base.hasPermission(PERM_DEFAULT_OP));
        Assert.assertFalse("isPermissionSet for non-OP should return false for default false permission", base.hasPermission(PERM_DEFAULT_FALSE));
        Assert.assertTrue("isPermissionSet for non-OP should return true for default not-op permission", base.hasPermission(PERM_DEFAULT_NOT_OP));
    }

    @Test
    /**
     * Check if attachments are created and removed correctly, 
     * ignoring permissions
     */
    public void testPermissibleBase_basicAddRemoveAttachments() {
        PermissibleBase base;

        base = new PermissibleBase(null);

        FakePlugin plugin = new FakePlugin();

        // Illegal operations
        try {
            base.addAttachment(null);
            Assert.fail("addAttachment should fail for null plugin");
        } catch(Exception e) {}

        try {
            base.addAttachment(plugin);
            Assert.fail("addAttachment should fail for disabled plugins");
        } catch(Exception e) {}

        try {
            base.removeAttachment(null);
            Assert.fail("removeAttachment should fail for null attachment");
        } catch(Exception e) {}

        // enable fake plugin
        plugin.setEnabled(true);

        PermissionAttachment attachment = base.addAttachment(plugin);
        Assert.assertNotNull("addAttachment should return a valid attachment", attachment);
        Assert.assertEquals("the attachment should refer to the permissibleBase that it is part of", base, attachment.getPermissible());
        Assert.assertEquals("the attachment should refer to the plugin that registered it", plugin, attachment.getPlugin());

        base.removeAttachment(attachment);
        try {
            base.removeAttachment(attachment);
            Assert.fail("removing already removed attachments should fail");
        } catch(Exception e) {}

        attachment = base.addAttachment(plugin);
        // Remove at attachment
        Assert.assertTrue("attachment.remove should return true", attachment.remove());
        Assert.assertFalse("attachment.remove should return false if already removed", attachment.remove());

        // Cross check if it was really removed from base
        try {
            base.removeAttachment(attachment);
            Assert.fail("the attachment shouldn't have been attached to base anymore");
        } catch(Exception e) {}
    }

    @Test
    /**
     * Check if permissions are assigned/unassigned correctly when
     * adding/removing attachments
     */
    public void testPermissibleBase_advancedAddRemoveAttachments() {
        PermissibleBase base;

        // without serveroperator object
        base = new PermissibleBase(null);

        FakePlugin plugin = new FakePlugin();
        // enable the fake plugin
        plugin.setEnabled(true);

        String permission = "permission";

        Assert.assertFalse("base should not have permission yet", base.hasPermission(permission));

        // Stack attachments on top of each other, cancelling each other out

        // Add a positive attachment
        PermissionAttachment attachment = base.addAttachment(plugin, permission, true);
        Assert.assertTrue("base should have permission now", base.hasPermission(permission));
        Assert.assertTrue("permission should be set", base.isPermissionSet(permission));

        // Add a negative attachment on top of that
        PermissionAttachment attachment2 = base.addAttachment(plugin, permission, false);
        Assert.assertFalse("base should no longer have permission now", base.hasPermission(permission));
        Assert.assertTrue("permission should be set", base.isPermissionSet(permission));

        // Remove the negative attachment, it should leave the positive behind
        attachment2.remove();
        Assert.assertTrue("base should have permission again", base.hasPermission(permission));
        Assert.assertTrue("permission should be set", base.isPermissionSet(permission));

        // Remove the negative attachment too
        attachment.remove();
        Assert.assertFalse("base should no longer have permission now", base.hasPermission(permission));
        Assert.assertFalse("permission should no longer be set", base.isPermissionSet(permission));
    }

    @Test
    /**
     * Check if getEffectivePermissions delivers default and
     * attachment permissions correctly
     */
    public void testPermissibleBase_getEffectivePermissions() {
        PermissibleBase base;

        // without serveroperator object
        base = new PermissibleBase(null);

        Set<PermissionAttachmentInfo> infos = base.getEffectivePermissions();

        // Two default permissions should be set
        Assert.assertEquals(infos.size(), DEFAULT_NON_OP_PERMISSION_COUNT);

        for(PermissionAttachmentInfo info : infos) {
            Assert.assertEquals("effective Permissions have to reference base", info.getPermissible(), base);
            Assert.assertEquals("effective Permissions have to be the same as result of hasPermission", info.getValue(), base.hasPermission(info.getPermission()));
        }

        // with non-op serveroperator object
        base = new PermissibleBase(createServerOperator(false));

        infos = base.getEffectivePermissions();

        // Two default permissions should be set
        Assert.assertEquals(infos.size(), DEFAULT_NON_OP_PERMISSION_COUNT);

        for(PermissionAttachmentInfo info : infos) {
            Assert.assertEquals("effective Permissions have to reference base", info.getPermissible(), base);
            Assert.assertEquals("effective Permissions have to be the same as result of hasPermission", info.getValue(), base.hasPermission(info.getPermission()));
        }

        // with op serveroperator object
        base = new PermissibleBase(createServerOperator(true));

        infos = base.getEffectivePermissions();

        // Two default permissions should be set
        Assert.assertEquals(infos.size(), DEFAULT_OP_PERMISSION_COUNT);

        for(PermissionAttachmentInfo info : infos) {
            Assert.assertEquals("effective Permissions have to reference base", info.getPermissible(), base);
            Assert.assertEquals("effective Permissions have to be the same as result of hasPermission", info.getValue(), base.hasPermission(info.getPermission()));
        }

        // add attachment
        FakePlugin plugin = new FakePlugin();
        plugin.setEnabled(true);

        String permission1 = "permission1";
        String permission2 = "permission2";

        PermissionAttachment attachment1 = base.addAttachment(plugin, permission1, false);
        PermissionAttachment attachment2 = base.addAttachment(plugin, permission2, false);

        infos = base.getEffectivePermissions();

        // Two default permissions plus two permissions from attachments should
        // be set
        Assert.assertEquals(infos.size(), DEFAULT_OP_PERMISSION_COUNT + 2);

        for(PermissionAttachmentInfo info : infos) {
            Assert.assertEquals("effective Permissions have to reference base", info.getPermissible(), base);
            Assert.assertEquals("effective Permissions have to be the same as result of hasPermission", info.getValue(), base.hasPermission(info.getPermission()));
        }

        // Effective permissions should no longer contain these now
        base.removeAttachment(attachment1);
        base.removeAttachment(attachment2);

        Assert.assertEquals(infos.size(), DEFAULT_OP_PERMISSION_COUNT);

        for(PermissionAttachmentInfo info : infos) {
            Assert.assertEquals("effective Permissions have to reference base", info.getPermissible(), base);
            Assert.assertEquals("effective Permissions have to be the same as result of hasPermission", info.getValue(), base.hasPermission(info.getPermission()));
        }
    }
}
