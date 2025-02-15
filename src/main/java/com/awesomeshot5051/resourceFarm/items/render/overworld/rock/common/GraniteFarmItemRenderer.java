package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class GraniteFarmItemRenderer extends BlockItemRendererBase<GraniteFarmRenderer, GraniteFarmTileentity> {

    public GraniteFarmItemRenderer() {
        super(GraniteFarmRenderer::new, () -> new GraniteFarmTileentity(BlockPos.ZERO, ModBlocks.GRANITE_FARM.get().defaultBlockState()));
    }

}
