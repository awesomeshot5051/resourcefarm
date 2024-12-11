package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateGoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateGoldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateGoldOreFarmItemRenderer extends BlockItemRendererBase<DeepslateGoldOreFarmRenderer, DeepslateGoldOreFarmTileentity> {

    public DeepslateGoldOreFarmItemRenderer() {
        super(DeepslateGoldOreFarmRenderer::new, () -> new DeepslateGoldOreFarmTileentity(BlockPos.ZERO, ModBlocks.DGOLD_FARM.get().defaultBlockState()));
    }

}
