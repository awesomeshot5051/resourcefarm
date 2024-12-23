package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.StoneFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.StoneFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class StoneFarmItemRenderer extends BlockItemRendererBase<StoneFarmRenderer, StoneFarmTileentity> {

    public StoneFarmItemRenderer() {
        super(StoneFarmRenderer::new, () -> new StoneFarmTileentity(BlockPos.ZERO, ModBlocks.STONE_FARM.get().defaultBlockState()));
    }

}
