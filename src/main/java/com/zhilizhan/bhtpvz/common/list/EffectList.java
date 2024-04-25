package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class EffectList {
    public static final WeightList<MobEffect> EFFECT = new WeightList<>();

    static {
        EFFECT.addItem(MobEffects.JUMP, 100);
        EFFECT.addItem(MobEffects.SATURATION, 100);
        EFFECT.addItem(MobEffects.BLINDNESS, 100);
        EFFECT.addItem(MobEffects.POISON, 100);
        EFFECT.addItem(MobEffects.NIGHT_VISION, 100);
        EFFECT.addItem(MobEffects.WEAKNESS, 100);
    }
}
