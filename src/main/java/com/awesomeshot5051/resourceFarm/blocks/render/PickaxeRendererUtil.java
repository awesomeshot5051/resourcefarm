package com.awesomeshot5051.resourceFarm.blocks.render;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import static com.mojang.math.Axis.YP;
import static com.mojang.math.Axis.ZP;

public class PickaxeRendererUtil {

    public static <T extends VillagerTileentity> void renderSwingingPickaxe(T farm, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, ItemStack pickaxeStack, Direction direction, long timer) {
        // Get the item renderer
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Save the current matrix state
        matrixStack.pushPose();

        // Translate the pickaxe above the block (preserve your carefully manipulated translate values)
        matrixStack.translate(0.2, .55, 0.5); // Your values here, not to be changed

        // Apply swinging animation
        float swingProgress = (timer % 20) / 20.0f; // Progress through one swing cycle
        float angle = (float) Math.sin(swingProgress * Math.PI * 2) * 45; // Swing back and forth
        switch (direction) {
            case EAST:
                matrixStack.mulPose(YP.rotationDegrees(90));
//                axis=XP;
                matrixStack.translate(-0.3, 0, 0.3);
                // Rotate 90 degrees to the east
                break;
            case WEST:
                matrixStack.mulPose(YP.rotationDegrees(270));// Rotate 270 degrees to the west
                matrixStack.translate(-0.3, 0, -0.3);
                break;
            case NORTH:
                matrixStack.mulPose(YP.rotationDegrees(180));
                matrixStack.translate(-0.6, 0, 0);// Rotate 180 degrees to the north
                break;
            case SOUTH:
                // No rotation needed for south, as it's the default
                matrixStack.translate(0, 0, 0);
                break;
            default:
                break;
        }
        matrixStack.mulPose(ZP.rotationDegrees(angle));
        // Scale the pickaxe to make it fit
        matrixStack.scale(0.5f, 0.5f, 0.5f);

        // Render the pickaxe
        itemRenderer.renderStatic(
                pickaxeStack,
                ItemDisplayContext.GROUND,
                combinedLight,
                combinedOverlay,
                matrixStack,
                buffer,
                farm.getLevel()
                ,
                0
        );

        // Restore the previous matrix state
        matrixStack.popPose();
    }

}

