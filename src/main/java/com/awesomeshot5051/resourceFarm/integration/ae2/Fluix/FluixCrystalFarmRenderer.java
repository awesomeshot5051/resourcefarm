package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.integration.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.client.extensions.common.*;
import net.neoforged.neoforge.client.model.data.*;
import net.neoforged.neoforge.fluids.*;
import org.joml.*;

import java.lang.Math;


public class FluixCrystalFarmRenderer extends RendererBase<FluixCrystalFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public FluixCrystalFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        itemRenderer = renderer.getItemRenderer();

    }

    @Override
    public void render(FluixCrystalFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        renderFluid(new FluidStack(Fluids.WATER, 1000), matrixStack, buffer, combinedLight, combinedOverlay);
        Level level = farm.getLevel();
        assert level != null;
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.translate(0.8, 0.3, 1.1); // Center position with slight lift
        double generateTime = FluixCrystalFarmTileentity.getFluixCrystalGenerateTime(farm);
        double breakTime = FluixCrystalFarmTileentity.getFluixCrystalBreakTime(farm);

        if ((!farm.getAE2ItemsList().isEmpty()) && AE2Check.containsAllItems(AE2Blocks.itemsRequiredForFC, farm.getAE2ItemsList())) {
            //renderFluid(farm, new FluidStack(Fluids.WATER, 1000), matrixStack, buffer, getLightLevel(farm.getLevel(), farm.getBlockPos()), combinedOverlay);
            if (farm.getTimer() >= generateTime) {
                if (farm.redstoneUpgradeEnabled && !level.hasNeighborSignal(farm.getBlockPos())) {
                    blockRenderDispatcher.renderSingleBlock(
                            Blocks.AIR.defaultBlockState(),
                            matrixStack,
                            buffer,
                            combinedLight,
                            combinedOverlay,
                            ModelData.EMPTY,
                            RenderType.SOLID
                    );
                } else {
                    itemRenderer.renderStatic(
                            AE2Blocks.FLUIX_CRYSTAL.get().getDefaultInstance(),
                            ItemDisplayContext.GROUND, combinedLight,
                            combinedOverlay,
                            matrixStack,
                            buffer,
                            farm.getLevel(),
                            0
                    );
                }
            } else if (farm.getTimer() >= breakTime) {
                blockRenderDispatcher.renderSingleBlock(
                        Blocks.AIR.defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );
            } else {
                // Render floating ingredients at different positions
                renderFloatingItem(Items.REDSTONE.getDefaultInstance(), matrixStack, buffer, combinedLight, combinedOverlay, 0.0, 0);
                renderFloatingItem(AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get().getDefaultInstance(), matrixStack, buffer, combinedLight, combinedOverlay, 0.2, 120);
                renderFloatingItem(Items.QUARTZ.getDefaultInstance(), matrixStack, buffer, combinedLight, combinedOverlay, -0.2, 240);
            }
        } else {
            // Render items dynamically based on how many exist
            int itemCount = farm.getAE2ItemsList().size();
            if (itemCount > 0) {
                for (int i = 0; i < itemCount; i++) {
                    ItemStack item = farm.getAE2ItemsList().get(i);
                    double angle = (360.0 / itemCount) * i; // Spread out items evenly in a circle
                    renderFloatingItem(item, matrixStack, buffer, combinedLight, combinedOverlay, 0.6, angle);
                }
            }
            if (farm.getAE2ItemsList().stream().anyMatch(item -> item.is(Items.WATER_BUCKET))) {
                //renderFluid(farm, new FluidStack(Fluids.WATER, 1000), matrixStack, buffer, getLightLevel(farm.getLevel(), farm.getBlockPos()), combinedOverlay);
            }
        }

        matrixStack.popPose();
    }

    /**
     * Renders an item floating with a circular offset.
     *
     * @param item            The item to render
     * @param matrixStack     The PoseStack for transformation
     * @param buffer          The buffer for rendering
     * @param combinedLight   Lighting value
     * @param combinedOverlay Overlay value
     * @param yOffset         Vertical offset for floating effect
     * @param angle           Angle in degrees for circular spacing
     */
    private void renderFloatingItem(ItemStack item, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, double yOffset, double angle) {
        matrixStack.pushPose();

        double radius = 0.15; // Distance from center
        double radians = Math.toRadians(angle);
        double xOffset = radius * Math.cos(radians);
        double zOffset = radius * Math.sin(radians);

        matrixStack.translate(xOffset, yOffset, zOffset);

        itemRenderer.renderStatic(
                item,
                ItemDisplayContext.GROUND, combinedLight,
                combinedOverlay,
                matrixStack,
                buffer,
                null,
                0
        );

        matrixStack.popPose();
    }


    public void renderBreakingAnimation(BlockState blockState, PoseStack matrixStack, MultiBufferSource buffer, int breakStage, int combinedLight, int combinedOverlay) {
        if (breakStage < 0 || breakStage > 9) return;


        TextureAtlasSprite breakingSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(ResourceLocation.fromNamespaceAndPath("minecraft", "block/destroy_stage_" + breakStage));


        RenderType breakingRenderType = RenderType.crumbling(breakingSprite.atlasLocation());

        blockRenderDispatcher.getModelRenderer().renderModel(
                matrixStack.last(),
                buffer.getBuffer(breakingRenderType),
                blockState,
                blockRenderDispatcher.getBlockModel(blockState),
                1.0F, 1.0F, 1.0F,
                combinedLight,
                combinedOverlay,
                ModelData.EMPTY,
                breakingRenderType);
    }


    private void renderFluid(FluidStack fluidStack, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (fluidStack.isEmpty()) {
            return;
        }
        float glassBorder = 0.06F;
        float maxHeight = 1F - 2 * glassBorder;
        float width = 1F - 2 * glassBorder;

        Fluid fluid = fluidStack.getFluid();
        ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid).getStillTexture();
        TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
        int fluidColor = IClientFluidTypeExtensions.of(fluid).getTintColor(fluidStack);


        // Get the fluid color
        float red = (fluidColor >> 16 & 0xFF) / 255.0F;
        float green = (fluidColor >> 8 & 0xFF) / 255.0F;
        float blue = (fluidColor & 0xFF) / 255.0F;
        float alpha = ((fluidColor >> 24) & 0xFF) / 255.0F;

        // Calculate fluid height based on amount
        float capacity = (float) 1000;
        float fillPercent = Math.max(0.0F, Math.min(1.0F, fluidStack.getAmount() / capacity));
        float height = maxHeight * fillPercent;

        poseStack.pushPose();

        // Adjust the rendering position
        //poseStack.translate(0f, 1.25f, 0f); // TEMP: For testing purposes (Comment this out if needed)
        poseStack.translate(glassBorder, glassBorder, glassBorder);

        VertexConsumer builder = bufferSource.getBuffer(RenderType.translucent());

        // Get the UV coordinates from the sprite
        float minU = fluidStillSprite.getU0();
        float maxU = fluidStillSprite.getU1();
        float minV = fluidStillSprite.getV0();
        float maxV = fluidStillSprite.getV1();

        // Render the fluid cube
        // Top face (Y+)
        vertex(builder, poseStack, 0, height, 0, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, height, width, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, width, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, 0, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);

// Bottom face (Y-)
        vertex(builder, poseStack, 0, 0, 0, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, 0, 0, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, 0, width, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, 0, width, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);

// North face (Z-)
        vertex(builder, poseStack, 0, 0, 0, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, height, 0, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, 0, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, 0, 0, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);

// South face (Z+)
        vertex(builder, poseStack, width, 0, width, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, width, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, height, width, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, 0, width, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);

// East face (X+)
        vertex(builder, poseStack, width, 0, 0, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, 0, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, height, width, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, width, 0, width, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);


// West face (X-)
        vertex(builder, poseStack, 0, 0, width, minU, maxV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, height, width, minU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, height, 0, maxU, minV, red, green, blue, alpha, packedLight, packedOverlay);
        vertex(builder, poseStack, 0, 0, 0, maxU, maxV, red, green, blue, alpha, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void vertex(VertexConsumer builder, PoseStack poseStack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha, int packedLight, int packedOverlay) {
        Matrix4f pose = poseStack.last().pose();

        int skyLight = LightTexture.FULL_SKY;
        int blockLight = LightTexture.FULL_BLOCK;

        builder.addVertex(pose, x, y, z)
                .setColor(red, green, blue, alpha)
                .setUv(u, v)
                .setOverlay(packedOverlay)
                .setUv2(blockLight, skyLight)
                .setNormal(0, 1, 0);
    }

    private int getLightLevel(Level level, BlockPos pos) {
        if (level == null) return 0;
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(skyLight, blockLight);
    }
}

