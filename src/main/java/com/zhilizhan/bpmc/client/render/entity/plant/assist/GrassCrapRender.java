package com.zhilizhan.bpmc.client.render.entity.plant.assist;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bpmc.client.model.entity.plant.assist.GrassCarpModel;
import com.zhilizhan.bpmc.common.entity.plant.assist.GrassCarpEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassCrapRender extends PVZPlantRender<GrassCarpEntity> {

    public GrassCrapRender(EntityRendererManager arg) {
        super(arg, new GrassCarpModel(), 0.6F);
    }

}
