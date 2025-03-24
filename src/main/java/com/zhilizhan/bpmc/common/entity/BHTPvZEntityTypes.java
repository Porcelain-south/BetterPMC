package com.zhilizhan.bpmc.common.entity;

import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.zhilizhan.bpmc.BPMC;
import com.zhilizhan.bpmc.client.render.entity.bullet.IceCabbageRender;
import com.zhilizhan.bpmc.client.render.entity.normal.OriginMoobRender;
import com.zhilizhan.bpmc.client.render.entity.plant.assist.GrassCrapRender;
import com.zhilizhan.bpmc.client.render.entity.plant.ice.IceCabbagePultRender;
import com.zhilizhan.bpmc.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bpmc.common.entity.normal.OriginMoobEntity;
import com.zhilizhan.bpmc.common.entity.plant.assist.GrassCarpEntity;
import com.zhilizhan.bpmc.common.entity.plant.ice.IceCabbagePultEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BPMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, BPMC.MOD_ID);

	//植物
	public static final RegistryObject<EntityType<GrassCarpEntity>> GRASS_CARP = registerPlantEntityType(GrassCarpEntity::new, "grass_carp");//草鱼
	public static final RegistryObject<EntityType<IceCabbagePultEntity>> ICE_CABBAGE_PULT = registerPlantEntityType(IceCabbagePultEntity::new, "ice_cabbage_pult"); // 冰卷心菜投手

	// 子弹
	public static final RegistryObject<EntityType<IceCabbageEntity>> ICE_CABBAGE = registerEntityType(IceCabbageEntity::new, "ice_cabbage", EntityClassification.MISC); // 冰卷心菜子弹

	// 普通生物
	public static final RegistryObject<EntityType<OriginMoobEntity>> ORIGIN_MOOB = registerEntityType(OriginMoobEntity::new, "origin_moob", EntityClassification.CREATURE);//起源蘑菇牛


	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		// 普通生物
		RenderingRegistry.registerEntityRenderingHandler(ORIGIN_MOOB.get(), OriginMoobRender::new); // 起源蘑菇牛
	    //植物
		RenderingRegistry.registerEntityRenderingHandler(GRASS_CARP.get(), GrassCrapRender::new); // 草鱼
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE_PULT.get(), IceCabbagePultRender::new); // 冰卷心菜投手
		// 子弹
		RenderingRegistry.registerEntityRenderingHandler(ICE_CABBAGE.get(), IceCabbageRender::new); // 冰卷心菜子弹

	}
	private static <T extends PVZPlantEntity> RegistryObject<EntityType<T>> registerPlantEntityType(EntityType.IFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_PLANT).build(StringUtil.prefix(name).toString()));
	}

	private static <T extends PVZZombieEntity> RegistryObject<EntityType<T>> registerZombieEntityType(EntityType.IFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_ZOMBIE).fireImmune().build(StringUtil.prefix(name).toString()));
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(EntityType.IFactory<T> factory, String name, EntityClassification category){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, category).build(StringUtil.prefix(name).toString()));
	}
}
