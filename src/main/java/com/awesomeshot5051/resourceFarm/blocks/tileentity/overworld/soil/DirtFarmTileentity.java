package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.mojang.serialization.*;
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

import static com.awesomeshot5051.corelib.datacomponents.Upgrades.*;
import static com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments.*;

public class DirtFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    private final boolean soundOn = true;
    public ItemStack shovelType;
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES);
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public List<ItemStack> upgradeList = Main.UPGRADES;
    public boolean redstoneUpgradeEnabled;
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public DirtFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.DIRT_FARM.get(), ModBlocks.DIRT_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        shovelType = new ItemStack(Items.WOODEN_SHOVEL);
    }

    public static double getDirtGenerateTime(DirtFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getShovelType().getItem().equals(Items.IRON_SHOVEL) ? 15 :
                        tileEntity.getShovelType().getItem().equals(Items.GOLDEN_SHOVEL) ? 20 :
                                tileEntity.getShovelType().getItem().equals(Items.DIAMOND_SHOVEL) ? 25 :
                                        tileEntity.getShovelType().getItem().equals(Items.NETHERITE_SHOVEL) ? 30 :
                                                1);
    }

    public static double getDirtBreakTime(DirtFarmTileentity tileEntity) {

        ShovelType shovel = ShovelType.fromItem(tileEntity.getShovelType().getItem());
        if (tileEntity.getShovelType().isEnchanted()) {
            tileEntity.setShovelEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (ShovelEnchantments.getShovelEnchantmentStatus(tileEntity.shovelEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }

        return getDirtGenerateTime(tileEntity) + (shovel.equals(ShovelType.NETHERITE) ? (baseValue * 8) :
                shovel.equals(ShovelType.DIAMOND) ? (baseValue * 4) :
                        shovel.equals(ShovelType.IRON) ? (baseValue * 2) :
                                shovel.equals(ShovelType.STONE) ? (baseValue * 2) :
                                        shovel.equals(ShovelType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10));
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


    public ItemStack getShovelType() {
        return shovelType;
    }

    @Override
    public void tick() {
        timer++;

        if (timer >= getDirtBreakTime(this)) {
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
        drops.add(new ItemStack(Items.DIRT, dropCount));
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);

        if (shovelType != null) {
            CompoundTag pickTypeTag = new CompoundTag();
            pickTypeTag.putString("id", BuiltInRegistries.ITEM.getKey(shovelType.getItem()).toString());
            pickTypeTag.putInt("count", shovelType.getCount());
            compound.put("PickType", pickTypeTag);
        }
        if (!shovelEnchantments.isEmpty()) {
            ListTag enchantmentsList = new ListTag();
            for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : shovelEnchantments.entrySet()) {
                if (entry.getValue()) {
                    CompoundTag enchantmentTag = new CompoundTag();
                    enchantmentTag.putString("id", entry.getKey().location().toString());
                    enchantmentsList.add(enchantmentTag);
                }
            }
            compound.put("ShovelEnchantments", enchantmentsList);
        }
        if (!upgrades.isEmpty()) {
            ListTag upgradesList = new ListTag();
            for (Map.Entry<ItemStack, Boolean> upgradeMap : upgrades.entrySet()) {
                if (upgradeMap.getValue()) {
                    CompoundTag upgradeTag = new CompoundTag();
                    DataResult<Tag> tag = ItemStack.SINGLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, upgradeMap.getKey());
                    upgradeTag.put("id", tag.getOrThrow());
                    upgradesList.add(upgradeTag);
                }
            }
            compound.put("Upgrades", upgradesList);
        }
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("ShovelType")) {
            SyncableTileentity.loadPickType(compound, provider).ifPresent(stack -> this.shovelType = stack);
        }
        if (compound.contains("ShovelEnchantments")) {
            shovelEnchantments = SyncableTileentity.loadShovelEnchantments(compound, provider, this);
        }
        if (shovelType == null) {

            shovelType = new ItemStack(Items.WOODEN_SHOVEL);
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

