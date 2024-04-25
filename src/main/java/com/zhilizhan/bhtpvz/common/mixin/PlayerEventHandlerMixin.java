package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.api.paz.IPlantInfo;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.tool.mc.OriginShovelItem;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.zhilizhan.bhtpvz.common.item.DamsonCrystalShove;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEventHandler.class)
public class PlayerEventHandlerMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void quickRemoveByPlayer(Player player, Entity entity, ItemStack stack) {
        if (!PlayerUtil.isPlayerSurvival(player) || entity instanceof AbstractPAZEntity && ((AbstractPAZEntity)entity).getOwnerUUID().isPresent() && player.getUUID().equals(((AbstractPAZEntity)entity).getOwnerUUID().get())) {
            boolean removed = false;
            if (entity instanceof PVZPlantEntity && stack.getItem() instanceof ShovelItem) {
                PVZPlantEntity plantEntity = (PVZPlantEntity)entity;
                if (plantEntity.getOuterPlantInfo().isPresent()) {
                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, ((IPlantInfo)plantEntity.getOuterPlantInfo().get()).getSunCost()));
                    plantEntity.removeOuterPlant();
                } else if (plantEntity.getPlantInfo().isPresent()) {
                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, ((IPlantInfo)plantEntity.getPlantInfo().get()).getSunCost()));
                    plantEntity.remove();
                }

                removed = !(stack.getItem() instanceof OriginShovelItem || stack.getItem() instanceof DamsonCrystalShove);
                EntityUtil.playSound(plantEntity, (SoundEvent) SoundRegister.PLACE_PLANT_GROUND.get());
            } else if (entity instanceof PVZZombieEntity) {
            }

            if (removed && PlayerUtil.isPlayerSurvival(player)) {
                stack.hurtAndBreak(3, player, (p) -> {
                    p.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                });
            }
        }

    }
}
