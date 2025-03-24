package com.zhilizhan.bpmc.common.entity.normal;

import com.zhilizhan.bpmc.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bpmc.common.item.BHTPvZItems;
import com.zhilizhan.bpmc.common.list.EffectList;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OriginMoobEntity extends CowEntity implements IForgeShearable {


    public OriginMoobEntity(EntityType<? extends CowEntity> arg, World arg2) {
        super(arg, arg2);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (world.isClientSide() || this.isBaby()) {
            return Collections.emptyList();
        } else {
            ((ServerWorld)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            this.remove();
            CowEntity cowentity = EntityType.COW.create(this.level);
            if (cowentity != null) {
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
            }
            this.level.addFreshEntity(cowentity);
            List<ItemStack> items = new ArrayList<>();

            for(int i = 0; i < 3; ++i) {
                items.add(new ItemStack(BHTPvZItems.ORIGIN_MUSHROOM.get()));
                if(Math.random()<0.1f)items.add(new ItemStack(BHTPvZItems.CHLOROPHYLL.get()));
            }

            return items;
        }
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.BOWL && !this.isBaby()) {
            ItemStack itemstack1;
            itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
            SuspiciousStewItem.saveMobEffect(itemstack1, this.getEffect(), this.getEffectDuration(this.getEffect()));


            ItemStack itemstack2 = DrinkHelper.createFilledResult(itemstack, player, itemstack1, false);
            player.setItemInHand(hand, itemstack2);
            SoundEvent soundevent;

            soundevent = SoundEvents.MOOSHROOM_MILK;

            this.playSound(soundevent, 1.0F, 1.0F);
            return ActionResultType.sidedSuccess(this.level.isClientSide);

        } else {
            return super.mobInteract(player, hand);
        }
    }

    public OriginMoobEntity getBreedOffspring(ServerWorld serverLevel, AgeableEntity mate) {
        return  BHTPvZEntityTypes.ORIGIN_MOOB.get().create(serverLevel);
    }

    private Effect getEffect() {
        return EffectList.EFFECT.getRandomItem(this.random).get();
    }

    private int getEffectDuration(Effect mobEffect) {
        if(mobEffect.isBeneficial()){
            if(mobEffect == Effects.SATURATION)return 10;
            return 1200;
        }else {
            return 260;
        }
    }
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }
}
