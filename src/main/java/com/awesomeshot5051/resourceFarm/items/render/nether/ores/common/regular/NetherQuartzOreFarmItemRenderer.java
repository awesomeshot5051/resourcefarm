package com.awesomeshot5051.resourceFarm.items.render.nether.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.common.regular.NetherQuartzOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.common.regular.NetherQuartzOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class NetherQuartzOreFarmItemRenderer extends BlockItemRendererBase<NetherQuartzOreFarmRenderer, NetherQuartzOreFarmTileentity> {

    public NetherQuartzOreFarmItemRenderer() {
        super(NetherQuartzOreFarmRenderer::new, () -> new NetherQuartzOreFarmTileentity(BlockPos.ZERO, ModBlocks.NETHER_QUARTZ_FARM.get().defaultBlockState()));
    }

}
