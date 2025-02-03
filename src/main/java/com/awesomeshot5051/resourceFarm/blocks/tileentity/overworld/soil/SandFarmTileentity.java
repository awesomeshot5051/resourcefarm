package com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.inventory.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
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

import static com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments.*;

@SuppressWarnings("ALL")
public class SandFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    public ItemStack shovelType;
    public boolean upgradeEnabled;
    public CustomData customData = CustomData.EMPTY;
    public Map<ResourceKey<Enchantment>, Boolean> shovelEnchantments = initializeShovelEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected long tick = timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;
    private boolean soundOn = true;

    public SandFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SAND_FARM.get(), ModBlocks.SAND_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        shovelType = new ItemStack(Items.WOODEN_SHOVEL);
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

        return getSandGenerateTime(tileEntity) + (shovel.equals(ShovelType.NETHERITE) ? (baseValue * 8) :
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
        int dropCount = serverWorld.random.nextIntBetweenInclusive(1, 3);
        if (getShovelEnchantmentStatus(shovelEnchantments, Enchantments.FORTUNE)) {
            dropCount = serverWorld.random.nextIntBetweenInclusive(1, 5);
        }
        List<ItemStack> drops = new ArrayList<>();
        if (upgradeEnabled) drops.add(new ItemStack(Items.GLASS, dropCount));
        else drops.add(new ItemStack(Items.SAND, dropCount));
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    public CustomData getCustomData() {
        return customData;
    }

    public boolean checkshovelType() {
        return shovelType.getItem().equals(Items.STONE_SHOVEL);
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

            shovelType = new ItemStack(Items.WOODEN_PICKAXE);
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
