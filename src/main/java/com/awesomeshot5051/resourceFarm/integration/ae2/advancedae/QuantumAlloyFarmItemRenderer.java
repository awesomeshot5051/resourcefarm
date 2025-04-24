package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class QuantumAlloyFarmItemRenderer extends BlockItemRendererBase<QuantumAlloyFarmRenderer, QuantumAlloyFarmTileentity> {

    public QuantumAlloyFarmItemRenderer() {
        super(QuantumAlloyFarmRenderer::new, () -> new QuantumAlloyFarmTileentity(BlockPos.ZERO, ModBlocks.QA_FARM.get().defaultBlockState()));
    }

}
