package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class

XpSapling extends Item {
    public XpSapling(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
            if(!level.isClientSide) {
                player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
                    int amount = 200;
                    {
                        l.getPlayerData().addResource(Resources.TREE_XP, amount);
                        PlayerUtil.playClientSound(player, SoundEvents.EXPERIENCE_BOTTLE_THROW);
                        stack.shrink(1);
                    }
                });
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.xp_sapling.use").withStyle(ChatFormatting.GREEN));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 50;
    }
}
