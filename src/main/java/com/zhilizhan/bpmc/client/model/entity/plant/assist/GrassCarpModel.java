package com.zhilizhan.bpmc.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bpmc.common.entity.plant.assist.GrassCarpEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GrassCarpModel extends PVZPlantModel<GrassCarpEntity> {
    private final ModelRenderer total;
    private final ModelRenderer tails_r1;
    private final ModelRenderer tailn_r1;
    private final ModelRenderer leaf;
    private final ModelRenderer tail;
    private final ModelRenderer right;
    private final ModelRenderer right_r1;
    private final ModelRenderer left;
    private final ModelRenderer left_r1;

    public GrassCarpModel() {
        texWidth = 32;
        texHeight = 32;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);
        total.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
        total.texOffs(10, 12).addBox(-1.0F, -8.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        total.texOffs(1, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

        tails_r1 = new ModelRenderer(this);
        tails_r1.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(tails_r1);
        setRotationAngle(tails_r1, 0.0F, 0.0F, 0.3491F);
        tails_r1.texOffs(0, 22).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        tailn_r1 = new ModelRenderer(this);
        tailn_r1.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(tailn_r1);
        setRotationAngle(tailn_r1, 0.0F, 0.0F, -0.3491F);
        tailn_r1.texOffs(0, 22).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        leaf = new ModelRenderer(this);
        leaf.setPos(0.0F, -6.0F, 3.0F);
        total.addChild(leaf);
        leaf.texOffs(0, 12).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 2.5F, 5.0F);
        total.addChild(tail);
        tail.texOffs(24, 0).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        tail.texOffs(24, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        right = new ModelRenderer(this);
        right.setPos(-3.0F, -3.0F, 0.0F);
        total.addChild(right);


        right_r1 = new ModelRenderer(this);
        right_r1.setPos(0.0F, 0.0F, 0.0F);
        right.addChild(right_r1);
        setRotationAngle(right_r1, 0.0F, 0.0F, -0.5236F);
        right_r1.texOffs(18, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        left = new ModelRenderer(this);
        left.setPos(3.0F, -3.0F, 0.0F);
        total.addChild(left);


        left_r1 = new ModelRenderer(this);
        left_r1.setPos(0.0F, 0.0F, 0.0F);
        left.addChild(left_r1);
        setRotationAngle(left_r1, 0.0F, 0.0F, 0.5236F);
        left_r1.texOffs(18, 3).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }


    public void setupAnim(GrassCarpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.right.zRot = -0.2F + 0.4F * MathHelper.sin(ageInTicks * 0.2F);
        this.left.zRot = 0.2F - 0.4F * MathHelper.sin(ageInTicks * 0.2F);
       // this.leaf.zRot = 0.4F - 0.3F * Mth.sin(ageInTicks * 0.1F);
    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        total.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelRenderer getPlantWholeBody() {
        return total;
    }
}

