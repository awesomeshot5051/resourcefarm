package com.awesomeshot5051.resourceFarm.integration.ae2.Quartz;

import appeng.core.definitions.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.enums.*;
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

import java.util.*;

import static com.awesomeshot5051.corelib.datacomponents.PickaxeEnchantments.*;
import static com.awesomeshot5051.corelib.datacomponents.Upgrades.*;
import static com.awesomeshot5051.resourceFarm.integration.SpeedStatusCheck.*;

@SuppressWarnings("ALL")
public class CertusQuartzCrystalFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    public ItemStack pickType;
    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, this.upgradeList);
    public boolean redstoneUpgradeEnabled;
    public boolean smelterUpgradeEnabled;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public ItemStack pickaxeType;
    public boolean soundOn;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;
    private Boolean inscriberPressInstalled = false;
    private Map<ItemStack, Integer> speedUpItems = new HashMap<>();

    public CertusQuartzCrystalFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CQC_FARM.get(), ModBlocks.CQC_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.WOODEN_PICKAXE);
        speedUpItems = new HashMap<>();
    }

    public static double getCertusQuartzGenerateTime(CertusQuartzCrystalFarmTileentity tileEntity) {
        double returnValue = (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                1);

        Map<ItemStack, Integer> speedUpItems = tileEntity.getSpeedUpItems();
        if (speedStatusCheck(speedUpItems, AEBlocks.GROWTH_ACCELERATOR.asItem().getDefaultInstance(),
                AEBlocks.CRYSTAL_RESONANCE_GENERATOR.asItem().getDefaultInstance())) {

            // Find the matching key in the map
            for (Map.Entry<ItemStack, Integer> entry : speedUpItems.entrySet()) {
                if (entry.getKey().is(AEBlocks.CRYSTAL_RESONANCE_GENERATOR.asItem())) {
                    Integer count = entry.getValue();
                    if (count != null && count > 0) {
                        returnValue = returnValue / count;
                    }
                    break;
                }
            }
        }
        return returnValue;
    }

    public static double getCertusQuartzBreakTime(CertusQuartzCrystalFarmTileentity tileEntity) {
        PickaxeType pickAxe = PickaxeType.fromItem(tileEntity.getPickType().getItem());
        if (tileEntity.getPickType().isEnchanted()) {
            tileEntity.setPickaxeEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(tileEntity.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }

        double returnValue = getCertusQuartzGenerateTime(tileEntity) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 8) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 4) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 2) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 2) :
                                        pickAxe.equals(PickaxeType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10));

        Map<ItemStack, Integer> speedUpItems = tileEntity.getSpeedUpItems();
        if (speedStatusCheck(speedUpItems, AEBlocks.GROWTH_ACCELERATOR.asItem().getDefaultInstance(),
                AEBlocks.CRYSTAL_RESONANCE_GENERATOR.asItem().getDefaultInstance())) {

            // Find the matching key in the map
            for (Map.Entry<ItemStack, Integer> entry : speedUpItems.entrySet()) {
                if (entry.getKey().is(AEBlocks.CRYSTAL_RESONANCE_GENERATOR.asItem())) {
                    Integer count = entry.getValue();
                    if (count != null && count > 0) {
                        returnValue = returnValue / count;
                    }
                    break;
                }
            }
        }
        return returnValue;
    }

    public long getTimer() {
        return timer;
    }

    public boolean getInscriberPressInstalled() {
        return this.inscriberPressInstalled;
    }

    public void setInscriberPressInstalled(boolean inscriberPressInstalled) {
        this.inscriberPressInstalled = inscriberPressInstalled;
    }

    public Map<ItemStack, Integer> getSpeedUpItems() {
        return speedUpItems != null ? speedUpItems : new HashMap<>();
    }

    public void setSpeedUpItems(Map<ItemStack, Integer> speedUpItems) {
        this.speedUpItems = speedUpItems;
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


    public ItemStack getPickType() {
        return pickType;
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
        if (Upgrades.getUpgradeStatus(upgrades, ModItems.REDSTONE_UPGRADE.toStack())) {
            if (!level.hasNeighborSignal(getBlockPos())) {
                return;
            } else if (timer >= getCertusQuartzBreakTime(this)) {
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
        } else if (timer >= getCertusQuartzBreakTime(this)) {
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
        drops.add(new ItemStack(AEItems.CERTUS_QUARTZ_CRYSTAL.get(), dropCount));
        if (Upgrades.getUpgradeStatus(upgrades, ModItems.INSCRIBER_UPGRADE.toStack())) {
            drops.clear();
            drops.add(new ItemStack(AEItems.CERTUS_QUARTZ_DUST.get(), dropCount));
        }
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }


    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);

        try {
            if (pickType != null) {
                DataResult<Tag> tag = ItemStack.STRICT_SINGLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, pickType.getItem().getDefaultInstance());
                compound.put("PickType", tag.getOrThrow());
            }
        } catch (IllegalStateException e) {
            System.err.println("Failed to encode pickType due to registry access issue: " + e.getMessage());
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
        if (inscriberPressInstalled) {
            compound.putBoolean("InscriberPressInstalled", inscriberPressInstalled);
        }
        if (getSpeedUpItems() != null && !speedUpItems.isEmpty()) {
            ListTag speedUpItemsList = new ListTag();
            for (Map.Entry<ItemStack, Integer> entry : speedUpItems.entrySet()) {
                CompoundTag itemTag = new CompoundTag();
                DataResult<Tag> tag = ItemStack.SINGLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, entry.getKey());
                itemTag.put("item", tag.getOrThrow());
                itemTag.putInt("count", entry.getValue());
                speedUpItemsList.add(itemTag);
            }
            compound.put("SpeedUpItems", speedUpItemsList);
        }
        CompoundTag soundOnTag = new CompoundTag();
        soundOnTag.putBoolean("soundOn", soundOn);
        compound.put("soundON", soundOnTag);
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }


    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("PickType")) {
            SyncableTileentity.loadPickType(compound, provider).ifPresent(stack -> this.pickType = stack);
        }
        if (compound.contains("PickaxeEnchantments")) {
            pickaxeEnchantments = SyncableTileentity.loadPickaxeEnchantments(compound, provider, this);
        }
        if (compound.contains("Upgrades")) {
            upgrades = SyncableTileentity.loadUpgrades(compound, provider, this);
        }
        if (pickType == null) {

            pickType = new ItemStack(Items.WOODEN_PICKAXE);
        }
        if (compound.contains("InscriberPressInstalled")) {
            inscriberPressInstalled = compound.getBoolean("InscriberPressInstalled");
        }
        if (compound.contains("SpeedUpItems")) {
            speedUpItems = new HashMap<>();
            ListTag speedUpItemsList = compound.getList("SpeedUpItems", 10);

            for (int i = 0; i < speedUpItemsList.size(); ++i) {
                CompoundTag itemTag = speedUpItemsList.getCompound(i);
                CompoundTag itemData = itemTag.getCompound("item");
                ItemStack item = ItemStack.parseOptional(provider, itemData);
                int count = itemTag.getInt("count");

                if (!item.isEmpty()) {
                    speedUpItems.put(item, count);
                }
            }
        } else {
            speedUpItems = new HashMap<>(); // Ensure it's never null
        }
        soundOn = compound.getBoolean("soundON");
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