package com.zhilizhan.bpmc.common.list;

import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.others.WeightList;

public class ZombieList {
    public static final WeightList<ZombieType> ZOMBIE = new WeightList<>();
    static {
        ZOMBIE.addItem(GrassZombies.NORMAL_ZOMBIE, 100);
        ZOMBIE.addItem(GrassZombies.NEWSPAPER_ZOMBIE, 90);
        ZOMBIE.addItem(GrassZombies.SCREENDOOR_ZOMBIE, 70);
        ZOMBIE.addItem(PoolZombies.SNORKEL_ZOMBIE, 100);
        ZOMBIE.addItem(PoolZombies.BALLOON_ZOMBIE, 80);
        ZOMBIE.addItem(RoofZombies.IMP, 80);
        ZOMBIE.addItem(GrassZombies.CONEHEAD_ZOMBIE, 100);
        ZOMBIE.addItem(GrassZombies.POLE_ZOMBIE, 90);
        ZOMBIE.addItem(GrassZombies.DANCING_ZOMBIE, 70);
        ZOMBIE.addItem(GrassZombies.OLD_ZOMBIE, 80);
        ZOMBIE.addItem(PoolZombies.JACK_IN_BOX_ZOMBIE, 85);
        ZOMBIE.addItem(PoolZombies.DIGGER_ZOMBIE, 70);
        ZOMBIE.addItem(PoolZombies.POGO_ZOMBIE, 85);

    }
}
