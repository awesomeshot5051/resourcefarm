package com.awesomeshot5051.resourceFarm.integration.ae2.extendedae;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.data.*;

import static com.mojang.math.Axis.*;


public class EntroCrystalFarmRenderer extends RendererBase<EntroCrystalFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public EntroCrystalFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(EntroCrystalFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (!farm.getEntroRequiremnts().isEmpty() && farm.checkPasses(farm)) {

            Level level = farm.getLevel();
            assert level != null;
            super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);

            double generateTime = EntroCrystalFarmTileentity.getEntroCrystalGenerateTime(farm);
            double breakTime = EntroCrystalFarmTileentity.getEntroCrystalBreakTime(farm);

            matrixStack.pushPose();
            matrixStack.scale(.3f, .3f, .3f);
            matrixStack.translate(1f, 1f, 1f);

            if (farm.getTimer() < generateTime) {
                blockRenderDispatcher.renderSingleBlock(
                        Block.byItem(AE2Blocks.ENTRO_BUDDING_HARDLY.get()).defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );
                matrixStack.popPose();
                return;
            }

            if (farm.getTimer() < breakTime) {
                double progress = (farm.getTimer() - generateTime) / (breakTime - generateTime);

                Block buddingBlock;
                Block crystalBlock;
                if (progress < 0.25) {
                    buddingBlock = Block.byItem(AE2Blocks.ENTRO_BUDDING_HARDLY.get());
                    crystalBlock = Block.byItem(AE2Blocks.SMALL_ENTRO_BUD.get());
                } else if (progress < 0.5) {
                    buddingBlock = Block.byItem(AE2Blocks.ENTRO_BUDDING_HALF.get());
                    crystalBlock = Block.byItem(AE2Blocks.MEDIUM_ENTRO_BUD.get());
                } else if (progress < 0.75) {
                    buddingBlock = Block.byItem(AE2Blocks.ENTRO_BUDDING_MOSTLY.get());
                    crystalBlock = Block.byItem(AE2Blocks.LARGE_ENTRO_BUD.get());
                } else {
                    buddingBlock = Block.byItem(AE2Blocks.ENTRO_BUDDING_FULLY.get());
                    crystalBlock = Block.byItem(AE2Blocks.ENTRO_CLUSTER.get());
                }

                blockRenderDispatcher.renderSingleBlock(
                        buddingBlock.defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );
                // Render the crystal on all six sides
                renderCrystalsOnAllSides(matrixStack, buffer, combinedLight, combinedOverlay, crystalBlock);

            } else {
                blockRenderDispatcher.renderSingleBlock(
                        Block.byItem(AE2Blocks.ENTRO_BUDDING_HARDLY.get()).defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );
            }

            matrixStack.popPose();

        } else {
            matrixStack.pushPose();
            matrixStack.scale(.3f, .3f, .3f);
            matrixStack.translate(1f, 1f, 1f);

            for (ItemStack stack : farm.getEntroRequiremnts()) {
                itemRenderer.renderStatic(
                        stack,
                        ItemDisplayContext.GROUND,
                        combinedLight,
                        combinedOverlay,
                        matrixStack,
                        buffer,
                        null,
                        0
                );
            }

            matrixStack.popPose();
        }
    }

    /**
     * Renders the crystal on all six sides of the cube.
     */
    private void renderCrystalsOnAllSides(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, Block crystalBlock) {
        float scale = 0.5f;
        float offset = 1.0f;

        // Top
        matrixStack.pushPose();
        matrixStack.translate(0.2, offset, 0.2);
        matrixStack.scale(scale, scale, scale);
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();

        // Bottom
        matrixStack.pushPose();
        matrixStack.translate(0.8, -offset + 1.1, 0.2);
        matrixStack.scale(scale, scale, scale);
        matrixStack.mulPose(ZP.rotationDegrees(180));
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();

        // Front
        matrixStack.pushPose();
        matrixStack.translate(.3, 0.3, -offset + 1);
        matrixStack.mulPose(XP.rotationDegrees(-90));
        matrixStack.scale(scale, scale, scale);
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();

        // Back
        matrixStack.pushPose();
        matrixStack.translate(0.2, 0.8, offset);
        matrixStack.mulPose(XP.rotationDegrees(90));
        matrixStack.scale(scale, scale, scale);
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();

        // Left
        matrixStack.pushPose();
        matrixStack.translate(-offset + 1, 0.3, 0.2);
        matrixStack.mulPose(ZP.rotationDegrees(90));
        matrixStack.scale(scale, scale, scale);
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();

        // Right
        matrixStack.pushPose();
        matrixStack.translate(offset, 0.8, 0.3);
        matrixStack.mulPose(ZP.rotationDegrees(-90));
        matrixStack.scale(scale, scale, scale);
        blockRenderDispatcher.renderSingleBlock(crystalBlock.defaultBlockState(), matrixStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.SOLID);
        matrixStack.popPose();
    }

}

