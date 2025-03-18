package com.awesomeshot5051.resourceFarm.integration.ae2.extendedae;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SiliconBlockFarmItemRenderer extends BlockItemRendererBase<SiliconBlockFarmRenderer, SiliconBlockFarmTileentity> {

    public SiliconBlockFarmItemRenderer() {
        super(SiliconBlockFarmRenderer::new, () -> new SiliconBlockFarmTileentity(BlockPos.ZERO, ModBlocks.SIB_FARM.get().defaultBlockState()));
    }

}
