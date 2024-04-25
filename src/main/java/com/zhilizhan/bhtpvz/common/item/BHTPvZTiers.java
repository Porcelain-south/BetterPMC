package com.zhilizhan.bhtpvz.common.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BHTPvZTiers {
    public static final Tier DAMSON_CRYSTAL = new BHTPvZTier(4, 2031, 9.0f, 4.0f, 15, () -> Ingredient.of(BHTPvZItems.DAMSON_CRYSTAL_INGOT.get()));
}
