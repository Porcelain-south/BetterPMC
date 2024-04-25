package com.zhilizhan.bhtpvz.common.entity.plant.assist;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;

public class GrassCarpEntity extends PVZPlantEntity {
    private final float EFFECT_CD = this.getHealSpeed();
    public GrassCarpEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FloatGoal(this));
      }

    public void normalPlantTick() {
        super.normalPlantTick();

        if (!this.level.isClientSide && this.getExistTick() % EFFECT_CD == 0) {
            this.giveHealToFriendly();
        }
    }
    private void giveHealToFriendly() {
        float range = this.getEffectRange();
        EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, 2.5F)).forEach((entity) -> {
           
            if (entity.getHealth()<entity.getMaxHealth()) {
                entity.heal(Math.max(1f,entity.getMaxHealth()/20));
            }
            if (entity instanceof GrassCarpEntity && entity!=this) {
                entity.kill();
            }

        });
    }

    public float getHealSpeed() {
        return (this.getSkillValue(BHTPvZSkill.GRASS_CARP_HEAL_SPEED)*20);
    }

    public float getEffectRange() {
        return this.getSkillValue(BHTPvZSkill.GRASS_CARP_HEAL_RANGE);
    }
    protected float getLife() {
        return this.getSkillValue(BHTPvZSkill.GRASS_CARP_MORE_HEALTH);
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.8F, 0.8F);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.GRASS_CARP;
    }

}