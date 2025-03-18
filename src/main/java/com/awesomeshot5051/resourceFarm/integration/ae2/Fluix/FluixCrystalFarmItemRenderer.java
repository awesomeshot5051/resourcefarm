package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class FluixCrystalFarmItemRenderer extends BlockItemRendererBase<FluixCrystalFarmRenderer, FluixCrystalFarmTileentity> {

    public FluixCrystalFarmItemRenderer() {
        super(FluixCrystalFarmRenderer::new, () -> new FluixCrystalFarmTileentity(BlockPos.ZERO, ModBlocks.FLCR_FARM.get().defaultBlockState()));
    }

}
