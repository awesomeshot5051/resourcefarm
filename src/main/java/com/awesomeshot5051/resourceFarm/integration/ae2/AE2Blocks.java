package com.awesomeshot5051.resourceFarm.integration.ae2;

import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.*;

import java.util.*;
import java.util.function.*;

public class AE2Blocks {
    public static final Supplier<Block> FLAWED_BUDDING_QUARTZ = lazyBlockGet("ae2:flawed_budding_quartz");
    public static final Supplier<Block> FLAWLESS_BUDDING_QUARTZ = lazyBlockGet("ae2:flawless_budding_quartz");
    public static final Supplier<Item> CHARGED_CERTUS_QUARTZ_CRYSTAL = lazyItemGet("ae2:charged_certus_quartz_crystal");
    public static final List<ItemStack> itemsRequiredForFC = List.of(
            new ItemStack(CHARGED_CERTUS_QUARTZ_CRYSTAL.get()),
            new ItemStack(Items.REDSTONE),
            new ItemStack(Items.WATER_BUCKET),
            new ItemStack(Items.QUARTZ));
    public static final Supplier<Item> CERTUS_QUARTZ_CRYSTAL = lazyItemGet("ae2:certus_quartz_crystal");
    public static final Supplier<Item> FLUIX_CRYSTAL = lazyItemGet("ae2:fluix_crystal");
    public static final Supplier<Item> FLUIX_DUST = lazyItemGet("ae2:fluix_dust");
    public static final Supplier<Block> FLUIX_BLOCK = lazyBlockGet("ae2:fluix_block");
    public static final Supplier<Block> QUARTZ_CLUSTER = lazyBlockGet("ae2:quartz_cluster");
    public static final Supplier<Block> QUARTZ_GLASS = lazyBlockGet("ae2:quartz_glass");
    public static final Supplier<Block> SKY_STONE_BLOCK = lazyBlockGet("ae2:sky_stone_block");
    public static final Supplier<Block> MYSTERIOUS_CUBE = lazyBlockGet("ae2:mysterious_cube");
    public static final Supplier<Item> SILICON = lazyItemGet("ae2:silicon");
    public static final Supplier<Item> ENTRO_CRYSTAL = lazyItemGet("extendedae:entro_crystal");
    public static final Supplier<Item> ENTRO_CLUSTER = lazyItemGet("extendedae:entro_cluster");
    public static final Supplier<Item> LARGE_ENTRO_BUD = lazyItemGet("extendedae:entro_cluster_large");
    public static final Supplier<Item> MEDIUM_ENTRO_BUD = lazyItemGet("extendedae:entro_cluster_medium");
    public static final Supplier<Item> SMALL_ENTRO_BUD = lazyItemGet("extendedae:entro_cluster_small");
    public static final Supplier<Item> ENTRO_BUDDING_FULLY = lazyItemGet("extendedae:entro_budding_fully");
    public static final Supplier<Item> ENTRO_BUDDING_MOSTLY = lazyItemGet("extendedae:entro_budding_mostly");
    public static final Supplier<Item> ENTRO_BUDDING_HALF = lazyItemGet("extendedae:entro_budding_half");
    public static final Supplier<Item> ENTRO_BUDDING_HARDLY = lazyItemGet("extendedae:entro_budding_hardly");
    public static final Supplier<Item> ENTRO_SEED = lazyItemGet("extendedae:entro_seed");
    public static final Supplier<Item> ENTRO_DUST = lazyItemGet("extendedae:entro_dust");
    public static final Supplier<Block> ENTRO_BLOCK = lazyBlockGet("extendedae:entro_block");
    public static final Supplier<Block> SILICON_BLOCK = lazyBlockGet("extendedae:silicon_block");
    public static final Supplier<Block> SKY_STONE_SLAB = lazyBlockGet("ae2:sky_stone_slab");
    public static final Supplier<Block> SMOOTH_SKY_STONE_SLAB = lazyBlockGet("ae2:smooth_sky_stone_slab");
    public static final Supplier<Block> SKY_STONE_BRICK_SLAB = lazyBlockGet("ae2:sky_stone_brick_slab");
    public static final Supplier<Block> SKY_STONE_SMALL_BRICK_SLAB = lazyBlockGet("ae2:sky_stone_small_brick_slab");
    public static final Supplier<Block> FLUIX_SLAB = lazyBlockGet("ae2:fluix_slab");
    public static final Supplier<Block> QUARTZ_SLAB = lazyBlockGet("ae2:quartz_slab");
    public static final Supplier<Block> CUT_QUARTZ_SLAB = lazyBlockGet("ae2:cut_quartz_slab");
    public static final Supplier<Block> SMOOTH_QUARTZ_SLAB = lazyBlockGet("ae2:smooth_quartz_slab");
    public static final Supplier<Block> QUARTZ_BRICK_SLAB = lazyBlockGet("ae2:quartz_brick_slab");
    public static final Supplier<Block> CHISELED_QUARTZ_SLAB = lazyBlockGet("ae2:chiseled_quartz_slab");
    public static final Supplier<Block> QUARTZ_PILLAR_SLAB = lazyBlockGet("ae2:quartz_pillar_slab");


    public static List<ItemStack> itemsRequiredForFD;

    public static void createItemsRequiredForFD(Level level) {
        if (level.registryAccess().registry(ItemTags.SLABS.registry()).isPresent()) {
            for (Item item : level.registryAccess().registry(ItemTags.SLABS.registry()).get().stream().toList()) {
                itemsRequiredForFD.add(new ItemStack(item));
            }
            itemsRequiredForFD.add(FLUIX_CRYSTAL.get().asItem().getDefaultInstance());
        }
    }

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
