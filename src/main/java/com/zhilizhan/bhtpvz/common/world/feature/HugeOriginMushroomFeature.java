package com.zhilizhan.bhtpvz.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

import java.util.Random;

public class HugeOriginMushroomFeature  extends AbstractHugeMushroomFeature {

    public HugeOriginMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    protected void makeCap(LevelAccessor level, Random random, BlockPos pos, int treeHeight, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config) {
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
                        this.setBlock(level, mutablePos, (BlockState)((BlockState)((BlockState)((BlockState)config.capProvider.getState(random, pos)))));
                    }
                }
            }
        }

    }

    protected int getTreeRadiusForHeight(int i, int j, int foliageRadius, int y) {
        return y <= 3 ? 0 : foliageRadius;
    }

}
