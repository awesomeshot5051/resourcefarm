package com.awesomeshot5051.resourceFarm.enums;

import net.minecraft.util.*;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.*;

public enum PickaxeType implements StringRepresentable {
    WOODEN,
    STONE,
    IRON,
    GOLDEN,
    DIAMOND,
    NETHERITE;

    public static PickaxeType fromItem(Item item) {
        if (item == Items.WOODEN_PICKAXE) return WOODEN;
        if (item == Items.STONE_PICKAXE) return STONE;
        if (item == Items.IRON_PICKAXE) return IRON;
        if (item == Items.GOLDEN_PICKAXE) return GOLDEN;
        if (item == Items.DIAMOND_PICKAXE) return DIAMOND;
        if (item == Items.NETHERITE_PICKAXE) return NETHERITE;
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