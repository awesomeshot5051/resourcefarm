package com.awesomeshot5051.resourceFarm.integration.ae2.extendedae;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class EntroBlockFarmItemRenderer extends BlockItemRendererBase<EntroBlockFarmRenderer, EntroBlockFarmTileentity> {

    public EntroBlockFarmItemRenderer() {
        super(EntroBlockFarmRenderer::new, () -> new EntroBlockFarmTileentity(BlockPos.ZERO, ModBlocks.ENTB_FARM.get().defaultBlockState()));
    }

}
