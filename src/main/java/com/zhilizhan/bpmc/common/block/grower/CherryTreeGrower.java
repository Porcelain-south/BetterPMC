package com.zhilizhan.bpmc.common.block.grower;

import com.zhilizhan.bpmc.common.world.BHTPvZFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class CherryTreeGrower extends Tree {
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean largeHive) {
        return BHTPvZFeatures.CHERRY;
    }
}
