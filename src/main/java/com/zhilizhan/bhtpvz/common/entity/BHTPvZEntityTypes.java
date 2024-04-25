package com.zhilizhan.bhtpvz.common.entity;

import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.render.entity.normal.OriginMoobRender;
import com.zhilizhan.bhtpvz.client.render.entity.plant.assist.GrassCrapRender;
import com.zhilizhan.bhtpvz.common.entity.normal.OriginMoobEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.GrassCarpEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, BHTPvZ.MOD_ID);


	// 普通生物
	public static final RegistryObject<EntityType<OriginMoobEntity>> ORIGIN_MOOB = registerEntityType(OriginMoobEntity::new, "origin_moob",MobCategory.CREATURE);//起源蘑菇牛

	public static final RegistryObject<EntityType<GrassCarpEntity>> GRASS_CARP = registerPlantEntityType(GrassCarpEntity::new, "grass_carp");//草鱼

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		// 普通生物
		RenderingRegistry.registerEntityRenderingHandler(ORIGIN_MOOB.get(), OriginMoobRender::new); // 起源蘑菇牛
	    //植物
		RenderingRegistry.registerEntityRenderingHandler(GRASS_CARP.get(), GrassCrapRender::new); // 草鱼

	}
	private static <T extends PVZPlantEntity> RegistryObject<EntityType<T>> registerPlantEntityType(EntityType.EntityFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_PLANT).build(StringUtil.prefix(name).toString()));
	}

	private static <T extends PVZZombieEntity> RegistryObject<EntityType<T>> registerZombieEntityType(EntityType.EntityFactory<T> factory, String name){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_ZOMBIE).fireImmune().build(StringUtil.prefix(name).toString()));
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(EntityType.EntityFactory<T> factory, String name, MobCategory category){
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, category).build(StringUtil.prefix(name).toString()));
	}
}
