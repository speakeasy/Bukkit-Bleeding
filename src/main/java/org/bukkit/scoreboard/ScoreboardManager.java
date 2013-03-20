package org.bukkit.scoreboard;

/**
 * Manager of Scoreboards
 */
public interface ScoreboardManager {

    /**
     * Gets the primary Scoreboard controlled by the server
     *
     * This Scoreboard is saved by the server
     *
     * The main Scoreboard is affected by the /scoreboard command
     *
     * @return
     */
    Scoreboard getMainScoreboard();

    /**
     * Registers a new Scoreboard to be tracked by the server
     *
     * @return the registered Scoreboard
     */
    Scoreboard registerScoreboard();

    /**
     * Register a Scoreboard to be tracked by the server
     *
     * @param scoreboard Scoreboard to register
     */
    void registerScoreboard(Scoreboard scoreboard);

    /**
     * Stops tracking a scoreboard. Any players viewing this scoreboard will switch to the main scoreboard.
     *
     * @param scoreboard Scoreboard to unregister
     */
    void unregisterScoreboard(Scoreboard scoreboard);
}
