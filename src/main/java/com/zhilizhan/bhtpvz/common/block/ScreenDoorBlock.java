package com.zhilizhan.bhtpvz.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class ScreenDoorBlock extends DoorBlock {

    public ScreenDoorBlock(BlockBehaviour.Properties arg) {
        super(arg);
    }

    private int getCloseSound() {
        return this.material == Material.METAL ? 1011 : 1012;
    }

    private int getOpenSound() {
        return this.material == Material.METAL ? 1005 : 1006;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        {
            state = state.cycle(OPEN);
            level.setBlock(pos, state, 10);
            level.levelEvent(player, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }


}
