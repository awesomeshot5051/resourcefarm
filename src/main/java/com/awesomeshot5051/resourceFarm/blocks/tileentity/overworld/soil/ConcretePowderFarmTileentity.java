package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.soil.*;
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

import static com.awesomeshot5051.corelib.datacomponents.ShovelEnchantments.*;
import static com.awesomeshot5051.corelib.datacomponents.Upgrades.*;


public class ConcretePowderFarmTileentity extends FarmTileentity implements ITickableBlockEntity {


    private final boolean soundOn = true;
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, upgradeList);
    public boolean redstoneUpgradeEnabled;
    public ItemStack shovelType;
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
        shovelType = new ItemStack(Items.WOODEN_SHOVEL);
    }

    public static double getConcretePowderGenerateTime(ConcretePowderFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getShovelType().getItem().equals(Items.IRON_SHOVEL) ? 15 :
                        tileEntity.getShovelType().getItem().equals(Items.GOLDEN_SHOVEL) ? 20 :
                                tileEntity.getShovelType().getItem().equals(Items.DIAMOND_SHOVEL) ? 25 :
                                        tileEntity.getShovelType().getItem().equals(Items.NETHERITE_SHOVEL) ? 30 :
                                                1);
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

        return getConcretePowderGenerateTime(tileEntity) + (shovel.equals(ShovelType.NETHERITE) ? (baseValue * 8) :
                shovel.equals(ShovelType.DIAMOND) ? (baseValue * 4) :
                        shovel.equals(ShovelType.IRON) ? (baseValue * 2) :
                                shovel.equals(ShovelType.STONE) ? (baseValue * 2) :
                                        shovel.equals(ShovelType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10));
    }

    private DyeColor getBlockColor() {
        BlockState state = this.getBlockState();
        if (state.hasProperty(ConcretePowderFarmBlock.COLOR)) {
            return state.getValue(ConcretePowderFarmBlock.COLOR);
        }
        return DyeColor.WHITE;
    }

    private void setBlockColor(DyeColor color) {
        assert level != null;
        BlockState state = this.getBlockState(); // Replace with how you get the block state
        if (state.hasProperty(ConcretePowderFarmBlock.COLOR)) {
            state.setValue(ConcretePowderFarmBlock.COLOR, color);
        }
    }

    private ItemStack setBlockColor(DyeColor concretePowderColor, int dropCount) {
        return switch (concretePowderColor) {
            case ORANGE -> new ItemStack(Items.ORANGE_CONCRETE_POWDER, dropCount);
            case MAGENTA -> new ItemStack(Items.MAGENTA_CONCRETE_POWDER, dropCount);
            case LIGHT_BLUE -> new ItemStack(Items.LIGHT_BLUE_CONCRETE_POWDER, dropCount);
            case YELLOW -> new ItemStack(Items.YELLOW_CONCRETE_POWDER, dropCount);
            case LIME -> new ItemStack(Items.LIME_CONCRETE_POWDER, dropCount);
            case PINK -> new ItemStack(Items.PINK_CONCRETE_POWDER, dropCount);
            case GRAY -> new ItemStack(Items.GRAY_CONCRETE_POWDER, dropCount);
            case LIGHT_GRAY -> new ItemStack(Items.LIGHT_GRAY_CONCRETE_POWDER, dropCount);
            case CYAN -> new ItemStack(Items.CYAN_CONCRETE_POWDER, dropCount);
            case PURPLE -> new ItemStack(Items.PURPLE_CONCRETE_POWDER, dropCount);
            case BLUE -> new ItemStack(Items.BLUE_CONCRETE_POWDER, dropCount);
            case BROWN -> new ItemStack(Items.BROWN_CONCRETE_POWDER, dropCount);
            case GREEN -> new ItemStack(Items.GREEN_CONCRETE_POWDER, dropCount);
            case RED -> new ItemStack(Items.RED_CONCRETE_POWDER, dropCount);
            case BLACK -> new ItemStack(Items.BLACK_CONCRETE_POWDER, dropCount);
            default -> new ItemStack(Items.WHITE_CONCRETE_POWDER, dropCount);
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

    public ItemStack getShovelType() {
        return shovelType;
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


        int dropCount = serverWorld.random.nextIntBetweenInclusive(1, 3);
        if (getShovelEnchantmentStatus(shovelEnchantments, Enchantments.FORTUNE)) {
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

        if (shovelType != null) {
            DataResult<Tag> tag = ItemStack.SINGLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, shovelType.getItem().getDefaultInstance());
            compound.put("ShovelType", tag.getOrThrow());
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
        compound.putString("Color", getBlockColor().getName());
        compound.putLong("Timer", timer);
        super.saveAdditional(compound, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
        if (compound.contains("ShovelType")) {
            SyncableTileentity.loadShovelType(compound, provider).ifPresent(stack -> this.shovelType = stack);
        }
        if (compound.contains("ShovelEnchantments")) {
            shovelEnchantments = SyncableTileentity.loadShovelEnchantments(compound, provider, this);
        }
        if (shovelType == null) {
            shovelType = new ItemStack(Items.WOODEN_SHOVEL);
        }
        if (compound.contains("Upgrades")) {
            upgrades = SyncableTileentity.loadUpgrades(compound, provider, this);
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

    protected Map<ResourceKey<Enchantment>, Boolean> getShovelEnchantments() {
        return shovelEnchantments;
    }
}

