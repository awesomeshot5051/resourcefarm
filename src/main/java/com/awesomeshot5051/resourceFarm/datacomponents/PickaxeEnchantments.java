package com.awesomeshot5051.resourceFarm.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class PickaxeEnchantments {


    public static Map<ResourceKey<Enchantment>, Boolean> initializePickaxeEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.MENDING, false);
        enchantmentsMap.put(Enchantments.EFFICIENCY, false);
        enchantmentsMap.put(Enchantments.UNBREAKING, false);
        enchantmentsMap.put(Enchantments.FORTUNE, false);
        enchantmentsMap.put(Enchantments.SILK_TOUCH, false);
        return enchantmentsMap;
    }


    private static void setPickaxeEnchantment(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        if (pickaxeEnchantments.containsKey(enchantment)) {
            pickaxeEnchantments.put(enchantment, value);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static boolean getPickaxeEnchantmentStatus(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment) {
        if (pickaxeEnchantments.containsKey(enchantment)) {
            return pickaxeEnchantments.get(enchantment);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }


    public static void togglePickaxeEnchantment(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setPickaxeEnchantment(pickaxeEnchantments, enchantment, value);
    }


    public static void togglePickaxeEnchantments(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setPickaxeEnchantment(pickaxeEnchantments, entry.getKey(), entry.getValue());
        }
    }
}
