package org.bukkit.permissions.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import com.avaje.ebean.config.ServerConfig;

public class FakeServer implements Server {

    private final SimpleCommandMap commandMap    = new SimpleCommandMap(this);
    private final PluginManager    pluginManager = new SimplePluginManager(this, commandMap);

    public String getName() {
        return "FakeServer for Permissions Testing";
    }

    public String getVersion() {
        return "1.0.1";
    }

    public String getBukkitVersion() {
        return "1.0.1";
    }

    public Player[] getOnlinePlayers() {

        throw new RuntimeException("Not implemented!");
    }

    public int getMaxPlayers() {

        throw new RuntimeException("Not implemented!");
    }

    public int getPort() {

        throw new RuntimeException("Not implemented!");
    }

    public int getViewDistance() {

        throw new RuntimeException("Not implemented!");
    }

    public String getIp() {

        throw new RuntimeException("Not implemented!");
    }

    public String getServerName() {

        throw new RuntimeException("Not implemented!");
    }

    public String getServerId() {

        throw new RuntimeException("Not implemented!");
    }

    public boolean getAllowEnd() {

        throw new RuntimeException("Not implemented!");
    }

    public boolean getAllowNether() {

        throw new RuntimeException("Not implemented!");
    }

    public boolean hasWhitelist() {

        throw new RuntimeException("Not implemented!");
    }

    public void setWhitelist(boolean value) {

        throw new RuntimeException("Not implemented!");
    }

    public Set<OfflinePlayer> getWhitelistedPlayers() {

        throw new RuntimeException("Not implemented!");
    }

    public void reloadWhitelist() {

        throw new RuntimeException("Not implemented!");
    }

    public int broadcastMessage(String message) {

        throw new RuntimeException("Not implemented!");
    }

    public String getUpdateFolder() {

        throw new RuntimeException("Not implemented!");
    }

    public File getUpdateFolderFile() {

        throw new RuntimeException("Not implemented!");
    }

    public Player getPlayer(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public Player getPlayerExact(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public List<Player> matchPlayer(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public BukkitScheduler getScheduler() {

        throw new RuntimeException("Not implemented!");
    }

    public ServicesManager getServicesManager() {

        throw new RuntimeException("Not implemented!");
    }

    public List<World> getWorlds() {

        throw new RuntimeException("Not implemented!");
    }

    public World createWorld(String name, Environment environment) {

        throw new RuntimeException("Not implemented!");
    }

    public World createWorld(String name, Environment environment, long seed) {

        throw new RuntimeException("Not implemented!");
    }

    public World createWorld(String name, Environment environment, ChunkGenerator generator) {

        throw new RuntimeException("Not implemented!");
    }

    public World createWorld(String name, Environment environment, long seed, ChunkGenerator generator) {

        throw new RuntimeException("Not implemented!");
    }

    public World createWorld(WorldCreator creator) {

        throw new RuntimeException("Not implemented!");
    }

    public boolean unloadWorld(String name, boolean save) {

        throw new RuntimeException("Not implemented!");
    }

    public boolean unloadWorld(World world, boolean save) {

        throw new RuntimeException("Not implemented!");
    }

    public World getWorld(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public World getWorld(UUID uid) {

        throw new RuntimeException("Not implemented!");
    }

    public MapView getMap(short id) {

        throw new RuntimeException("Not implemented!");
    }

    public MapView createMap(World world) {

        throw new RuntimeException("Not implemented!");
    }

    public void reload() {

        throw new RuntimeException("Not implemented!");
    }

    public Logger getLogger() {
        return Logger.getLogger("Minecraft");
    }

    public PluginCommand getPluginCommand(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public void savePlayers() {

        throw new RuntimeException("Not implemented!");
    }

    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {

        throw new RuntimeException("Not implemented!");
    }

    public void configureDbConfig(ServerConfig config) {

        throw new RuntimeException("Not implemented!");
    }

    public boolean addRecipe(Recipe recipe) {

        throw new RuntimeException("Not implemented!");
    }

    public Map<String, String[]> getCommandAliases() {

        throw new RuntimeException("Not implemented!");
    }

    public int getSpawnRadius() {

        throw new RuntimeException("Not implemented!");
    }

    public void setSpawnRadius(int value) {

        throw new RuntimeException("Not implemented!");
    }

    public boolean getOnlineMode() {

        throw new RuntimeException("Not implemented!");
    }

    public boolean getAllowFlight() {

        throw new RuntimeException("Not implemented!");
    }

    public void shutdown() {

        throw new RuntimeException("Not implemented!");
    }

    public int broadcast(String message, String permission) {

        throw new RuntimeException("Not implemented!");
    }

    public OfflinePlayer getOfflinePlayer(String name) {

        throw new RuntimeException("Not implemented!");
    }

    public Set<String> getIPBans() {

        throw new RuntimeException("Not implemented!");
    }

    public void banIP(String address) {

        throw new RuntimeException("Not implemented!");
    }

    public void unbanIP(String address) {

        throw new RuntimeException("Not implemented!");
    }

    public Set<OfflinePlayer> getBannedPlayers() {

        throw new RuntimeException("Not implemented!");
    }

    public Set<OfflinePlayer> getOperators() {

        throw new RuntimeException("Not implemented!");
    }

    public GameMode getDefaultGameMode() {

        throw new RuntimeException("Not implemented!");
    }

    public void setDefaultGameMode(GameMode mode) {

        throw new RuntimeException("Not implemented!");
    }

    public ConsoleCommandSender getConsoleSender() {

        throw new RuntimeException("Not implemented!");
    }

    public File getWorldContainer() {

        throw new RuntimeException("Not implemented!");
    }

    public OfflinePlayer[] getOfflinePlayers() {

        throw new RuntimeException("Not implemented!");
    }

}
