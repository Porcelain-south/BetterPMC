package com.zhilizhan.bhtpvz.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WisdomLog extends RotatedPillarBlock {

    public WisdomLog(Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND && worldIn.random.nextInt(21) == 0) {
            player.sendMessage(new TranslatableComponent( "block.bhtpvz.wisdom_log").withStyle(ChatFormatting.YELLOW).append(":") , Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent( "block.bhtpvz.wisdom_log_say_1").withStyle(ChatFormatting.GREEN) , Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent("block.bhtpvz.wisdom_log_say_2").withStyle(ChatFormatting.GREEN) , Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent("block.bhtpvz.wisdom_log_say_3").withStyle(ChatFormatting.GREEN) , Util.NIL_UUID);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}
