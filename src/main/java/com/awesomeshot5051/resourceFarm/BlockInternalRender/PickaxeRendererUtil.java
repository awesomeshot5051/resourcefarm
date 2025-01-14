package com.awesomeshot5051.resourceFarm.BlockInternalRender;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.FakeWorldTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import com.awesomeshot5051.resourceFarm.sounds.ModSounds;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.mojang.math.Axis.YP;
import static com.mojang.math.Axis.ZP;

public class PickaxeRendererUtil {
    private static VillagerTileentity Farm;

    // Add a static variable to track when the sound has been played in the current swing
    private static boolean soundPlayedThisSwing = false;

    public static <T extends VillagerTileentity> void renderSwingingPickaxe(T farm, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, ItemStack pickaxeStack, Direction direction, long timer) {
        Farm = farm;

        // Item renderer
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Save matrix state
        matrixStack.pushPose();

        // Translate the pickaxe above the block
        matrixStack.translate(0.2, .55, 0.5);

        // Swing animation
        float swingProgress = (timer % 20) / 20.0f; // Progress through one swing cycle
        float angle = (float) Math.sin(swingProgress * Math.PI * 2) * 45; // Swing back and forth

        switch (direction) {
            case EAST:
                matrixStack.mulPose(YP.rotationDegrees(90));
                matrixStack.translate(-0.3, 0, 0.3);
                break;
            case WEST:
                matrixStack.mulPose(YP.rotationDegrees(270));
                matrixStack.translate(-0.3, 0, -0.3);
                break;
            case NORTH:
                matrixStack.mulPose(YP.rotationDegrees(180));
                matrixStack.translate(-0.6, 0, 0);
                break;
            case SOUTH:
                matrixStack.translate(0, 0, 0);
                break;
            default:
                break;
        }

        matrixStack.mulPose(ZP.rotationDegrees(angle));

        // Play sound only once per swing
//        if (farm.getSound()) {
        if (angle >= 45 && !soundPlayedThisSwing && Main.CLIENT_CONFIG.pickaxeSoundRendered.get()) {
            playSound(Objects.requireNonNull(farm.getLevel()), farm.getBlockState(), ModSounds.PICKAXE_SOUND.get());
            soundPlayedThisSwing = true;
        } else if (swingProgress < 0.5) { // Reset the flag in the first half of the swing cycle
            soundPlayedThisSwing = false;
        }
//        }

        // Scale the pickaxe
        matrixStack.scale(0.5f, 0.5f, 0.5f);

        // Render the pickaxe
        itemRenderer.renderStatic(
                pickaxeStack,
                ItemDisplayContext.GROUND,
                combinedLight,
                combinedOverlay,
                matrixStack,
                buffer,
                farm.getLevel(),
                0
        );

        // Restore matrix state
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

