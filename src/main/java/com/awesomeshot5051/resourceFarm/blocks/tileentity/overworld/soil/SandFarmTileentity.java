package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.OutputItemHandler;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.SyncableTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments;
import com.awesomeshot5051.resourceFarm.enums.ShovelType;
import com.awesomeshot5051.corelib.blockentity.ITickableBlockEntity;
import com.awesomeshot5051.corelib.inventory.ItemListInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments.getShovelEnchantmentStatus;
import static com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments.initializeShovelEnchantments;

@SuppressWarnings("ALL")
public class SandFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack shovelType;
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected long tick = timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;
    private boolean soundOn = true;

    public SandFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SAND_FARM.get(), ModBlocks.SAND_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        shovelType = new ItemStack(Items.STONE_SHOVEL);
    }

    public static double getSandGenerateTime(SandFarmTileentity tileEntity) {
        ShovelType shovel = ShovelType.fromItem(tileEntity.getShovelType().getItem());
        return (double) Main.SERVER_CONFIG.sandGenerateTime.get() /
                (shovel.equals(ShovelType.NETHERITE) ? 30 :
                        shovel.equals(ShovelType.DIAMOND) ? 25 :
                                shovel.equals(ShovelType.GOLDEN) ? 20 :
                                        shovel.equals(ShovelType.IRON) ? 15 :
                                                shovel.equals(ShovelType.STONE) ? 10
                                                        : 1);
    }

    public static double getSandBreakTime(SandFarmTileentity tileEntity) {

        ShovelType shovel = ShovelType.fromItem(tileEntity.getShovelType().getItem());
        if (tileEntity.getShovelType().isEnchanted()) {
            tileEntity.setShovelEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (ShovelEnchantments.getShovelEnchantmentStatus(tileEntity.shovelEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }
//
        return getSandGenerateTime(tileEntity) + (shovel.equals(ShovelType.NETHERITE) ? (baseValue * 8) :
                shovel.equals(ShovelType.DIAMOND) ? (baseValue * 4) :
                        shovel.equals(ShovelType.IRON) ? (baseValue * 2) :
                                shovel.equals(ShovelType.STONE) ? (baseValue * 2) :
                                        shovel.equals(ShovelType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10)); // Default to Wooden PICKAXE break time if none matches

    }


    public long getTimer() {
        return timer;
    }

    @Override
    public boolean toggleSound() {
        return !soundOn;
    }

    @Override
    public boolean getSound() {
        return soundOn;
    }

    public long getTick() {
        return tick;
    }


    public ItemStack getShovelType() {
        return shovelType;
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
        int dropCount = serverWorld.random.nextIntBetweenInclusive(1, 3);
        if (getShovelEnchantmentStatus(shovelEnchantments, Enchantments.FORTUNE)) {
            dropCount = serverWorld.random.nextIntBetweenInclusive(1, 5);
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Items.SAND, dropCount)); // Change this as needed for custom loot
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    public boolean checkshovelType() {
        return shovelType.getItem().equals(Items.STONE_SHOVEL);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);
        // Save the shovelType as an NBT tag
        if (shovelType != null) {
            CompoundTag shovelTypeTag = new CompoundTag();
            shovelTypeTag.putString("id", shovelType.getItem().builtInRegistryHolder().key().location().toString()); // Save the item ID
            shovelTypeTag.putInt("count", shovelType.getCount()); // Save the count
            compound.put("shovelType", shovelTypeTag); // Add the tag to the main compound
        }
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("shovelType")) {
            SyncableTileentity.loadShovelType(compound, provider).ifPresent(stack -> this.shovelType = stack);

        }
        if (shovelType == null) {
            // If no shovelType is saved, set a default one (e.g., Stone Pickaxe)
            shovelType = new ItemStack(Items.STONE_PICKAXE);
        }

        timer = compound.getLong("Timer");
        super.loadAdditional(compound, provider);
    }

    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getShovelEnchantments() {
        return shovelEnchantments;
    }
}
