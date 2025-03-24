package com.zhilizhan.bpmc.common.item;

import com.hungteen.pvz.common.item.ItemRegister;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum BHTPvZArmorMaterial implements IArmorMaterial {

    ORIGIN("origin", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, () -> Ingredient.of(ItemRegister.ORIGIN_INGOT.get())),
    DAMSON_CRYSTAL("damson_crystal", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f, () -> Ingredient.of(BHTPvZItems.DAMSON_CRYSTAL_INGOT.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private BHTPvZArmorMaterial(String string2, int j, int[] is, int k, SoundEvent arg, float f, float g, Supplier<Ingredient> supplier) {
        this.name = string2;
        this.durabilityMultiplier = j;
        this.slotProtections = is;
        this.enchantmentValue = k;
        this.sound = arg;
        this.toughness = f;
        this.knockbackResistance = g;
        this.repairIngredient = new LazyValue<>(supplier);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.slotProtections[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
