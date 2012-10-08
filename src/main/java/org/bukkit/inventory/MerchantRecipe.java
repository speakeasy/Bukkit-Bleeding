package org.bukkit.inventory;

import org.apache.commons.lang.Validate;

public class MerchantRecipe implements Recipe {

    private ItemStack buyingItem1;
    private ItemStack buyingItem2;
    private ItemStack sellingItem;
    private int uses;

    public MerchantRecipe(ItemStack buyingItem1, ItemStack sellingItem, int uses) {
        this(buyingItem1, null, sellingItem, uses);
    }

    public MerchantRecipe(ItemStack buyingItem1, ItemStack sellingItem) {
        this(buyingItem1, null, sellingItem, 0);
    }

    public MerchantRecipe(ItemStack buyingItem1, ItemStack buyingItem2, ItemStack sellingItem) {
        this(buyingItem1, buyingItem2, sellingItem, 0);
    }

    public MerchantRecipe(ItemStack buyingItem1, ItemStack buyingItem2, ItemStack sellingItem, int uses) {
        Validate.notNull(buyingItem1, "MerchantRecipe must have a valid first buying item");
        Validate.notNull(sellingItem, "MerchantRecipe must have a valid selling item");
        this.buyingItem1 = buyingItem1;
        this.buyingItem2 = buyingItem2;
        this.sellingItem = sellingItem;
        this.uses = uses;
    }

    public ItemStack getResult() {
        return this.sellingItem != null ? sellingItem.clone() : null;
    }

    public ItemStack getBuyingItem1() {
        return this.buyingItem1 != null ? this.buyingItem1.clone() : null;
    }

    public ItemStack getBuyingItem2() {
        return this.buyingItem2 != null ? this.buyingItem2.clone() : null;
    }

    public boolean hasSecondItem() {
        return this.buyingItem2 != null;
    }

    public int getUses() {
        return this.uses;
    }

    @Override
    public MerchantRecipe clone() {
        return new MerchantRecipe(this.getBuyingItem1(), this.getBuyingItem2(), this.getResult(), this.getUses());
    }
}
