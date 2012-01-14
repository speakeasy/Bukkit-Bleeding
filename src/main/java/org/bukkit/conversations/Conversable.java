package org.bukkit.conversations;

import org.bukkit.command.CommandSender;

/**
 * The Conversable interface is used to indicate objects that can have conversations.
 */
public interface Conversable extends CommandSender {

    /**
     * Tests to see of a Conversable object is actively engaged in a conversation.
     * @return True if a conversation is in progress
     */
    public boolean isConversing();

    /**
     * Accepts input into the active conversation. If no conversation is in progress,
     * this method does nothing.
     * @param input The input message into the conversation
     */
    public void acceptConversationInput(String input);

    /**
     * Enters into a dialog with a Conversation object.
     * @param conversation The conversation to begin
     */
    public void beginConversation(Conversation conversation);

    /**
     * Abandons an active conversation.
     * @param conversation The conversation to abandon
     */
    public void abandonConversation(Conversation conversation);
}
