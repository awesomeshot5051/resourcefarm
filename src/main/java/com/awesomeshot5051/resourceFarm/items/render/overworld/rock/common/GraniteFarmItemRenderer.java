package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.GraniteFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.GraniteFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GraniteFarmItemRenderer extends BlockItemRendererBase<GraniteFarmRenderer, GraniteFarmTileentity> {

    public GraniteFarmItemRenderer() {
        super(GraniteFarmRenderer::new, () -> new GraniteFarmTileentity(BlockPos.ZERO, ModBlocks.GRANITE_FARM.get().defaultBlockState()));
    }

}
