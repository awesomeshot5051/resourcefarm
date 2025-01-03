package com.awesomeshot5051.resourceFarm.BlockInternalRender;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.sounds.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.mojang.math.Axis.*;

public class ShovelRendererUtil {
    private static VillagerTileentity Farm;
    private static boolean soundPlayedThisSwing = false;

    public static <T extends VillagerTileentity> void renderSwingingShovel(T farm, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, ItemStack pickaxeStack, Direction direction, long timer) {
        Farm = farm;
        // Get the item renderer
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Save the current matrix state
        matrixStack.pushPose();

        // Translate the pickaxe above the block (preserve your carefully manipulated translate values)
        matrixStack.translate(0.2, .55, 0.5); // Your values here, not to be changed

        // Apply swinging animation
        float swingProgress = (timer % 20) / 20.0f; // Progress through one swing cycle
        float angle = (float) Math.sin(swingProgress * Math.PI * 2) * 30; // Shovel moves up and down
        matrixStack.mulPose(ZP.rotationDegrees(90));
        switch (direction) {
            case EAST:
                matrixStack.mulPose(YP.rotationDegrees(90));
//                matrixStack.mulPose(XP.rotationDegrees(-45));
//                axis=XP;
                matrixStack.translate(-.2, -.6, 0);
                // Rotate 90 degrees to the east
                break;
            case WEST:
                matrixStack.mulPose(YP.rotationDegrees(-90));
                matrixStack.mulPose(ZP.rotationDegrees(-90));// Rotate 270 degrees to the west
                matrixStack.translate(0, -0.3, 0.01);
                break;
            case NORTH:
                matrixStack.mulPose(YP.rotationDegrees(90));
                matrixStack.mulPose(ZP.rotationDegrees(90));
                matrixStack.translate(-.5, -.3, 0);// Rotate 180 degrees to the north
                break;
            case SOUTH:
                matrixStack.mulPose(YP.rotationDegrees(90));
                matrixStack.mulPose(ZP.rotationDegrees(-90));
                // No rotation needed for south, as it's the default
                matrixStack.translate(0, -0.3, 0);
                break;
            default:
                break;
        }
        matrixStack.mulPose(XP.rotationDegrees(angle));
        // Scale the pickaxe to make it fit
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        // Play sound only once per swing
        if (angle >= 45 && !soundPlayedThisSwing && Main.CLIENT_CONFIG.pickaxeSoundRendered.get()) {
            playSound(Objects.requireNonNull(farm.getLevel()), farm.getBlockState(), ModSounds.SHOVEL_SOUND.get());
            soundPlayedThisSwing = true;
        } else if (swingProgress < 0.5) { // Reset the flag in the first half of the swing cycle
            soundPlayedThisSwing = false;
        }
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

    private static void playSound(@NotNull Level level, BlockState state, SoundEvent sound) {
        Vec3i vec3i = state.getValue(FakeWorldTileentity.FACING).getNormal();
        double d0 = Farm.getBlockPos().getX() + 0.5D + (double) vec3i.getX() / 2.0D;
        double d1 = Farm.getBlockPos().getY() + 0.5D + (double) vec3i.getY() / 2.0D;
        double d2 = Farm.getBlockPos().getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
        level.playLocalSound(d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F, true);
    }
}
