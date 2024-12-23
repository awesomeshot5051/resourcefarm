package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.TerracottaFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.TerracottaFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class TerracottaFarmItemRenderer extends BlockItemRendererBase<TerracottaFarmRenderer, TerracottaFarmTileentity> {

    public TerracottaFarmItemRenderer() {
        super(TerracottaFarmRenderer::new, () -> new TerracottaFarmTileentity(BlockPos.ZERO, ModBlocks.TERRACOTTA_FARM.get().defaultBlockState()));
    }

}
