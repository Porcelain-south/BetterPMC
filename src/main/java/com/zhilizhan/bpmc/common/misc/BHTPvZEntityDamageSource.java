package com.zhilizhan.bpmc.common.misc;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bpmc.common.entity.bullet.itembullet.IceCabbageEntity;
import net.minecraft.entity.Entity;

public class BHTPvZEntityDamageSource {

    //冰卷心菜投手伤害
    public static PVZEntityDamageSource iceCabbage(IceCabbageEntity iceCabbage, Entity indirectEntity) {
        return (new PVZEntityDamageSource("ice_cabbage", iceCabbage, indirectEntity).setParabola().setIceDamage());
    }
}
