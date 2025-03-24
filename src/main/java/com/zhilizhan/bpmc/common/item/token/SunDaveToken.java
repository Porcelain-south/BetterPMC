package com.zhilizhan.bpmc.common.item.token;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SunDaveToken extends Item {
    public SunDaveToken(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World level, @Nonnull PlayerEntity player, Hand usedHand) {
        player.startUsingItem(usedHand);
        return ActionResult.success(player.getItemInHand(usedHand));
    }
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World level = context.getLevel();
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
        return ActionResultType.CONSUME;
    }

    public int getDaveCount(PlayerEntity player) {
        final int range = 120;
        final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof SunDaveEntity).count();
        return (int)count;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bpmc.sun_token.use").withStyle(TextFormatting.YELLOW));
    }
}
