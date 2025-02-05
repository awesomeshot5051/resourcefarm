package com.awesomeshot5051.resourceFarm.items;

import com.awesomeshot5051.corelib.blockentity.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;

public class ChiselItem extends Item {

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity blockEntity = level.getBlockEntity(context.getClickedPos());

        if (!level.isClientSide()) {
            if (blockEntity instanceof FarmTileentity) {
                ((FarmTileentity) blockEntity).toggleSound();
            }
        }

        return InteractionResult.SUCCESS;
    }
}
