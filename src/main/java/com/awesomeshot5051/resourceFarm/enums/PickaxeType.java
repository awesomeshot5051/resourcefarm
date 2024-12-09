package com.awesomeshot5051.resourceFarm.enums;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

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

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase();  // Returns the enum name in lowercase (e.g., "wooden", "stone", etc.)
    }
}