package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WisdomSapling extends XpSapling {
    public WisdomSapling(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
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
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.wisdom_sapling.use").withStyle(ChatFormatting.GREEN));
    }
}
