package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.GoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common..GoldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GoldOreFarmItemRenderer extends BlockItemRendererBase<GoldOreFarmRenderer, GoldOreFarmTileentity> {

    public GoldOreFarmItemRenderer() {
        super(GoldOreFarmRenderer::new, () -> new GoldOreFarmTileentity(BlockPos.ZERO, ModBlocks.Dgold_FARM.get().defaultBlockState()));
    }

}
