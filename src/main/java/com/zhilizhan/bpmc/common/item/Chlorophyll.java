package com.zhilizhan.bpmc.common.item;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Chlorophyll extends Item {
    public Chlorophyll(Item.Properties properties) {
    super(properties);
}

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand usedHand) {
        player.startUsingItem(usedHand);
        return ActionResult.success(player.getItemInHand(usedHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if(!level.isClientSide) {
                player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
                    int amount = 1;
                    {
                        l.getPlayerData().addResource(Resources.ENERGY_NUM, amount);
                        PlayerUtil.playClientSound(player, SoundEvents.EXPERIENCE_BOTTLE_THROW);
                        stack.shrink(1);
                    }
                });
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        textComponents.add(new TranslationTextComponent("tooltip.bpmc.chlorophyll.use").withStyle(TextFormatting.GREEN));
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 30;
    }
}

