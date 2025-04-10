package com.awesomeshot5051.resourceFarm.integration;

import net.minecraft.world.item.*;

import java.util.*;

public class SpeedStatusCheck {
    public static boolean speedStatusCheck(Map<ItemStack, Integer> map, ItemStack accelerator, ItemStack generator) {
        // Check if both required items exist in the map
        if (map.keySet().stream().noneMatch(itemStack -> itemStack.is(accelerator.getItem())) || map.keySet().stream().noneMatch(itemStack -> itemStack.is(generator.getItem()))) {
            return false;
        }

        int acceleratorCount = map.keySet().stream()
                .filter(itemStack -> itemStack.is(accelerator.getItem()))
                .findFirst()
                .map(map::get) // Only calls map.get() if a value is found
                .orElse(0); // Default to 0 if no match

        int generatorCount = map.keySet().stream()
                .filter(itemStack -> itemStack.is(generator.getItem()))
                .findFirst()
                .map(map::get)
                .orElse(0);

        // Ensure both values are at least one and balanced
        return generatorCount >= 1 && acceleratorCount == generatorCount;
    }
}

