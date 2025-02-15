package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.ClayFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.ClayFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class ClayFarmItemRenderer extends BlockItemRendererBase<ClayFarmRenderer, ClayFarmTileentity> {

    public ClayFarmItemRenderer() {
        super(ClayFarmRenderer::new, () -> new ClayFarmTileentity(BlockPos.ZERO, ModBlocks.CLAY_FARM.get().defaultBlockState()));
    }

}
