package com.zhilizhan.bpmc.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class WaterPotBlock extends AbstractFacingBlock {
    protected static final VoxelShape WATER_POT_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);

    public WaterPotBlock(Properties properties) {
        super(Properties.copy(Blocks.FLOWER_POT).strength(1.0F).noOcclusion());
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, net.minecraft.util.math.BlockPos pos, ISelectionContext context) {
        return WATER_POT_AABB;
    }

    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, net.minecraft.util.math.BlockPos pos, MobEntity entity) {
        return PathNodeType.BLOCKED;
    }
}