package com.awesomeshot5051.resourceFarm.blocks.tileentity.end.rock.common;

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

public class EndStoneFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    private final boolean soundOn = true;
    public ItemStack pickType;
    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, upgradeList);
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public boolean redstoneUpgradeEnabled;
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public EndStoneFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.ESTONE_FARM.get(), ModBlocks.ESTONE_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.WOODEN_PICKAXE);
    }

    public static double getEndStoneGenerateTime(EndStoneFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                1);

    }

    public static double getEndStoneBreakTime(EndStoneFarmTileentity farm) {
        PickaxeType pickAxe = PickaxeType.fromItem(farm.getPickType().getItem());
        if (farm.getPickType().isEnchanted()) {
            farm.setPickaxeEnchantmentStatus(farm);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(farm.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }
        return getEndStoneGenerateTime(farm) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 3.2) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 5.6) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 4.8) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 6.4) :
                                        pickAxe.equals(PickaxeType.WOODEN) ? (baseValue * 6.4) :
                                                6.4);

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
    public Map<ItemStack, Boolean> getUpgrades() {
        return upgrades;
    }

    @Override
    public void tick() {
        timer++;
        for (ItemStack upgrade : upgradeList) {
            Upgrades.setUpgradeStatus(upgrades, upgrade, true);
        }
        redstoneUpgradeEnabled = Upgrades.getUpgradeStatus(upgrades, ModItems.REDSTONE_UPGRADE.toStack());
        assert level != null;
        if (redstoneUpgradeEnabled && !level.hasNeighborSignal(getBlockPos())) {
            return;
        }
        if (timer >= getEndStoneBreakTime(this)) {
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
        drops.add(new ItemStack(Items.END_STONE, dropCount));
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
        if (pickType == null) {

            pickType = new ItemStack(Items.WOODEN_PICKAXE);
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
