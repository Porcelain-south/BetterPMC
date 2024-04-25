package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.item.ItemRegister;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class BHTPvZArmorMaterials {
    public static final ArmorMaterial ORIGIN = new BHTPvZArmorMaterial("origin", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, () -> Ingredient.of(ItemRegister.ORIGIN_INGOT.get()));
    public static final ArmorMaterial DAMSON_CRYSTAL = new BHTPvZArmorMaterial("damson_crystal", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f, () -> Ingredient.of(BHTPvZItems.DAMSON_CRYSTAL_INGOT.get()));
}
