package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.GrassFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.GrassFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GrassFarmItemRenderer extends BlockItemRendererBase<GrassFarmRenderer, GrassFarmTileentity> {

    public GrassFarmItemRenderer() {
        super(GrassFarmRenderer::new, () -> new GrassFarmTileentity(BlockPos.ZERO, ModBlocks.GRASS_FARM.get().defaultBlockState()));
    }

}
