package com.zhilizhan.bpmc.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractBigMushroomFeature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;

import java.util.Random;

public class HugeOriginMushroomFeature extends AbstractBigMushroomFeature {

    public HugeOriginMushroomFeature(Codec<BigMushroomFeatureConfig> codec) {
        super(codec);
    }

    protected void makeCap(IWorld level, Random random, BlockPos pos, int treeHeight, BlockPos.Mutable mutablePos, BigMushroomFeatureConfig config) {
        int i = config.foliageRadius;

        for(int j = -i; j <= i; ++j) {
            for(int k = -i; k <= i; ++k) {
                boolean flag = j == -i;
                boolean flag1 = j == i;
                boolean flag2 = k == -i;
                boolean flag3 = k == i;
                boolean flag4 = flag || flag1;
                boolean flag5 = flag2 || flag3;
                if (!flag4 || !flag5) {
                    mutablePos.setWithOffset(pos, j, treeHeight, k);
                    if (level.getBlockState(mutablePos).canBeReplacedByLeaves(level, mutablePos)) {
                        this.setBlock(level, mutablePos, config.capProvider.getState(random, pos));
                    }
                }
            }
        }

    }

    protected int getTreeRadiusForHeight(int i, int j, int foliageRadius, int y) {
        return y <= 3 ? 0 : foliageRadius;
    }
}