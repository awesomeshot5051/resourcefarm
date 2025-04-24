package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class ShatteredSingularityFarmItemRenderer extends BlockItemRendererBase<ShatteredSingularityFarmRenderer, ShatteredSingularityFarmTileentity> {

    public ShatteredSingularityFarmItemRenderer() {
        super(ShatteredSingularityFarmRenderer::new, () -> new ShatteredSingularityFarmTileentity(BlockPos.ZERO, ModBlocks.SS_FARM.get().defaultBlockState()));
    }

}
