package org.bukkit.permissions.util;

import java.io.File;
import java.io.InputStream;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.util.config.Configuration;

import com.avaje.ebean.EbeanServer;

public class FakePlugin implements Plugin {

    private boolean enabled = false;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        throw new RuntimeException("Not implemented!");
    }

    public File getDataFolder() {
        throw new RuntimeException("Not implemented!");
    }

    public PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile("Fake Plugin", "1.0", "fake.plugin.main");
    }

    public Configuration getConfiguration() {
        throw new RuntimeException("Not implemented!");
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public FileConfiguration getConfig() {
        throw new RuntimeException("Not implemented!");
    }

    public InputStream getResource(String filename) {
        throw new RuntimeException("Not implemented!");
    }

    public void saveConfig() {
        throw new RuntimeException("Not implemented!");
    }

    public void saveDefaultConfig() {
        throw new RuntimeException("Not implemented!");
    }

    public void saveResource(String resourcePath, boolean replace) {
        throw new RuntimeException("Not implemented!");
    }

    public void reloadConfig() {
        throw new RuntimeException("Not implemented!");
    }

    public PluginLoader getPluginLoader() {
        throw new RuntimeException("Not implemented!");
    }

    public Server getServer() {
        throw new RuntimeException("Not implemented!");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void onDisable() {
        throw new RuntimeException("Not implemented!");
    }

    public void onLoad() {
        throw new RuntimeException("Not implemented!");
    }

    public void onEnable() {
        throw new RuntimeException("Not implemented!");
    }

    public boolean isNaggable() {
        throw new RuntimeException("Not implemented!");
    }

    public void setNaggable(boolean canNag) {
        throw new RuntimeException("Not implemented!");
    }

    public EbeanServer getDatabase() {
        throw new RuntimeException("Not implemented!");
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        throw new RuntimeException("Not implemented!");
    }

}
