package org.bukkit.event.player;

import java.util.List;

import org.bukkit.entity.Merchant;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.MerchantRecipe;

/**
 * Fires when a player is about to trade with a merchant
 */
public class PlayerMerchantTradeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Merchant merchant;
    private final State state;
    private final List<MerchantRecipe> currentDefaultOffer;
    private final List<MerchantRecipe> previousDefaultOffer;
    private final List<MerchantRecipe> currentOffer;

    public PlayerMerchantTradeEvent(final Player player, final Merchant merchant, List<MerchantRecipe> currentDefaultOffer, List<MerchantRecipe> previousDefaultOffer, List<MerchantRecipe> currentOffer, State state) {
        super(player);
        this.merchant = merchant;
        this.currentDefaultOffer = currentDefaultOffer;
        this.previousDefaultOffer = previousDefaultOffer;
        this.currentOffer = currentOffer;
        this.state = state;
    }

    /**
     * Gets the merchant trading with the player
     * 
     * @return Merchant about to trade with the player
     */
    public Merchant getMerchant() {
        return merchant;
    }

    public List<MerchantRecipe> getCurrentMerchantOffer() {
        return this.currentDefaultOffer;
    }

    public List<MerchantRecipe> getPreviousMerchantOffer() {
        return this.previousDefaultOffer;
    }

    /**
     * This is the one you edit.
     * 
     * @return
     */
    public List<MerchantRecipe> getOffer() {
        return this.currentOffer;
    }

    public State getState() {
        return state;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum State {
        NEW_INTERACTION,
        UPDATED_INTERACTION,
    }
}
