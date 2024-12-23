package com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.ObsidianFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.ObsidianFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class ObsidianFarmItemRenderer extends BlockItemRendererBase<ObsidianFarmRenderer, ObsidianFarmTileentity> {

    public ObsidianFarmItemRenderer() {
        super(ObsidianFarmRenderer::new, () -> new ObsidianFarmTileentity(BlockPos.ZERO, ModBlocks.OBSIDIAN_FARM.get().defaultBlockState()));
    }

}
