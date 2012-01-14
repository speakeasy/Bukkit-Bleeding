package org.bukkit.conversations;

/**
 * A ConversationFactory is responsible for creating a {@link Conversation} from a predefined template. A ConversationFactory
 * is typically created when a plugin is instantiated and builds a Conversation each time a user initiates a conversation
 * with the plugin. Each Conversation maintains its own state and calls back as needed into the plugin.
 *
 * The ConversationFactory implements a fluid API, allowing parameters to be set as an extension to the constructor.
 */
public class ConversationFactory {

    private boolean isModal;
    ConversationPrefix prefix;
    int timeout;
    Prompt firstPrompt;

    /**
     * Constructs a ConversationFactory.
     */
    public ConversationFactory()
    {
        isModal = true;
        prefix = new NullConversationPrefix();
        timeout = 600; // 5 minutes
        firstPrompt = Prompt.END_OF_CONVERSATION;
    }

    /**
     * Sets the modality of all {@link Conversation}s created by this factory. If a conversation is modal, all messages
     * directed to the player are suppressed for the duration of the conversation.
     *
     * The default is True.
     * @param modal The modality of all conversations to be created.
     * @return This object.
     */
    public ConversationFactory withModality(boolean modal)
    {
        isModal = modal;
        return this;
    }

    /**
     * Sets the {@link ConversationPrefix} that prepends all output from all generated conversations.
     *
     * The default is a {@link NullConversationPrefix};
     * @param prefix The ConversationPrefix to use.
     * @return This object.
     */
    public ConversationFactory withPrefix(ConversationPrefix prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Sets the number of inactive seconds to wait before automatically abandoning all generated conversations.
     *
     * The default is 600 seconds (5 minutes).
     * @param timeoutSeconds The number of seconds to wait.
     * @return This object.
     */
    public ConversationFactory withTimeout(int timeoutSeconds) {
        this.timeout = timeoutSeconds;
        return this;
    }

    /**
     * Sets the first prompt to use in all generated conversations.
     *
     * The default is Prompt.END_OF_CONVERSATION.
     * @param prompt The first prompt.
     * @return This object.
     */
    public ConversationFactory withFirstPrompt(Prompt prompt) {
        firstPrompt = prompt;
        return this;
    }

    /**
     * Constructs a {@link Conversation} in accordance with the defaults set for this factory.
     * @param forWhom The entity for whom the new conversation is mediating.
     * @return A new conversation.
     */
    public Conversation buildConversation(Conversable forWhom) {
        Conversation conversation = new Conversation(forWhom, firstPrompt);
        conversation.setModal(isModal);
        conversation.setPrefix(prefix);
        conversation.setTimeoutSeconds(timeout);
        return conversation;
    }
}
