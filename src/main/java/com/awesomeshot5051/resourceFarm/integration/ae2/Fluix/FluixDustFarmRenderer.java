package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.corelib.integration.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.awesomeshot5051.resourceFarm.data.providers.tags.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.client.model.data.*;


public class FluixDustFarmRenderer extends RendererBase<FluixDustFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public FluixDustFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(FluixDustFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level level = farm.getLevel();
        assert level != null;
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);

        matrixStack.pushPose();
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.translate(0.5, 0, 0.5); // Center the render position

        double generateTime = FluixDustFarmTileentity.getFluixDustGenerateTime(farm);
        double breakTime = FluixDustFarmTileentity.getFluixDustBreakTime(farm);

        if (!farm.fluixDustList.isEmpty() && AE2Check.containsAllItems(farm.fluixDustList, ModItemTags.SLABS_AND_FLUX_CRYSTAL, farm.getLevel())) {
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
                            ItemDisplayContext.GROUND,
                            combinedLight,
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
                // Get the first valid slab in the list
                Block slab = farm.fluixDustList.stream()
                        .filter(itemStack -> itemStack.is(ItemTags.SLABS))
                        .map(itemStack -> Block.byItem(itemStack.getItem()))
                        .filter(block -> block != Blocks.AIR)
                        .findFirst()
                        .orElse(Blocks.SMOOTH_STONE_SLAB); // Default slab if none found

                matrixStack.pushPose();

                // Calculate slab movement in real time
                float slabYOffset = (float) (0.5 - (farm.getTimer() / breakTime) * 0.5); // Moves down from 0.5 to 0

                matrixStack.translate(0, slabYOffset, 0); // Move the slab down dynamically

                blockRenderDispatcher.renderSingleBlock(
                        slab.defaultBlockState(),
                        matrixStack,
                        buffer,
                        combinedLight,
                        combinedOverlay,
                        ModelData.EMPTY,
                        RenderType.SOLID
                );

                matrixStack.popPose();
            }
        }
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
}
