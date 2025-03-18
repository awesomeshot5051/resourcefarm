package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class FlawlessBuddingQuartzFarmItemRenderer extends BlockItemRendererBase<FlawlessBuddingQuartzFarmRenderer, FlawlessBuddingQuartzFarmTileentity> {

    public FlawlessBuddingQuartzFarmItemRenderer() {
        super(FlawlessBuddingQuartzFarmRenderer::new, () -> new FlawlessBuddingQuartzFarmTileentity(BlockPos.ZERO, ModBlocks.FLBQ_FARM.get().defaultBlockState()));
    }

}
