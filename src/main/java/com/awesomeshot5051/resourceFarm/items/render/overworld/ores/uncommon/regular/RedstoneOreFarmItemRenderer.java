package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.uncommon.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.regular.RedstoneOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.regular.RedstoneOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class RedstoneOreFarmItemRenderer extends BlockItemRendererBase<RedstoneOreFarmRenderer, RedstoneOreFarmTileentity> {

    public RedstoneOreFarmItemRenderer() {
        super(RedstoneOreFarmRenderer::new, () -> new RedstoneOreFarmTileentity(BlockPos.ZERO, ModBlocks.REDSTONE_FARM.get().defaultBlockState()));
    }

}
