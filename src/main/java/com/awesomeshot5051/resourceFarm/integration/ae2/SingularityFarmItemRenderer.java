package com.awesomeshot5051.resourceFarm.integration.ae2;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class SingularityFarmItemRenderer extends BlockItemRendererBase<SingularityFarmRenderer, SingularityFarmTileentity> {

    public SingularityFarmItemRenderer() {
        super(SingularityFarmRenderer::new, () -> new SingularityFarmTileentity(BlockPos.ZERO, ModBlocks.SI_FARM.get().defaultBlockState()));
    }

}
