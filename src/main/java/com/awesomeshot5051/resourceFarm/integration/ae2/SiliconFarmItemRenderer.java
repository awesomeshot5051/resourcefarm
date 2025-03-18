package com.awesomeshot5051.resourceFarm.integration.ae2;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SiliconFarmItemRenderer extends BlockItemRendererBase<SiliconFarmRenderer, SiliconFarmTileentity> {

    public SiliconFarmItemRenderer() {
        super(SiliconFarmRenderer::new, () -> new SiliconFarmTileentity(BlockPos.ZERO, ModBlocks.SIL_FARM.get().defaultBlockState()));
    }

}
