package com.zhilizhan.bpmc.common.list;

import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class EffectList {
    public static final WeightList<Effect> EFFECT = new WeightList<>();

    static {
        EFFECT.addItem(Effects.JUMP, 100);
        EFFECT.addItem(Effects.SATURATION, 100);
        EFFECT.addItem(Effects.BLINDNESS, 100);
        EFFECT.addItem(Effects.POISON, 100);
        EFFECT.addItem(Effects.NIGHT_VISION, 100);
        EFFECT.addItem(Effects.WEAKNESS, 100);
    }
}
