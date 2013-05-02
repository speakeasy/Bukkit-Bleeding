package org.bukkit.plugin.messaging;

import org.apache.commons.lang.Validate;

import org.bukkit.plugin.Plugin;

/**
 * Contains information about a {@link Plugin}s registration to a plugin channel.
 */
public final class PluginMessageListenerRegistration {
    private final Messenger messenger;
    private final Plugin plugin;
    private final String channel;
    private final PluginMessageListener listener;

    public PluginMessageListenerRegistration(Messenger messenger, Plugin plugin, String channel, PluginMessageListener listener) {
        Validate.notNull(messenger, "Messenger cannot be null!");
        Validate.notNull(plugin, "Plugin cannot be null!");
        Validate.notNull(channel, "Channel cannot be null!");
        Validate.notNull(listener, "Listener cannot be null!");

        this.messenger = messenger;
        this.plugin = plugin;
        this.channel = channel;
        this.listener = listener;
    }

    /**
     * Gets the plugin channel that this registration is about.
     *
     * @return Plugin channel.
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Gets the registered listener described by this registration.
     *
     * @return Registered listener.
     */
    public PluginMessageListener getListener() {
        return listener;
    }

    /**
     * Gets the plugin that this registration is for.
     *
     * @return Registered plugin.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Checks if this registration is still valid.
     *
     * @return True if this registration is still valid, otherwise false.
     */
    public boolean isValid() {
        return messenger.isRegistrationValid(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PluginMessageListenerRegistration other = (PluginMessageListenerRegistration) obj;
        if (this.messenger != other.messenger && (this.messenger == null || !this.messenger.equals(other.messenger))) {
            return false;
        }
        if (this.plugin != other.plugin && (this.plugin == null || !this.plugin.equals(other.plugin))) {
            return false;
        }
        if ((this.channel == null) ? (other.channel != null) : !this.channel.equals(other.channel)) {
            return false;
        }
        if (this.listener != other.listener && (this.listener == null || !this.listener.equals(other.listener))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.messenger != null ? this.messenger.hashCode() : 0);
        hash = 53 * hash + (this.plugin != null ? this.plugin.hashCode() : 0);
        hash = 53 * hash + (this.channel != null ? this.channel.hashCode() : 0);
        hash = 53 * hash + (this.listener != null ? this.listener.hashCode() : 0);
        return hash;
    }
}
