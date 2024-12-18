package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.veryrare.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.deepslate.DeepslateEmeraldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.deepslate.DeepslateEmeraldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateEmeraldOreFarmItemRenderer extends BlockItemRendererBase<DeepslateEmeraldOreFarmRenderer, DeepslateEmeraldOreFarmTileentity> {

    public DeepslateEmeraldOreFarmItemRenderer() {
        super(DeepslateEmeraldOreFarmRenderer::new, () -> new DeepslateEmeraldOreFarmTileentity(BlockPos.ZERO, ModBlocks.DEMERALD_FARM.get().defaultBlockState()));
    }

}
