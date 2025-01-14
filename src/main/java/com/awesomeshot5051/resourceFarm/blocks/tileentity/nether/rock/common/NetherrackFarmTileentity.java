package com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.OutputItemHandler;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.SyncableTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.VillagerTileentity;
import com.awesomeshot5051.resourceFarm.datacomponents.PickaxeEnchantments;
import com.awesomeshot5051.resourceFarm.enums.PickaxeType;
import de.maxhenkel.corelib.blockentity.ITickableBlockEntity;
import de.maxhenkel.corelib.inventory.ItemListInventory;
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

import static com.awesomeshot5051.resourceFarm.datacomponents.PickaxeEnchantments.getPickaxeEnchantmentStatus;
import static com.awesomeshot5051.resourceFarm.datacomponents.PickaxeEnchantments.initializePickaxeEnchantments;

public class NetherrackFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack pickType;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;
    private final boolean soundOn = true;

    public NetherrackFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NETHERRACK_FARM.get(), ModBlocks.NETHERRACK_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.STONE_PICKAXE);
    }

    public static double getNetherrackGenerateTime(NetherrackFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                1); // Default to Wooden PICKAXE divisor if none matches

    }

    public static double getNetherrackBreakTime(NetherrackFarmTileentity tileEntity) {
        PickaxeType pickAxe = PickaxeType.fromItem(tileEntity.getPickType().getItem());
        if (tileEntity.getPickType().isEnchanted()) {
            tileEntity.setPickaxeEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(tileEntity.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }
//
        return getNetherrackGenerateTime(tileEntity) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 8) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 4) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 2) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 2) :
                                        pickAxe.equals(PickaxeType.GOLDEN) ? (baseValue * 2) :
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


    public ItemStack getPickType() {
        return pickType;
    }

    @Override
    public void tick() {
        timer++;
        if (timer >= getNetherrackBreakTime(this)) {
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
        if (getPickaxeEnchantmentStatus(pickaxeEnchantments, Enchantments.FORTUNE)) {
            dropCount = serverWorld.random.nextIntBetweenInclusive(1, 5);
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Items.NETHERRACK, dropCount)); // Change this as needed for custom loot
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);
// Save the shovelType as an NBT tag
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
// If no shovelType is saved, set a default one (e.g., Stone Pickaxe)
            pickType = new ItemStack(Items.STONE_PICKAXE);
        }

        timer = compound.getLong("Timer");
        super.loadAdditional(compound, provider);
    }

    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getPickaxeEnchantments() {
        return pickaxeEnchantments;
    }
}
