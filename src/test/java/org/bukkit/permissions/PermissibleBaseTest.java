package org.bukkit.permissions;

import junit.framework.Assert;

import org.bukkit.Bukkit;
import org.bukkit.permissions.util.FakePlugin;
import org.bukkit.permissions.util.FakeServer;
import org.bukkit.plugin.Plugin;
import org.junit.Before;
import org.junit.Test;

public class PermissibleBaseTest {

    @Before
    public void setupFakeEnvironment() {
        if(Bukkit.getServer() == null) {
            Bukkit.setServer(new FakeServer());
        }
    }

    /**
     * Create a minimal implementation of "ServerOperator"
     * 
     * @param isOp initial op status
     * @return
     */
    private ServerOperator createServerOperator(final boolean isOp) {
        return new ServerOperator() {

            private boolean op = isOp;

            public boolean isOp() {
                return op;
            }

            public void setOp(boolean value) {
                this.op = value;
            }
        };
    }

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

        Plugin plugin = new FakePlugin();
        plugin.onEnable();

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

    }

}
