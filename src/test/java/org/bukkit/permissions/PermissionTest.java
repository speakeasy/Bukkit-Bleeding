package org.bukkit.permissions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.bukkit.Bukkit;
import org.bukkit.permissions.util.FakeServer;
import org.bukkit.plugin.PluginManager;
import org.junit.Before;

public class PermissionTest {

    // Some default permissions
    protected static final String PERM_DEFAULT_FALSE              = "defaultFalse";
    protected static final String PERM_DEFAULT_TRUE               = "defaultTrue";
    protected static final String PERM_DEFAULT_OP                 = "defaultOP";
    protected static final String PERM_DEFAULT_NOT_OP             = "defaultNotOp";

    protected static final String PERM_DEFAULT_PARENT_TRUE        = "defaultParentTrue";
    protected static final String PERM_DEFAULT_CHILD_TRUE         = "defaultChildTrue";
    protected static final String PERM_DEFAULT_PARENT_OP          = "defaultParentOp";
    protected static final String PERM_DEFAULT_CHILD_OP           = "defaultChildOp";
    protected static final String PERM_DEFAULT_PARENT_NOT_OP      = "defaultParentNotOp";
    protected static final String PERM_DEFAULT_CHILD_NOT_OP       = "defaultChildNotOp";
    protected static final String PERM_DEFAULT_PARENT_FALSE       = "defaultParentFalse";
    protected static final String PERM_DEFAULT_CHILD_FALSE        = "defaultChildFalse";

    protected static final int    DEFAULT_OP_PERMISSION_COUNT     = 6;
    protected static final int    DEFAULT_NON_OP_PERMISSION_COUNT = 6;

    @Before
    public void setupFakeEnvironment() {
        // Make sure we run each test with a new server
        try {
            Field f = Bukkit.class.getDeclaredField("server");
            f.setAccessible(true);
            f.set(null, new FakeServer());
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Set up some default permissions
        PluginManager manager = Bukkit.getPluginManager();

        manager.addPermission(new Permission(PERM_DEFAULT_FALSE, PermissionDefault.FALSE));
        manager.addPermission(new Permission(PERM_DEFAULT_TRUE, PermissionDefault.TRUE));
        manager.addPermission(new Permission(PERM_DEFAULT_OP, PermissionDefault.OP));
        manager.addPermission(new Permission(PERM_DEFAULT_NOT_OP, PermissionDefault.NOT_OP));

        Map<String, Boolean> childrenTrue = new HashMap<String, Boolean>();
        childrenTrue.put(PERM_DEFAULT_CHILD_TRUE, true);
        manager.addPermission(new Permission(PERM_DEFAULT_CHILD_TRUE));
        manager.addPermission(new Permission(PERM_DEFAULT_PARENT_TRUE, PermissionDefault.TRUE, childrenTrue));

        Map<String, Boolean> childrenOp = new HashMap<String, Boolean>();
        childrenOp.put(PERM_DEFAULT_CHILD_OP, true);
        manager.addPermission(new Permission(PERM_DEFAULT_CHILD_OP));
        manager.addPermission(new Permission(PERM_DEFAULT_PARENT_OP, PermissionDefault.OP, childrenOp));

        Map<String, Boolean> childrenNotOp = new HashMap<String, Boolean>();
        childrenNotOp.put(PERM_DEFAULT_CHILD_NOT_OP, true);
        manager.addPermission(new Permission(PERM_DEFAULT_CHILD_NOT_OP));
        manager.addPermission(new Permission(PERM_DEFAULT_PARENT_NOT_OP, PermissionDefault.NOT_OP, childrenNotOp));

        Map<String, Boolean> childrenFalse = new HashMap<String, Boolean>();
        childrenFalse.put(PERM_DEFAULT_CHILD_FALSE, true);
        manager.addPermission(new Permission(PERM_DEFAULT_CHILD_FALSE));
        manager.addPermission(new Permission(PERM_DEFAULT_PARENT_FALSE, PermissionDefault.FALSE, childrenFalse));
    }

    /**
     * Create a minimal implementation of "ServerOperator"
     * 
     * @param isOp initial op status
     * @return
     */
    protected ServerOperator createServerOperator(final boolean isOp) {
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

    protected void isSet(PermissibleBase base, boolean expected, String... permission) {
        for(String p : permission) {
            Assert.assertEquals("isPermissionSet by name for " + (base.isOp() ? "OP" : "non-OP") + " should return " + expected + " for " + p + " permission", expected, base.isPermissionSet(p));
        }
    }

    protected void isSet(PermissibleBase base, boolean expected, Permission... permission) {
        for(Permission p : permission) {
            Assert.assertEquals("isPermissionSet for " + (base.isOp() ? "OP" : "non-OP") + " should return " + expected + " for " + p.getName() + " permission", expected, base.isPermissionSet(p));
        }
    }

    protected void hasPerm(PermissibleBase base, boolean expected, String... permission) {
        for(String p : permission) {
            Assert.assertEquals("hasPermission by name for " + (base.isOp() ? "OP" : "non-OP") + " should return " + expected + " for " + p + " string permission", expected, base.hasPermission(p));
        }
    }
}
