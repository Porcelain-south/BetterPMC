package com.zhilizhan.bhtpvz.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.normal.OriginMoobEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginMushroomLayer<T extends OriginMoobEntity> extends RenderLayer<T, CowModel<T>> {
    public OriginMushroomLayer(RenderLayerParent<T, CowModel<T>> arg) {
        super(arg);
    }

    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isBaby() && !livingEntity.isInvisible()) {
            BlockRenderDispatcher lv = Minecraft.getInstance().getBlockRenderer();
            BlockState lv2 = BHTPvZBlocks.ORIGIN_MUSHROOM.get().defaultBlockState().getBlockState();
            int m = LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F);
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
