package com.awesomeshot5051.resourceFarm.BlockInternalRender;

import com.awesomeshot5051.corelib.blockentity.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.data.*;
import org.joml.*;

import java.lang.Math;

import static com.mojang.math.Axis.*;

@SuppressWarnings("ALL")
public class BucketRenderUtil {
    public static <T extends FarmTileentity> void renderWaterSplash(
            T farm,
            PoseStack matrixStack,
            MultiBufferSource buffer,
            int combinedLight,
            int combinedOverlay,
            Direction direction,
            long timer) {


        matrixStack.pushPose();
        BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();


        matrixStack.translate(0.5, 1.0, 0.5);


        switch (direction) {
            case EAST:
                matrixStack.mulPose(YP.rotationDegrees(90));
                break;
            case WEST:
                matrixStack.mulPose(YP.rotationDegrees(270));
                break;
            case NORTH:
                matrixStack.mulPose(YP.rotationDegrees(180));
                break;
            case SOUTH:
            default:

                break;
        }


        float splashProgress = (timer % 20) / 20.0f;
        float splashScale = 0.5f + (float) Math.sin(splashProgress * Math.PI) * 0.2f;


        matrixStack.scale(splashScale, splashScale, splashScale);
        blockRenderDispatcher.renderSingleBlock(
                Blocks.WATER.defaultBlockState(),
                matrixStack,
                buffer,
                combinedLight,
                combinedOverlay,
                ModelData.EMPTY,
                RenderType.SOLID
        );

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.translucent());


        matrixStack.popPose();
    }


    private static void renderSplashQuad(PoseStack matrixStack, VertexConsumer vertexConsumer, int combinedLight, int combinedOverlay) {
        Matrix4f pose = matrixStack.last().pose();


        float r = 0.2f;
        float g = 0.5f;
        float b = 1.0f;
        float alpha = 0.7f;


        vertexConsumer.addVertex(pose, -0.5f, 0.0f, -0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, 0.5f, 0.0f, -0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, 0.5f, 0.0f, 0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, -0.5f, 0.0f, 0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
    }

}
