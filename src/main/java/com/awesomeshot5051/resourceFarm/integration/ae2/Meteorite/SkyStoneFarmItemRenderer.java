package com.awesomeshot5051.resourceFarm.integration.ae2.Meteorite;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SkyStoneFarmItemRenderer extends BlockItemRendererBase<SkyStoneFarmRenderer, SkyStoneFarmTileentity> {

    public SkyStoneFarmItemRenderer() {
        super(SkyStoneFarmRenderer::new, () -> new SkyStoneFarmTileentity(BlockPos.ZERO, ModBlocks.SSB_FARM.get().defaultBlockState()));
    }

}
