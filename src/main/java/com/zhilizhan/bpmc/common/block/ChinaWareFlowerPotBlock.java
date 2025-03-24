package com.zhilizhan.bpmc.common.block;

import com.hungteen.pvz.common.block.special.FlowerPotBlock;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.sound.BHTPvZSound;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;


public class ChinaWareFlowerPotBlock extends FlowerPotBlock {

    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!level.isClientSide && player.getMainHandItem().getItem()== BHTPvZItems.HAMMER.get()) {
            //移除方块
            level.removeBlock(pos, false);

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.VASE_BREAKING.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

            //如果是生存模式添加30tick的CD，否则10tick
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);

                //减少锤子5点耐久
                if(player instanceof ServerPlayerEntity)player.getMainHandItem().hurt(5,level.random, (ServerPlayerEntity) player);
            }
            else
            {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);
            }

            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
