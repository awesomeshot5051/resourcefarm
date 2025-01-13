package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.regular;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.client.model.data.*;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.PickaxeRendererUtil.*;

@SuppressWarnings("ALL")
public class RedstoneOreFarmRenderer extends RendererBase<RedstoneOreFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public RedstoneOreFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    @Override
    public void render(RedstoneOreFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);
        // Render the Redstone Ore Block
        if (farm.getTimer() >= RedstoneOreFarmTileentity.getRedstoneGenerateTime(farm)) {
            blockRenderDispatcher.renderSingleBlock(
                    Blocks.REDSTONE_ORE.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        } else if (farm.getTimer() >= RedstoneOreFarmTileentity.getRedstoneBreakTime(farm)) {
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
        // Render the Pickaxe
        renderSwingingPickaxe(farm, matrixStack, buffer, combinedLight, combinedOverlay, farm.getPickType(), getDirection(), farm.getTimer());
    }

    public void renderBreakingAnimation(BlockState blockState, PoseStack matrixStack, MultiBufferSource buffer, int breakStage, int combinedLight, int combinedOverlay) {
        if (breakStage < 0 || breakStage > 9) return; // Ensure valid stage

        // Get breaking texture
        TextureAtlasSprite breakingSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(ResourceLocation.fromNamespaceAndPath("minecraft", "block/destroy_stage_" + breakStage));

        // Use RenderType for breaking overlay
        RenderType breakingRenderType = RenderType.crumbling(breakingSprite.atlasLocation());
        // Render breaking texture over the block
        blockRenderDispatcher.getModelRenderer().renderModel(
                matrixStack.last(),
                buffer.getBuffer(breakingRenderType),
                blockState,
                blockRenderDispatcher.getBlockModel(blockState),
                1.0F, 1.0F, 1.0F,  // Color
                combinedLight,
                combinedOverlay,
                ModelData.EMPTY,
                breakingRenderType);
    }
}
