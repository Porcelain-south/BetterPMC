package com.zhilizhan.bhtpvz.common.block;

import com.zhilizhan.bhtpvz.common.world.BHTPvZFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class OriginMushroomBlock extends MushroomBlock {
    public OriginMushroomBlock(Properties arg) {
        super(arg);
    }
    public boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, Random random) {
        level.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature = BHTPvZFeatures.MUSHROOM;

        if (configuredfeature.place(level, level.getChunkSource().getGenerator(), random, pos)) {
            return true;
        } else {
            level.setBlock(pos, state, 3);
            return false;
        }
    }
}
