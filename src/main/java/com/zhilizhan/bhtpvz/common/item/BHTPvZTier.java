package com.zhilizhan.bhtpvz.common.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class
BHTPvZTier implements Tier {
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    protected BHTPvZTier(int i, int j, float f, float g, int k, Supplier<Ingredient> supplier) {
        this.level = i;
        this.uses = j;
        this.speed = f;
        this.damage = g;
        this.enchantmentValue = k;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
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
