package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class Hammer extends TieredItem {
    public Hammer(Properties properties) {
        super(Tiers.WOOD, properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //挥空cd
        player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);

        //播放音效
        player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        //检查是否有CD
        if (player.getCooldowns().isOnCooldown(BHTPvZItems.HAMMER.get())){
            //如果有CD则结束方法
            return InteractionResult.FAIL;
        }
        else if (interactionTarget instanceof PVZZombieEntity) {
            //对PVZ僵尸照成20点伤害
            interactionTarget.hurt(DamageSource.playerAttack(player), 20.0F);

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.HAMMER_BONK.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            //减少锤子5点耐久
            stack.hurtAndBreak(5, interactionTarget, (arg) -> arg.broadcastBreakEvent(player.getUsedItemHand()));

            //如果是生存模式添加30tick的CD，否则10tick
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);
            }
            else
            {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);
            }
            return InteractionResult.SUCCESS;
        }
        //挥空cd
        player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);

        //播放音效
        player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS;
    }

    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslatableComponent("tooltip.bhtpvz.hammer.use")).withStyle(ChatFormatting.GOLD));
    }
}
