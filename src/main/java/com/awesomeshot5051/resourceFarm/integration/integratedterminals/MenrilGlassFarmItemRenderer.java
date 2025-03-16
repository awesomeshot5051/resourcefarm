package com.awesomeshot5051.resourceFarm.integration.integratedterminals;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class MenrilGlassFarmItemRenderer extends BlockItemRendererBase<MenrilGlassFarmRenderer, MenrilGlassFarmTileentity> {

    public MenrilGlassFarmItemRenderer() {
        super(MenrilGlassFarmRenderer::new, () -> new MenrilGlassFarmTileentity(BlockPos.ZERO, ModBlocks.MGLASS_FARM.get().defaultBlockState()));
    }

}
