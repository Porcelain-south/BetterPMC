package com.zhilizhan.bhtpvz.common.effect;

import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HalitosisMobEffect extends MobEffect {
    public HalitosisMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player) {
            float range = 2.0F;
            EntityUtil.getFriendlyLivings(livingEntity, EntityUtil.getEntityAABB(livingEntity, range, range)).forEach((entity) -> entity.addEffect(EffectUtil.viewEffect(MobEffects.POISON, 40, 0)));
        }
    }
}
