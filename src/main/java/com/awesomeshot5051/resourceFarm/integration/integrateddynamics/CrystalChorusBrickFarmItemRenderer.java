package com.awesomeshot5051.resourceFarm.integration.integrateddynamics;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class CrystalChorusBrickFarmItemRenderer extends BlockItemRendererBase<CrystalChorusBrickFarmRenderer, CrystalChorusBrickFarmTileentity> {

    public CrystalChorusBrickFarmItemRenderer() {
        super(CrystalChorusBrickFarmRenderer::new, () -> new CrystalChorusBrickFarmTileentity(BlockPos.ZERO, ModBlocks.CCHORUS_FARM.get().defaultBlockState()));
    }

}
