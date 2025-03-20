package com.awesomeshot5051.resourceFarm.integration.ae2;

import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.*;

import java.util.*;
import java.util.function.*;

public class AE2Blocks {
    public static final Supplier<Block> FLAWED_BUDDING_QUARTZ = lazyBlockGet("ae2:flawed_budding_quartz");
    public static final Supplier<Block> FLAWLESS_BUDDING_QUARTZ = lazyBlockGet("ae2:flawless_budding_quartz");
    public static final Supplier<Item> CHARGED_CERTUS_QUARTZ_CRYSTAL = lazyItemGet("ae2:charged_certus_quartz_crystal");
    public static final Supplier<Block> CERTUS_QUARTZ_CRYSTAL = lazyBlockGet("ae2:certus_quartz_crystal");
    public static final Supplier<Item> FLUIX_CRYSTAL = lazyItemGet("ae2:fluix_crystal");
    public static final List<ItemStack> itemsRequiredForFC = List.of(
            new ItemStack(CHARGED_CERTUS_QUARTZ_CRYSTAL.get()),
            new ItemStack(Items.REDSTONE),
            new ItemStack(Items.WATER_BUCKET),
            new ItemStack(Items.QUARTZ));
    public static final Supplier<Item> FLUIX_DUST = lazyItemGet("ae2:fluix_dust");
    public static final Supplier<Block> SMALL_QUARTZ_BUD = lazyBlockGet("ae2:small_quartz_bud");
    //    public static final Supplier<Block> MEDIUM_QUARTZ_BUD = lazyBlockGet("ae2:medium_quartz_bud");
//    public static final Supplier<Block> LARGE_QUARTZ_BUD = lazyBlockGet("ae2:large_quartz_bud");
    public static final Supplier<Block> QUARTZ_CLUSTER = lazyBlockGet("ae2:quartz_cluster");
    public static final Supplier<Block> QUARTZ_GLASS = lazyBlockGet("ae2:quartz_glass");
    public static final Supplier<Block> SKY_STONE_BLOCK = lazyBlockGet("ae2:sky_stone_block");
    public static final Supplier<Block> MYSTERIOUS_CUBE = lazyBlockGet("ae2:mysterious_cube");
    public static final Supplier<Item> SILICON = lazyItemGet("ae2:silicon");
    public static final Supplier<Block> ENTRO_CRYSTAL = lazyBlockGet("extendedae:entro_crystal");
    public static final Supplier<Item> ENTRO_SEED = lazyItemGet("extendedae:entro_seed");
    public static final Supplier<Item> ENTRO_DUST = lazyItemGet("extendedae:entro_dust");
    public static final Supplier<Block> ENTRO_BLOCK = lazyBlockGet("extendedae:entro_block");
    public static final Supplier<Block> ENTRO_BUDDING_FULLY = lazyBlockGet("extendedae:entro_budding_fully");
    public static final Supplier<Block> SILICON_BLOCK = lazyBlockGet("extendedae:silicon_block");

    /**
     * Returns a lazy supplier for the given registry ID.
     * If the AE2 (or ExtendedAE) mod is not loaded, returns Blocks.AIR.
     *
     * @param registryId A string in the format "namespace:path"
     * @return a Supplier that provides the requested Block.
     */
    private static Supplier<Block> lazyBlockGet(String registryId) {
        return () -> {
            // Check if AE2 (or extendedae) is loaded
            String namespace = registryId.split(":")[0];
            // For AE2 blocks, we check if "ae2" is loaded; for extendedae, check "extendedae"
            if ((namespace.equals("ae2") && !ModList.get().isLoaded("ae2"))
                    || (namespace.equals("extendedae") && !ModList.get().isLoaded("extendedae"))) {
                return Blocks.AIR;
            }
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, registryId.split(":")[1]);
            Block block = BuiltInRegistries.BLOCK.get(location);
            if (block == Blocks.AIR) {
                throw new IllegalStateException("Failed to retrieve block: " + registryId);
            }
            return block;
        };
    }

    private static Supplier<Item> lazyItemGet(String registryId) {
        return () -> {
            // Check if AE2 (or extendedae) is loaded
            String namespace = registryId.split(":")[0];
            // For AE2 blocks, we check if "ae2" is loaded; for extendedae, check "extendedae"
            if ((namespace.equals("ae2") && !ModList.get().isLoaded("ae2"))
                    || (namespace.equals("extendedae") && !ModList.get().isLoaded("extendedae"))) {
                return Items.AIR;
            }
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, registryId.split(":")[1]);
            Item item = BuiltInRegistries.ITEM.get(location);
            if (item == Items.AIR) {
                throw new IllegalStateException("Failed to retrieve block: " + registryId);
            }
            return item;
        };
    }
}
