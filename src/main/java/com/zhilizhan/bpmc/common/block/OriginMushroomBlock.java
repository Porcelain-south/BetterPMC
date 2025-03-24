package com.zhilizhan.bpmc.common.block;

import com.zhilizhan.bpmc.common.world.BHTPvZFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class OriginMushroomBlock extends MushroomBlock {
    public OriginMushroomBlock(Properties arg) {
        super(arg);
    }
    public boolean growMushroom(ServerWorld level, BlockPos pos, BlockState state, Random random) {
        level.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature = BHTPvZFeatures.ORGIGIN_MUSHROOM;

        if (configuredfeature.place(level, level.getChunkSource().getGenerator(), random, pos)) {
            return true;
        } else {
            level.setBlock(pos, state, 3);
            return false;
        }
    }
}
