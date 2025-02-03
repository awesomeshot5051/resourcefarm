package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.ConcretePowderFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.RendererBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.ShovelRendererUtil.renderSwingingShovel;

public class ConcretePowderFarmRenderer extends RendererBase<ConcretePowderFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public ConcretePowderFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    @Override
    public void render(ConcretePowderFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);

        if (farm.getTimer() >= ConcretePowderFarmTileentity.getConcretePowderGenerateTime(farm)) {
            blockRenderDispatcher.renderSingleBlock(
                    Blocks.GREEN_CONCRETE_POWDER.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        } else if (farm.getTimer() >= ConcretePowderFarmTileentity.getConcretePowderBreakTime(farm)) {
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

        renderSwingingShovel(farm, matrixStack, buffer, combinedLight, combinedOverlay, farm.getShovelType(), getDirection(), farm.getTimer());
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
