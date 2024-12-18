package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.rare.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.deepslate.DeepslateLapisOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.deepslate.DeepslateLapisOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateLapisOreFarmItemRenderer extends BlockItemRendererBase<DeepslateLapisOreFarmRenderer, DeepslateLapisOreFarmTileentity> {

    public DeepslateLapisOreFarmItemRenderer() {
        super(DeepslateLapisOreFarmRenderer::new, () -> new DeepslateLapisOreFarmTileentity(BlockPos.ZERO, ModBlocks.DLAPIS_FARM.get().defaultBlockState()));
    }

}
