package com.zhilizhan.bpmc.common.item;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bpmc.common.sound.BHTPvZSound;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.TieredItem;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class Hammer extends TieredItem {
    public Hammer(Properties properties) {
        super(ItemTier.WOOD, properties);
    }
    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand usedHand) {
        //挥空cd
        player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);

        //播放音效
        player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        return ActionResult.success(player.getItemInHand(usedHand));
    }
    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity interactionTarget, Hand usedHand) {
        //检查是否有CD
        if (player.getCooldowns().isOnCooldown(BHTPvZItems.HAMMER.get())){
            //如果有CD则结束方法
            return ActionResultType.FAIL;
        }
        else if (interactionTarget instanceof PVZZombieEntity) {
            //对PVZ僵尸照成20点伤害
            interactionTarget.hurt(DamageSource.playerAttack(player), 20.0F);

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.HAMMER_BONK.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

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
            return ActionResultType.SUCCESS;
        }
        //挥空cd
        player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);

        //播放音效
        player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
        return ActionResultType.SUCCESS;
    }

    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslationTextComponent("tooltip.bpmc.hammer.use")).withStyle(TextFormatting.GOLD));
    }
}
