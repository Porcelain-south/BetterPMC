package com.zhilizhan.bhtpvz.common.impl;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.GrassCarpModel;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BHTPvZPlants extends PlantType {
    private static final List<IPlantType> LIST = new ArrayList<>();

  //草鱼

    public static final IPlantType GRASS_CARP = new BHTPvZPlants("grass_carp", new PlantFeatures()
            .cost(225).requiredLevel(23)
            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.ASSIST)
            .entityType(() -> BHTPvZEntityTypes.GRASS_CARP.get()).isWaterPlant()
            .summonCard(() -> BHTPvZItems.GRASS_CARP_CARD.get())
            .enjoyCard(() -> BHTPvZItems.GRASS_CARP_ENJOY_CARD.get())
            .plantModel(() -> GrassCarpModel::new).scale(1.4f)
            .cdSkill(Arrays.asList(BHTPvZSkill.GRASS_CARP_HEAL_RANGE, BHTPvZSkill.GRASS_CARP_HEAL_SPEED, BHTPvZSkill.GRASS_CARP_MORE_HEALTH)));
    public static void register() {
        PVZAPI.get().registerPlantTypes(LIST);
    }

    private BHTPvZPlants(String name, PlantFeatures features) {
        super(name, features);
        LIST.add(this);
    }
    @Override
    public String getModID() {
        return BHTPvZ.MOD_ID;
    }

    @Override
    public int getSortPriority() {
        return 80;
    }

    @Override
    public String getCategoryName() {
        return "bhtpvz";
    }
}
