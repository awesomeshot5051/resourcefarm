package com.awesomeshot5051.resourceFarm.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class ShovelEnchantments {


    public static Map<ResourceKey<Enchantment>, Boolean> initializeShovelEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.MENDING, false);
        enchantmentsMap.put(Enchantments.EFFICIENCY, false);
        enchantmentsMap.put(Enchantments.UNBREAKING, false);
        enchantmentsMap.put(Enchantments.FORTUNE, false);
        enchantmentsMap.put(Enchantments.SILK_TOUCH, false);
        return enchantmentsMap;
    }


    private static void setShovelEnchantment(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        if (pickaxeEnchantments.containsKey(enchantment)) {
            pickaxeEnchantments.put(enchantment, value);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static boolean getShovelEnchantmentStatus(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment) {
        if (pickaxeEnchantments.containsKey(enchantment)) {
            return pickaxeEnchantments.get(enchantment);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }


    public static void toggleShovelEnchantment(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setShovelEnchantment(pickaxeEnchantments, enchantment, value);
    }


    public static void toggleShovelEnchantments(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setShovelEnchantment(pickaxeEnchantments, entry.getKey(), entry.getValue());
        }
    }
}
