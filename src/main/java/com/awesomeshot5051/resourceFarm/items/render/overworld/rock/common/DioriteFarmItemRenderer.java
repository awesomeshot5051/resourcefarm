package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.DioriteFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.DioriteFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DioriteFarmItemRenderer extends BlockItemRendererBase<DioriteFarmRenderer, DioriteFarmTileentity> {

    public DioriteFarmItemRenderer() {
        super(DioriteFarmRenderer::new, () -> new DioriteFarmTileentity(BlockPos.ZERO, ModBlocks.DIORITE_FARM.get().defaultBlockState()));
    }

}
