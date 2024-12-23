package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.TuffFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.TuffFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class TuffFarmItemRenderer extends BlockItemRendererBase<TuffFarmRenderer, TuffFarmTileentity> {

    public TuffFarmItemRenderer() {
        super(TuffFarmRenderer::new, () -> new TuffFarmTileentity(BlockPos.ZERO, ModBlocks.TUFF_FARM.get().defaultBlockState()));
    }

}
