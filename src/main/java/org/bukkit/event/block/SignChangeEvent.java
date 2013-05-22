package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when a sign is changed by a player.
 * <p>
 * If a Sign Change event is cancelled, the sign will not be changed.
 */
public class SignChangeEvent extends SignDataEvent implements Cancellable {
    private boolean cancel = false;

    public SignChangeEvent(final Block theBlock, final Player thePlayer, final String[] theLines) {
        super(theBlock, thePlayer, theLines);
    }

    /**
     * Sets a single line for the sign involved in this event
     *
     * @param index index of the line to set
     * @param line text to set
     * @throws IndexOutOfBoundsException thrown when the provided index is > 4 and < 0
     */
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
        lines[index] = line;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
