package com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common;

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
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.items.*;

import java.util.*;

import static com.awesomeshot5051.resourceFarm.datacomponents.PickaxeEnchantments.*;

public class NetherrackFarmTileentity extends FarmTileentity implements ITickableBlockEntity {

    private final boolean soundOn = true;
    public ItemStack pickType;
    public Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = initializePickaxeEnchantments();
    public ItemStack pickaxeType;
    protected NonNullList<ItemStack> inventory;
    protected long timer;
    protected ItemStackHandler itemHandler;
    protected OutputItemHandler outputItemHandler;

    public NetherrackFarmTileentity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NETHERRACK_FARM.get(), ModBlocks.NETHERRACK_FARM.get().defaultBlockState(), pos, state);
        inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        itemHandler = new ItemStackHandler(inventory);
        outputItemHandler = new OutputItemHandler(inventory);
        pickType = new ItemStack(Items.WOODEN_PICKAXE);
    }

    public static double getNetherrackGenerateTime(NetherrackFarmTileentity tileEntity) {
        return (double) Main.SERVER_CONFIG.coalGenerateTime.get() /
                (tileEntity.getPickType().getItem().equals(Items.IRON_PICKAXE) ? 15 :
                        tileEntity.getPickType().getItem().equals(Items.GOLDEN_PICKAXE) ? 20 :
                                tileEntity.getPickType().getItem().equals(Items.DIAMOND_PICKAXE) ? 25 :
                                        tileEntity.getPickType().getItem().equals(Items.NETHERITE_PICKAXE) ? 30 :
                                                1);

    }

    public static double getNetherrackBreakTime(NetherrackFarmTileentity tileEntity) {
        PickaxeType pickAxe = PickaxeType.fromItem(tileEntity.getPickType().getItem());
        if (tileEntity.getPickType().isEnchanted()) {
            tileEntity.setPickaxeEnchantmentStatus(tileEntity);
        }
        int baseValue = 20;
        if (PickaxeEnchantments.getPickaxeEnchantmentStatus(tileEntity.pickaxeEnchantments, Enchantments.EFFICIENCY)) {
            baseValue = 10;
        }

        return getNetherrackGenerateTime(tileEntity) + (pickAxe.equals(PickaxeType.NETHERITE) ? (baseValue * 8) :
                pickAxe.equals(PickaxeType.DIAMOND) ? (baseValue * 4) :
                        pickAxe.equals(PickaxeType.IRON) ? (baseValue * 2) :
                                pickAxe.equals(PickaxeType.STONE) ? (baseValue * 2) :
                                        pickAxe.equals(PickaxeType.GOLDEN) ? (baseValue * 2) :
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


    public ItemStack getPickType() {
        return pickType;
    }

    @Override
    public void tick() {
        timer++;
        if (timer >= getNetherrackBreakTime(this)) {
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
        drops.add(new ItemStack(Items.NETHERRACK, dropCount));
        return drops;
    }

    public Container getOutputInventory() {
        return new ItemListInventory(inventory, this::setChanged);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {

        ContainerHelper.saveAllItems(compound, inventory, false, provider);

        if (pickType != null) {
            CompoundTag pickTypeTag = new CompoundTag();
            pickTypeTag.putString("id", BuiltInRegistries.ITEM.getKey(pickType.getItem()).toString());
            pickTypeTag.putInt("count", pickType.getCount());
            compound.put("PickType", pickTypeTag);
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
