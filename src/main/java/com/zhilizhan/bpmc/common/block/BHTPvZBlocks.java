package com.zhilizhan.bpmc.common.block;

import com.zhilizhan.bpmc.BPMC;
import com.zhilizhan.bpmc.common.block.grower.CherryTreeGrower;
import com.zhilizhan.bpmc.common.block.grower.StarfruitTreeGrower;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BPMC.MOD_ID);

    public static final RegistryObject<Block> CHERRY_LEAVES = BLOCKS.register("cherry_leaves", ()-> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES))); // 樱桃树树叶
    public static final RegistryObject<Block> STARFRUIT_LEAVES = BLOCKS.register("starfruit_leaves", ()-> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES))); // 杨桃树树叶
    public static final RegistryObject<Block> CHERRY_SAPLING = BLOCKS.register("cherry_sapling", ()-> new SaplingBlock(new CherryTreeGrower(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS))); // 樱桃树树苗
    public static final RegistryObject<Block> STARFRUIT_SAPLING = BLOCKS.register("starfruit_sapling", ()-> new SaplingBlock(new StarfruitTreeGrower(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS))); // 杨桃树树苗
    public static final RegistryObject<Block> ORIGIN_MUSHROOM = BLOCKS.register("origin_mushroom", ()-> new OriginMushroomBlock(Block.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(BHTPvZBlocks::always))); // 原始蘑菇
    public static final RegistryObject<Block> ORIGIN_MUSHROOM_BLOCK = BLOCKS.register("origin_mushroom_block", ()-> new Block(Block.Properties.of(Material.STONE).harvestLevel(2).strength(2.0f, 6.0f).sound(SoundType.STONE))); // 原始蘑菇块
    public static final RegistryObject<Block> QUESTION_MARK_POT = BLOCKS.register("question_mark_pot", ()-> new QuestionMarkPotBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(6.0f,6.0f))); // 问号罐
    public static final RegistryObject<Block> PLANT_POT = BLOCKS.register("plant_pot", ()-> new PlantPotBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(6.0f,6.0f))); // 植物罐
    public static final RegistryObject<Block> POT_GRASS = BLOCKS.register("pot_grass", ()-> new PotGrassBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(6.0f,6.0f))); // 罐子草
    public static final RegistryObject<Block> WATER_POT = BLOCKS.register("water_pot", ()->new WaterPotBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE))); // 水盆
    public static final RegistryObject<Block> SQUASH = BLOCKS.register("squash", ()-> new MelonBlock(Block.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_GREEN).strength(1.0f).sound(SoundType.WOOD))); // 倭瓜
    public static final RegistryObject<Block> MORION_ORE = BLOCKS.register("morion_ore", ()-> new OreBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(3.0f, 3.0f))); // 黑晶矿
    public static final RegistryObject<Block> MORION_BLOCK = BLOCKS.register("morion_block", ()-> new Block(Block.Properties.of(Material.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(5.0f, 6.0f).sound(SoundType.METAL))); // 黑晶块
    public static final RegistryObject<Block> DAMSON_CRYSTAL_BLOCK = BLOCKS.register("damson_crystal_block", ()-> new Block(Block.Properties.of(Material.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(5.0F, 6.0f).sound(SoundType.METAL))); // 暗紫合晶块
    public static final RegistryObject<Block> CHILI = BLOCKS.register("chili", ()-> new ChiliBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP))); // 辣椒作物
    public static final RegistryObject<Block> GARLIC = BLOCKS.register("garlic", ()-> new GarlicBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP))); // 大蒜作物
    public static final RegistryObject<Block> DECOMPOSITION_STAGE = BLOCKS.register("decomposition_stage", ()-> new Block(Block.Properties.of(Material.STONE).harvestLevel(2).strength(2.0f, 6.0f).sound(SoundType.STONE))); // 分解台
    public static final RegistryObject<ChinaWareFlowerPotBlock> CHINAWARE_FLOWER_POT = BLOCKS.register("chinaware_flower_pot", ChinaWareFlowerPotBlock::new);

    private static Boolean always(BlockState state, IBlockReader blockGetter, BlockPos pos) {
        return true;
    }


}
