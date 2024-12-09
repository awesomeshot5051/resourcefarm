package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateCoalOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateCoalOreFarmItemRenderer extends BlockItemRendererBase<DeepslateCoalOreFarmRenderer, DeepslateCoalOreFarmTileentity> {

    public DeepslateCoalOreFarmItemRenderer() {
        super(DeepslateCoalOreFarmRenderer::new, () -> new DeepslateCoalOreFarmTileentity(BlockPos.ZERO, ModBlocks.DCOAL_FARM.get().defaultBlockState()));
    }

}
