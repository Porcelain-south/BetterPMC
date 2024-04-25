package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class Garlic extends BlockItem {
    public Garlic(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        if(interactionTarget instanceof PVZZombieEntity){
            ((PVZZombieEntity) interactionTarget).checkAndAddPotionEffect(new MobEffectInstance(MobEffects.POISON, 120, 0));
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
