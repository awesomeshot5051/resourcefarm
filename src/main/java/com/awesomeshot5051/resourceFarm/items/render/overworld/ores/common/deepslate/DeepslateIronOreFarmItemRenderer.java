package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateIronOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateIronOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateIronOreFarmItemRenderer extends BlockItemRendererBase<DeepslateIronOreFarmRenderer, DeepslateIronOreFarmTileentity> {

    public DeepslateIronOreFarmItemRenderer() {
        super(DeepslateIronOreFarmRenderer::new, () -> new DeepslateIronOreFarmTileentity(BlockPos.ZERO, ModBlocks.DIRON_FARM.get().defaultBlockState()));
    }

}
