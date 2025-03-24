package com.zhilizhan.bpmc.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum BHTPvZTier implements IItemTier {
   DAMSON_CRYSTAL(4, 2031, 9.0f, 4.0f, 15, () -> Ingredient.of(BHTPvZItems.DAMSON_CRYSTAL_INGOT.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    BHTPvZTier(int i, int j, float f, float g, int k, Supplier<Ingredient> supplier) {
        this.level = i;
        this.uses = j;
        this.speed = f;
        this.damage = g;
        this.enchantmentValue = k;
        this.repairIngredient = new LazyValue<>(supplier);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
