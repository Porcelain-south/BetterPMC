package com.zhilizhan.bpmc.common.event;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.util.Utils;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class LivingEvents {

	public static void PlayerRightClickItem(PlayerInteractEvent.EntityInteractSpecific event) {
		Entity entity = event.getTarget();
		PlayerEntity player = event.getPlayer();
		Item item = event.getPlayer().getMainHandItem().getItem();
		if(! event.getWorld().isClientSide&&event.getHand() == Hand.MAIN_HAND){
			if (entity instanceof PVZPlantEntity && EntityUtil.isEntityValid(entity) && (item.equals(BHTPvZItems.DAMSON_CRYSTAL_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel((Enchantment) EnchantmentRegister.ENERGY_TRANSFER.get(), item.getDefaultInstance()) > 0) && ((PVZPlantEntity)entity).canStartSuperMode() && (!PlayerUtil.isPlayerSurvival(player) || PlayerUtil.getResource(player, Resources.ENERGY_NUM) > 0)) {
			if (PlayerUtil.isPlayerSurvival(player)) {
				PlayerUtil.addResource(player, Resources.ENERGY_NUM, -1);
			}
			((PVZPlantEntity)entity).startSuperMode(true);
			int treeLevel = PlayerUtil.getResource(player, Resources.TREE_LVL);
			player.addEffect(new EffectInstance(EffectRegister.ENERGETIC_EFFECT.get(), 100 + (treeLevel + 1) / 2, 0));
		}

	}}

	public static void playerClick(PlayerInteractEvent.RightClickBlock e) {
		PlayerEntity player = e.getPlayer();
		World world = e.getWorld();
		if (!world.isClientSide) {
            Block block = world.getBlockState(e.getPos()).getBlock();
            if (block instanceof BedBlock) {
                Integer sleeptime = 15000;//可以入眠的tick时间;
                Integer currenttime = (int)world.getDayTime()%24000;
				if (currenttime.equals(sleeptime)) {//判断是否可以入眠,true为不能入眠
                    e.setCanceled(true);
					if(e.getPlayer() instanceof ServerPlayerEntity) {
						((ServerPlayerEntity)player).setRespawnPosition(world.dimension(), e.getPos(), 0.0F, false, true);
						Utils.sendMessage(player, new TranslationTextComponent("message.bpmc.sleep.not_time"), TextFormatting.DARK_GREEN);//失败消息
					}
				}
            }
        }
	}
}
