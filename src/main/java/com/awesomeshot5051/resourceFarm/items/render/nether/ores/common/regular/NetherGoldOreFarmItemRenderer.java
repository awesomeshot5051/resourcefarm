package com.awesomeshot5051.resourceFarm.items.render.nether.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.common.regular.NetherGoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.common.regular.NetherGoldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class NetherGoldOreFarmItemRenderer extends BlockItemRendererBase<NetherGoldOreFarmRenderer, NetherGoldOreFarmTileentity> {

    public NetherGoldOreFarmItemRenderer() {
        super(NetherGoldOreFarmRenderer::new, () -> new NetherGoldOreFarmTileentity(BlockPos.ZERO, ModBlocks.NETHER_GOLD_FARM.get().defaultBlockState()));
    }

}
