package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class RedSandFarmItemRenderer extends BlockItemRendererBase<RedSandFarmRenderer, RedSandFarmTileentity> {

    public RedSandFarmItemRenderer() {
        super(RedSandFarmRenderer::new, () -> new RedSandFarmTileentity(BlockPos.ZERO, ModBlocks.RSAND_FARM.get().defaultBlockState()));
    }

}
