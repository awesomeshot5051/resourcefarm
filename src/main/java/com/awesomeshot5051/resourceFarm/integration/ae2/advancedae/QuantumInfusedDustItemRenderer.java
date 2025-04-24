package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class QuantumInfusedDustItemRenderer extends BlockItemRendererBase<QuantumInfusedDustRenderer, QuantumInfusedDustFarmTileentity> {

    public QuantumInfusedDustItemRenderer() {
        super(QuantumInfusedDustRenderer::new, () -> new QuantumInfusedDustFarmTileentity(BlockPos.ZERO, ModBlocks.QID_FARM.get().defaultBlockState()));
    }

}
