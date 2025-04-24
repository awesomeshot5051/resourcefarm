package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;

import com.awesomeshot5051.corelib.datacomponents.Upgrades;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.RendererBase;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;


public class QuantumInfusedDustRenderer extends RendererBase<QuantumInfusedDustFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public QuantumInfusedDustRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(QuantumInfusedDustFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level level = farm.getLevel();
        assert level != null;
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        if (!(farm.getInscriberPressInstalled() && Upgrades.getUpgradeStatus(farm.getUpgrades(), ModItems.INSCRIBER_UPGRADE.toStack()))) {
            return;
        }
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);
        if (farm.getTimer() >= QuantumInfusedDustFarmTileentity.getQAGenerateTime(farm)) {
            if (farm.redstoneUpgradeEnabled && !(level.hasNeighborSignal(farm.getBlockPos()))) {
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
                        AAEItems.QUANTUM_INFUSED_DUST.get().getDefaultInstance(),
                        ItemDisplayContext.GROUND, combinedLight,
                        combinedOverlay,
                        matrixStack,
                        buffer,
                        farm.getLevel(),
                        0
                );
            }
        } else if (farm.getTimer() >= QuantumInfusedDustFarmTileentity.getQABreakTime(farm)) {
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
