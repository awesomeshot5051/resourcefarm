package com.awesomeshot5051.resourceFarm.util;

import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.*;
import net.neoforged.neoforge.items.*;

public class CustomItemStackHandler extends ItemStackHandler {
    protected final BlockEntity blockEntity;

    public CustomItemStackHandler(int size, BlockEntity blockEntity) {
        super(size);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void onContentsChanged(int slot) {
        blockEntity.setChanged();
    }

    public NonNullList<ItemStack> getStacks() {
        return this.stacks;
    }

    public NonNullList<ItemStack> getStacksCopy(int startIndex) {
        var t = NonNullList.withSize(this.stacks.size() - startIndex, ItemStack.EMPTY);
        for (int i = startIndex; i < this.stacks.size(); i++) {
            t.set(i - startIndex, this.stacks.get(i).copy());
        }
        return t;
    }

    public NonNullList<ItemStack> getStacksCopy() {
        return this.getStacksCopy(0);
    }

    public SimpleContainer getContainer() {
        SimpleContainer inventory = new SimpleContainer(this.getSlots());
        for (int i = 0; i < this.getSlots(); i++) {
            inventory.setItem(i, this.getStackInSlot(i));
        }
        return inventory;
    }

    public boolean isCompletelyEmpty() {
        // if all the slots are empty, return true
        boolean isEmpty = true;
        for (ItemStack stack : this.stacks) {
            if (!stack.isEmpty()) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (ItemStack stack : this.stacks) {
            if (stack.getCount() < stack.getMaxStackSize()) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean allDisabled() {
        return false;
    }
}
