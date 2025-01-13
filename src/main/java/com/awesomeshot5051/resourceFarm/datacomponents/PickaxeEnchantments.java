package com.awesomeshot5051.resourceFarm.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class PickaxeEnchantments {
    // Initialize the enchantments map with default values


    // Method to initialize the enchantments map
    public static Map<ResourceKey<Enchantment>, Boolean> initializePickaxeEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.MENDING, false);      // Repairs using experience orbs
        enchantmentsMap.put(Enchantments.EFFICIENCY, false);  // Increases mining speed
        enchantmentsMap.put(Enchantments.UNBREAKING, false);  // Increases durability
        enchantmentsMap.put(Enchantments.FORTUNE, false);     // Increases drops
        enchantmentsMap.put(Enchantments.SILK_TOUCH, false);  // Mines blocks intact
        return enchantmentsMap;
    }

    // Helper method to set the value for a single enchantment
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

    // Method to toggle a single enchantment
    public static void togglePickaxeEnchantment(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setPickaxeEnchantment(pickaxeEnchantments, enchantment, value);
    }

    // Method to toggle multiple enchantments
    public static void togglePickaxeEnchantments(Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setPickaxeEnchantment(pickaxeEnchantments, entry.getKey(), entry.getValue());
        }
    }
}
