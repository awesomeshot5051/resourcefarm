package com.awesomeshot5051.resourceFarm.items;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ChiselItem extends Item {

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity blockEntity = level.getBlockEntity(context.getClickedPos());

        if (!level.isClientSide()) {
            if (blockEntity instanceof VillagerTileentity) {
                ((VillagerTileentity) blockEntity).toggleSound();
            }
        }

        return InteractionResult.SUCCESS;
    }
}
