package org.bukkit.inventory;

import java.util.List;
import java.util.Map;

public interface InventoryTransaction {
    /**
     * Sets the material matching rule for this transaction.
     * @param match Material matching rule.
     * @return This transaction, for chaining.
     * @see Materials
     */
    InventoryTransaction material(Materials match);

    /**
     * @return The material matching rule for this transaction.
     */
    Materials material();

    /**
     * Sets the data matching rule for this transaction.
     * @param match Data matching rule.
     * @return This transaction, for chaining.
     * @see Data
     */
    InventoryTransaction data(Data match);

    /**
     * @return The data matching rule for this transaction.
     */
    Data data();

    /**
     * Sets the amount matching rule for this transaction.
     * @param match Amount matching rule.
     * @return This transaction, for chaining.
     * @see Amount
     */
    InventoryTransaction amount(Amount match);

    /**
     * @return The amount matching rule for this transaction.
     */
    Amount amount();

    /**
     * Sets the enchantment matching rule for this transaction.
     * @param match Enchantment matching rule.
     * @return This transaction, for chaining.
     * @see Enchantments
     */
    InventoryTransaction enchant(Enchantments match);

    /**
     * @return The enchantments matching rule for this transaction.
     */
    Enchantments enchant();

    /**
     * Sets the rule for filling partial stacks in this transaction. This is
     * only used if you're adding items to the inventory.
     * <p>
     * Note: This is not a matching rule, and as such applies even if you provide
     * your own custom matcher.
     * @param rule Stack filling rule.
     * @return This transaction, for chaining.
     * @see Stacks
     */
    InventoryTransaction stacks(Stacks rule);

    /**
     * Sets the maximum stack size to allow when filling partial stacks in this transaction.
     * This overrides the max stack sizes of items and slots.
     * <p>
     * Note: This is not a matching rule, and as such applies even if you provide
     * your own custom matcher.
     * @param size Max stack size
     * @return This transaction, for chaining.
     * @see Stacks
     */
    InventoryTransaction stacks(int size);

    /**
     * Returns a (key, value) pair representing the stack filling rules for this transaction.
     * The key is the specific rule, while the value is the actual maximum stack size if applicable
     * to this rule, or 0 otherwise.
     * @return The stack filling rule for this transaction.
     */
    Map.Entry<Stacks, Integer> stacks();

    /**
     * If the predefined matching rules are not good enough for you, you can
     * provide your own custom Matcher for matching.
     * @param match The matching rule.
     * @return This transaction, for chaining.
     * @see Matcher
     */
    InventoryTransaction matcher(Matcher match);

    /**
     * If you have not set a matcher for the transaction, this will return the default matcher
     * which uses the various enum-based rules to make decisions; this could be useful if you
     * want to write a custom matcher that extends the behaviour of the default matcher.
     * @return The matcher for this transaction.
     */
    Matcher matcher();

    /**
     * Adds some items to the inventory. The returned list is basically the input minus whatever items could actually
     * be fit, including by filling partial slots.
     * @param items The items to add.
     * @return The leftover items that didn't fit in the inventory.
     */
    List<ItemStack> add(ItemStack... items);

    /**
     * Removes some items from the inventory. The returned map contains the items that were actually removed;
     * they may be less than what was requested, and they may also be distributed differently among stacks.
     * It is indexed by the slot the item was removed in; in general it is not guaranteed that each of these slots
     * is now empty, however, since this may remove partial stacks.
     * @param items The items to remove.
     * @return The items that were removed.
     */
    Map<Integer, ItemStack> remove(ItemStack... items);

    /**
     * Searches for some items in the inventory, without removing them. Returns the actual items found,
     * which may be less than requested and distributed differently among stacks. The index is the slot
     * in which the item was found. The items returned by this method are references to the items still
     * in the inventory, so changing the items via methods in ItemStack will change the inventory; however,
     * the map itself is not backed by the inventory, so changing the contents of the map will have no
     * effect on the inventory.
     * @param items The items to find.
     * @return The items found.
     */
    Map<Integer, ItemStack> find(ItemStack... items);

    /**
     * Checks if this inventory contains the specified item.
     * @param item The item to search for.
     * @return True if it exists.
     */
    boolean contains(ItemStack item);

    /**
     * Uses the set matching rules to check if this inventory contains the specified item.
     * @param item The item to search for
     * @return -1 if it does not exist, else the slot number of the first slot containing the item.
     */
    int first(ItemStack item);

    /**
     * Matching rules for checking material.
     */
    enum Materials {
        /**
         * The material must be an exact match.
         */
        EXACT,
        /**
         * If the item is armor or a tool, it suffices that it's the same type of tool.
         * For example, requesting diamond pickaxe will also match gold, iron, stone, or
         * wood pickaxe.
         */
        PARTIAL
    }
    /**
     * Matching rules for checking data/damage values.
     */
    enum Data {
        /**
         * Check data only for items where it's normally significant.
         */
        AUTO,
        /**
         * Check data for all items.
         */
        CHECK,
        /**
         * Ignore data on all items.
         */
        IGNORE
    }
    /**
     * Matching rules for checking stack sizes.
     */
    enum Amount {
        /**
         * Stack size must be exactly the same as requested in order to match.
         */
        EXACT,
        /**
         * Stack size must be the same as or greater than requested in order to match.
         */
        MINIMUM,
        /**
         * Ignore stack size in matching.
         */
        IGNORE
    }
    /**
     * Matching rules for checking enchantments.
     */
    enum Enchantments {
        /**
         * Enchantments must be exactly the same as requested in order to match.
         * That means the item must have all the same enchantments at the same levels,
         * and no additional enchantments.
         */
        EXACT,
        /**
         * Enchantments must be the same as requested in order to match, but the levels
         * may differ. This means the item must have all the same enchantments (not necessarily
         * at the same levels), and no additional enchantments.
         */
        TYPE_EXACT,
        /**
         * All the requested enchantments must be present at the requested levels in order to match,
         * but there may be other enchantments as well and it'll still match.
         */
        PARTIAL,
        /**
         * All the requested enchantments must be present (not necessarily at the requested levels)
         * in order to match; there may also be other enchantments as well which won't prevent it
         * from matching.
         */
        TYPE_PARTIAL,
        /**
         * Ignore enchantments when matching.
         */
        IGNORE
    }
    /**
     * Rules for filling item stacks
     * <p>
     * Note: The following slots have a max stack size of 1:
     * <ul>
     * <li>Armour slots in the player inventory.
     * <li>Potion slots in a brewing stand.
     * <li>The enchanting slot.
     * </ul>
     * These slot-specific maximums may or may not be enforced by these rules.
     */
    enum Stacks {
        /**
         * Uses each item's specific maximum stack size to determine if it'll fit.
         */
        AUTO,
        /**
         * Uses the slot's maximum stack size (usually 64) to determine if it'll fit.
         */
        MAXIMUM,
        /**
         * Uses the absolute maximum stack size, which is {@link Short#MAX_VALUE}.
         */
        OVERFILL,
        /**
         * Uses a specific max stack size that you provide (defaults to 64).
         */
        SPECIFIC
    }
    /**
     * A matcher for more complex matching rules than the predefined ones. Basically all it needs
     * to do is return true if the items should be considered a match, and false if they should not.
     */
    interface Matcher {
        /**
         * @param inInventory The item currently in the inventory that's being checked.
         * @param matchingAgainst The item passed to the transaction that's being compared with.
         * @return True if it should be considered a match.
         */
        boolean match(ItemStack inInventory, ItemStack matchingAgainst);
    }
}
