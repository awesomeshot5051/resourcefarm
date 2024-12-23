package com.awesomeshot5051.resourceFarm.items.render.end.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.end.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.end.rock.common.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class EndStoneFarmItemRenderer extends BlockItemRendererBase<EndStoneFarmRenderer, EndStoneFarmTileentity> {

    public EndStoneFarmItemRenderer() {
        super(EndStoneFarmRenderer::new, () -> new EndStoneFarmTileentity(BlockPos.ZERO, ModBlocks.ESTONE_FARM.get().defaultBlockState()));
    }

}
