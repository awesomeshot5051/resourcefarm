package com.awesomeshot5051.resourceFarm.integration.integrateddynamics;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class CrystalChorusFarmItemRenderer extends BlockItemRendererBase<CrystalChorusFarmRenderer, CrystalChorusFarmTileentity> {

    public CrystalChorusFarmItemRenderer() {
        super(CrystalChorusFarmRenderer::new, () -> new CrystalChorusFarmTileentity(BlockPos.ZERO, ModBlocks.CCHORUS_FARM.get().defaultBlockState()));
    }

}
