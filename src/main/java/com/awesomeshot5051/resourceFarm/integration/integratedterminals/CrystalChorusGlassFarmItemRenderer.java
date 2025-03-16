package com.awesomeshot5051.resourceFarm.integration.integratedterminals;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class CrystalChorusGlassFarmItemRenderer extends BlockItemRendererBase<CrystalChorusGlassFarmRenderer, CrystalChorusGlassFarmTileentity> {

    public CrystalChorusGlassFarmItemRenderer() {
        super(CrystalChorusGlassFarmRenderer::new, () -> new CrystalChorusGlassFarmTileentity(BlockPos.ZERO, ModBlocks.CCGLASS_FARM.get().defaultBlockState()));
    }

}
