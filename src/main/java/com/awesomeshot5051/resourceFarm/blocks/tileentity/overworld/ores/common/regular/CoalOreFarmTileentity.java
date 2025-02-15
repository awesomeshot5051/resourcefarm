package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.items.*;

import java.util.*;

import static com.awesomeshot5051.corelib.datacomponents.PickaxeEnchantments.*;
import static com.awesomeshot5051.corelib.datacomponents.Upgrades.*;

public class CoalOreFarmTileentity extends FarmTileentity implements ITickableBlockEntity {
    private final boolean soundOn = true;
    public ItemStack pickType;
    public List<ItemStack> upgradeList = new ArrayList<>();
    public Map<ItemStack, Boolean> upgrades = initializeUpgrades(Main.UPGRADES, upgradeList);
    public boolean redstoneUpgradeEnabled;

    public boolean smelterUpgradeEnabled;
    public CustomData customData = CustomData.EMPTY;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public CoalOreFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.COAL_FARM.get(), ModBlocks.COAL_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.WOODEN_PICKAXE);
    }

    public static double getCoalGenerateTime(CoalOreFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.WOODEN_PICKAXE) ? 1 :
                        tileEntity.getPickType().getItem().equals(Items.STONE_PICKAXE) ? 10 :
                                tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                                1);

    }

    public static double getCoalBreakTime(CoalOreFarmTileentity tileEntity) {
        PickaxeType pickAxe = PickaxeType.fromItem(tileEntity.getPickType().getItem());
        if (tileEntity.getPickType().isEnchanted()) {
            tileEntity.setPickaxeEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(tileEntity.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }

        return getCoalGenerateTime(tileEntity) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 8) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 4) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 2) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 2) :
                                        pickAxe.equals(PickaxeType.GOLDEN) ? (baseValue * 2) :
                                                (baseValue * 10));
    }


    public ItemStack getPickType() {
        return pickType;
    }

    @Override
    public Map<ItemStack, Boolean> getUpgrades() {
        return upgrades;
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


        if (timer >= getCoalBreakTime(this)) {
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
        drops.add(new ItemStack(Items.COAL, dropCount));
        if (getPickaxeEnchantmentStatus(pickaxeEnchantments, Enchantments.SILK_TOUCH)) {
            drops.clear();
            if (smelterUpgradeEnabled) {
                drops.add(new ItemStack(Items.COAL, dropCount));
            } else {
                drops.add(new ItemStack(Items.COAL_ORE));
            }
        }

        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    public CustomData getCustomData() {
        return customData;
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        super.saveAdditional(compound, provider);

        ContainerHelper.saveAllItems(compound, inventory, false, provider);
        compound.putLong("Timer", timer);
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        ContainerHelper.loadAllItems(compound, inventory, provider);
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
