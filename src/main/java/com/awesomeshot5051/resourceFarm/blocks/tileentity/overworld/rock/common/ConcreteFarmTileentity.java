package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
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
import static com.awesomeshot5051.resourceFarm.blocks.overworld.rock.common.ConcreteFarmBlock.*;

@SuppressWarnings("ALL")
public class ConcreteFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

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
    protected long tick = timer;
    private boolean soundOn = true;

    public ConcreteFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CONCRETE_FARM.get(), ModBlocks.CONCRETE_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.WOODEN_PICKAXE);
    }

    public static double getConcreteGenerateTime(ConcreteFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                1);
    }

    public static double getConcreteBreakTime(ConcreteFarmTileentity tileEntity) {
        PickaxeType pickAxe = PickaxeType.fromItem(tileEntity.getPickType().getItem());
        if (tileEntity.getPickType().isEnchanted()) {
            tileEntity.setPickaxeEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(tileEntity.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }

        return getConcreteGenerateTime(tileEntity) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 8) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 4) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 2) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 2) :
                                        pickAxe.equals(PickaxeType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10));
    }

    public DyeColor getBlockColor() {
        BlockState state = this.getBlockState();
        if (state.hasProperty(COLOR)) {
            return state.getValue(COLOR);
        }
        return DyeColor.WHITE;
    }

    private void setBlockColor(DyeColor color) {
        assert level != null;
        BlockState state = getBlockState(); // Replace with how you get the block state
        if (state.hasProperty(ConcreteFarmBlock.COLOR)) {
            setBlockState(state.setValue(ConcreteFarmBlock.COLOR, color));
        }
    }

    private ItemStack setBlockColor(DyeColor concreteColor, int dropCount) {
        return switch (concreteColor) {
            case ORANGE -> new ItemStack(Items.ORANGE_CONCRETE, dropCount);
            case MAGENTA -> new ItemStack(Items.MAGENTA_CONCRETE, dropCount);
            case LIGHT_BLUE -> new ItemStack(Items.LIGHT_BLUE_CONCRETE, dropCount);
            case YELLOW -> new ItemStack(Items.YELLOW_CONCRETE, dropCount);
            case LIME -> new ItemStack(Items.LIME_CONCRETE, dropCount);
            case PINK -> new ItemStack(Items.PINK_CONCRETE, dropCount);
            case GRAY -> new ItemStack(Items.GRAY_CONCRETE, dropCount);
            case LIGHT_GRAY -> new ItemStack(Items.LIGHT_GRAY_CONCRETE, dropCount);
            case CYAN -> new ItemStack(Items.CYAN_CONCRETE, dropCount);
            case PURPLE -> new ItemStack(Items.PURPLE_CONCRETE, dropCount);
            case BLUE -> new ItemStack(Items.BLUE_CONCRETE, dropCount);
            case BROWN -> new ItemStack(Items.BROWN_CONCRETE, dropCount);
            case GREEN -> new ItemStack(Items.GREEN_CONCRETE, dropCount);
            case RED -> new ItemStack(Items.RED_CONCRETE, dropCount);
            case BLACK -> new ItemStack(Items.BLACK_CONCRETE, dropCount);
            default -> new ItemStack(Items.WHITE_CONCRETE, dropCount);
        };
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

    public long getTick() {
        return tick;
    }

    public void setTick(long tick) {
        this.tick = tick;
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
        tick++;

        if (timer >= getConcreteBreakTime(this)) {
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
        DyeColor blockColor = getBlockColor();
        ItemStack BlockColor = setBlockColor(blockColor, dropCount);
        drops.add(BlockColor);
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
        compound.putString("Color", getBlockColor().getName());
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
            Main.LOGGER.info("{} uses {}", this.getType(), Component.translatable(String.valueOf(pickType.getItem())));
        }
        if (pickType == null) {
            pickType = new ItemStack(Items.WOODEN_PICKAXE);
        }
        if (compound.contains("Color", Tag.TAG_STRING)) {
            setBlockColor(DyeColor.valueOf(compound.getString("Color").toUpperCase()));
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

