package com.awesomeshot5051.resourceFarm.items.render.nether.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common.NetherrackFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.rock.common.NetherrackFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class NetherrackFarmItemRenderer extends BlockItemRendererBase<NetherrackFarmRenderer, NetherrackFarmTileentity> {

    public NetherrackFarmItemRenderer() {
        super(NetherrackFarmRenderer::new, () -> new NetherrackFarmTileentity(BlockPos.ZERO, ModBlocks.NETHERRACK_FARM.get().defaultBlockState()));
    }

}
