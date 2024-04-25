package com.zhilizhan.bhtpvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.GrassCarpModel;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.GrassCarpEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassCrapRender extends PVZPlantRender<GrassCarpEntity> {

    public GrassCrapRender(EntityRenderDispatcher arg) {
        super(arg, new GrassCarpModel(), 0.6F);
    }

}
