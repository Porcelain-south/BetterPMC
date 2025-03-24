package com.zhilizhan.bpmc.common.entity.bullet.itembullet;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EffectUtil;
import com.zhilizhan.bpmc.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.misc.BHTPvZEntityDamageSource;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class IceCabbageEntity extends PultBulletEntity implements IRendersAsItem {

	public IceCabbageEntity(World level, LivingEntity shooter) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), level, shooter);
	}
	public IceCabbageEntity(EntityType<IceCabbageEntity> iceCabbageEntityEntityType, World worldIn) {
		super(BHTPvZEntityTypes.ICE_CABBAGE.get(), worldIn);
	}

	@Override
	protected void dealDamage(Entity target) {
		float damage = this.getAttackDamage();
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.iceCabbage(this, this.getThrower());
        LivingEntity owner = this.getThrower();
        if (owner instanceof IIceEffect) {
            ((IIceEffect)owner).getColdEffect().ifPresent(source::addEffect);
            ((IIceEffect)owner).getFrozenEffect().ifPresent(source::addEffect);
        } else if (owner instanceof PlayerEntity) {
            source.addEffect(EffectUtil.effect(EffectRegister.COLD_EFFECT.get(), 100, 5));
        }
		target.hurt(source, damage);
    }

	@Override
	public EntitySize getDimensions(Pose pose) {
		return EntitySize.scalable(0.6f, 0.6f);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(BHTPvZItems.ICE_CABBAGE.get());
	}
}
