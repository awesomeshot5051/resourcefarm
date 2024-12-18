package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.SandFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.SandFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class SandFarmItemRenderer extends BlockItemRendererBase<SandFarmRenderer, SandFarmTileentity> {

    public SandFarmItemRenderer() {
        super(SandFarmRenderer::new, () -> new SandFarmTileentity(BlockPos.ZERO, ModBlocks.SAND_FARM.get().defaultBlockState()));
    }

}
