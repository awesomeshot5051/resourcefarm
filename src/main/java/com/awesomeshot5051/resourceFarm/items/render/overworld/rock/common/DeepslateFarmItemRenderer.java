package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.DeepslateFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.DeepslateFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class DeepslateFarmItemRenderer extends BlockItemRendererBase<DeepslateFarmRenderer, DeepslateFarmTileentity> {

    public DeepslateFarmItemRenderer() {
        super(DeepslateFarmRenderer::new, () -> new DeepslateFarmTileentity(BlockPos.ZERO, ModBlocks.DEEPSLATE_FARM.get().defaultBlockState()));
    }

}
