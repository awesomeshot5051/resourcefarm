package com.awesomeshot5051.resourceFarm.items.render.nether.soil;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.soil.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SoulSoilFarmItemRenderer extends BlockItemRendererBase<SoulSoilFarmRenderer, SoulSoilFarmTileentity> {

    public SoulSoilFarmItemRenderer() {
        super(SoulSoilFarmRenderer::new, () -> new SoulSoilFarmTileentity(BlockPos.ZERO, ModBlocks.SSOIL_FARM.get().defaultBlockState()));
    }

}
