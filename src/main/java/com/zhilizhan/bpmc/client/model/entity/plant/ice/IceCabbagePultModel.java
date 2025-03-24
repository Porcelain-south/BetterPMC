package com.zhilizhan.bpmc.client.model.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bpmc.common.entity.plant.ice.IceCabbagePultEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class IceCabbagePultModel extends PVZPlantModel<IceCabbagePultEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cabbage;
	private final ModelRenderer dicoration;
	private final ModelRenderer pult;
	private final ModelRenderer out;
	private final ModelRenderer bullet;

	public IceCabbagePultModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0f, 24.0f, 0.0f);


		cabbage = new ModelRenderer(this);
		cabbage.setPos(0.0f, 0.0f, 0.0f);
		total.addChild(cabbage);
		cabbage.texOffs(0, 0).addBox(-7.0f, -1.0f, -7.0f, 14.0f, 1.0f, 14.0f, 0.0f, false);
		cabbage.texOffs(4, 47).addBox(-6.0f, -0.1f, -6.0f, 12.0f, 1.0f, 12.0f, 0.0f, false);
		cabbage.texOffs(0, 28).addBox(-4.5f, -8.0f, -4.5f, 9.0f, 8.0f, 9.0f, 0.0f, false);
		cabbage.texOffs(32, 20).addBox(-4.0f, -9.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);

		dicoration = new ModelRenderer(this);
		dicoration.setPos(0.0f, 0.0f, 0.0f);
		cabbage.addChild(dicoration);
		dicoration.texOffs(0, 15).addBox(-5.0f, -7.0f, -5.0f, 10.0f, 3.0f, 10.0f, 0.0f, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0f, -9.0f, 0.0f);
		total.addChild(pult);
		setRotationAngle(pult, -0.4363f, 0.0f, 0.0f);
		pult.texOffs(8, 0).addBox(-1.0f, -6.0f, -1.0f, 2.0f, 7.0f, 1.0f, 0.0f, false);

		out = new ModelRenderer(this);
		out.setPos(0.0f, -6.0f, -1.0f);
		pult.addChild(out);
		setRotationAngle(out, -1.309f, 0.0f, 0.0f);
		out.texOffs(0, 0).addBox(-1.0f, -6.0f, 0.0f, 2.0f, 6.0f, 2.0f, 0.0f, false);
		out.texOffs(42, 0).addBox(-3.0f, -12.0f, 0.0f, 6.0f, 6.0f, 4.0f, 0.0f, false);

		bullet = new ModelRenderer(this);
		bullet.setPos(0.0f, -9.0f, 1.0f);
		out.addChild(bullet);
		setRotationAngle(bullet, 1.5708f, 0.0f, 0.0f);
		bullet.texOffs(0, 45).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
		bullet.texOffs(0, 53).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, 0.5f, false);
	}

	@Override
	public void setupAnim(IceCabbagePultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0f / entity.getPultAnimTime();
			pult.xRot = (1.0f - MathHelper.abs(MathHelper.cos(percent * 3.14159f))) * 1.5f;
			this.bullet.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.bullet.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}
}
