package com.zhilizhan.bpmc.common.item.sapling;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class WisdomSapling extends XpSapling {
    public WisdomSapling(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if(!level.isClientSide) {
                player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
                    int amount = 1000;
                    l.getPlayerData().addResource(Resources.TREE_XP, amount);
                    PlayerUtil.playClientSound(player, SoundEvents.EXPERIENCE_BOTTLE_THROW);
                    stack.shrink(1);
                });
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bpmc.wisdom_sapling.use").withStyle(TextFormatting.GREEN));
    }
}
