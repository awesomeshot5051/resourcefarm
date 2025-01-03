package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.OutputItemHandler;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.SyncableTileentity;
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

public class SandFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack pickType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected long tick = timer;
    protected ItemStackHandler itemHandler;
    protected long breakStage;
    protected OutputItemHandler outputItemHandler;

    public SandFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SAND_FARM.get(), ModBlocks.SAND_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.STONE_SHOVEL);
    }

    public static double getSandGenerateTime(SandFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.sandGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.WOODEN_SHOVEL) ? 5 :
                        tileEntity.getPickType().getItem().equals(Items.STONE_SHOVEL) ? 10 :
                                tileEntity.getPickType().getItem().equals(Items.IRON_SHOVEL) ? 15 :
                                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_SHOVEL) ? 20 :
                                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_SHOVEL) ? 25 :
                                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_SHOVEL) ? 30 :
                                                                5); // Default to Wooden Shovel divisor if none matches

    }

    public static double getSandBreakTime(SandFarmTileentity tileEntity) {

        return getSandGenerateTime(tileEntity) + (tileEntity.getPickType().getItem().equals(Items.WOODEN_SHOVEL) ? (20 * 10) :
                tileEntity.getPickType().getItem().equals(Items.STONE_SHOVEL) ? (20 * 8) :
                        tileEntity.getPickType().getItem().equals(Items.IRON_SHOVEL) ? (20 * 4) :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_SHOVEL) ? (20 * 2) :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_SHOVEL) ? (20 * 2) :
                                                tileEntity.getPickType().getItem().equals(Items.GOLDEN_SHOVEL) ? (20 * 2) :
                                                        (20 * 10)); // Default to Wooden Shovel break time if none matches

    }


    public long getTimer() {
        return timer;
    }

    public long getTick() {
        return tick;
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
        tick++;
//        Main.LOGGER.info("Tick is: {}\nTime in seconds is {}", timer, timer / 20);
        if (timer >= getSandBreakTime(this)) {
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
        drops.add(new ItemStack(Items.SAND)); // Change this as needed for custom loot
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    public boolean checkPickType() {
        return pickType.getItem().equals(Items.STONE_SHOVEL);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);
        // Save the pickType as an NBT tag
        if (pickType != null) {
            CompoundTag pickTypeTag = new CompoundTag();
            pickTypeTag.putString("id", pickType.getItem().builtInRegistryHolder().key().location().toString()); // Save the item ID
            pickTypeTag.putInt("count", pickType.getCount()); // Save the count
            compound.put("PickType", pickTypeTag); // Add the tag to the main compound
        }
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("PickType")) {
            SyncableTileentity.loadPickType(compound, provider).ifPresent(stack -> this.pickType = stack);

        }
        if (pickType == null) {
            // If no pickType is saved, set a default one (e.g., Stone Pickaxe)
            pickType = new ItemStack(Items.STONE_PICKAXE);
        }

        timer = compound.getLong("Timer");
        super.loadAdditional(compound, provider);
    }

    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }
}
