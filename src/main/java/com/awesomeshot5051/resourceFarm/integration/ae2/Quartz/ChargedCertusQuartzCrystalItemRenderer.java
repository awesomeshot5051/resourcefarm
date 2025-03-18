package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class ChargedCertusQuartzCrystalItemRenderer extends BlockItemRendererBase<ChargedCertusQuartzCrystalFarmRenderer, ChargedCertusQuartzCrystalFarmTileentity> {

    public ChargedCertusQuartzCrystalItemRenderer() {
        super(ChargedCertusQuartzCrystalFarmRenderer::new, () -> new ChargedCertusQuartzCrystalFarmTileentity(BlockPos.ZERO, ModBlocks.CCQC_FARM.get().defaultBlockState()));
    }

}
