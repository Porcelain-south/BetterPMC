package com.zhilizhan.bpmc.common.block;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import static com.zhilizhan.bpmc.common.list.PlantList.PLANT;


public class PotGrassBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PotGrassBlock(Block.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }


    public void onPlace(BlockState state, World level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            if (level.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
                this.trySpawnPlant(level, pos);
            }
        }
    }

    private void trySpawnPlant(World level, BlockPos pos) {
        IPlantType plantType = PLANT.getRandomItem(RANDOM).get();
        level.removeBlock(pos, false);
        MobEntity plant = plantType.getEntityType().get().create(level);
        EntityUtil.onEntitySpawn(level, plant, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.FENCE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

