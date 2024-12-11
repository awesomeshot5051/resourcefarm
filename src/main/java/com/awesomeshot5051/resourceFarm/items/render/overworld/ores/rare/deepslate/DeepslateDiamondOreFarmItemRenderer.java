package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.rare.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.deepslate.DeepslateDiamondOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.deepslate.DeepslateDiamondOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateDiamondOreFarmItemRenderer extends BlockItemRendererBase<DeepslateDiamondOreFarmRenderer, DeepslateDiamondOreFarmTileentity> {

    public DeepslateDiamondOreFarmItemRenderer() {
        super(DeepslateDiamondOreFarmRenderer::new, () -> new DeepslateDiamondOreFarmTileentity(BlockPos.ZERO, ModBlocks.DDIAMOND_FARM.get().defaultBlockState()));
    }

}
