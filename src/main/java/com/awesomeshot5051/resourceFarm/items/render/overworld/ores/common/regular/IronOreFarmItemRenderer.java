package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.IronOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.IronOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class IronOreFarmItemRenderer extends BlockItemRendererBase<IronOreFarmRenderer, IronOreFarmTileentity> {

    public IronOreFarmItemRenderer() {
        super(IronOreFarmRenderer::new, () -> new IronOreFarmTileentity(BlockPos.ZERO, ModBlocks.IRON_FARM.get().defaultBlockState()));
    }

}
