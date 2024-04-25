package com.zhilizhan.bhtpvz.common.effect;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BHTPvZ.MOD_ID);

    public static final RegistryObject<MobEffect> HALITOSIS = MOB_EFFECTS.register("halitosis", ()-> new HalitosisMobEffect(MobEffectCategory.BENEFICIAL, 7259200));// 口臭buff
}
