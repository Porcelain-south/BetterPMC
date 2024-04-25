package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaterPotBlock extends AbstractFacingBlock {
    protected static final VoxelShape LILY_PAD_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);

    public WaterPotBlock(Properties properties) {
        super(Properties.copy(Blocks.FLOWER_POT).strength(1.0F).noOcclusion());
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return LILY_PAD_AABB;
    }

    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.BLOCKED;
    }
}