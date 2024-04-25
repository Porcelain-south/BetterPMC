package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin(PlantCardItem.class)
public class PlantCardItemMixin {
	/**
	 * @author
	 * @reason
	 */
	@Nullable
	@Overwrite
	public static BlockState getBlockState(Player player, IPlantType plant) {
		return plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(player) :
				plant == PVZPlants.FLOWER_POT ? Math.random()>=0.1F? BlockRegister.FLOWER_POT.get().getStateForPlacement(player): BHTPvZBlocks.CHINAWARE_FLOWER_POT.get().getStateForPlacement(player) :
						null;
	}

	/**
	 * @author
	 * @reason
	 */
	@Nullable
	@Overwrite
	public static BlockState getBlockState(Direction direction, IPlantType plant) {
		return plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(direction) :
				plant == PVZPlants.FLOWER_POT ? Math.random()>=0.1F? BlockRegister.FLOWER_POT.get().getStateForPlacement(direction): BHTPvZBlocks.CHINAWARE_FLOWER_POT.get().getStateForPlacement(direction): null;
	}
}