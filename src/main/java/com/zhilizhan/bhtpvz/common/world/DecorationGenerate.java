package com.zhilizhan.bhtpvz.common.world;

import com.hungteen.pvz.common.world.biome.BiomeRegister;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class DecorationGenerate {
    @SubscribeEvent
    public static void addOresToBiomes(BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BHTPvZFeatures.ORE_MORION); // 黑晶矿生成
        }
    }

    @SubscribeEvent
    public static void addTreesToBiomes(BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome != null) {
            if (biome.equals(BiomeRegister.ZEN_GARDEN.get())) {
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.TREES_CHERRY); // 樱桃树生成
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.TREES_STARFRUIT); // 杨桃树生成
                  }else if(biome.equals(BHTPvZBiomes.BEWILDER_GARDEN.get())) {
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.HUGE_ORIGIN_MUSHROOM); // 药水蘑菇生成
                }

        }
    }

    @SubscribeEvent
    public static void addBlocksToBiomes(BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome != null) {
            if (biome.equals(BiomeRegister.ZEN_GARDEN.get())) {
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_SQUASH); // 窝瓜生成
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_QUESTION_MARK_POT); // 问号罐生成
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BHTPvZFeatures.PATCH_PLANT_POT); // 植物罐生成
            }
        }
    }
}
