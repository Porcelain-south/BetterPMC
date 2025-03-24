package com.zhilizhan.bpmc.client.render.entity.plant.ice;


import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bpmc.client.model.entity.plant.ice.IceCabbagePultModel;
import com.zhilizhan.bpmc.common.entity.plant.ice.IceCabbagePultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceCabbagePultRender extends PVZPlantRender<IceCabbagePultEntity> {
	public IceCabbagePultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IceCabbagePultModel(), 0.5f);
	}
}
