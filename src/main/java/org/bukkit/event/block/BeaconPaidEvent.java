package org.bukkit.event.block;

import java.util.Collection;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

import com.google.common.collect.ImmutableList;

/**
 * This event is called when a player pays to change a beacon. The payment
 * will only be removed after the event if it is not cancelled, so it will
 * still be in the {@link org.bukkit.inventory.BeaconInventory}.
 */
public class BeaconPaidEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private ImmutableList<PotionEffect> effects;
    private Player player;
    private boolean cancelled = false;

    public BeaconPaidEvent(final Block block, Player player, Collection<PotionEffect> newEffects) {
        super(block);
        this.player = player;
        effects = ImmutableList.copyOf(newEffects);
    }

    /**
     * Get the player that performed the transaction.
     *
     * @return the triggering player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the PotionEffects chosen by the player. (These have already been
     * validated.)
     *
     * @return the PotionEffects to apply to the beacon
     */
    public List<PotionEffect> getEffects() {
        return effects;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
