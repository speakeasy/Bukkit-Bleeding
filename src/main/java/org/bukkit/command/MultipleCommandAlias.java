package org.bukkit.command;

/**
 * Represents a command that delegates to one or more other commands
 */
public class MultipleCommandAlias extends Command {
    private Command[] commands;
    private String[][] arguments;

    public MultipleCommandAlias(String name, Command[] commands) {
        this(name, commands, new String[commands.length][0]);
    }

    public MultipleCommandAlias(String name, Command[] commands, String[][] arguments) {
        super(name);
        this.commands = commands;
        this.arguments = arguments;
    }

    public Command[] getCommands() {
        return commands;
    }

    public String[][] getArguments() {
        return arguments;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        boolean result = false;

        for (int i = 0; i < commands.length; i++) {
            String[] prepend = arguments[i];
            String[] combined = new String[prepend.length + args.length];
            System.arraycopy(prepend, 0, combined, 0, prepend.length);
            System.arraycopy(args, 0, combined, prepend.length, args.length);
            result |= commands[i].execute(sender, commandLabel, combined);
        }

        return result;
    }
}
