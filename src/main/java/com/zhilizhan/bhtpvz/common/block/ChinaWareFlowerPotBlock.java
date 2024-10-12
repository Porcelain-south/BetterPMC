package com.zhilizhan.bhtpvz.common.block;
import com.hungteen.pvz.common.block.special.FlowerPotBlock;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ChinaWareFlowerPotBlock extends FlowerPotBlock {
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!level.isClientSide && player.getMainHandItem().getItem()== BHTPvZItems.HAMMER.get()) {
            //移除方块
            level.removeBlock(pos, false);

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.VASE_BREAKING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            //如果是生存模式添加30tick的CD，否则10tick
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);

                //减少锤子5点耐久
                if(player instanceof ServerPlayer)player.getMainHandItem().hurt(5,level.random, (ServerPlayer) player);
            }
            else
            {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
