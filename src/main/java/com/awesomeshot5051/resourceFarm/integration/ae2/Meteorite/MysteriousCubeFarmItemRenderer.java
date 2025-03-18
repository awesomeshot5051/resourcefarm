package com.awesomeshot5051.resourceFarm.integration.ae2.Meteorite;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class MysteriousCubeFarmItemRenderer extends BlockItemRendererBase<MysteriousCubeFarmRenderer, MysteriousCubeFarmTileentity> {

    public MysteriousCubeFarmItemRenderer() {
        super(MysteriousCubeFarmRenderer::new, () -> new MysteriousCubeFarmTileentity(BlockPos.ZERO, ModBlocks.MC_FARM.get().defaultBlockState()));
    }

}
