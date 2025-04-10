package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.corelib.integration.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.items.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.awesomeshot5051.corelib.datacomponents.PickaxeEnchantments.*;
import static com.awesomeshot5051.corelib.datacomponents.Upgrades.*;
import static com.awesomeshot5051.resourceFarm.data.providers.tags.ModItemTags.*;


public class FluixDustFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, this.upgradeList);
    public boolean redstoneUpgradeEnabled;
    public boolean smelterUpgradeEnabled;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public ItemStack pickaxeType;
    public boolean soundOn;
    public List<ItemStack> fluixDustList = new ArrayList<>();


    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public FluixDustFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.FLDU_FARM.get(), ModBlocks.FLDU_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
    }

    public static double getFluixDustGenerateTime(FluixDustFarmTileentity farm) {
        return Upgrades.getUpgradeStatus(farm.upgrades, ModItems.SPEED_UPGRADE.toStack()) ? (double) Main.SERVER_CONFIG.coalGenerateTime.get() - (double) (20 * 4) / 8 : Main.SERVER_CONFIG.coalGenerateTime.get() - 20 * 4;

    }

    public static double getFluixDustBreakTime(FluixDustFarmTileentity farm) {

        return Upgrades.getUpgradeStatus(farm.upgrades, ModItems.SPEED_UPGRADE.toStack()) ? getFluixDustGenerateTime(farm) + (double) (20 * 4) / 8 : getFluixDustGenerateTime(farm) + 20 * 4;

    }

    @Override
    public long getTimer() {
        return timer;
    }

    @Override
    public boolean toggleSound() {
        this.soundOn = !this.soundOn;
        sync();
        return this.soundOn;
    }

    @Override
    public boolean getSound() {
        return this.soundOn;
    }


    @Override
    public Map<ItemStack, Boolean> getUpgrades() {
        return this.upgrades;
    }

    @Override
    public void tick() {
        timer++;
        for (ItemStack upgrade : this.upgradeList) {
            Upgrades.setUpgradeStatus(this.upgrades, upgrade, true);
        }
        this.redstoneUpgradeEnabled = Upgrades.getUpgradeStatus(upgrades, ModItems.REDSTONE_UPGRADE.toStack());
        assert level != null;
        if (!fluixDustList.isEmpty() && AE2Check.containsAllItems(fluixDustList, SLABS_AND_FLUX_CRYSTAL, level, 2)) {
            if (Upgrades.getUpgradeStatus(upgrades, ModItems.REDSTONE_UPGRADE.toStack())) {
                assert level != null;
                if (!level.hasNeighborSignal(getBlockPos())) {
                    return;
                } else if (timer >= getFluixDustBreakTime(this)) {
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
            } else if (timer >= getFluixDustBreakTime(this)) {
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
        }
        setChanged();
    }

    private List<ItemStack> getDrops() {
        if (!(level instanceof ServerLevel serverWorld)) {
            return Collections.emptyList();
        }

        int dropCount = serverWorld.random.nextIntBetweenInclusive(1, 3);
//        if (getUpgradeStatus(upgrades, ModItems.FORTUNE_UPGRADE.toStack())) {
//            dropCount = serverWorld.random.nextIntBetweenInclusive(1, 5);
//        }
        List<ItemStack> drops = new ArrayList<>();
        if (!fluixDustList.isEmpty() && AE2Check.containsAllItems(fluixDustList, SLABS_AND_FLUX_CRYSTAL, level, 2)) {
            drops.add(new ItemStack(AE2Blocks.FLUIX_DUST.get(), dropCount));
        }
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }


    @Override
    protected void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);

        if (!pickaxeEnchantments.isEmpty()) {
            ListTag enchantmentsList = new ListTag();
            for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : pickaxeEnchantments.entrySet()) {
                if (entry.getValue()) {
                    CompoundTag enchantmentTag = new CompoundTag();
                    enchantmentTag.putString("id", entry.getKey().location().toString());
                    enchantmentsList.add(enchantmentTag);
                }
            }
            compound.put("PickaxeEnchantments", enchantmentsList);
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
// Saving ae2ItemsList correctly
        if (fluixDustList != null && !fluixDustList.isEmpty()) {
            DataResult<Tag> tagResult = ItemStack.CODEC.listOf().encodeStart(NbtOps.INSTANCE, fluixDustList);
            compound.put("ae2Items", tagResult.result().orElse(new ListTag())); // Ensure it's saved correctly
        }
        CompoundTag soundOnTag = new CompoundTag();
        soundOnTag.putBoolean("soundOn", soundOn);
        compound.put("soundON", soundOnTag);
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }


    @Override
    protected void loadAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("PickaxeEnchantments")) {
            pickaxeEnchantments = SyncableTileentity.loadPickaxeEnchantments(compound, provider, this);
        }
        if (compound.contains("Upgrades")) {
            upgrades = SyncableTileentity.loadUpgrades(compound, provider, this);
        }
        // Fix: Ensure "ae2Items" exists before decoding
        if (compound.contains("ae2Items")) {
            DataResult<List<ItemStack>> decodedResult = ItemStack.CODEC.listOf().parse(NbtOps.INSTANCE, compound.get("ae2Items"));
            fluixDustList = decodedResult.result().orElse(List.of()); // Default to empty list if decoding fails
        } else {
            fluixDustList = List.of(); // Ensure it's never null
        }
        soundOn = compound.getBoolean("soundON");
        timer = compound.getLong("Timer");
        super.loadAdditional(compound, provider);
    }


    public IItemHandler getItemHandler() {
        return outputItemHandler;
    }

    @Override
    protected Map<ResourceKey<Enchantment>, Boolean> getPickaxeEnchantments() {
        return pickaxeEnchantments;
    }

}