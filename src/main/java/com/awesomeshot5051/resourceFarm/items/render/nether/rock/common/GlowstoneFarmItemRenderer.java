package com.awesomeshot5051.resourceFarm.items.render.nether.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common.GlowstoneFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.rock.common.GlowstoneFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GlowstoneFarmItemRenderer extends BlockItemRendererBase<GlowstoneFarmRenderer, GlowstoneFarmTileentity> {

    public GlowstoneFarmItemRenderer() {
        super(GlowstoneFarmRenderer::new, () -> new GlowstoneFarmTileentity(BlockPos.ZERO, ModBlocks.GLOWSTONE_FARM.get().defaultBlockState()));
    }

}
