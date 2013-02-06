package org.bukkit.block;

/**
 * Represents a command block.
 */
public interface CommandBlock extends BlockState {

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand();

    /**
     * Sets the command.
     *
     * @param command The new command.
     */
    public void setCommand(String command);

    /**
     * Execute the command block.
     */
    public void execute();
}
