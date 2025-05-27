package com.zhilizhan.bpmc;

import com.zhilizhan.bpmc.common.block.BHTPvZBlocks;
import com.zhilizhan.bpmc.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bpmc.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bpmc.common.entity.normal.OriginMoobEntity;
import com.zhilizhan.bpmc.common.event.LivingEvents;
import com.zhilizhan.bpmc.common.impl.BHTPvZPlants;
import com.zhilizhan.bpmc.common.impl.BHTPvZSkill;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.item.BHTPvZSpawnEggItem;
import com.zhilizhan.bpmc.common.sound.BHTPvZSound;
import com.zhilizhan.bpmc.common.world.DecorationGenerate;
import com.zhilizhan.bpmc.common.world.biome.BHTPvZBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(BPMC.MOD_ID)
@Mod.EventBusSubscriber(modid = BPMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BPMC {
    public static final String MOD_ID = "bpmc";

    // 事件总线
    public BPMC() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus bus2 = MinecraftForge.EVENT_BUS;

        BHTPvZEntityTypes.ENTITY_TYPES.register(bus);
        BHTPvZItems.ITEMS.register(bus);
        BHTPvZBlocks.BLOCKS.register(bus);
        BHTPvZMobEffects.MOB_EFFECTS.register(bus);
        BHTPvZBiomes.BIOMES.register(bus);
        BHTPvZPlants.register();
        BHTPvZSkill.SkillType.register();
//        MinecraftForge.EVENT_BUS.register(LivingEvents.class);
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(bus);
        BHTPvZSound.SOUNDS.register(bus);
        //为原始蘑菇牛注册属性
        bus.addListener((EntityAttributeCreationEvent e) -> e.put(BHTPvZEntityTypes.ORIGIN_MOOB.get(), OriginMoobEntity.createAttributes().build()));
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addOresToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addTreesToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addBlocksToBiomes);
        bus2.addListener(LivingEvents::PlayerRightClickItem);
        bus2.addListener(LivingEvents::playerClick);

    }

    // 创造物品栏
    public static final ItemGroup BHTPVZ = new ItemGroup("better_hung_teen_plants_vs_zombies") {
        @Nonnull
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BHTPvZItems.CHERRY.get());
        }
    };

    //初始化刷怪蛋（颜色）
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        BHTPvZSpawnEggItem.initUnaddedEggs();
    }
    public static ResourceLocation prefix(String a) {
        return new ResourceLocation(MOD_ID, a);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BHTPvZBiomes.addBiomeTypes();
            BHTPvZBiomes.addBiomesToGeneration();
        });
    }
}
