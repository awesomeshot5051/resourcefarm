package com.awesomeshot5051.resourceFarm.items.render.nether.soil;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.soil.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SoulSandFarmItemRenderer extends BlockItemRendererBase<SoulSandFarmRenderer, SoulSandFarmTileentity> {

    public SoulSandFarmItemRenderer() {
        super(SoulSandFarmRenderer::new, () -> new SoulSandFarmTileentity(BlockPos.ZERO, ModBlocks.SSAND_FARM.get().defaultBlockState()));
    }

}
