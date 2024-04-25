package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.others.WeightList;

public class PlantList {
    public static final WeightList<IPlantType> PLANT = new WeightList<>();

    static {
        PLANT.addItem(PVZPlants.SUN_FLOWER, 60);
        PLANT.addItem(PVZPlants.WALL_NUT, 70);
        PLANT.addItem(PVZPlants.SNOW_PEA, 80);
        PLANT.addItem(PVZPlants.CHERRY_BOMB, 75);
        PLANT.addItem(PVZPlants.MELON_PULT, 60);
        PLANT.addItem(PVZPlants.JALAPENO, 75);
    }
}
