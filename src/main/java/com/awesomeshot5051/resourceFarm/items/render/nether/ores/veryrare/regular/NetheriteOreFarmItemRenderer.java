package com.awesomeshot5051.resourceFarm.items.render.nether.ores.veryrare.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.veryrare.regular.NetheriteOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.veryrare.regular.NetheriteOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class NetheriteOreFarmItemRenderer extends BlockItemRendererBase<NetheriteOreFarmRenderer, NetheriteOreFarmTileentity> {

    public NetheriteOreFarmItemRenderer() {
        super(NetheriteOreFarmRenderer::new, () -> new NetheriteOreFarmTileentity(BlockPos.ZERO, ModBlocks.NETHERITE_FARM.get().defaultBlockState()));
    }

}
