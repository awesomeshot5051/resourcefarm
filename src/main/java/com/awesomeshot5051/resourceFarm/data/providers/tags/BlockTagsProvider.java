package com.awesomeshot5051.resourceFarm.data.providers.tags;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class BlockTagsProvider extends IntrinsicHolderTagsProvider<Block> implements ResourceFarmDataProvider {
    public static final TagKey<Block> SMELTABLE_RESULTS = TagKey.create(
            // The registry key. The type of the registry must match the generic type of the tag.
            Registries.BLOCK,
            // The location of the tag. This example will put our tag at data/examplemod/tags/blocks/example_tag.json.
            ResourceLocation.fromNamespaceAndPath(Main.MODID, "smeltable_results")
    );
    // List to hold all deepslate farms
    private static List<Block> deepslateFarms;
    private static List<Block> regularFarms;
    private static List<Block> DiamondToolNeeded;

    public BlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                             ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, registries, block -> block.builtInRegistryHolder().key(), Main.MODID,
                existingFileHelper);
    }

    private static void createDeepslateFarms() {
        // List of block IDs to remove from deepslateFarms and add to DiamondToolNeeded
        List<String> diamondToolBlocks = List.of("netherite_farm", "demerald_farm", "ddiamond_farm", "emerald_farm", "diamond_farm");

        // Initialize the deepslateFarms list
        deepslateFarms = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> entry.getId().getPath().startsWith("d"))
                .filter(entry -> !diamondToolBlocks.contains(entry.getId().getPath())) // Exclude specified blocks
                .map(DeferredHolder::get)
                .collect(Collectors.toList());

        // Initialize the DiamondToolNeeded list with the excluded blocks
        DiamondToolNeeded = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> diamondToolBlocks.contains(entry.getId().getPath())) // Include only specified blocks
                .map(DeferredHolder::get)
                .collect(Collectors.toList());
    }

    private static void createRegularFarms() {
        // Initialize the list with all blocks starting with 'D'
        regularFarms = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> !entry.getId().getPath().startsWith("d"))
                .filter(entry -> !DiamondToolNeeded.contains(entry.get())) // Exclude specified blocks
                .map(DeferredHolder::get)
                .collect(Collectors.toList());

        // Add blocks to DiamondToolNeeded if they are in the diamondToolBlocks list
        if (DiamondToolNeeded == null) {
            DiamondToolNeeded = new ArrayList<>();
        }

        ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> DiamondToolNeeded.contains(entry.get())) // Include only specified blocks
                .map(DeferredHolder::get)
                .forEach(DiamondToolNeeded::add);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(SMELTABLE_RESULTS)
                .add(ModBlocks.COPPER_FARM.getKey(),          // Smelts to Copper Ingots
                        ModBlocks.DCOPPER_FARM.getKey(),         // Smelts to Copper Ingots
                        ModBlocks.COAL_FARM.getKey(),            // Smelts to Coal
                        ModBlocks.DCOAL_FARM.getKey(),           // Smelts to Coal
                        ModBlocks.IRON_FARM.getKey(),            // Smelts to Iron Ingots
                        ModBlocks.DIRON_FARM.getKey(),           // Smelts to Iron Ingots
                        ModBlocks.GOLD_FARM.getKey(),            // Smelts to Gold Ingots
                        ModBlocks.DGOLD_FARM.getKey(),           // Smelts to Gold Ingots
                        ModBlocks.NETHER_GOLD_FARM.getKey(),     // Smelts to Gold Nuggets
                        ModBlocks.NETHERITE_FARM.getKey(),       // Smelts to Netherite Scrap (if applicable)
                        ModBlocks.NETHER_QUARTZ_FARM.getKey(),   // Smelts to Quartz
                        ModBlocks.REDSTONE_FARM.getKey(),        // Smelts to Redstone (if applicable)
                        ModBlocks.DREDSTONE_FARM.getKey(),       // Smelts to Redstone (if applicable)
                        ModBlocks.LAPIS_FARM.getKey(),           // Smelts to Lapis Lazuli (if applicable)
                        ModBlocks.DLAPIS_FARM.getKey(),          // Smelts to Lapis Lazuli (if applicable)
                        ModBlocks.SAND_FARM.getKey(),            // Smelts to Glass
                        ModBlocks.RSAND_FARM.getKey(),           // Smelts to Glass
                        ModBlocks.COBBLESTONE_FARM.getKey(),     // Smelts to Stone
                        ModBlocks.STONE_FARM.getKey());            // Smelts to Smooth Stone)
        addEffectiveTools();
    }

    private void addEffectiveTools() {
        createDeepslateFarms();
        createRegularFarms();
        for (Block block : deepslateFarms) {
            tag(BlockTags.NEEDS_IRON_TOOL).add(block);
        }
        for (Block block : regularFarms) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
        }
        for (Block block : DiamondToolNeeded) {
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block);
        }
    }


}
