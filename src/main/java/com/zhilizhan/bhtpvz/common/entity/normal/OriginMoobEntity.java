package com.zhilizhan.bhtpvz.common.entity.normal;

import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.list.EffectList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OriginMoobEntity extends Cow implements IForgeShearable {


    public OriginMoobEntity(EntityType<? extends Cow> arg, Level arg2) {
        super(arg, arg2);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }



    public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
        world.playSound((Player)null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        if (world.isClientSide() || this.isBaby()) {
            return Collections.emptyList();
        } else {
            ((ServerLevel)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            this.remove();
            Cow cowentity = EntityType.COW.create(this.level);
            cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
            cowentity.setHealth(this.getHealth());
            cowentity.yBodyRot = this.yBodyRot;
            if (this.hasCustomName()) {
                cowentity.setCustomName(this.getCustomName());
                cowentity.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isPersistenceRequired()) {
                cowentity.setPersistenceRequired();
            }

            cowentity.setInvulnerable(this.isInvulnerable());
            this.level.addFreshEntity(cowentity);
            List<ItemStack> items = new ArrayList<>();

            for(int i = 0; i < 3; ++i) {
                items.add(new ItemStack(BHTPvZItems.ORIGIN_MUSHROOM.get()));
                if(Math.random()<0.02f)items.add(new ItemStack(BHTPvZItems.CHLOROPHYLL.get()));
            }

            return items;
        }
    }
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.BOWL && !this.isBaby()) {
            ItemStack itemstack1;
            itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
            SuspiciousStewItem.saveMobEffect(itemstack1, this.getEffect(), this.getEffectDuration(this.getEffect()));


            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1, false);
            player.setItemInHand(hand, itemstack2);
            SoundEvent soundevent;

            soundevent = SoundEvents.MOOSHROOM_MILK;

            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(this.level.isClientSide);

        } else {
            return super.mobInteract(player, hand);
        }
    }

    public OriginMoobEntity getBreedOffspring(ServerLevel serverLevel, AgableMob mate) {
        return  BHTPvZEntityTypes.ORIGIN_MOOB.get().create(serverLevel);
    }

    private MobEffect getEffect() {
        return EffectList.EFFECT.getRandomItem(this.random).get();
    }
    private int getEffectDuration(MobEffect mobEffect) {
        if(mobEffect.isBeneficial()){
            if(mobEffect == MobEffects.SATURATION)return 10;
            return 1200;
        }else {
            return 260;
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }
}
