package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SunDaveToken extends Item {
    public SunDaveToken(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (!level.isClientSide && player != null && !player.getCooldowns().isOnCooldown(this) && context.getClickedFace() == Direction.UP) {
            if (getDaveCount(player) < 1) {
                SunDaveEntity dave = EntityRegister.SUN_DAVE.get().create(level);
                if (dave != null) {
                    EntityUtil.onEntitySpawn(level, dave, pos.above());
                }
                stack.shrink(1);
            }
        }
        return InteractionResult.CONSUME;
    }

    public int getDaveCount(Player player) {
        final int range = 120;
        final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof SunDaveEntity).count();
        return (int)count;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.sun_token.use").withStyle(ChatFormatting.YELLOW));
    }
}
