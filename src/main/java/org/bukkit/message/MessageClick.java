package org.bukkit.message;

import org.apache.commons.lang.Validate;

/**
 * Represents a click action
 */
public final class MessageClick implements Cloneable {
    public static MessageClick ofOpenURL(String url) {
        return forType(Type.OPEN_URL, url);
    }

    public static MessageClick ofRunCommand(String command) {
        return forType(Type.RUN_COMMAND, command);
    }

    public static MessageClick ofSetCommand(String command) {
        return forType(Type.SET_COMMAND, command);
    }

    private static MessageClick forType(Type type, String action) {
        Validate.notEmpty(action); // TODO: Null or Empty?
        return new MessageClick(type, action);
    }

    /**
     * An enum for the various ways text can be clicked
     */
    public enum Type {
        /**
         * Opens specified URL
         */
        OPEN_URL,
        /**
         * Runs specified command
         */
        RUN_COMMAND,
        /**
         * Sets the players text box with the provided text
         */
        SET_COMMAND,
        ;
    }

    private final Type type;
    private final String action;

    private MessageClick(Type type, String action) {
        this.type = type;
        this.action = action;
    }

    public Type getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    @Override
    public MessageClick clone(){
        try {
            return (MessageClick) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new Error(ex);
        }
    }
}
