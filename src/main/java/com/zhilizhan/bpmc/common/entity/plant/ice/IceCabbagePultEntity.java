package com.zhilizhan.bpmc.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.zhilizhan.bpmc.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bpmc.common.impl.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Optional;

public class IceCabbagePultEntity extends CabbagePultEntity implements IIceEffect{
	private static final int FROZEN_TICK = 15;

	public IceCabbagePultEntity(EntityType<? extends CreatureEntity> type, World level) {
		super(type, level);
	}

	@Override
	protected PultBulletEntity createBullet() {
		return new IceCabbageEntity(level, this);
	}

	@Override
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.MORE_CABBAGE_DAMAGE);
	}

	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.of(new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false));
	}

	public int getColdLvl() {
		return 3;
	}

	public int getColdTick() {
		return 80;
	}

	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.empty();
	}

	@Override
	public IPlantType getPlantType() {
		return BHTPvZPlants.ICE_CABBAGE_PULT;
	}
}
