package org.bukkit.command.defaults;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.bukkit.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class DifficultyCommand extends VanillaCommand {
    private static final List<String> DIFFICULTY_NAMES = ImmutableList.of("peaceful", "easy", "normal", "hard");

    public DifficultyCommand() {
        super("difficulty");
        this.description = "Sets the game difficulty";
        this.usageMessage = "/difficulty <new difficulty> ";
        this.setPermission("bukkit.command.difficulty");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length != 1 || args[0].length() == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        Difficulty difficulty = getDifficultyForString(sender, args[0]);

        if (Bukkit.isHardcore()) {
            difficulty = Difficulty.HARD;
        }

        Bukkit.getWorlds().get(0).setDifficulty(difficulty);

        int levelCount = 1;
        if (Bukkit.getAllowNether()) {
            Bukkit.getWorlds().get(levelCount).setDifficulty(difficulty);
            levelCount++;
        }

        if (Bukkit.getAllowEnd()) {
            Bukkit.getWorlds().get(levelCount).setDifficulty(difficulty);
        }

        Command.broadcastCommandMessage(sender, "Set difficulty to " + difficulty.toString());
        return true;
    }

    protected Difficulty getDifficultyForString(CommandSender sender, String name) {
        if (name.equalsIgnoreCase("peaceful") || name.equalsIgnoreCase("p")) {
            return Difficulty.PEACEFUL;
        } else if (name.equalsIgnoreCase("easy") || name.equalsIgnoreCase("e")) {
            return Difficulty.EASY;
        } else if (name.equalsIgnoreCase("normal") || name.equalsIgnoreCase("n")) {
            return Difficulty.NORMAL;
        } else if (name.equalsIgnoreCase("hard") || name.equalsIgnoreCase("h")) {
            return Difficulty.HARD;
        } else {
            switch (getInteger(sender, name, 0, 3)) {
                case 0:
                    return Difficulty.PEACEFUL;
                case 1:
                    return Difficulty.EASY;
                case 2:
                    return Difficulty.NORMAL;
                case 3:
                    return Difficulty.HARD;
                default:
                    return null;
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], DIFFICULTY_NAMES, new ArrayList<String>(DIFFICULTY_NAMES.size()));
        }

        return ImmutableList.of();
    }
}
