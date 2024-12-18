package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.CobblestoneFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.CobblestoneFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class CobblestoneFarmItemRenderer extends BlockItemRendererBase<CobblestoneFarmRenderer, CobblestoneFarmTileentity> {

    public CobblestoneFarmItemRenderer() {
        super(CobblestoneFarmRenderer::new, () -> new CobblestoneFarmTileentity(BlockPos.ZERO, ModBlocks.COBBLESTONE_FARM.get().defaultBlockState()));
    }

}
