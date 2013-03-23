package org.bukkit.command.defaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class ScoreboardCommand extends VanillaCommand {

    private static final List<String> MAIN_CHOICES = ImmutableList.of("objectives", "players", "teams");
    private static final List<String> OBJECTIVES_CHOICES = ImmutableList.of("list", "add", "remove", "setdisplay");
    private static final List<String> OBJECTIVES_CRITERIA = ImmutableList.of("health", "playerKillCount", "totalKillCount", "deathCount", "dummy");
    private static final ImmutableMap<String, Scoreboard.DisplaySlot> OBJECTIVES_DISPLAYSLOT = ImmutableMap.of("belowName", Scoreboard.DisplaySlot.BELOW_NAME, "list", Scoreboard.DisplaySlot.PLAYER_LIST, "sidebar", Scoreboard.DisplaySlot.SIDEBAR);
    private static final List<String> PLAYERS_CHOICES = ImmutableList.of("set", "add", "remove", "reset", "list");
    private static final List<String> TEAMS_CHOICES = ImmutableList.of("add", "remove", "join", "leave", "empty", "list", "option");
    private static final List<String> TEAMS_OPTION_CHOICES = ImmutableList.of("color", "friendlyfire", "seeFriendlyInvisibles");
    private static final List<String> TEAMS_OPTION_COLOR;
    private static final List<String> BOOLEAN = ImmutableList.of("true", "false");

    static {
        List<String> colors = new ArrayList<String>();
        for (ChatColor color : ChatColor.values()) {
            if (color.isColor()) {
                colors.add(color.name().toLowerCase());
            }
        }
        colors.add(ChatColor.RESET.name().toLowerCase()); // Mimic vanilla
        TEAMS_OPTION_COLOR = ImmutableList.copyOf(colors);
    }

    public ScoreboardCommand() {
        super("scoreboard");
        this.description = "Scoreboard control";
        this.usageMessage = "/scoreboard";
        this.setPermission("bukkit.command.scoreboard");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender))
            return true;
        if (args.length < 1 || args[0].length() == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /scoreboard <objectives|players|teams>");
            return false;
        }

        if (args[0].equalsIgnoreCase("objectives")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /scoreboard objectives <list|add|remove|setdisplay>");
                return false;
            }
            if (args[1].equalsIgnoreCase("list")) {
                Set<Objective> objectives = Bukkit.getScoreboardManager().getMainScoreboard().getObjectives();
                if (objectives.isEmpty()) {
                    sender.sendMessage(ChatColor.RED + "There are no objectives on the scoreboard");
                    return false;
                }
                sender.sendMessage(ChatColor.DARK_GREEN + "Showing " + objectives.size() + " objective(s) on scoreboard");
                for (Objective objective : objectives) {
                    sender.sendMessage("- " + objective.getName() + ": displays as '" + objective.getDisplayName() + "' and is type '" + objective.getCriteria() + "'");
                }
            } else if (args[1].equalsIgnoreCase("add")) {
                if (args.length < 4) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard objectives add <name> <criteriaType> [display name ...]");
                    return false;
                }
                String name = args[2];
                String criteria = args[3];

                if (criteria == null) {
                    sender.sendMessage(ChatColor.RED + "Invalid objective criteria type. Valid types are: " + stringCollectionToString(OBJECTIVES_CRITERIA));
                } else if (name.length() > 16) {
                    sender.sendMessage(ChatColor.RED + "The name '" + name + "' is too long for an objective, it can be at most 16 characters long");
                } else if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective(name) != null) {
                    sender.sendMessage(ChatColor.RED + "An objective with the name '" + name + "' already exists");
                } else {
                    String displayName = null;
                    if (args.length > 4) {
                        displayName = StringUtils.join(ArrayUtils.subarray(args, 4, args.length), ' ');
                        if (displayName.length() > 32) {
                            sender.sendMessage(ChatColor.RED + "The name '" + displayName + "' is too long for an objective, it can be at most 32 characters long");
                            return false;
                        }
                    }
                    Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().registerObjective(name, criteria);
                    if (displayName != null && displayName.length() > 0) {
                        objective.setDisplayName(displayName);
                    }
                    sender.sendMessage("Added new objective '" + name + "' successfully");
                }
            } else if (args[1].equalsIgnoreCase("remove")) {
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard objectives remove <name>");
                    return false;
                }
                String name = args[2];
                Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(name);
                if (objective == null) {
                    sender.sendMessage(ChatColor.RED + "No objective was found by the name '" + name + "'");
                } else {
                    Bukkit.getScoreboardManager().getMainScoreboard().unregisterObjective(objective);
                    sender.sendMessage("Removed objective '" + name + "' successfully");
                }
            } else if (args[1].equalsIgnoreCase("setdisplay")) {
                if (args.length != 3 && args.length != 4) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard objectives setdisplay <slot> [objective]");
                    return false;
                }
                String slotName = args[2];
                Scoreboard.DisplaySlot slot = OBJECTIVES_DISPLAYSLOT.get(slotName);
                if (slot == null) {
                    sender.sendMessage(ChatColor.RED + "No such display slot '" + slotName + "'");
                } else {
                    Objective objective = null;
                    if (args.length == 4) {
                        String objectiveName = args[3];
                        objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objectiveName);
                        if (objective == null) {
                            sender.sendMessage(ChatColor.RED + "No objective was found by the name '" + objectiveName + "'");
                            return false;
                        }
                    }
                    Bukkit.getScoreboardManager().getMainScoreboard().setDisplaySlot(slot, objective);
                    if (objective == null) {
                        sender.sendMessage("Cleared objective display slot '" + slotName + "'");
                    } else {
                        sender.sendMessage("Set the display objective in slot '" + slotName + "' to '" + objective.getName() + "'");
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("players")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.RED + "/scoreboard players <set|add|remove|reset|list>");
                return false;
            }
            if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) {
                if (args.length != 5) {
                    if (args[1].equalsIgnoreCase("set")) {
                        sender.sendMessage(ChatColor.RED + "/scoreboard players set <player> <objective> <score>");
                    } else if (args[1].equalsIgnoreCase("add")) {
                        sender.sendMessage(ChatColor.RED + "/scoreboard players add <player> <objective> <count>");
                    } else {
                        sender.sendMessage(ChatColor.RED + "/scoreboard players remove <player> <objective> <count>");
                    }
                    return false;
                }
                String objectiveName = args[3];
                Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objectiveName);
                if (objective == null) {
                    sender.sendMessage(ChatColor.RED + "No objective was found by the name '" + objectiveName + "'");
                    return false;
                } else if (!objective.isModifiable()) {
                    sender.sendMessage(ChatColor.RED + "The objective '" + objectiveName + "' is read-only and cannot be set");
                    return false;
                }

                String valueString = args[4];
                int value;
                try {
                    value = Integer.parseInt(valueString);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "'" + valueString + "' is not a valid number");
                    return false;
                }
                if (value < 1 && !args[1].equalsIgnoreCase("set")) {
                    sender.sendMessage(ChatColor.RED + "The number you have entered (" + value + ") is too small, it must be at least 1");
                    return false;
                }

                String playerName = args[2];
                if (playerName.length() > 16) {
                    sender.sendMessage(ChatColor.RED + "'" + playerName + "' is too long for a player name");
                    return false;
                }
                Score score = Bukkit.getScoreboardManager().getMainScoreboard().getScore(objective, Bukkit.getOfflinePlayer(playerName));
                int newScore;
                if (args[1].equalsIgnoreCase("set")) {
                    newScore = value;
                } else if (args[1].equalsIgnoreCase("add")) {
                    newScore = score.getScore() + value;
                } else {
                    newScore = score.getScore() - value;
                }
                score.setScore(newScore);
                sender.sendMessage("Set score of " + objectiveName + " for player " + playerName + " to " + newScore);
            } else if (args[1].equalsIgnoreCase("reset")) {
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard players reset <player>");
                    return false;
                }
                String playerName = args[2];
                if (playerName.length() > 16) {
                    sender.sendMessage(ChatColor.RED + "'" + playerName + "' is too long for a player name");
                    return false;
                }
                Bukkit.getScoreboardManager().getMainScoreboard().resetScores(Bukkit.getOfflinePlayer(playerName));
                sender.sendMessage("Reset all scores of player " + playerName);
            } else if (args[1].equalsIgnoreCase("list")) {
                if (args.length > 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard players list <player>");
                    return false;
                }
                if (args.length == 2) {
                    Set<OfflinePlayer> players = Bukkit.getScoreboardManager().getMainScoreboard().getPlayers();
                    if (players.isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "There are no tracked players on the scoreboard");
                    } else {
                        sender.sendMessage(ChatColor.DARK_GREEN + "Showing " + players.size() + " tracked players on the scoreboard");
                        sender.sendMessage(offlinePlayerSetToString(players));
                    }
                } else {
                    String playerName = args[2];
                    if (playerName.length() > 16) {
                        sender.sendMessage(ChatColor.RED + "'" + playerName + "' is too long for a player name");
                        return false;
                    }
                    Set<Score> scores = Bukkit.getScoreboardManager().getMainScoreboard().getScores(Bukkit.getOfflinePlayer(playerName));
                    if (scores.isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "Player " + playerName + " has no scores recorded");
                    } else {
                        sender.sendMessage(ChatColor.DARK_GREEN + "Showing " + scores.size() + " tracked objective(s) for " + playerName);
                        for (Score score : scores) {
                            sender.sendMessage("- " + score.getObjective().getDisplayName() + ": " + score.getScore() + " (" + score.getObjective().getName() + ")");
                        }
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("teams")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.RED + "/scoreboard teams <list|add|remove|empty|join|leave|option>");
                return false;
            }
            if (args[1].equalsIgnoreCase("list")) {
                if (args.length == 2) {
                    Set<Team> teams = Bukkit.getScoreboardManager().getMainScoreboard().getTeams();
                    if (teams.isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "There are no teams registered on the scoreboard");
                    } else {
                        sender.sendMessage(ChatColor.DARK_GREEN + "Showing " + teams.size() + " teams on the scoreboard");
                        for (Team team : teams) {
                            sender.sendMessage("- " + team.getName() + ": '" + team.getDisplayName() + "' has " + team.getSize() + " players");
                        }
                    }
                } else if (args.length == 3) {
                    String teamName = args[2];
                    Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
                    if (team == null) {
                        sender.sendMessage(ChatColor.RED + "No team was found by the name '" + teamName + "'");
                    } else {
                        Set<OfflinePlayer> players = team.getPlayers();
                        if (players.isEmpty()) {
                            sender.sendMessage(ChatColor.RED + "Team " + team.getName() + " has no players");
                        } else {
                            sender.sendMessage(ChatColor.DARK_GREEN + "Showing " + players.size() + " player(s) in team " + team.getName());
                            sender.sendMessage(offlinePlayerSetToString(players));
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams list [name]");
                    return false;
                }
            } else if (args[1].equalsIgnoreCase("add")) {
                if (args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams add <name> [display name ...]");
                    return false;
                }
                String name = args[2];
                if (name.length() > 16) {
                    sender.sendMessage(ChatColor.RED + "The name '" + name + "' is too long for a team, it can be at most 16 characters long");
                } else if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name) != null) {
                    sender.sendMessage(ChatColor.RED + "A team with the name '" + name + "' already exists");
                } else {
                    String displayName = null;
                    if (args.length > 3) {
                        displayName = StringUtils.join(ArrayUtils.subarray(args, 4, args.length), ' ');
                        if (displayName.length() > 32) {
                            sender.sendMessage(ChatColor.RED + "The display name '" + displayName + "' is too long for a team, it can be at most 32 characters long");
                            return false;
                        }
                    }
                    Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerTeam(name);
                    if (displayName != null && displayName.length() > 0) {
                        team.setDisplayName(displayName);
                    }
                    sender.sendMessage("Added new team '" + team.getName() + "' successfully");
                }
            } else if (args[1].equalsIgnoreCase("remove")) {
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams remove <name>");
                    return false;
                }
                String name = args[2];
                Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name);
                if (team == null) {
                    sender.sendMessage(ChatColor.RED + "No team was found by the name '" + name + "'");
                } else {
                    Bukkit.getScoreboardManager().getMainScoreboard().unregisterTeam(team);
                    sender.sendMessage("Removed team " + team.getName());
                }
            } else if (args[1].equalsIgnoreCase("empty")) {
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams clear <name>");
                    return false;
                }
                String name = args[2];
                Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name);
                if (team == null) {
                    sender.sendMessage(ChatColor.RED + "No team was found by the name '" + name + "'");
                } else {
                    Set<OfflinePlayer> players = team.getPlayers();
                    if (players.isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "Team " + team.getName() + " is already empty, cannot remove nonexistant players");
                    } else {
                        for (OfflinePlayer player : players) {
                            Bukkit.getScoreboardManager().getMainScoreboard().setPlayerTeam(player, null);
                        }
                        sender.sendMessage("Removed all " + players.size() + " player(s) from team " + team.getName());
                    }
                }
            } else if (args[1].equalsIgnoreCase("join")) {
                if ((sender instanceof Player) ? args.length < 3 : args.length < 4) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams join <team> [player...]");
                    return false;
                }
                String teamName = args[2];
                Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
                if (team == null) {
                    sender.sendMessage(ChatColor.RED + "No team was found by the name '" + teamName + "'");
                } else {
                    Set<String> addedPlayers = new HashSet<String>();
                    if ((sender instanceof Player) && args.length == 3) {
                        Bukkit.getScoreboardManager().getMainScoreboard().setPlayerTeam((Player) sender, team);
                        addedPlayers.add(sender.getName());
                    } else {
                        for (int i = 3; i < args.length; i++) {
                            String playerName = args[i];
                            OfflinePlayer offlinePlayer;
                            Player player = Bukkit.getPlayerExact(playerName);
                            if (player != null) {
                                offlinePlayer = player;
                            } else {
                                offlinePlayer = Bukkit.getOfflinePlayer(playerName);
                            }
                            Bukkit.getScoreboardManager().getMainScoreboard().setPlayerTeam(offlinePlayer, team);
                            addedPlayers.add(offlinePlayer.getName());
                        }
                    }
                    String[] playerArray = addedPlayers.toArray(new String[0]);
                    StringBuilder builder = new StringBuilder();
                    for (int x = 0; x < playerArray.length; x++) {
                        if (x == playerArray.length - 1) {
                            builder.append(" and ");
                        } else if (x > 0) {
                            builder.append(", ");
                        }
                        builder.append(playerArray[x]);
                    }
                    sender.sendMessage("Added " + addedPlayers.size() + " player(s) to team " + team.getName() + ": " + builder.toString());
                }
            } else if (args[1].equalsIgnoreCase("leave")) {
                if ((sender instanceof Player) ? args.length < 2 : args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams leave [player...]");
                    return false;
                }
                Set<String> left = new HashSet<String>();
                Set<String> noTeam = new HashSet<String>();
                if ((sender instanceof Player) && args.length == 3) {
                    if (Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam((Player) sender) != null) {
                        Bukkit.getScoreboardManager().getMainScoreboard().setPlayerTeam((Player) sender, null);
                        left.add(sender.getName());
                    } else {
                        noTeam.add(sender.getName());
                    }
                } else {
                    for (int i = 3; i < args.length; i++) {
                        String playerName = args[i];
                        OfflinePlayer offlinePlayer;
                        Player player = Bukkit.getPlayerExact(playerName);
                        if (player != null) {
                            offlinePlayer = player;
                        } else {
                            offlinePlayer = Bukkit.getOfflinePlayer(playerName);
                        }
                        if (Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(offlinePlayer) != null) {
                            Bukkit.getScoreboardManager().getMainScoreboard().setPlayerTeam(offlinePlayer, null);
                            left.add(offlinePlayer.getName());
                        } else {
                            noTeam.add(offlinePlayer.getName());
                        }
                    }
                }
                if (!left.isEmpty()) {
                    sender.sendMessage("Removed " + left.size() + " player(s) from their teams: " + stringCollectionToString(left));
                }
                if (!noTeam.isEmpty()) {
                    sender.sendMessage("Could not remove " + noTeam.size() + " player(s) from their teams: " + stringCollectionToString(noTeam));
                }
            } else if (args[1].equalsIgnoreCase("option")) {
                if (args.length != 4 && args.length != 5) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams option <team> <friendlyfire|color|seefriendlyinvisibles> <value>");
                    return false;
                }
                String teamName = args[2];
                Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
                if (team == null) {
                    sender.sendMessage(ChatColor.RED + "No team was found by the name '" + teamName + "'");
                    return false;
                }
                String option = args[3].toLowerCase();
                if (!option.equals("friendlyfire") && !option.equals("color") && !option.equals("seefriendlyinvisibles")) {
                    sender.sendMessage(ChatColor.RED + "/scoreboard teams option <team> <friendlyfire|color|seefriendlyinvisibles> <value>");
                    return false;
                }
                if (args.length == 4) {
                    if (option.equals("color")) {
                        sender.sendMessage(ChatColor.RED + "Valid values for option color are: " + stringCollectionToString(TEAMS_OPTION_COLOR));
                    } else {
                        sender.sendMessage(ChatColor.RED + "Valid values for option " + option + " are: true and false");
                    }
                } else {
                    String value = args[4].toLowerCase();
                    if (option.equals("color")) {
                        ChatColor color = ChatColor.valueOf(value.toUpperCase());
                        if (color == null) {
                            sender.sendMessage(ChatColor.RED + "Valid values for option color are: " + stringCollectionToString(TEAMS_OPTION_COLOR));
                            return false;
                        }
                        team.setPrefix(color.toString());
                        team.setSuffix(ChatColor.RESET.toString());
                    } else {
                        if (!value.equals("true") && !value.equals("false")) {
                            sender.sendMessage(ChatColor.RED + "Valid values for option " + option + " are: true and false");
                            return false;
                        }
                        if (option.equals("friendlyfire")) {
                            team.setAllowFriendlyFire(value.equals("true"));
                        } else {
                            team.setCanSeeFriendlyInvisibles(value.equals("true"));
                        }
                    }
                    sender.sendMessage("Set option " + option + " for team " + team.getName() + " to " + value);
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /scoreboard <objectives|players|teams>");
            return false;
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], MAIN_CHOICES, new ArrayList<String>());
        }
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("objectives")) {
                if (args.length == 2) {
                    return StringUtil.copyPartialMatches(args[1], OBJECTIVES_CHOICES, new ArrayList<String>());
                }
                if (args[1].equalsIgnoreCase("add")) {
                    if (args.length == 4) {
                        return StringUtil.copyPartialMatches(args[3], OBJECTIVES_CRITERIA, new ArrayList<String>());
                    }
                } else if (args[1].equalsIgnoreCase("remove")) {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], this.getCurrentObjectives(), new ArrayList<String>());
                    }
                } else if (args[1].equalsIgnoreCase("setdisplay")) {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], OBJECTIVES_DISPLAYSLOT.keySet(), new ArrayList<String>());
                    }
                    if (args.length == 4) {
                        return StringUtil.copyPartialMatches(args[3], this.getCurrentObjectives(), new ArrayList<String>());
                    }
                }
            } else if (args[0].equalsIgnoreCase("players")) {
                if (args.length == 2) {
                    return StringUtil.copyPartialMatches(args[1], PLAYERS_CHOICES, new ArrayList<String>());
                }
                if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) {
                    if (args.length == 3) {
                        return super.tabComplete(sender, alias, args);
                    }
                    if (args.length == 4) {
                        return StringUtil.copyPartialMatches(args[3], this.getCurrentObjectives(), new ArrayList<String>());
                    }
                } else {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], this.getCurrentPlayers(), new ArrayList<String>());
                    }
                }
            } else if (args[0].equalsIgnoreCase("teams")) {
                if (args.length == 2) {
                    return StringUtil.copyPartialMatches(args[1], TEAMS_CHOICES, new ArrayList<String>());
                }
                if (args[1].equalsIgnoreCase("join")) {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], this.getCurrentTeams(), new ArrayList<String>());
                    }
                    if (args.length >= 4) {
                        return super.tabComplete(sender, alias, args);
                    }
                } else if (args[1].equalsIgnoreCase("leave")) {
                    return super.tabComplete(sender, alias, args);
                } else if (args[1].equalsIgnoreCase("option")) {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], this.getCurrentTeams(), new ArrayList<String>());
                    }
                    if (args.length == 4) {
                        return StringUtil.copyPartialMatches(args[3], TEAMS_OPTION_CHOICES, new ArrayList<String>());
                    }
                    if (args.length == 5) {
                        if (args[3].equalsIgnoreCase("color")) {
                            return StringUtil.copyPartialMatches(args[4], TEAMS_OPTION_COLOR, new ArrayList<String>());
                        } else {
                            return StringUtil.copyPartialMatches(args[4], BOOLEAN, new ArrayList<String>());
                        }
                    }
                } else {
                    if (args.length == 3) {
                        return StringUtil.copyPartialMatches(args[2], this.getCurrentTeams(), new ArrayList<String>());
                    }
                }
            }
        }

        return ImmutableList.of();
    }

    private static String offlinePlayerSetToString(Set<OfflinePlayer> set) {
        OfflinePlayer[] array = set.toArray(new OfflinePlayer[0]);
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < array.length; x++) {
            if (x == array.length - 1) {
                builder.append(" and ");
            } else if (x > 0) {
                builder.append(", ");
            }
            builder.append(array[x].getName());
        }
        return builder.toString();

    }

    private static String stringCollectionToString(Collection<String> set) {
        String[] array = set.toArray(new String[0]);
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < array.length; x++) {
            if (x > 0) {
                if (x == array.length - 1) {
                    builder.append(" and ");
                } else {
                    builder.append(", ");
                }
            }
            builder.append(array[x]);
        }
        return builder.toString();
    }

    private List<String> getCurrentObjectives() {
        List<String> list = new ArrayList<String>();
        for (Objective objective : Bukkit.getScoreboardManager().getMainScoreboard().getObjectives()) {
            list.add(objective.getName());
        }
        return ImmutableList.copyOf(list);
    }

    private List<String> getCurrentPlayers() {
        List<String> list = new ArrayList<String>();
        for (OfflinePlayer player : Bukkit.getScoreboardManager().getMainScoreboard().getPlayers()) {
            list.add(player.getName());
        }
        return ImmutableList.copyOf(list);
    }

    private List<String> getCurrentTeams() {
        List<String> list = new ArrayList<String>();
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            list.add(team.getName());
        }
        return ImmutableList.copyOf(list);
    }
}
