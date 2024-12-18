package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.uncommon.deepslate;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.deepslate.DeepslateRedstoneOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.deepslate.DeepslateRedstoneOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateRedstoneOreFarmItemRenderer extends BlockItemRendererBase<DeepslateRedstoneOreFarmRenderer, DeepslateRedstoneOreFarmTileentity> {

    public DeepslateRedstoneOreFarmItemRenderer() {
        super(DeepslateRedstoneOreFarmRenderer::new, () -> new DeepslateRedstoneOreFarmTileentity(BlockPos.ZERO, ModBlocks.DREDSTONE_FARM.get().defaultBlockState()));
    }

}
