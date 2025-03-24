package com.zhilizhan.bpmc.common.sound;

import com.zhilizhan.bpmc.BPMC;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BPMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZSound {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BPMC.MOD_ID);
    public static final RegistryObject<SoundEvent> BUZZER = registerSound("buzzer");
    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> {
            return new SoundEvent(BPMC.prefix(name));
        });
    }
}
