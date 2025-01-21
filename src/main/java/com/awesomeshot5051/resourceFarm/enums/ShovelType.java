package com.awesomeshot5051.resourceFarm.enums;

import net.minecraft.util.*;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.*;

public enum ShovelType implements StringRepresentable {
    WOODEN,
    STONE,
    IRON,
    GOLDEN,
    DIAMOND,
    NETHERITE;

    public static ShovelType fromItem(Item item) {
        if (item == Items.WOODEN_SHOVEL) return WOODEN;
        if (item == Items.STONE_SHOVEL) return STONE;
        if (item == Items.IRON_SHOVEL) return IRON;
        if (item == Items.GOLDEN_SHOVEL) return GOLDEN;
        if (item == Items.DIAMOND_SHOVEL) return DIAMOND;
        if (item == Items.NETHERITE_SHOVEL) return NETHERITE;
        return WOODEN;  // Default
    }

    public static int getRank(Item item) {
        if (fromItem(item).equals(WOODEN)) return 0;
        if (fromItem(item).equals(STONE)) return 1;
        if (fromItem(item).equals(IRON)) return 2;
        if (fromItem(item).equals(GOLDEN)) return 3;
        if (fromItem(item).equals(DIAMOND)) return 4;
        if (fromItem(item).equals(NETHERITE)) return 5;
        return 0;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase();  // Returns the enum name in lowercase (e.g., "wooden", "stone", etc.)
    }
}