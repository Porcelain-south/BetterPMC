package com.zhilizhan.bhtpvz.common.item;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DamsonCrystalShove extends ShovelItem {
    public DamsonCrystalShove() {
        super(BHTPvZTiers.DAMSON_CRYSTAL,1.5f,-0.3f, new Item.Properties().tab(BHTPvZ.BHTPVZ));
    }
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslatableComponent("tooltip.pvz.origin_shovel")).withStyle(ChatFormatting.DARK_GREEN));
    }
}
