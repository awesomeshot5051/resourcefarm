package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.SnowFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.SnowFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class SnowFarmItemRenderer extends BlockItemRendererBase<SnowFarmRenderer, SnowFarmTileentity> {

    public SnowFarmItemRenderer() {
        super(SnowFarmRenderer::new, () -> new SnowFarmTileentity(BlockPos.ZERO, ModBlocks.SNOW_FARM.get().defaultBlockState()));
    }

}
