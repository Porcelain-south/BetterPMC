package com.zhilizhan.bpmc.common.effect;

import com.zhilizhan.bpmc.BPMC;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZMobEffects {
    public static final DeferredRegister<Effect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BPMC.MOD_ID);

    public static final RegistryObject<Effect> HALITOSIS = MOB_EFFECTS.register("halitosis", ()-> new HalitosisMobEffect(EffectType.BENEFICIAL, 7259200));// 口臭buff
}
