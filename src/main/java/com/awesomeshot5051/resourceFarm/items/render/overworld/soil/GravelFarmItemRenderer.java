package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.GravelFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.GravelFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GravelFarmItemRenderer extends BlockItemRendererBase<GravelFarmRenderer, GravelFarmTileentity> {

    public GravelFarmItemRenderer() {
        super(GravelFarmRenderer::new, () -> new GravelFarmTileentity(BlockPos.ZERO, ModBlocks.GRAVEL_FARM.get().defaultBlockState()));
    }

}
