package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.corelib.integration.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.awesomeshot5051.resourceFarm.data.providers.tags.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.data.*;


public class FluixDustFarmRenderer extends RendererBase<FluixDustFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public FluixDustFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        this.itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(FluixDustFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level level = farm.getLevel();
        assert level != null;
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);

        matrixStack.pushPose();

        double generateTime = FluixDustFarmTileentity.getFluixDustGenerateTime(farm);
        double breakTime = FluixDustFarmTileentity.getFluixDustBreakTime(farm);

        if (!farm.fluixDustList.isEmpty() && AE2Check.containsAllItems(farm.fluixDustList, ModItemTags.SLABS_AND_FLUX_CRYSTAL, farm.getLevel(), 2)) {
            matrixStack.pushPose();

            // Smooth slab movement logic
            float progress = (float) (farm.getTimer() / breakTime);
            float slabYOffset = progress < .5 ? (.5f - progress) * 0.5f : (progress - .5f) * 0.5f;

            // Find the correct slab block
            Block slab = farm.fluixDustList.stream()
                    .filter(itemStack -> itemStack.is(ModItemTags.AE_SLABS) || itemStack.is(ItemTags.SLABS))
                    .map(itemStack -> Block.byItem(itemStack.getItem()))
                    .filter(block -> block != Blocks.AIR)
                    .findFirst()
                    .orElse(Blocks.AIR); // Default slab if none found

            // Move slab smoothly up and down
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.translate(0.4, slabYOffset + .5, 0.4);

            // Render slab
            blockRenderDispatcher.renderSingleBlock(
                    slab.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );

            matrixStack.popPose(); // Reset transform for other renders

            // Render Fluix Crystal BEFORE break time
            if (progress < .5) {
                renderSapling(matrixStack);
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
                matrixStack.popPose();
            }

            // Render Fluix Dust AFTER break time but BEFORE full reset
            if (progress > .5) {
                renderSapling(matrixStack);
                itemRenderer.renderStatic(
                        AE2Blocks.FLUIX_DUST.get().getDefaultInstance(),
                        ItemDisplayContext.GROUND,
                        combinedLight,
                        combinedOverlay,
                        matrixStack,
                        buffer,
                        farm.getLevel(),
                        0
                );
                matrixStack.popPose();
            }
        }
        matrixStack.popPose();
    }
}
