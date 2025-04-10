package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import appeng.core.definitions.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.data.*;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.PickaxeRendererUtil.*;
import static com.mojang.math.Axis.*;

public class CertusQuartzCrystalFarmRenderer extends RendererBase<CertusQuartzCrystalFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public CertusQuartzCrystalFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        this.itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(CertusQuartzCrystalFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level level = farm.getLevel();
        assert level != null;
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);

        double generateTime = CertusQuartzCrystalFarmTileentity.getCertusQuartzGenerateTime(farm);
        double breakTime = CertusQuartzCrystalFarmTileentity.getCertusQuartzBreakTime(farm);

        matrixStack.pushPose();
        matrixStack.scale(.4f, .4f, .4f);
        matrixStack.translate(.6f, .6f, .8f);
        if (farm.redstoneUpgradeEnabled) {
            if (!level.hasNeighborSignal(farm.getBlockPos())) {
                return;
            }
        }
        if (farm.getTimer() < generateTime) {
            blockRenderDispatcher.renderSingleBlock(
                    AEBlocks.CHIPPED_BUDDING_QUARTZ.block().defaultBlockState(),
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
                buddingBlock = AEBlocks.DAMAGED_BUDDING_QUARTZ.block();
                crystalBlock = Block.byItem(AEBlocks.SMALL_QUARTZ_BUD.item().get());
            } else if (progress < 0.5) {
                buddingBlock = AEBlocks.CHIPPED_BUDDING_QUARTZ.block();
                crystalBlock = Block.byItem(AEBlocks.MEDIUM_QUARTZ_BUD.item().get());
            } else if (progress < 0.75) {
                buddingBlock = AEBlocks.FLAWED_BUDDING_QUARTZ.block();
                crystalBlock = Block.byItem(AEBlocks.LARGE_QUARTZ_BUD.item().get());
            } else {
                buddingBlock = AEBlocks.FLAWLESS_BUDDING_QUARTZ.block();
                crystalBlock = Block.byItem(AEBlocks.QUARTZ_CLUSTER.item().get());
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

            renderCrystalsOnAllSides(matrixStack, buffer, combinedLight, combinedOverlay, crystalBlock);


        } else {
            blockRenderDispatcher.renderSingleBlock(
                    AEBlocks.CHIPPED_BUDDING_QUARTZ.block().defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        }

        matrixStack.popPose();
        renderSwingingPickaxe(farm, matrixStack, buffer, combinedLight, combinedOverlay, farm.getPickType(), getDirection(), farm.getTimer());
    }


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
