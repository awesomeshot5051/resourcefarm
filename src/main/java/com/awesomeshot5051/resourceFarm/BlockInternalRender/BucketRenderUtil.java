package com.awesomeshot5051.resourceFarm.BlockInternalRender;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
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
    public static <T extends VillagerTileentity> void renderWaterSplash(
            T farm,
            PoseStack matrixStack,
            MultiBufferSource buffer,
            int combinedLight,
            int combinedOverlay,
            Direction direction,
            long timer) {

        // Save the current matrix state
        matrixStack.pushPose();
        BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();

        // Translate to the top of the block
        matrixStack.translate(0.5, 1.0, 0.5); // Centered above the block

        // Add rotation based on the block's facing direction
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
                // Default facing (south), no rotation needed
                break;
        }

        // Animate the water splash (using a sine wave for smooth scaling)
        float splashProgress = (timer % 20) / 20.0f; // Progress through one splash cycle
        float splashScale = 0.5f + (float) Math.sin(splashProgress * Math.PI) * 0.2f; // Oscillate scale

        // Scale for the splash animation
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
        // Render a semi-transparent blue quad to simulate water splash
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.translucent());
//        renderSplashQuad(matrixStack, vertexConsumer, combinedLight, combinedOverlay);

        // Restore the previous matrix state
        matrixStack.popPose();
    }

    /**
     * Helper method to render a semi-transparent splash quad.
     */
    private static void renderSplashQuad(PoseStack matrixStack, VertexConsumer vertexConsumer, int combinedLight, int combinedOverlay) {
        Matrix4f pose = matrixStack.last().pose();

        // Define the color for water splash (RGBA)
        float r = 0.2f; // Light blue
        float g = 0.5f;
        float b = 1.0f;
        float alpha = 0.7f; // Semi-transparent

        // Define the quad vertices
        vertexConsumer.addVertex(pose, -0.5f, 0.0f, -0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, 0.5f, 0.0f, -0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, 0.5f, 0.0f, 0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
        vertexConsumer.addVertex(pose, -0.5f, 0.0f, 0.5f).setColor(r, g, b, alpha).setUv(combinedLight, combinedOverlay).setUv2(combinedLight, combinedOverlay);
    }

}
