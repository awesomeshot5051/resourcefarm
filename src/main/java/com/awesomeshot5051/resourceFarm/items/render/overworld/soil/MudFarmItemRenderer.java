package com.awesomeshot5051.resourceFarm.items.render.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.MudFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.MudFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class MudFarmItemRenderer extends BlockItemRendererBase<MudFarmRenderer, MudFarmTileentity> {

    public MudFarmItemRenderer() {
        super(MudFarmRenderer::new, () -> new MudFarmTileentity(BlockPos.ZERO, ModBlocks.MUD_FARM.get().defaultBlockState()));
    }

}
