package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.others.WeightList;

public class PlantItemList {
    public static final WeightList<PlantCardItem> PLANT_ITEM = new WeightList<>();
    static {
        PLANT_ITEM.addItem(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get(),100);
        PLANT_ITEM.addItem(ItemRegister.ICE_SHROOM_ENJOY_CARD.get(),90);
        PLANT_ITEM.addItem(ItemRegister.WALL_NUT_ENJOY_CARD.get(),80);
        PLANT_ITEM.addItem(ItemRegister.POTATO_MINE_ENJOY_CARD.get(),90);
        PLANT_ITEM.addItem(ItemRegister.SQUASH_ENJOY_CARD.get(),100);
        PLANT_ITEM.addItem(ItemRegister.THREE_PEATER_ENJOY_CARD.get(),70);
        PLANT_ITEM.addItem(ItemRegister.PLANTERN_ENJOY_CARD.get(),70);
        PLANT_ITEM.addItem(ItemRegister.CHERRY_BOMB_ENJOY_CARD.get(),80);
    }
}
