package com.zhilizhan.bhtpvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class LivingEvents {

	public static void PlayerRightClickItem(PlayerInteractEvent.EntityInteractSpecific event) {
		Entity entity = event.getTarget();
		Player player = event.getPlayer();
		Item item = event.getPlayer().getMainHandItem().getItem();
		if(! event.getWorld().isClientSide&&event.getHand() == InteractionHand.MAIN_HAND){
			if (entity instanceof PVZPlantEntity && EntityUtil.isEntityValid(entity) && (item.equals(BHTPvZItems.DAMSON_CRYSTAL_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel((Enchantment) EnchantmentRegister.ENERGY_TRANSFER.get(), item.getDefaultInstance()) > 0) && ((PVZPlantEntity)entity).canStartSuperMode() && (!PlayerUtil.isPlayerSurvival(player) || PlayerUtil.getResource(player, Resources.ENERGY_NUM) > 0)) {
			if (PlayerUtil.isPlayerSurvival(player)) {
				PlayerUtil.addResource(player, Resources.ENERGY_NUM, -1);
			}
			((PVZPlantEntity)entity).startSuperMode(true);
			int treeLevel = PlayerUtil.getResource(player, Resources.TREE_LVL);
			player.addEffect(new MobEffectInstance((MobEffect) EffectRegister.ENERGETIC_EFFECT.get(), 100 + (treeLevel + 1) / 2, 0));
		}

	}}

	public static void playerClick(PlayerInteractEvent.RightClickBlock e) {
		Player player = e.getPlayer();
		Level world = e.getWorld();
		if (!world.isClientSide) {
            Block block = world.getBlockState(e.getPos()).getBlock();
            if (block instanceof BedBlock) {
                Integer sleeptime = 15001;//可以入眠的tick时间;
                Integer currenttime = (int)world.getDayTime()%24000;
				if (currenttime <= sleeptime) {//判断是否可以入眠,true为不能入眠
                    e.setCanceled(true);
					if(e.getPlayer() instanceof ServerPlayer) {
						((ServerPlayer)player).setRespawnPosition(world.dimension(), e.getPos(), 0.0F, false, true);
						Utils.sendMessage(player, "还没到睡觉时间", ChatFormatting.DARK_GREEN);//失败消息
					}
				}
            }
        }
	}
}
