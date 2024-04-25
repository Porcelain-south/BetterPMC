package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.zhilizhan.bhtpvz.common.list.PlantItemList.PLANT_ITEM;

public class PlantPotBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PlantPotBlock(Properties properties) {
        super(properties);
    }
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        //随机植物卡
        ItemStack plant = PLANT_ITEM.getRandomItem(RANDOM).get().getDefaultInstance();

        if (!level.isClientSide && player.getMainHandItem().getItem()==BHTPvZItems.HAMMER.get()) {
            //移除方块
            level.removeBlock(pos, false);

            //爆出战利品
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(plant.getItem())));

            //播放音效
            player.level.playSound(null, player.blockPosition(), BHTPvZSound.BUZZER.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.level.playSound(null, player.blockPosition(), SoundRegister.VASE_BREAKING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            //如果是生存模式添加30tick的CD，否则10tick
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);

                //减少锤子5点耐久
                if(player instanceof ServerPlayer)player.getMainHandItem().hurt(5,level.random, (ServerPlayer) player);
            }
            else
            {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 10);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.FENCE;
    }
}
