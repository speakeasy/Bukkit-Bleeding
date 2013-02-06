package org.bukkit.event.block;

import java.util.List;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.HandlerList;

/**
 * Command Block event
 */
public class CommandBlockCommandEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<String> commands;
    private final BlockCommandSender sender;

    public CommandBlockCommandEvent(final BlockCommandSender sender, final List<String> commands) {
        super(sender.getBlock());
        this.commands = commands;
        this.sender = sender;
    }

    /**
     * Gets the mutable list of commands that the command block is attempting to execute
     *
     * @return Commands the block is attempting to execute
     */
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Get the command sender.
     *
     * @return The sender
     */
    public BlockCommandSender getSender() {
        return sender;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
