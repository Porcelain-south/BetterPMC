package com.zhilizhan.bhtpvz.common.item;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DamsonCrystalSword extends SwordItem {
    public DamsonCrystalSword() {
        super(BHTPvZTiers.DAMSON_CRYSTAL, 3, -2.4F, (new Item.Properties()).tab(BHTPvZ.BHTPVZ));
    }

    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslatableComponent("tooltip.pvz.origin_sword")).withStyle(ChatFormatting.DARK_GREEN));
    }
}
