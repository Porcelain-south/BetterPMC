package com.zhilizhan.bpmc.common.item;

import com.zhilizhan.bpmc.BPMC;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DamsonCrystalSword extends SwordItem {
    public DamsonCrystalSword() {
        super(BHTPvZTier.DAMSON_CRYSTAL, 3, -2.4F, (new Properties()).tab(BPMC.BHTPVZ));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslationTextComponent("tooltip.pvz.origin_sword")).withStyle(TextFormatting.DARK_GREEN));
    }
}
