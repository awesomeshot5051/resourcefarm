package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.resourceFarm.blocks.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.soil.*;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.*;
import net.minecraft.server.level.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

public class DyeDispenseItemBehavior extends DefaultDispenseItemBehavior {
    @Override
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        if (!(item.getItem() instanceof DyeItem)) {
            return super.execute(blockSource, item);
        }
        ServerLevel serverLevel = blockSource.level();
        Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
        BlockPos pos = blockSource.pos().relative(direction);
        BlockState state = serverLevel.getBlockState(pos);
        if (state.getBlock() instanceof ConcretePowderFarmBlock) {
            DyeColor dyeColor = ((DyeItem) item.getItem()).getDyeColor();
            ((ConcretePowderFarmBlock) state.getBlock()).dyeBlock(state, serverLevel, dyeColor, pos);
            item.shrink(1);
        } else if (state.getBlock() instanceof ConcreteFarmBlock) {
            DyeColor dyeColor = ((DyeItem) item.getItem()).getDyeColor();
            ((ConcreteFarmBlock) state.getBlock()).dyeBlock(state, serverLevel, dyeColor, pos);
            item.shrink(1);
        } else {
            return super.execute(blockSource, item);
        }
        return item;
    }

    @Override
    protected void playSound(BlockSource blockSource) {
        blockSource.level().levelEvent(1000, blockSource.pos(), 0);
    }
}
