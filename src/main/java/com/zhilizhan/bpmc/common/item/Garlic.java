package com.zhilizhan.bpmc.common.item;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class Garlic extends BlockItem {
    public Garlic(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if(livingEntity instanceof PVZZombieEntity && !level.isClientSide){
            ((PVZZombieEntity) livingEntity).checkAndAddPotionEffect(new EffectInstance(Effects.POISON, 120, 0));
            stack.shrink(1);
        }
        return stack;
    }
}
