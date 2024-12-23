package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class DirtFarmItemRenderer extends BlockItemRendererBase<DirtFarmRenderer, DirtFarmTileentity> {

    public DirtFarmItemRenderer() {
        super(DirtFarmRenderer::new, () -> new DirtFarmTileentity(BlockPos.ZERO, ModBlocks.DIRT_FARM.get().defaultBlockState()));
    }

}
