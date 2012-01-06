package org.bukkit.permissions;

import java.lang.reflect.Field;

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

    protected static final int    DEFAULT_OP_PERMISSION_COUNT     = 2;
    protected static final int    DEFAULT_NON_OP_PERMISSION_COUNT = 2;

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
}
