package com.awesomeshot5051.resourceFarm.items.render.nether.rock.common;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common.BlackstoneFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.rock.common.BlackstoneFarmRenderer;
import com.awesomeshot5051.resourceFarm.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class BlackstoneFarmItemRenderer extends BlockItemRendererBase<BlackstoneFarmRenderer, BlackstoneFarmTileentity> {

    public BlackstoneFarmItemRenderer() {
        super(BlackstoneFarmRenderer::new, () -> new BlackstoneFarmTileentity(BlockPos.ZERO, ModBlocks.BLACKSTONE_FARM.get().defaultBlockState()));
    }

}
