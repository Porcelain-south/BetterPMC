package com.zhilizhan.bpmc.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.sound.BHTPvZSound;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import static com.zhilizhan.bpmc.common.list.PlantItemList.PLANT_ITEM;

public class PlantPotBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PlantPotBlock(Properties properties) {
        super(properties);
    }

    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        //随机植物卡
        ItemStack plant = PLANT_ITEM.getRandomItem(RANDOM).get().getDefaultInstance();

        if (!level.isClientSide && player.getMainHandItem().getItem()==BHTPvZItems.HAMMER.get()) {
            //移除方块
            level.removeBlock(pos, false);

            //爆出战利品
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(plant.getItem())));

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.VASE_BREAKING.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

            //如果是生存模式添加30tick的CD，否则10tick
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);

                //减少锤子5点耐久
                if(player instanceof ServerPlayerEntity)player.getMainHandItem().hurt(5,level.random, (ServerPlayerEntity) player);
            }
            else
            {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);
            }

            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.FENCE;
    }
}
