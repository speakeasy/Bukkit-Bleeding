package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlaySoundCommand extends VanillaCommand {
    public PlaySoundCommand() {
        super("playsound");
        this.description = "Plays a sound to a given player";
        this.usageMessage = "/playsound <sound> <player> [x] [y] [z] [volume] [pitch] [minimumVolume]";
        this.setPermission("bukkit.command.playsound");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) {
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }
        String soundArg = args[0];
        String playerArg = args[1];

        Player player = Bukkit.getPlayerExact(playerArg);
        if (player != null) {
            double x = Math.floor(player.getLocation().getX());
            double y = Math.floor(player.getLocation().getY() + 0.5D);
            double z = Math.floor(player.getLocation().getZ());
            double volume = 1.0D;
            double pitch = 1.0D;
            double minimumVolume = 0.0D;

            int i = 2;
            if (args.length > i) {
                x = getDouble(sender, args[i++]);
            }
            if (args.length > i) {
                y = getDouble(sender, args[i++]);
            }
            if (args.length > i) {
                z = getDouble(sender, args[i++]);
            }
            if (args.length > i) {
                volume = getDouble(sender, args[i++], 0.0D, Float.MAX_VALUE);
            }
            if (args.length > i) {
                pitch = getDouble(sender, args[i++], 0.0D, 2.0D);
            }
            if (args.length > i) {
                minimumVolume = getDouble(sender, args[i++], 0.0D, 1.0D);
            }

            double fixedVolume = volume > 1.0D ? volume * 16.0D : 16.0D;
            Location soundLocation = new Location(player.getWorld(), x, y, z);
            if (player.getLocation().distance(soundLocation) > fixedVolume) {
                if (minimumVolume <= 0.0D) {
                    sender.sendMessage(ChatColor.RED + String.format("%s is too far away to hear the sound", playerArg));
                    return false;
                }

                double playerX = player.getLocation().getX();
                double playerY = player.getLocation().getY();
                double playerZ = player.getLocation().getZ();
                double distanceX = x - playerX;
                double distanceY = y - playerY;
                double distanceZ = z - playerZ;
                double d = Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);

                if (d > 0.0D) {
                    playerX += distanceX / d * 2.0D;
                    playerY += distanceY / d * 2.0D;
                    playerZ += distanceZ / d * 2.0D;
                }

                soundLocation = new Location(player.getWorld(), playerX, playerY, playerZ);

                player.playSound(soundLocation, soundArg, (float) minimumVolume, (float) pitch);
            } else {
                player.playSound(soundLocation, soundArg, (float) volume, (float) pitch);
            }
            sender.sendMessage(String.format("Played '%s' to %s", soundArg, playerArg));
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + String.format("Can't find player %s", playerArg));
            return false;
        }
    }
}
