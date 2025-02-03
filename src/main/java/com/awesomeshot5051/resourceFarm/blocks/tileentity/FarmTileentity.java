package com.awesomeshot5051.resourceFarm.blocks.tileentity;


import com.awesomeshot5051.resourceFarm.datacomponents.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;

@SuppressWarnings("ALL")
public class FarmTileentity extends FakeWorldTileentity {

    private boolean soundOn;

    public FarmTileentity(BlockEntityType<?> type, BlockState defaultState, BlockPos pos, BlockState state) {
        super(type, defaultState, pos, state);
    }

    public ItemStack getPickType() {
        return ItemStack.EMPTY;
    }

    public ItemStack getShovelType() {
        return ItemStack.EMPTY;
    }

    public <T extends FarmTileentity> void setPickaxeEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getPickaxeEnchantments();
        ItemEnchantments enchantments = farm.getPickType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {

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

    public <T extends FarmTileentity> void setShovelEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getShovelEnchantments();
        ItemEnchantments enchantments = farm.getShovelType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {

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

}
