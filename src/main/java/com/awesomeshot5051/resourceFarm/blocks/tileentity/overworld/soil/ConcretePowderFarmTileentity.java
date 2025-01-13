package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import de.maxhenkel.corelib.blockentity.*;
import de.maxhenkel.corelib.inventory.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
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

public class ConcretePowderFarmTileentity extends VillagerTileentity implements ITickableBlockEntity {

    public ItemStack shovelType;
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public ConcretePowderFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CONCRETE_POWDER_FARM.get(), ModBlocks.CONCRETE_POWDER_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        shovelType = new ItemStack(Items.STONE_SHOVEL);
    }

    public static double getConcretePowderGenerateTime(ConcretePowderFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getShovelType().getItem().equals(Items.IRON_SHOVEL) ? 15 :
                        tileEntity.getShovelType().getItem().equals(Items.GOLDEN_SHOVEL) ? 20 :
                                tileEntity.getShovelType().getItem().equals(Items.DIAMOND_SHOVEL) ? 25 :
                                        tileEntity.getShovelType().getItem().equals(Items.NETHERITE_SHOVEL) ? 30 :
                                                1); // Default to Wooden SHOVEL divisor if none matches
    }

    public static double getConcretePowderBreakTime(ConcretePowderFarmTileentity tileEntity) {

        ShovelType shovel = ShovelType.fromItem(tileEntity.getShovelType().getItem());
        if (tileEntity.getShovelType().isEnchanted()) {
            tileEntity.setShovelEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (ShovelEnchantments.getShovelEnchantmentStatus(tileEntity.shovelEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }
//
        return getConcretePowderGenerateTime(tileEntity) + (shovel.equals(ShovelType.NETHERITE) ? (baseValue * 8) :
                shovel.equals(ShovelType.DIAMOND) ? (baseValue * 4) :
                        shovel.equals(ShovelType.IRON) ? (baseValue * 2) :
                                shovel.equals(ShovelType.STONE) ? (baseValue * 2) :
                                        shovel.equals(ShovelType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10)); // Default to Wooden PICKAXE break time if none matches
    }

    public long getTimer() {
        return timer;
    }


    public ItemStack getShovelType() {
        return shovelType;
    }

    @Override
    public void tick() {
        timer++;

        if (timer >= getConcretePowderBreakTime(this)) {
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

        // Create a list of all concrete powder colors
        Item[] concretePowders = {
                Items.WHITE_CONCRETE_POWDER,
                Items.ORANGE_CONCRETE_POWDER,
                Items.MAGENTA_CONCRETE_POWDER,
                Items.LIGHT_BLUE_CONCRETE_POWDER,
                Items.YELLOW_CONCRETE_POWDER,
                Items.LIME_CONCRETE_POWDER,
                Items.PINK_CONCRETE_POWDER,
                Items.GRAY_CONCRETE_POWDER,
                Items.LIGHT_GRAY_CONCRETE_POWDER,
                Items.CYAN_CONCRETE_POWDER,
                Items.PURPLE_CONCRETE_POWDER,
                Items.BLUE_CONCRETE_POWDER,
                Items.BROWN_CONCRETE_POWDER,
                Items.GREEN_CONCRETE_POWDER,
                Items.RED_CONCRETE_POWDER,
                Items.BLACK_CONCRETE_POWDER
        };

        // Pick a random color
        Item randomConcretePowder = concretePowders[serverWorld.random.nextInt(concretePowders.length)];

        // Create a new ItemStack for the randomly chosen color
        int dropCount = serverWorld.random.nextIntBetweenInclusive(1, 3);
        if (getPickaxeEnchantmentStatus(pickaxeEnchantments, Enchantments.FORTUNE)) {
            dropCount = serverWorld.random.nextIntBetweenInclusive(1, 5);
        }
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(randomConcretePowder));

        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);
        // Save the shovelType as an NBT tag
        if (shovelType != null) {
            CompoundTag shovelTypeTag = new CompoundTag();
            shovelTypeTag.putString("id", BuiltInRegistries.ITEM.getKey(shovelType.getItem()).toString()); // Save the item ID
            shovelTypeTag.putInt("count", shovelType.getCount()); // Save the count
            compound.put("ShovelType", shovelTypeTag); // Add the tag to the main compound
        }
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("ShovelType")) {
            SyncableTileentity.loadShovelType(compound, provider).ifPresent(stack -> this.shovelType = stack);

        }
        if (shovelType == null) {
            // If no shovelType is saved, set a default one (e.g., Stone Shovel)
            shovelType = new ItemStack(Items.STONE_SHOVEL);
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

