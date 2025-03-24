package com.zhilizhan.bpmc.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hungteen.pvz.common.block.BlockRegister;
import com.zhilizhan.bpmc.BPMC;
import com.zhilizhan.bpmc.common.block.BHTPvZBlocks;
import com.zhilizhan.bpmc.common.world.feature.HugeOriginMushroomFeature;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class BHTPvZFeatures {
    // 树木
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CHERRY = register("cherry", Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(BHTPvZBlocks.CHERRY_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1)).ignoreVines().build()));// 杨桃树
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> STARFRUIT = register("starfruit", Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(BHTPvZBlocks.STARFRUIT_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1)).ignoreVines().build()));// 杨桃树

    // 树木生成
    public static final ConfiguredFeature<?, ?> TREES_CHERRY = register("trees_cherry", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CHERRY.weighted(1.0F)), CHERRY)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.02f, 1)))); // 樱桃树生成
    public static final ConfiguredFeature<?, ?> TREES_STARFRUIT = register("trees_starfruit", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(STARFRUIT.weighted(1.0F)), STARFRUIT)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.02f, 1)))); // 杨桃树生成

    //原始蘑菇
    public static final ConfiguredFeature<?, ?> ORGIGIN_MUSHROOM = register(
            "origin_mushroom",  new HugeOriginMushroomFeature(BigMushroomFeatureConfig.CODEC).configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(BHTPvZBlocks.ORIGIN_MUSHROOM_BLOCK.get().defaultBlockState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 3)));
    public static final ConfiguredFeature<?, ?> HUGE_ORIGIN_MUSHROOM = register("huge_origin_mushroom", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ORGIGIN_MUSHROOM.weighted(0.1F)), ORGIGIN_MUSHROOM)).decorated(Features.Placements.HEIGHTMAP_SQUARE));

    // 其他生成
    public static final ConfiguredFeature<?, ?> ORE_MORION = register("ore_morion", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BHTPvZBlocks.MORION_ORE.get().defaultBlockState(), 10)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 10, 80)).squared().count(7))); // 黑晶矿生成
    public static final ConfiguredFeature<?, ?> PATCH_SQUASH = register("patch_squash", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BHTPvZBlocks.SQUASH.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 窝瓜生成
    public static final ConfiguredFeature<?, ?> PATCH_TOXIC_SHROOM = register("patch_toxic_shroom", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BlockRegister.TOXIC_SHROOM.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.MYCELIUM.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(32)); // 孢子生成
    public static final ConfiguredFeature<?, ?> PATCH_QUESTION_MARK_POT = register("patch_question_mark_pot", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BHTPvZBlocks.QUESTION_MARK_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 问号罐生成
    public static final ConfiguredFeature<?, ?> PATCH_PLANT_POT = register("patch_plant_pot", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider((BHTPvZBlocks.PLANT_POT.get()).defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32)); // 植物罐生成

    private static  <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BPMC.MOD_ID, id), configuredFeature);
    }
}
