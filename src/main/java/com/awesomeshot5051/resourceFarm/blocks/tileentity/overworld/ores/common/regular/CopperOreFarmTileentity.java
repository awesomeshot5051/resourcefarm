package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular;

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

public class CopperOreFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    // Update the loot table for endermans instead of iron golems
//    private static final ResourceKey<LootTable> ENDERMAN_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/enderman"));


    public ItemStack pickType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected long breakStage;
    protected OutputItemHandler outputItemHandler;

    public CopperOreFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.COPPER_FARM.get(), ModBlocks.COPPER_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.STONE_PICKAXE);
    }

    public static int getCopperGenerateTime() {
        return Main.SERVER_CONFIG.copperGenerateTime.get() - 20 * 10;
    }

    public static int getCopperBreakTime() {
        return getCopperGenerateTime() + 20 * 10;
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
        // Increment the main timer
        timer++;

        // Sync break stage only during breaking animation
        if (timer >= getCopperGenerateTime() && timer < getCopperBreakTime()) {
            breakStage = (timer - getCopperGenerateTime()) / (20); // Advance every 20 ticks
            if (breakStage > 9) { // Reset if it exceeds max stage
                breakStage = 0;
            }
        } else {
            breakStage = 0; // Reset break stage when not animating
        }

        // Handle reset and item drops
        if (timer >= getCopperBreakTime()) {
            for (ItemStack drop : getDrops()) {
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    drop = itemHandler.insertItem(i, drop, false);
                    if (drop.isEmpty()) {
                        break;
                    }
                }
            }

            timer = 0L; // Reset the timer
            sync(); // Sync to the client
        }

        setChanged(); // Mark the tile entity as dirty
    }

    private List<ItemStack> getDrops() {
        if (!(level instanceof ServerLevel serverWorld)) {
            return Collections.emptyList();
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Items.COPPER_INGOT));


        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.saveAdditional(compound, provider);
        ContainerHelper.saveAllItems(compound, inventory, false, provider);
//        if (Picktype != null) ContainerHelper.saveAllItems(compound, Picktype, false, provider);
        compound.putLong("Timer", timer);
//        compound.putString("pick_type", Picktype.get(1).toString());
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
//        NonNullList<ItemStack> tempList = NonNullList.copyOf((java.util.Collection<? extends ItemStack>) compound.get("pick_type"));
        super.loadAdditional(compound, provider);
    }

    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }
}
