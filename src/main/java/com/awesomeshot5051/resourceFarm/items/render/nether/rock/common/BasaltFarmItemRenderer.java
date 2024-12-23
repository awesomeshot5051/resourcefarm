package com.awesomeshot5051.resourceFarm.items.render.nether.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common.BasaltFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.rock.common.BasaltFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class BasaltFarmItemRenderer extends BlockItemRendererBase<BasaltFarmRenderer, BasaltFarmTileentity> {

    public BasaltFarmItemRenderer() {
        super(BasaltFarmRenderer::new, () -> new BasaltFarmTileentity(BlockPos.ZERO, ModBlocks.BASALT_FARM.get().defaultBlockState()));
    }

}
