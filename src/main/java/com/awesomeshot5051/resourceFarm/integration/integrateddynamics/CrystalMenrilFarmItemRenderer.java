package com.awesomeshot5051.resourceFarm.integration.integrateddynamics;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class CrystalMenrilFarmItemRenderer extends BlockItemRendererBase<CrystalMenrilFarmRenderer, CrystalMenrilFarmTileentity> {

    public CrystalMenrilFarmItemRenderer() {
        super(CrystalMenrilFarmRenderer::new, () -> new CrystalMenrilFarmTileentity(BlockPos.ZERO, ModBlocks.CMENRIL_FARM.get().defaultBlockState()));
    }

}
