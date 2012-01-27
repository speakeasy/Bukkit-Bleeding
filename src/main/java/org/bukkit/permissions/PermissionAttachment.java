package org.bukkit.permissions;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.plugin.Plugin;

/**
 * Holds information about a permission attachment on a {@link Permissible} object
 */
public class PermissionAttachment {
    private PermissionRemovedExecutor removed;
    private final Map<String, Boolean> permissions = new LinkedHashMap<String, Boolean>();
    private final Permissible permissible;
    private final Plugin plugin;
    private int priority = 0;

    public PermissionAttachment(Plugin plugin, Permissible permissible) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        this.permissible = permissible;
        this.plugin = plugin;
    }

    /**
     * Gets the plugin responsible for this attachment
     *
     * @return Plugin responsible for this permission attachment
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the priority value of this attachment
     *
     * @return A value representing the priority of this attachment
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority value of this attachment. Higher priority attachments override lower priorities.
     * 
     * @param priority A value between -1000 and 1000 representing the priority of this attachment
     */
    public void setPriority(int priority) {
        if (priority < -1000) {
            this.priority = -1000;
        } else if (priority > 1000) {
            this.priority = 1000;
        } else {
            this.priority = priority;
        }

        // If the attachment already contains permissions, recalculate
        if (permissions.size() > 0)
            permissible.recalculatePermissions();
    }

    /**
     * Sets an object to be called for when this attachment is removed from a {@link Permissible}. May be null.
     *
     * @param ex Object to be called when this is removed
     */
    public void setRemovalCallback(PermissionRemovedExecutor ex) {
        removed = ex;
    }

    /**
     * Gets the class that was previously set to be called when this attachment was removed from a {@link Permissible}. May be null.
     *
     * @return Object to be called when this is removed
     */
    public PermissionRemovedExecutor getRemovalCallback() {
        return removed;
    }

    /**
     * Gets the Permissible that this is attached to
     *
     * @return Permissible containing this attachment
     */
    public Permissible getPermissible() {
        return permissible;
    }

    /**
     * Gets a copy of all set permissions and values contained within this attachment.
     * <p />
     * This map may be modified but will not affect the attachment, as it is a copy.
     *
     * @return Copy of all permissions and values expressed by this attachment
     */
    public Map<String, Boolean> getPermissions() {
        return new LinkedHashMap<String, Boolean>(permissions);
    }

    /**
     * Sets a permission to the given value, by its fully qualified name
     *
     * @param name Name of the permission
     * @param value New value of the permission
     */
    public void setPermission(String name, boolean value) {
        permissions.put(name.toLowerCase(), value);
        permissible.recalculatePermissions();
    }

    /**
     * Sets a permission to the given value
     *
     * @param perm Permission to set
     * @param value New value of the permission
     */
    public void setPermission(Permission perm, boolean value) {
        permissions.put(perm.getName().toLowerCase(), value);
        permissible.recalculatePermissions();
    }

    /**
     * Set multiple permissions to given values at once, optionally unsetting
     * all permissions before
     *
     * @param perms Map of Permissions and new values of them
     * @param clearFirst if true, first remove all existing permissions
     */
    public void setPermissions(Map<Permission, Boolean> perms, boolean clearFirst) {
        if (clearFirst) permissions.clear();

        for (Entry<Permission, Boolean> perm : perms.entrySet()) {
            permissions.put(perm.getKey().getName().toLowerCase(), perm.getValue());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Set multiple permissions by name to given values at once, optionally
     * unsetting all permissions before
     *
     * @param perms Map of Permission names and new values of them
     * @param clearFirst if true, first remove all existing permissions
     */
    public void setPermissionsByName(Map<String, Boolean> perms, boolean clearFirst) {
        if (clearFirst)
            permissions.clear();

        for (Entry<String, Boolean> perm : perms.entrySet()) {
            permissions.put(perm.getKey().toLowerCase(), perm.getValue());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Removes the specified permission from this attachment.
     * <p />
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param name Name of the permission to remove
     */
    public void unsetPermission(String name) {
        permissions.remove(name.toLowerCase());
        permissible.recalculatePermissions();
    }

    /**
     * Removes the specified permission from this attachment.
     * <p />
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param perm Permission to remove
     */
    public void unsetPermission(Permission perm) {
        permissions.remove(perm.getName().toLowerCase());
        permissible.recalculatePermissions();
    }

    /**
     * Unset multiple permissions at once.
     *
     * If a permission does not exist in this attachment, nothing will happen.
     *
     * @param perms Collections of permissions to remove
     */
    public void unsetPermissions(Collection<Permission> perms) {
        for (Permission perm : perms) {
            permissions.remove(perm.getName().toLowerCase());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Unset multiple permissions by name at once.
     *
     * If a permission does not exist in this attachment, nothing will happen.
     *
     * @param perms Collections of permissions to remove
     */
    public void unsetPermissionsByName(Collection<String> perms) {
        for (String perm : perms) {
            permissions.remove(perm.toLowerCase());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Unset all permissions of the attachment at once
     */
    public void unsetAllPermissions() {
        permissions.clear();
        permissible.recalculatePermissions();
    }

    /**
     * Removes this attachment from its registered {@link Permissible}
     *
     * @return true if the permissible was removed successfully, false if it did not exist
     */
    public boolean remove() {
        try {
            permissible.removeAttachment(this);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
