package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class FluixDustFarmItemRenderer extends BlockItemRendererBase<FluixDustFarmRenderer, FluixDustFarmTileentity> {

    public FluixDustFarmItemRenderer() {
        super(FluixDustFarmRenderer::new, () -> new FluixDustFarmTileentity(BlockPos.ZERO, ModBlocks.FLDU_FARM.get().defaultBlockState()));
    }

}
