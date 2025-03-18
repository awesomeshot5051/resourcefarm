package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class FlawedBuddingQuartzFarmItemRenderer extends BlockItemRendererBase<FlawedBuddingQuartzFarmRenderer, FlawedBuddingQuartzFarmTileentity> {

    public FlawedBuddingQuartzFarmItemRenderer() {
        super(FlawedBuddingQuartzFarmRenderer::new, () -> new FlawedBuddingQuartzFarmTileentity(BlockPos.ZERO, ModBlocks.FBQ_FARM.get().defaultBlockState()));
    }

}
