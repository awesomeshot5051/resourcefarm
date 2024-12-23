package com.awesomeshot5051.resourceFarm.items.render.end.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.end.rock.common.PurpurFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.end.rock.common.PurpurFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class PurpurFarmItemRenderer extends BlockItemRendererBase<PurpurFarmRenderer, PurpurFarmTileentity> {

    public PurpurFarmItemRenderer() {
        super(PurpurFarmRenderer::new, () -> new PurpurFarmTileentity(BlockPos.ZERO, ModBlocks.PURPUR_FARM.get().defaultBlockState()));
    }

}
