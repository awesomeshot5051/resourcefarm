package com.awesomeshot5051.resourceFarm.integration.integrateddynamics;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import java.util.function.*;

public class IDBlocks {
    // Use lazy suppliers so that the lookup happens only when the value is first requested.
    public static final Supplier<Block> CRYSTAL_CHORUS_BLOCK =
            Main.dynamic_installed ? lazyGet("integrateddynamics:crystalized_chorus_block") : () -> Blocks.AIR;
    public static final Supplier<Block> CRYSTAL_MENRIL_BLOCK =
            Main.dynamic_installed ? lazyGet("integrateddynamics:crystalized_menril_block") : () -> Blocks.AIR;
    public static final Supplier<Block> MENRIL_BRICK =
            Main.dynamic_installed ? lazyGet("integrateddynamics:crystalized_menril_brick") : () -> Blocks.AIR;
    public static final Supplier<Block> CRYSTAL_CHORUS_BRICK =
            Main.dynamic_installed ? lazyGet("integrateddynamics:crystalized_chorus_brick") : () -> Blocks.AIR;
    public static final Supplier<Item> CHORUS_CHUNK =
            Main.dynamic_installed ? lazyGetItem("integrateddynamics:crystalized_chorus_chunk") : () -> Items.AIR;
    public static final Supplier<Item> MENRIL_CHUNK =
            Main.dynamic_installed ? lazyGetItem("integrateddynamics:crystalized_menril_chunk") : () -> Items.AIR;
    public static final Supplier<Item> PROTO_CHORUS =
            Main.dynamic_installed ? lazyGetItem("integrateddynamics:proto_chorus") : () -> Items.AIR;
    public static final Supplier<Block> MENRIL_GLASS =
            (Main.dynamic_installed && Main.terminals_installed) ? lazyGet("integratedterminals:menril_glass") : () -> Blocks.AIR;
    public static final Supplier<Block> CHORUS_GLASS =
            (Main.dynamic_installed && Main.terminals_installed) ? lazyGet("integratedterminals:chorus_glass") : () -> Blocks.AIR;
    // MENRIL_GROWN remains to be set up appropriately later.
    public static BlockState MENRIL_GROWN;

    // Lazy getter for Blocks
    private static Supplier<Block> lazyGet(String registryId) {
        return () -> {
            String[] parts = registryId.split(":");
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(parts[0], parts[1]);
            Block block = BuiltInRegistries.BLOCK.get(location);
            if (block == Blocks.AIR) {
                throw new IllegalStateException("Failed to retrieve block: " + registryId);
            }
            return block;
        };
    }

    // Lazy getter for Items
    private static Supplier<Item> lazyGetItem(String registryId) {
        return () -> {
            String[] parts = registryId.split(":");
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(parts[0], parts[1]);
            Item item = BuiltInRegistries.ITEM.get(location);
            if (item == Items.AIR) {
                throw new IllegalStateException("Failed to retrieve item: " + registryId);
            }
            return item;
        };
    }
}
