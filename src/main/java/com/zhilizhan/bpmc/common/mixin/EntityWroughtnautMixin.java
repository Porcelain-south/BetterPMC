package com.zhilizhan.bpmc.common.mixin;

import com.bobmowzie.mowziesmobs.server.entity.MowzieEntity;
import com.bobmowzie.mowziesmobs.server.entity.wroughtnaut.EntityWroughtnaut;
import com.bobmowzie.mowziesmobs.server.sound.MMSounds;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//@Mixin(EntityWroughtnaut.class)
@Mixin(value = EntityWroughtnaut.class, remap=false)
public abstract class EntityWroughtnautMixin  extends MowzieEntity {
    @Shadow public boolean vulnerable;

    public EntityWroughtnautMixin(EntityType<? extends MowzieEntity> type, World world) {
        super(type, world);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean hurt(DamageSource source, float amount) {
    Entity entitySource = source.getEntity();
        if (entitySource != null) {
        if ((!this.active || this.getTarget() == null) && entitySource instanceof LivingEntity && (!(entitySource instanceof PlayerEntity) || !((PlayerEntity)entitySource).isCreative()) && !(entitySource instanceof EntityWroughtnaut)) {
            this.setTarget((LivingEntity) entitySource);
        }

        if (this.vulnerable) {
            int arc = 220;
            float entityHitAngle = (float)((Math.atan2(entitySource.getZ() - this.getZ(), entitySource.getX() - this.getX()) * 57.29577951308232 - 90.0) % 360.0);
            float entityAttackingAngle = this.yBodyRot % 360.0F;
            if (entityHitAngle < 0.0F) {
                entityHitAngle += 360.0F;
            }

            if (entityAttackingAngle < 0.0F) {
                entityAttackingAngle += 360.0F;
            }

            float entityRelativeAngle = entityHitAngle - entityAttackingAngle;
            if ((!(entityRelativeAngle <= (float)arc / 2.0F) || !(entityRelativeAngle >= (float)(-arc) / 2.0F)) && !(entityRelativeAngle >= 360.0F - (float)arc / 2.0F) && !(entityRelativeAngle <= (float)(-arc) + 45.0F)) {
                this.setAnimation(NO_ANIMATION);
                if(source instanceof PVZEntityDamageSource && amount<=7.5F){
                    return super.hurt(source, 7.5F);
                }
                return super.hurt(source, amount);
            }

            this.playSound((SoundEvent)MMSounds.ENTITY_WROUGHT_UNDAMAGED.get(), 0.4F, 2.0F);
            return false;
        }

        this.playSound(MMSounds.ENTITY_WROUGHT_UNDAMAGED.get(), 0.4F, 2.0F);
    } else if (source.isBypassInvul()) {
        return super.hurt(source, amount);
    }

        return false;
}
}
