package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.items.render.*;
import net.minecraft.core.*;

public class AndesiteFarmItemRenderer extends BlockItemRendererBase<AndesiteFarmRenderer, AndesiteFarmTileentity> {

    public AndesiteFarmItemRenderer() {
        super(AndesiteFarmRenderer::new, () -> new AndesiteFarmTileentity(BlockPos.ZERO, ModBlocks.ANDESITE_FARM.get().defaultBlockState()));
    }

}
