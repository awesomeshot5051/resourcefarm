package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import de.maxhenkel.corelib.blockentity.*;
import de.maxhenkel.corelib.inventory.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.items.*;

import java.util.*;

import static com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments.*;

public class SandFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack shovelType;
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected long tick = timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

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
        int dropCount = 0;
if (getPickaxeEnchantmentStatus(pickaxeEnchantments, Enchantments.FORTUNE)) {
           dropCount = serverWorld.random.nextIntBetweenInclusive(0, 5);
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(dropCount, new ItemStack(Items.SAND)); // Change this as needed for custom loot
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
