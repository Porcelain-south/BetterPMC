package com.zhilizhan.bhtpvz.common.world.biome;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.world.BHTPvZFeatures;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BHTPvZ.MOD_ID);
    public static final RegistryObject<Biome> NIGHT = BIOMES.register("night", BHTPvZBiomes::nightBiome);
    public static final RegistryObject<Biome> BEWILDER_GARDEN = BIOMES.register("bewilder_garden", BHTPvZBiomes::BewilderGardenBiome);
    public static final ResourceKey<Biome> NIGHT_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BHTPvZ.MOD_ID, "night"));
    public static final ResourceKey<Biome> BEWILDER_GARDEN_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BHTPvZ.MOD_ID, "bewilder_garden"));

    private static Biome biome(Biome.BiomeCategory category, Biome.Precipitation rainType, float depth, float scale, float temperature, float downFall, BiomeSpecialEffects effect, MobSpawnSettings spawnSettings, BiomeGenerationSettings generateSettings) {
        return new Biome.BiomeBuilder().biomeCategory(category).precipitation(rainType).depth(depth).scale(scale).temperature(temperature).downfall(downFall).specialEffects(effect).mobSpawnSettings(spawnSettings).generationSettings(generateSettings).build();
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(NIGHT_KEY, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BEWILDER_GARDEN_KEY, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }

    public static void addBiomesToGeneration() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(NIGHT_KEY, 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BEWILDER_GARDEN_KEY, 10));
    }

    public static Biome nightBiome() {
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(2630949).fogColor(2630949).skyColor(2630949).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.setPlayerCanSpawn();
        spawnSettings.addMobCharge(EntityRegister.GIGA_TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addMobCharge(EntityRegister.TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.MOOSHROOM, 8, 4, 8));
        BiomeGenerationSettings.Builder biomeGenerationSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurfaceBuilders.MYCELIUM);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD); //生成末地传送门
        BiomeDefaultFeatures.addMushroomFieldVegetation(biomeGenerationSettings); //生成大蘑菇
        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettings); //生成蘑菇
        BiomeDefaultFeatures.addDefaultCarvers(biomeGenerationSettings); //生成矿洞和峡谷
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings); //生成矿石
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_TOXIC_SHROOM); //生成孢子
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_QUESTION_MARK_POT); //生成问号罐
        return biome(Biome.BiomeCategory.PLAINS, Biome.Precipitation.RAIN, 0.125f, 0.25f, 0.8f, 0.4f, effects.build(), spawnSettings.build(), biomeGenerationSettings.build());
    }

    public static Biome BewilderGardenBiome() {
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder().fogColor(0xF0FFF0).waterColor(0xF0FFF0).waterFogColor(0x8A8997).skyColor(0xF0FFF0).foliageColorOverride(0x66CDAA).grassColorOverride(0x66CDAA).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.setPlayerCanSpawn();
        spawnSettings.addMobCharge(EntityRegister.GIGA_TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addMobCharge(EntityRegister.TOMB_STONE.get(),1,0);//把墓碑Ban了
//        spawnSettings.addMobCharge(BHTPvZEntityTypes.ORIGIN_MOOB.get(),1,0);//蘑菇牛
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BHTPvZEntityTypes.ORIGIN_MOOB.get(), 10, 1, 3));
        BiomeGenerationSettings.Builder biomeGenerationSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurfaceBuilders.GRASS);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD); //生成末地传送门
        BiomeDefaultFeatures.addBadlandGrass(biomeGenerationSettings); //杂草
        BiomeDefaultFeatures.addDefaultFlowers(biomeGenerationSettings); //生成花
        BiomeDefaultFeatures.addDefaultCarvers(biomeGenerationSettings); //生成矿洞和峡谷
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings); //生成矿石
       return biome(Biome.BiomeCategory.PLAINS, Biome.Precipitation.RAIN, 0.125f, 0.25f, 0.8f, 0.4f, effects.build(), spawnSettings.build(), biomeGenerationSettings.build());
    }
}
