package com.awesomeshot5051.resourceFarm.integration.ae2.extendedae;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class EntroCrystalFarmItemRenderer extends BlockItemRendererBase<EntroCrystalFarmRenderer, EntroCrystalFarmTileentity> {

    public EntroCrystalFarmItemRenderer() {
        super(EntroCrystalFarmRenderer::new, () -> new EntroCrystalFarmTileentity(BlockPos.ZERO, ModBlocks.ENTC_FARM.get().defaultBlockState()));
    }

}
