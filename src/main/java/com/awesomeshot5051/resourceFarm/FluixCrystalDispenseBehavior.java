package com.awesomeshot5051.resourceFarm;

import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.Fluix.*;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.awesomeshot5051.corelib.integration.AE2Check.*;

public class FluixCrystalDispenseBehavior extends DefaultDispenseItemBehavior {
    @Override
    protected @NotNull ItemStack execute(BlockSource source, @NotNull ItemStack stack) {
        Level world = source.level();
        Direction facing = source.blockEntity().getBlockState().getValue(DispenserBlock.FACING);
        BlockPos targetPos = source.blockEntity().getBlockPos().relative(facing);
        BlockEntity tileEntity = world.getBlockEntity(targetPos);

        if (tileEntity instanceof FluixCrystalFarmTileentity farm) {
            List<ItemStack> itemsNeeded = new ArrayList<>(farm.ae2ItemsList);

            if (!itemsNeeded.contains(stack) && itemsNeeded.size() < 4 && !stack.is(Items.AIR)) {
                if (stack.is(Items.WATER_BUCKET) &&
                        itemsNeeded.stream().noneMatch(s -> s.is(Items.WATER_BUCKET))) {
                    // Add the water bucket item but replace the water content with a regular bucket
                    itemsNeeded.add(stack.copyWithCount(1)); // Add water bucket to the list
                    stack.shrink(1); // Remove 1 water bucket
                } else if (stack.is(AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get()) &&
                        itemsNeeded.stream().noneMatch(s -> s.is(AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get()))) {
                    itemsNeeded.add(stack.copyWithCount(1));
                } else if (stack.is(Items.REDSTONE) &&
                        itemsNeeded.stream().noneMatch(s -> s.is(Items.REDSTONE))) {
                    itemsNeeded.add(stack.copyWithCount(1));
                } else if (stack.is(Items.QUARTZ) &&
                        itemsNeeded.stream().noneMatch(s -> s.is(Items.QUARTZ))) {
                    itemsNeeded.add(stack.copyWithCount(1));
                }

                if (itemsNeeded.size() != 4) {
                    farm.ae2ItemsList = itemsNeeded;
                }
                if (itemsNeeded.size() == 4 && containsAllItems(AE2Blocks.itemsRequiredForFC, itemsNeeded)) {
                    farm.ae2Items = ItemContainerContents.fromItems(itemsNeeded);
                    farm.ae2ItemsList = itemsNeeded;
                    return ItemStack.EMPTY; // Remove the item from the dispenser
                }

                stack.shrink(1);
                return stack;
            }
        }

        return super.execute(source, stack); // Default behavior if no valid interaction
    }
}
