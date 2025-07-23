package com.zhilizhan.bpmc.common.entity.plant.assist;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bpmc.common.impl.BHTPvZPlants;
import com.zhilizhan.bpmc.common.impl.BHTPvZSkill;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class GrassCarpEntity extends PVZPlantEntity {
    private final float EFFECT_CD = this.getHealSpeed();
    public GrassCarpEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new SwimGoal(this));
    }

    @Override
    public void startSuperMode(boolean first) {
        super.startSuperMode(first);
        if(!level.isClientSide) {
            float range = this.getEffectRange();
            EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, 2.5F)).forEach(entity -> {
                if (entity instanceof AbstractPAZEntity) {
                    AbstractPAZEntity friendlyPAZEntity = (AbstractPAZEntity) entity;
                    if(friendlyPAZEntity.getInnerDefenceLife() < this.getSuperDefenceLife())
                    {
                        friendlyPAZEntity.setInnerDefenceLife(this.getSuperDefenceLife());
                    }
                }
            });
        }
    }

    public float getSuperDefenceLife() {
        return 50;
    }

    @Override
    public void normalPlantTick() {
        super.normalPlantTick();

        if (!this.level.isClientSide && this.getExistTick() % EFFECT_CD == 0) {
            this.giveHealToFriendly();
        }
    }
    private void giveHealToFriendly() {
        float range = this.getEffectRange();
        EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range*2, 2.5F)).forEach((entity) -> {

            if (entity instanceof GrassCarpEntity && entity!=this) {
                entity.kill();
            }

        });
        EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, 2.5F)).forEach((entity) -> {

            if (entity.getHealth()<entity.getMaxHealth()) {
                entity.heal(Math.min(Math.max(4f,entity.getMaxHealth()/5),20));
            }

        });
    }

    public float getHealSpeed() {
        return (this.getSkillValue(BHTPvZSkill.GRASS_CARP_HEAL_SPEED)*20);
    }

    public float getEffectRange() {
        return 16;
    }
    protected float getLife() {
        return this.getSkillValue(BHTPvZSkill.GRASS_CARP_MORE_HEALTH);
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.8F, 0.8F);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.GRASS_CARP;
    }

    @Override
    public int getSuperTimeLength() {
        return 20;
    }
}