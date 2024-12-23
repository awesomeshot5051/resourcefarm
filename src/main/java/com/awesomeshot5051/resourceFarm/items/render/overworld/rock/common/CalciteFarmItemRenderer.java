package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.CalciteFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.CalciteFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class CalciteFarmItemRenderer extends BlockItemRendererBase<CalciteFarmRenderer, CalciteFarmTileentity> {

    public CalciteFarmItemRenderer() {
        super(CalciteFarmRenderer::new, () -> new CalciteFarmTileentity(BlockPos.ZERO, ModBlocks.CALCITE_FARM.get().defaultBlockState()));
    }

}
