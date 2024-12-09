package com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.CopperOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public class CopperOreFarmItemRenderer extends BlockItemRendererBase<CopperOreFarmRenderer, CopperOreFarmTileentity> {

    public CopperOreFarmItemRenderer(ItemStack stack) {
        super(context -> new CopperOreFarmRenderer(context, stack), () -> new CopperOreFarmTileentity(BlockPos.ZERO, ModBlocks.COPPER_FARM.get().defaultBlockState()));
    }

    public CopperOreFarmItemRenderer() {
        super(CopperOreFarmRenderer::new, () -> new CopperOreFarmTileentity(BlockPos.ZERO, ModBlocks.COPPER_FARM.get().defaultBlockState()));
    }
}
