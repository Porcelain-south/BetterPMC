package com.zhilizhan.bpmc.client.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.zhilizhan.bpmc.common.block.BHTPvZBlocks;
import com.zhilizhan.bpmc.common.entity.normal.OriginMoobEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginMushroomLayer<T extends OriginMoobEntity> extends LayerRenderer<T, CowModel<T>> {
    public OriginMushroomLayer(IEntityRenderer<T, CowModel<T>> arg) {
        super(arg);
    }

    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isBaby() && !livingEntity.isInvisible()) {
            BlockRendererDispatcher lv = Minecraft.getInstance().getBlockRenderer();
            BlockState lv2 = BHTPvZBlocks.ORIGIN_MUSHROOM.get().defaultBlockState().getBlockState();
            int m = LivingRenderer.getOverlayCoords(livingEntity, 0.0F);
            matrixStack.pushPose();
            matrixStack.translate(0.20000000298023224, -0.3499999940395355, 0.5);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.translate(-0.5, -1, -0.5);
            lv.renderSingleBlock(lv2, matrixStack, buffer, packedLight, m);
            matrixStack.popPose();
            matrixStack.pushPose();
            matrixStack.translate(0.20000000298023224, -0.3499999940395355, 0.5);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(42.0F));
            matrixStack.translate(0.10000000149011612, 0.0, -0.6000000238418579);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.translate(-0.5, -1, -0.5);
            lv.renderSingleBlock(lv2, matrixStack, buffer, packedLight, m);
            matrixStack.popPose();
            matrixStack.pushPose();
            ((CowModel)this.getParentModel()).getHead().translateAndRotate(matrixStack);
            matrixStack.translate(0.0, -0.699999988079071, -0.20000000298023224);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.translate(-0.5, -1, -0.5);
            lv.renderSingleBlock(lv2, matrixStack, buffer, packedLight, m);
            matrixStack.popPose();
        }
    }
}
