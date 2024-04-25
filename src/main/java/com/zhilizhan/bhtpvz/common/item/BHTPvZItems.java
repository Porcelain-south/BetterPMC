package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.item.token.DaveToken;
import com.zhilizhan.bhtpvz.common.item.token.SunDaveToken;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BHTPvZ.MOD_ID);

    // 普通物品
    public static final RegistryObject<Item> ORIGIN_SPORE = ITEMS.register("origin_spore", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始孢子
    public static final RegistryObject<Item> MORION_INGOT = ITEMS.register("morion_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶锭
    public static final RegistryObject<Item> DAMSON_CRYSTAL_INGOT = ITEMS.register("damson_crystal_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶锭
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黄油

    // 种子
    public static final RegistryObject<Item> CHILI_SEEDS = ITEMS.register("chili_seeds", ()-> new BlockItem(BHTPvZBlocks.CHILI.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 辣椒种子

    // 食物
    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build()))); // 樱桃
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", ()-> new Garlic(BHTPvZBlocks.GARLIC.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build()))); // 大蒜
    public static final RegistryObject<Item> STARFRUIT = ITEMS.register("starfruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build()))); // 杨桃
    public static final RegistryObject<Item> ANGEL_STARFRUIT = ITEMS.register("angel_starfruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(8).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F).build()))); // 天使杨桃
    public static final RegistryObject<Item> SQUASH_SLICE = ITEMS.register("squash_slice", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build()))); //倭瓜片
    public static final RegistryObject<Item> ICE_CABBAGE = ITEMS.register("ice_cabbage", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1), 1.0F).build()))); // 冰卷心菜

    // 功能性食物
    public static final RegistryObject<Item> XP_SAPLING = ITEMS.register("xp_sapling", ()-> new XpSapling(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).effect(new MobEffectInstance(MobEffects.LUCK, 1000, 1), 1.0F).build()))); //经验树苗
    public static final RegistryObject<Item> WISDOM_SAPLING = ITEMS.register("wisdom_sapling", ()-> new WisdomSapling(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).effect(new MobEffectInstance(MobEffects.LUCK, 2000, 3), 1.0F).build()))); //智慧树苗
    public static final RegistryObject<Item> STEEL_COIN = ITEMS.register("steel_coin", ()-> new SteelCoin(new Item.Properties().tab(BHTPvZ.BHTPVZ))); //钢镚
    public static final RegistryObject<Item> CHLOROPHYLL = ITEMS.register("chlorophyll", ()-> new Chlorophyll(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).effect(new MobEffectInstance(MobEffects.LUCK, 1000, 1), 1.0F).build()))); //钢镚
    // 工具
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", ()-> new Hammer(new Item.Properties().tab(BHTPvZ.BHTPVZ).durability(300))); //锤子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SWORD = ITEMS.register("damson_crystal_sword", DamsonCrystalSword::new); // 暗紫合金剑
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SHOVEL = ITEMS.register("damson_crystal_shovel", DamsonCrystalShove::new); // 暗紫合金铲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_PICKAXE = ITEMS.register("damson_crystal_pickaxe", ()-> new PickaxeItem(BHTPvZTiers.DAMSON_CRYSTAL, 1, -2.8f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金镐
    public static final RegistryObject<Item> DAMSON_CRYSTAL_AXE = ITEMS.register("damson_crystal_axe", ()-> new AxeItem(BHTPvZTiers.DAMSON_CRYSTAL, 5.0f, -3.0f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金斧
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HOE = ITEMS.register("damson_crystal_hoe", ()-> new HoeItem(BHTPvZTiers.DAMSON_CRYSTAL, -4, 0.0f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金锄

    // 装备
    public static final RegistryObject<Item> ORIGIN_HELMET = ITEMS.register("origin_helmet", ()-> new ArmorItem(BHTPvZArmorMaterials.ORIGIN, EquipmentSlot.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华头盔
    public static final RegistryObject<Item> ORIGIN_CHESTPLATE = ITEMS.register("origin_chestplate", ()-> new ArmorItem(BHTPvZArmorMaterials.ORIGIN, EquipmentSlot.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华胸甲
    public static final RegistryObject<Item> ORIGIN_LEGGINGS = ITEMS.register("origin_leggings", ()-> new ArmorItem(BHTPvZArmorMaterials.ORIGIN, EquipmentSlot.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华护腿
    public static final RegistryObject<Item> ORIGIN_BOOTS = ITEMS.register("origin_boots", ()-> new ArmorItem(BHTPvZArmorMaterials.ORIGIN, EquipmentSlot.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华靴子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HELMET = ITEMS.register("damson_crystal_helmet", ()-> new ArmorItem(BHTPvZArmorMaterials.DAMSON_CRYSTAL, EquipmentSlot.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶头盔
    public static final RegistryObject<Item> DAMSON_CRYSTAL_CHESTPLATE = ITEMS.register("damson_crystal_chestplate", ()-> new ArmorItem(BHTPvZArmorMaterials.DAMSON_CRYSTAL, EquipmentSlot.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶胸甲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_LEGGINGS = ITEMS.register("damson_crystal_leggings", ()-> new ArmorItem(BHTPvZArmorMaterials.DAMSON_CRYSTAL, EquipmentSlot.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶护腿
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BOOTS = ITEMS.register("damson_crystal_boots", ()-> new ArmorItem(BHTPvZArmorMaterials.DAMSON_CRYSTAL, EquipmentSlot.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶靴子

    //普通生物刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> ORIGIN_MOOB_SPAWN_EGG = registerSpawnEgg("origin_moob_spawn_egg", BHTPvZEntityTypes.ORIGIN_MOOB, Colors.APPEASE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 蘑菇牛刷怪蛋

    // 生成工具
    public static final RegistryObject<Item> DAVE_TOKEN = ITEMS.register("dave_token", () -> new DaveToken(new Item.Properties().tab(BHTPvZ.BHTPVZ).stacksTo(1))); // 戴夫标志
    public static final RegistryObject<Item> SUN_DAVE_TOKEN = ITEMS.register("sun_dave_token", () -> new SunDaveToken(new Item.Properties().tab(BHTPvZ.BHTPVZ).stacksTo(1))); // 阳光戴夫标志

    // 方块物品
    public static final RegistryObject<Item> CHERRY_TREE_LEAVES = ITEMS.register("cherry_leaves", ()-> new BlockItem(BHTPvZBlocks.CHERRY_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 樱桃树树叶
    public static final RegistryObject<Item> STARFRUIT_LEAVES = ITEMS.register("starfruit_leaves", ()-> new BlockItem(BHTPvZBlocks.STARFRUIT_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 杨桃树树叶
    public static final RegistryObject<Item> CHERRY_TREE_SAPLING = ITEMS.register("cherry_sapling", ()-> new BlockItem(BHTPvZBlocks.CHERRY_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 樱桃树树苗
    public static final RegistryObject<Item> STARFRUIT_SAPLING = ITEMS.register("starfruit_sapling", ()-> new BlockItem(BHTPvZBlocks.STARFRUIT_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 杨桃树树苗
    public static final RegistryObject<Item> ORIGIN_MUSHROOM = ITEMS.register("origin_mushroom", ()-> new BlockItem(BHTPvZBlocks.ORIGIN_MUSHROOM.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始蘑菇
    public static final RegistryObject<Item> ORIGIN_MUSHROOM_BLOCK = ITEMS.register("origin_mushroom_block", ()-> new BlockItem(BHTPvZBlocks.ORIGIN_MUSHROOM_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始蘑菇块
    public static final RegistryObject<Item> QUESTION_MARK_POT = ITEMS.register("question_mark_pot", ()-> new BlockItem(BHTPvZBlocks.QUESTION_MARK_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 问号罐
    public static final RegistryObject<Item> PLANT_POT = ITEMS.register("plant_pot", ()-> new BlockItem(BHTPvZBlocks.PLANT_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 植物罐
    public static final RegistryObject<Item> POT_GRASS = ITEMS.register("pot_grass", ()-> new BlockItem(BHTPvZBlocks.POT_GRASS.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 罐子草

    public static final RegistryObject<Item> WATER_POT = ITEMS.register("water_pot", ()-> new BlockItem(BHTPvZBlocks.WATER_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 水盆

    public static final RegistryObject<Item> SQUASH = ITEMS.register("squash", ()-> new BlockItem(BHTPvZBlocks.SQUASH.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 倭瓜
    public static final RegistryObject<Item> MORION_ORE = ITEMS.register("morion_ore", ()-> new BlockItem(BHTPvZBlocks.MORION_ORE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶矿
    public static final RegistryObject<Item> MORION_BLOCK = ITEMS.register("morion_block", ()-> new BlockItem(BHTPvZBlocks.MORION_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶块
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BLOCK = ITEMS.register("damson_crystal_block", ()-> new BlockItem(BHTPvZBlocks.DAMSON_CRYSTAL_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶块
    public static final RegistryObject<Item> DECOMPOSITION_STAGE = ITEMS.register("decomposition_stage", ()-> new BlockItem(BHTPvZBlocks.DECOMPOSITION_STAGE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 分解台
    public static final RegistryObject<Item> CHINAWARE_FLOWER_POT = ITEMS.register("chinaware_flower_pot", ()-> new BlockItem(BHTPvZBlocks.CHINAWARE_FLOWER_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 青花瓷

    //植物卡
    public static final RegistryObject<PlantCardItem> GRASS_CARP_CARD = registerCard(BHTPvZPlants.GRASS_CARP, false); // 草鱼卡
    public static final RegistryObject<PlantCardItem> GRASS_CARP_ENJOY_CARD = registerCard(BHTPvZPlants.GRASS_CARP, true); // 草鱼体验卡

    private static RegistryObject<PlantCardItem> registerCard(IPlantType plant, boolean is){
        String name = plant.toString();
        if(is) {
            name = name + "_enjoy";
        }
        name = name + "_card";
        return ITEMS.register(name, () -> new PlantCardItem(plant, is));
    }
    // 刷怪蛋注册方法
    private static RegistryObject<BHTPvZSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<?>> entityType, Pair<Integer, Integer> color, Item.Properties tab) {
        return ITEMS.register(name, () -> new BHTPvZSpawnEggItem(entityType, color.getFirst(), color.getSecond(), tab));
    }

}
