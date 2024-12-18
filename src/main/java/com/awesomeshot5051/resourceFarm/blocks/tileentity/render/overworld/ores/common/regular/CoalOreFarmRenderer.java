package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular;


import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.RendererBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.data.ModelData;

import static com.awesomeshot5051.resourceFarm.BlockInternalRender.PickaxeRendererUtil.renderSwingingPickaxe;

public class CoalOreFarmRenderer extends RendererBase<CoalOreFarmTileentity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public CoalOreFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    @Override
    public void render(CoalOreFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);
        // Render the Copper Ore Block
        if (farm.getTimer() >= CoalOreFarmTileentity.getCoalGenerateTime(farm)) {
            blockRenderDispatcher.renderSingleBlock(
                    Blocks.COAL_ORE.defaultBlockState(),
                    matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay,
                    ModelData.EMPTY,
                    RenderType.SOLID
            );
        } else if (farm.getTimer() >= CoalOreFarmTileentity.getCoalBreakTime(farm)) {
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


}
