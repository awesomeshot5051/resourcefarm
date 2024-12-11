package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.OutputItemHandler;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import de.maxhenkel.corelib.blockentity.ITickableBlockEntity;
import de.maxhenkel.corelib.inventory.ItemListInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeepslateGoldOreFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack pickType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected long breakStage;
    protected OutputItemHandler outputItemHandler;

    public DeepslateGoldOreFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.DDGOLD_FARM.get(), ModBlocks.DDGOLD_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.STONE_PICKAXE);
    }

    public static int getGoldGenerateTime() {
        return Main.SERVER_CONFIG.goldGenerateTime.get() - 20 * 10;
    }

    public static int getGoldBreakTime() {
        return getGoldGenerateTime() + 20 * 10;
    }

    public long getTimer() {
        return timer;
    }

    public long getBreakStage() {
        return breakStage;
    }

    public ItemStack getPickType() {
        return pickType;
    }

    @Override
    public void tick() {
        timer++;
        if (timer >= getGoldGenerateTime() && timer < getGoldBreakTime()) {
            breakStage = (timer - getGoldGenerateTime()) / 20;
            if (breakStage > 9) {
                breakStage = 0;
            }
        } else {
            breakStage = 0;
        }

        if (timer >= getGoldBreakTime()) {
            for (ItemStack drop : getDrops()) {
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    drop = itemHandler.insertItem(i, drop, false);
                    if (drop.isEmpty()) {
                        break;
                    }
                }
            }
            timer = 0L;
            sync();
        }
        setChanged();
    }

    private List<ItemStack> getDrops() {
        if (!(level instanceof ServerLevel serverWorld)) {
            return Collections.emptyList();
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Items.GOLD_INGOT)); // Change this as needed for custom loot
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.saveAdditional(compound, provider);
        ContainerHelper.saveAllItems(compound, inventory, false, provider);
        compound.putLong("Timer", timer);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        NonNullList<ItemStack> picktypes = NonNullList.create();
        if (pickType != null) {
            picktypes = NonNullList.withSize(1, pickType);
        }
        ContainerHelper.loadAllItems(compound, picktypes, provider);
        timer = compound.getLong("Timer");
        super.loadAdditional(compound, provider);
    }

    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }
}
