package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.zhilizhan.bhtpvz.common.list.PlantList.PLANT;

public class PotGrassBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PotGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }


    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            if (level.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
                this.trySpawnPlant(level, pos);
            }
        }
    }

    private void trySpawnPlant(Level level, BlockPos pos) {
        IPlantType plantType = (IPlantType) PLANT.getRandomItem(RANDOM).get();
        level.removeBlock(pos, false);
        PathfinderMob plant = plantType.getEntityType().get().create(level);
        EntityUtil.onEntitySpawn(level, plant, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.FENCE;
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

