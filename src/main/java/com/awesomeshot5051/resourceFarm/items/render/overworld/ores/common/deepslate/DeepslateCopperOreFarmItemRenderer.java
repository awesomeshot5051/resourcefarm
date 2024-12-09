package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateCopperOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateCopperOreFarmItemRenderer extends BlockItemRendererBase<DeepslateCopperOreFarmRenderer, DeepslateCopperOreFarmTileentity> {

    public DeepslateCopperOreFarmItemRenderer() {
        super(DeepslateCopperOreFarmRenderer::new, () -> new DeepslateCopperOreFarmTileentity(BlockPos.ZERO, ModBlocks.DCOPPER_FARM.get().defaultBlockState()));
    }

}
