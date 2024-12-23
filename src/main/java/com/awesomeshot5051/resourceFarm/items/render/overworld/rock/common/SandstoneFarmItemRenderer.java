package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class SandstoneFarmItemRenderer extends BlockItemRendererBase<SandstoneFarmRenderer, SandstoneFarmTileentity> {

    public SandstoneFarmItemRenderer() {
        super(SandstoneFarmRenderer::new, () -> new SandstoneFarmTileentity(BlockPos.ZERO, ModBlocks.SSTONE_FARM.get().defaultBlockState()));
    }

}
