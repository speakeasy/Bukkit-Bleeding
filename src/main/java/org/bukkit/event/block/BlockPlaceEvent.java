package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a block is placed by a player.
 * <p />
 * If a Block Place event is cancelled, the block will not be placed.
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected boolean cancel;
    protected boolean canBuild;
    @Deprecated protected Block placedAgainst;
    @Deprecated protected BlockState replacedBlockState;
    protected final ItemStack itemInHand;
    protected final Player player;
    protected final BlockState placedBlock;

    public BlockPlaceEvent(final Block block, final BlockState placedBlock, final Player player, final ItemStack itemInHand, final boolean canBuild) {
        super(block);
        this.placedBlock = placedBlock;
        this.player = player;
        this.itemInHand = itemInHand;
        this.canBuild = canBuild;
    }

    @Deprecated
    public BlockPlaceEvent(final Block block, final BlockState replacedBlockState, final Block placedAgainst, final ItemStack itemInHand, final Player thePlayer, final boolean canBuild) {
        super(block);
        this.placedAgainst = placedAgainst;
        this.itemInHand = itemInHand;
        this.player = thePlayer;
        this.replacedBlockState = replacedBlockState;
        this.canBuild = canBuild;
        this.placedBlock = null;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the player who placed the block involved in this event.
     *
     * @return The Player who placed the block involved in this event
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the BlockState of the placed block
     *
     * @return The BlockState of the placed block
     */
    public BlockState getPlacedBlock() {
        return placedBlock;
    }

    /**
     * Clarity method for getting the placed block. Not really needed
     * except for reasons of clarity.
     *
     * @return The Block that was placed
     * @deprecated
     */
    @Deprecated
    public Block getBlockPlaced() {
        return getBlock();
    }

    /**
     * Gets the BlockState for the block which was replaced. Material type air mostly.
     *
     * @return The BlockState for the block which was replaced.
     * @deprecated
     */
    @Deprecated
    public BlockState getBlockReplacedState() {
        return this.replacedBlockState;
    }

    /**
     * Gets the block that this block was placed against
     *
     * @return Block the block that the new block was placed against
     */
    @Deprecated
    public Block getBlockAgainst() {
        return placedAgainst;
    }

    /**
     * Gets the item in the player's hand when they placed the block.
     *
     * @return The ItemStack for the item in the player's hand when they placed the block
     */
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    /**
     * Gets the value whether the player would be allowed to build here.
     * Defaults to spawn if the server was going to stop them (such as, the
     * player is in Spawn). Note that this is an entirely different check
     * than BLOCK_CANBUILD, as this refers to a player, not universe-physics
     * rule like cactus on dirt.
     *
     * @return boolean whether the server would allow a player to build here
     */
    public boolean canBuild() {
        return this.canBuild;
    }

    /**
     * Sets the canBuild state of this event. Set to true if you want the
     * player to be able to build.
     *
     * @param canBuild true if you want the player to be able to build
     */
    public void setBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
