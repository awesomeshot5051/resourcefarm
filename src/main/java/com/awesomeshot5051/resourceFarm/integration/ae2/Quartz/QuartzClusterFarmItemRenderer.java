package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class QuartzClusterFarmItemRenderer extends BlockItemRendererBase<QuartzClusterFarmRenderer, QuartzClusterFarmTileentity> {

    public QuartzClusterFarmItemRenderer() {
        super(QuartzClusterFarmRenderer::new, () -> new QuartzClusterFarmTileentity(BlockPos.ZERO, ModBlocks.QC_FARM.get().defaultBlockState()));
    }

}
