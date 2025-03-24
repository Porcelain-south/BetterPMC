package com.zhilizhan.bpmc.common.world.biome;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.zhilizhan.bpmc.BPMC;
import com.zhilizhan.bpmc.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bpmc.common.world.BHTPvZFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BPMC.MOD_ID);
    public static final RegistryObject<Biome> NIGHT = BIOMES.register("night", BHTPvZBiomes::nightBiome);
    public static final RegistryObject<Biome> BEWILDER_GARDEN = BIOMES.register("bewilder_garden", BHTPvZBiomes::BewilderGardenBiome);
    public static final RegistryKey<Biome> NIGHT_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BPMC.MOD_ID, "night"));
    public static final RegistryKey<Biome> BEWILDER_GARDEN_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BPMC.MOD_ID, "bewilder_garden"));

    private static Biome biome(Biome.Category category, Biome.RainType rainType, float depth, float scale, float temperature, float downFall, BiomeAmbience effect, MobSpawnInfo spawnSettings, BiomeGenerationSettings generateSettings) {
        return new Biome.Builder().biomeCategory(category).precipitation(rainType).depth(depth).scale(scale).temperature(temperature).downfall(downFall).specialEffects(effect).mobSpawnSettings(spawnSettings).generationSettings(generateSettings).build();
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
        BiomeAmbience.Builder effects = new BiomeAmbience.Builder().waterColor(4159204).waterFogColor(2630949).fogColor(2630949).skyColor(2630949).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);
        MobSpawnInfo.Builder spawnSettings = new MobSpawnInfo.Builder();
        spawnSettings.setPlayerCanSpawn();
        spawnSettings.addMobCharge(EntityRegister.GIGA_TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addMobCharge(EntityRegister.TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.MOOSHROOM, 8, 4, 8));
        BiomeGenerationSettings.Builder biomeGenerationSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.MYCELIUM);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD); //生成末地传送门
        DefaultBiomeFeatures.addMushroomFieldVegetation(biomeGenerationSettings); //生成大蘑菇
        DefaultBiomeFeatures.addDefaultMushrooms(biomeGenerationSettings); //生成蘑菇
        DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings); //生成矿洞和峡谷
        DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings); //生成矿石
        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_TOXIC_SHROOM); //生成孢子
        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_QUESTION_MARK_POT); //生成问号罐
        return biome(Biome.Category.PLAINS, Biome.RainType.RAIN, 0.125f, 0.25f, 0.8f, 0.4f, effects.build(), spawnSettings.build(), biomeGenerationSettings.build());
    }

    public static Biome BewilderGardenBiome() {
        BiomeAmbience.Builder effects = new BiomeAmbience.Builder().fogColor(0xF0FFF0).waterColor(0xF0FFF0).waterFogColor(0x8A8997).skyColor(0xF0FFF0).foliageColorOverride(0x66CDAA).grassColorOverride(0x66CDAA).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);
        MobSpawnInfo.Builder spawnSettings = new MobSpawnInfo.Builder();
        spawnSettings.setPlayerCanSpawn();
        spawnSettings.addMobCharge(EntityRegister.GIGA_TOMB_STONE.get(),1,0);//把墓碑Ban了
        spawnSettings.addMobCharge(EntityRegister.TOMB_STONE.get(),1,0);//把墓碑Ban了
//        spawnSettings.addMobCharge(BHTPvZEntityTypes.ORIGIN_MOOB.get(),1,0);//蘑菇牛
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(BHTPvZEntityTypes.ORIGIN_MOOB.get(), 10, 1, 3));
        BiomeGenerationSettings.Builder biomeGenerationSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD); //生成末地传送门
        DefaultBiomeFeatures.addBadlandGrass(biomeGenerationSettings); //杂草
        DefaultBiomeFeatures.addDefaultFlowers(biomeGenerationSettings); //生成花
        DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings); //生成矿洞和峡谷
        DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings); //生成矿石
       return biome(Biome.Category.PLAINS, Biome.RainType.RAIN, 0.125f, 0.25f, 0.8f, 0.4f, effects.build(), spawnSettings.build(), biomeGenerationSettings.build());
    }
}
