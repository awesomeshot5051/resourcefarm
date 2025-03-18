package com.awesomeshot5051.resourceFarm.integration.ae2.extendedae;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class EntroDustFarmItemRenderer extends BlockItemRendererBase<EntroDustFarmRenderer, EntroDustFarmTileentity> {

    public EntroDustFarmItemRenderer() {
        super(EntroDustFarmRenderer::new, () -> new EntroDustFarmTileentity(BlockPos.ZERO, ModBlocks.ENTD_FARM.get().defaultBlockState()));
    }

}
