package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.CoalOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class CoalOreFarmItemRenderer extends BlockItemRendererBase<CoalOreFarmRenderer, CoalOreFarmTileentity> {

    public CoalOreFarmItemRenderer() {
        super(CoalOreFarmRenderer::new, () -> new CoalOreFarmTileentity(BlockPos.ZERO, ModBlocks.COAL_FARM.get().defaultBlockState()));
    }

}
