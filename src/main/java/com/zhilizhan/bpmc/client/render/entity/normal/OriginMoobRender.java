package com.zhilizhan.bpmc.client.render.entity.normal;


import com.zhilizhan.bpmc.BPMC;
import com.zhilizhan.bpmc.client.render.layer.OriginMushroomLayer;
import com.zhilizhan.bpmc.common.entity.normal.OriginMoobEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginMoobRender extends MobRenderer<OriginMoobEntity, CowModel<OriginMoobEntity>> {
    private static final ResourceLocation ORIGIN_MOOB = BPMC.prefix("textures/entity/normal/origin_moob.png");


    public OriginMoobRender(EntityRendererManager arg) {
        super(arg, new CowModel<>(), 0.7F);
        this.addLayer(new OriginMushroomLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(OriginMoobEntity entity) {
        return ORIGIN_MOOB;
    }
}
