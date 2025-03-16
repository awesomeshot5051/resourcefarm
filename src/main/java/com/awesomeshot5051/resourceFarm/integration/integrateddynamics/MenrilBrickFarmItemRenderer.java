package com.awesomeshot5051.resourceFarm.integration.integrateddynamics;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class MenrilBrickFarmItemRenderer extends BlockItemRendererBase<MenrilBrickFarmRenderer, MenrilBrickFarmTileentity> {

    public MenrilBrickFarmItemRenderer() {
        super(MenrilBrickFarmRenderer::new, () -> new MenrilBrickFarmTileentity(BlockPos.ZERO, ModBlocks.MBRICK_FARM.get().defaultBlockState()));
    }

}
