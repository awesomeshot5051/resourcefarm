package com.awesomeshot5051.resourceFarm.blocks.tileentity;

//import com.awesomeshot5051.resourceFarm.datacomponents.VillagerData;

import com.awesomeshot5051.resourceFarm.datacomponents.PickaxeEnchantments;
import com.awesomeshot5051.resourceFarm.datacomponents.ShovelEnchantments;
import com.awesomeshot5051.resourceFarm.datacomponents.VillagerData;
import com.awesomeshot5051.resourceFarm.entity.EasyVillagerEntity;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;

@SuppressWarnings("ALL")
public class VillagerTileentity extends FakeWorldTileentity {

    protected ItemStack villager;
    protected EasyVillagerEntity villagerEntity;
    private boolean soundOn;

    public VillagerTileentity(BlockEntityType<?> type, BlockState defaultState, BlockPos pos, BlockState state) {
        super(type, defaultState, pos, state);
        villager = ItemStack.EMPTY;
    }

    public boolean hasVillager() {
        return !villager.isEmpty();
    }

    @Nullable
    public com.awesomeshot5051.resourceFarm.entity.EasyVillagerEntity getVillagerEntity() {
        if (villagerEntity == null && !villager.isEmpty()) {
            villagerEntity = VillagerData.createEasyVillager(villager, level);
        }
        return villagerEntity;
    }

    public ItemStack getPickType() {
        return ItemStack.EMPTY;
    }

    public ItemStack getShovelType() {
        return ItemStack.EMPTY;
    }

    public <T extends VillagerTileentity> void setPickaxeEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getPickaxeEnchantments();
        ItemEnchantments enchantments = farm.getPickType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            // Check and toggle enchantments based on their presence in the map
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.FORTUNE.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.FORTUNE, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    public <T extends VillagerTileentity> void setShovelEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getShovelEnchantments();
        ItemEnchantments enchantments = farm.getShovelType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            // Check and toggle enchantments based on their presence in the map
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    public boolean toggleSound() {
        return false;
    }

    public boolean getSound() {
        return false;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getPickaxeEnchantments() {
        return null;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getShovelEnchantments() {
        return null;
    }

    public long getTimer() {
        return 0;
    }

    protected void onAddVillager(EasyVillagerEntity villager) {

    }

}
