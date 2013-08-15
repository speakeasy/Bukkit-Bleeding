package org.bukkit.command;

import org.bukkit.block.Block;

public interface BlockCommandSender extends CommandSender {
    /**
     * Returns the block this command sender belongs to
     *
     * @return Block for the command sender
     */
    public Block getBlock();

    /**
     * Sets the output for the command block executing the command. This is
     * stateful, as in, the block will use the last call to this method when
     * determining output.
     * <p>
     * State for successive command invocations is unspecified.
     *
     * @param state the new state for the calling command block
     */
    public void setNextOutputState(boolean state);
}
