package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.ConcreteFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.ConcreteFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class ConcreteFarmItemRenderer extends BlockItemRendererBase<ConcreteFarmRenderer, ConcreteFarmTileentity> {

    public ConcreteFarmItemRenderer() {
        super(ConcreteFarmRenderer::new, () -> new ConcreteFarmTileentity(BlockPos.ZERO, ModBlocks.CONCRETE_FARM.get().defaultBlockState()));
    }

}
