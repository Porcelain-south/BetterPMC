package com.zhilizhan.bhtpvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.GrassCarpEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class GrassCarpModel extends PVZPlantModel<GrassCarpEntity> {
    private final ModelPart total;
    private final ModelPart tails_r1;
    private final ModelPart tailn_r1;
    private final ModelPart leaf;
    private final ModelPart tail;
    private final ModelPart right;
    private final ModelPart right_r1;
    private final ModelPart left;
    private final ModelPart left_r1;

    public GrassCarpModel() {
        texWidth = 32;
        texHeight = 32;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);
        total.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
        total.texOffs(10, 12).addBox(-1.0F, -8.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        total.texOffs(1, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

        tails_r1 = new ModelPart(this);
        tails_r1.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(tails_r1);
        setRotationAngle(tails_r1, 0.0F, 0.0F, 0.3491F);
        tails_r1.texOffs(0, 22).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        tailn_r1 = new ModelPart(this);
        tailn_r1.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(tailn_r1);
        setRotationAngle(tailn_r1, 0.0F, 0.0F, -0.3491F);
        tailn_r1.texOffs(0, 22).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        leaf = new ModelPart(this);
        leaf.setPos(0.0F, -6.0F, 3.0F);
        total.addChild(leaf);
        leaf.texOffs(0, 12).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, 2.5F, 5.0F);
        total.addChild(tail);
        tail.texOffs(24, 0).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        tail.texOffs(24, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        right = new ModelPart(this);
        right.setPos(-3.0F, -3.0F, 0.0F);
        total.addChild(right);


        right_r1 = new ModelPart(this);
        right_r1.setPos(0.0F, 0.0F, 0.0F);
        right.addChild(right_r1);
        setRotationAngle(right_r1, 0.0F, 0.0F, -0.5236F);
        right_r1.texOffs(18, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        left = new ModelPart(this);
        left.setPos(3.0F, -3.0F, 0.0F);
        total.addChild(left);


        left_r1 = new ModelPart(this);
        left_r1.setPos(0.0F, 0.0F, 0.0F);
        left.addChild(left_r1);
        setRotationAngle(left_r1, 0.0F, 0.0F, 0.5236F);
        left_r1.texOffs(18, 3).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }


    public void setupAnim(GrassCarpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.right.zRot = -0.2F + 0.4F * Mth.sin(ageInTicks * 0.2F);
        this.left.zRot = 0.2F - 0.4F * Mth.sin(ageInTicks * 0.2F);
       // this.leaf.zRot = 0.4F - 0.3F * Mth.sin(ageInTicks * 0.1F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        total.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPlantWholeBody() {
        return total;
    }
}

