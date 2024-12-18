package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.rare.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.regular.LapisOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.regular.LapisOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class LapisOreFarmItemRenderer extends BlockItemRendererBase<LapisOreFarmRenderer, LapisOreFarmTileentity> {

    public LapisOreFarmItemRenderer() {
        super(LapisOreFarmRenderer::new, () -> new LapisOreFarmTileentity(BlockPos.ZERO, ModBlocks.LAPIS_FARM.get().defaultBlockState()));
    }

}
