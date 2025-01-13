package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
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
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.client.model.data.*;

import java.util.*;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.PickaxeRendererUtil.*;

@SuppressWarnings("ALL")
public class ConcreteFarmRenderer extends RendererBase<ConcreteFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public ConcreteFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    @Override
    public void render(ConcreteFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);
        LiquidBlockRenderer liquidBlockRenderer = renderer.getBlockRenderDispatcher().getLiquidBlockRenderer();
        if (farm.getTick() >= ConcreteFarmTileentity.getConcreteGenerateTime(farm)) {
            blockRenderDispatcher.renderLiquid(farm.getBlockPos(),
                    Objects.requireNonNull(farm.getLevel()),
                    buffer.getBuffer(RenderType.translucent()),
                    Blocks.WATER.defaultBlockState(),
                    Fluids.FLOWING_WATER.defaultFluidState());
//            blockRenderDispatcher.renderSingleBlock(
//                    Blocks.BLACK_CONCRETE_POWDER.defaultBlockState(),
//                    matrixStack,
//                    buffer,
//                    combinedLight,
//                    combinedOverlay,
//                    ModelData.EMPTY,
//                    RenderType.SOLID
//            );
        } else if (farm.getTick() >= ConcreteFarmTileentity.getConcreteBreakTime(farm)) {
            blockRenderDispatcher.renderLiquid(farm.getBlockPos(),
                    Objects.requireNonNull(farm.getLevel()),
                    buffer.getBuffer(RenderType.translucent()),
                    Blocks.WATER.defaultBlockState(),
                    Fluids.FLOWING_WATER.defaultFluidState());
            farm.setTick(0L);
        }
        matrixStack.popPose();
        // Render the Pickaxe
//        BucketRenderUtil.renderWaterSplash(farm, matrixStack, buffer, combinedLight, combinedOverlay, getDirection(), farm.getTimer());
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
