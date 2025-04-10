package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class CertusQuartzCrystalFarmItemRenderer extends BlockItemRendererBase<CertusQuartzCrystalFarmRenderer, CertusQuartzCrystalFarmTileentity> {

    public CertusQuartzCrystalFarmItemRenderer() {
        super(CertusQuartzCrystalFarmRenderer::new, () -> new CertusQuartzCrystalFarmTileentity(BlockPos.ZERO, ModBlocks.CQC_FARM.get().defaultBlockState()));
    }

}
