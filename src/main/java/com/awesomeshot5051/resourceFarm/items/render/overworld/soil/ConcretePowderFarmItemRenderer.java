package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.ConcretePowderFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.ConcretePowderFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class ConcretePowderFarmItemRenderer extends BlockItemRendererBase<ConcretePowderFarmRenderer, ConcretePowderFarmTileentity> {

    public ConcretePowderFarmItemRenderer() {
        super(ConcretePowderFarmRenderer::new, () -> new ConcretePowderFarmTileentity(BlockPos.ZERO, ModBlocks.CONCRETE_POWDER_FARM.get().defaultBlockState()));
    }

}
