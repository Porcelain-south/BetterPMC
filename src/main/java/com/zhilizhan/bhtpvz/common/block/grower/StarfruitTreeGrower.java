package com.zhilizhan.bhtpvz.common.block.grower;

import com.zhilizhan.bhtpvz.common.world.BHTPvZFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class StarfruitTreeGrower extends AbstractTreeGrower {
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean largeHive) {
        return BHTPvZFeatures.STARFRUIT;
    }
}
