package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.client.model.data.*;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.ShovelRendererUtil.*;


public class SnowFarmRenderer extends RendererBase<SnowFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public SnowFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    @Override
    public void render(SnowFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level level = farm.getLevel();
        assert level != null;
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0.2, 0.5);
        if (farm.getTimer() >= SnowFarmTileentity.getSnowGenerateTime(farm)) {
            if (farm.redstoneUpgradeEnabled && level.hasNeighborSignal(farm.getBlockPos())) {
                blockRenderDispatcher.renderSingleBlock(
                        Blocks.SNOW_BLOCK.defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );
            } else blockRenderDispatcher.renderSingleBlock(
                    Blocks.SNOW_BLOCK.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        } else if (farm.getTimer() >= SnowFarmTileentity.getSnowBreakTime(farm)) {
            blockRenderDispatcher.renderSingleBlock(
                    Blocks.AIR.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        }

        matrixStack.popPose();

        if (farm.redstoneUpgradeEnabled) {
            if (level.hasNeighborSignal(farm.getBlockPos())) {
                renderSwingingShovel(farm, matrixStack, buffer, combinedLight, combinedOverlay, farm.getShovelType(), getDirection(), farm.getTimer());
            }
        } else {
            renderSwingingShovel(farm, matrixStack, buffer, combinedLight, combinedOverlay, farm.getShovelType(), getDirection(), farm.getTimer());
        }
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
}
