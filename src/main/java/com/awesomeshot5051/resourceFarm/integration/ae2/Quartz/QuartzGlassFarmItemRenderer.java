package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class QuartzGlassFarmItemRenderer extends BlockItemRendererBase<QuartzGlassFarmRenderer, QuartzGlassFarmTileentity> {

    public QuartzGlassFarmItemRenderer() {
        super(QuartzGlassFarmRenderer::new, () -> new QuartzGlassFarmTileentity(BlockPos.ZERO, ModBlocks.QG_FARM.get().defaultBlockState()));
    }

}
