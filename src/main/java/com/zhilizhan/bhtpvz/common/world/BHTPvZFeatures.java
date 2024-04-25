package com.zhilizhan.bhtpvz.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hungteen.pvz.common.block.BlockRegister;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.world.feature.HugeOriginMushroomFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;

public class BHTPvZFeatures {
    // 树木
    public static final ConfiguredFeature<TreeConfiguration, ?> CHERRY = register("cherry", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleStateProvider(BHTPvZBlocks.CHERRY_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));// 杨桃树
    public static final ConfiguredFeature<TreeConfiguration, ?> STARFRUIT = register("starfruit", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleStateProvider(BHTPvZBlocks.STARFRUIT_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));// 杨桃树

    // 树木生成
    public static final ConfiguredFeature<?, ?> TREES_CHERRY = register("trees_cherry", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(CHERRY.weighted(1.0F)), CHERRY)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.02f, 1)))); // 樱桃树生成
    public static final ConfiguredFeature<?, ?> TREES_STARFRUIT = register("trees_starfruit", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(STARFRUIT.weighted(1.0F)), STARFRUIT)).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.02f, 1)))); // 杨桃树生成

    //原始蘑菇
    public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> MUSHROOM = register(
            "mushroom",  new HugeOriginMushroomFeature(HugeMushroomFeatureConfiguration.CODEC).configured(new HugeMushroomFeatureConfiguration(new SimpleStateProvider(BHTPvZBlocks.ORIGIN_MUSHROOM_BLOCK.get().defaultBlockState()), new SimpleStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 3)));
    public static final ConfiguredFeature<?, ?> HUGE_ORIGIN_MUSHROOM = register("huge_origin_mushroom", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(MUSHROOM.weighted(1.0F)), MUSHROOM)).decorated(Features.Decorators.HEIGHTMAP_SQUARE));

    // 其他生成
    public static final ConfiguredFeature<?, ?> ORE_MORION = register("ore_morion", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NETHERRACK, BHTPvZBlocks.MORION_ORE.get().defaultBlockState(), 10)).decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(32, 10, 80)).squared().count(7))); // 黑晶矿生成
    public static final ConfiguredFeature<?, ?> PATCH_SQUASH = register("patch_squash", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider((BHTPvZBlocks.SQUASH.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 窝瓜生成
    public static final ConfiguredFeature<?, ?> PATCH_TOXIC_SHROOM = register("patch_toxic_shroom", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider((BlockRegister.TOXIC_SHROOM.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.MYCELIUM.getBlock())).noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_SQUARE).chance(32)); // 孢子生成
    public static final ConfiguredFeature<?, ?> PATCH_QUESTION_MARK_POT = register("patch_question_mark_pot", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider((BHTPvZBlocks.QUESTION_MARK_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 问号罐生成
    public static final ConfiguredFeature<?, ?> PATCH_PLANT_POT = register("patch_plant_pot", Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider((BHTPvZBlocks.PLANT_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 植物罐生成

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BHTPvZ.MOD_ID, id), configuredFeature);
    }
}
