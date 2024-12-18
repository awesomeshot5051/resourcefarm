package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.veryrare.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.regular.EmeraldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.regular.EmeraldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class EmeraldOreFarmItemRenderer extends BlockItemRendererBase<EmeraldOreFarmRenderer, EmeraldOreFarmTileentity> {

    public EmeraldOreFarmItemRenderer() {
        super(EmeraldOreFarmRenderer::new, () -> new EmeraldOreFarmTileentity(BlockPos.ZERO, ModBlocks.EMERALD_FARM.get().defaultBlockState()));
    }

}
