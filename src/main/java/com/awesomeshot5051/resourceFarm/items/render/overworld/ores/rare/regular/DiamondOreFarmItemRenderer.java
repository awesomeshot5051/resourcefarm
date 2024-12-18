package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.rare.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.regular.DiamondOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.regular.DiamondOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DiamondOreFarmItemRenderer extends BlockItemRendererBase<DiamondOreFarmRenderer, DiamondOreFarmTileentity> {

    public DiamondOreFarmItemRenderer() {
        super(DiamondOreFarmRenderer::new, () -> new DiamondOreFarmTileentity(BlockPos.ZERO, ModBlocks.DIAMOND_FARM.get().defaultBlockState()));
    }

}
