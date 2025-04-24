package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;

import com.awesomeshot5051.corelib.blockentity.FarmTileentity;
import com.awesomeshot5051.corelib.blockentity.ITickableBlockEntity;
import com.awesomeshot5051.corelib.blockentity.SyncableTileentity;
import com.awesomeshot5051.corelib.datacomponents.Upgrades;
import com.awesomeshot5051.corelib.integration.AE2Check;
import com.awesomeshot5051.corelib.inventory.ItemListInventory;
import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.OutputItemHandler;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.integration.ae2.AE2Blocks;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.mojang.serialization.DataResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.awesomeshot5051.corelib.datacomponents.PickaxeEnchantments.getPickaxeEnchantmentStatus;
import static com.awesomeshot5051.corelib.datacomponents.PickaxeEnchantments.initializePickaxeEnchantments;
import static com.awesomeshot5051.corelib.datacomponents.Upgrades.initializeUpgrades;

public class QuantumAlloyFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, this.upgradeList);
    public boolean redstoneUpgradeEnabled;
    public boolean smelterUpgradeEnabled;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public boolean soundOn;
    public ItemContainerContents ae2Items;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;
    private List<ItemStack> quantumAlloyRequirements = new ArrayList<>(4);
    private boolean inscriberInstalled = false;

    public QuantumAlloyFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.QA_FARM.get(), ModBlocks.QA_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
//        ae2Items = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.AIR)));
    }

    public static double getQuantumAlloyGenerateTime(QuantumAlloyFarmTileentity farm) {
        return Main.SERVER_CONFIG.coalGenerateTime.get() - 20 * 4;

    }

    public static double getQuantumAlloyBreakTime(QuantumAlloyFarmTileentity farm) {
        return getQuantumAlloyGenerateTime(farm) + 20 * 4;
    }

    @Override
    public <T extends FarmTileentity> boolean checkPasses(T farm) {
        return AE2Check.containsAllItems(AE2Blocks.quantumAlloyRequirements, this.quantumAlloyRequirements);
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
        this.smelterUpgradeEnabled = Upgrades.getUpgradeStatus(upgrades, ModItems.SMELTER_UPGRADE.toStack());
        setChanged();
        if (!(quantumAlloyRequirements.size() < 4) && !this.checkPasses(this)) {
        } else if (Upgrades.getUpgradeStatus(upgrades, ModItems.REDSTONE_UPGRADE.toStack())) {
            assert level != null;
            if (!level.hasNeighborSignal(getBlockPos())) {
            } else if (timer >= getQuantumAlloyBreakTime(this)) {
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
        } else {
            if (timer == getQuantumAlloyGenerateTime(this)) {
                sync();
            } else if (timer >= getQuantumAlloyBreakTime(this)) {
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
        if (!(quantumAlloyRequirements.size() < 4) && AE2Check.containsAllItems(quantumAlloyRequirements, AE2Blocks.quantumAlloyRequirements)) {
            drops.add(new ItemStack(AAEItems.QUANTUM_ALLOY.get(), dropCount));
        }
        if (AE2Check.containsAllItems(quantumAlloyRequirements, AE2Blocks.quantumAlloyRequirements) && Upgrades.getUpgradeStatus(upgrades, ModItems.INSCRIBER_UPGRADE.toStack()) && inscriberInstalled) {
            drops.clear();
            drops.add(new ItemStack(AAEItems.QUANTUM_PROCESSOR_PRINT, dropCount));
        }

        return drops;
    }

    public List<ItemStack> getQuantumAlloyRequirements() {
        return quantumAlloyRequirements;
    }

    public void setQuantumAlloyRequirements(List<ItemStack> quantumAlloyRequirements) {
        this.quantumAlloyRequirements = quantumAlloyRequirements;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }


    @Override
    protected void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider provider) {
        ContainerHelper.saveAllItems(compound, inventory, false, provider);

        // Saving quantumAlloyRequirements correctly
        if (quantumAlloyRequirements != null && !quantumAlloyRequirements.isEmpty()) {
            DataResult<Tag> tagResult = ItemStack.CODEC.listOf().encodeStart(NbtOps.INSTANCE, quantumAlloyRequirements);
            compound.put("QAItems", tagResult.result().orElse(new ListTag())); // Ensure it's saved correctly
        }
        if (inscriberInstalled) {
            compound.putBoolean("inscriberInstalled", true);
        }
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

        CompoundTag soundOnTag = new CompoundTag();
        soundOnTag.putBoolean("soundOn", soundOn);
        compound.put("soundON", soundOnTag);
        compound.putLong("Timer", timer);

        super.saveAdditional(compound, provider);
    }


    @Override
    protected void loadAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(compound, provider);
        ContainerHelper.loadAllItems(compound, inventory, provider);

//        if (compound.contains("PickType")) {
//            SyncableTileentity.loadPickType(compound, provider).ifPresent(stack -> this.pickType = stack);
//        }
        if (compound.contains("PickaxeEnchantments")) {
            pickaxeEnchantments = SyncableTileentity.loadPickaxeEnchantments(compound, provider, this);
        }
        if (compound.contains("Upgrades")) {
            upgrades = SyncableTileentity.loadUpgrades(compound, provider, this);
        }
        if (compound.contains("inscriberInstalled")) {
            inscriberInstalled = compound.getBoolean("inscriberInstalled");
        }
        if (compound.contains("QAItems")) {
            DataResult<List<ItemStack>> decodedResult = ItemStack.CODEC.listOf().parse(NbtOps.INSTANCE, compound.get("ae2Items"));

            quantumAlloyRequirements = new ArrayList<>(decodedResult.result().orElseGet(ArrayList::new));
        } else {
            quantumAlloyRequirements = List.of(); // Ensure it's never null
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

    public boolean getInscriberInstalled() {
        return inscriberInstalled;
    }

    public void setInscriberInstalled(boolean inscriberInstalled) {
        this.inscriberInstalled = inscriberInstalled;
    }
}