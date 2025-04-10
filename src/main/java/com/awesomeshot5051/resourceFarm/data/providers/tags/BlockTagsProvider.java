package com.awesomeshot5051.resourceFarm.data.providers.tags;

import com.awesomeshot5051.resourceFarm.*;
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
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class BlockTagsProvider extends IntrinsicHolderTagsProvider<Block> implements ResourceFarmDataProvider {
    public static final TagKey<Block> SMELTABLE_RESULTS = TagKey.create(

            Registries.BLOCK,

            ResourceLocation.fromNamespaceAndPath(Main.MODID, "smeltable_results")
    );
    private static List<Block> deepslateFarms;
    private static List<Block> regularFarms;
    private static List<Block> DiamondToolNeeded;

    public BlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                             ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, registries, block -> block.builtInRegistryHolder().key(), Main.MODID,
                existingFileHelper);
    }

    private static void createDeepslateFarms() {

        List<String> diamondToolBlocks = List.of("netherite_farm", "demerald_farm", "ddiamond_farm", "emerald_farm", "diamond_farm");


        deepslateFarms = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> entry.getId().getPath().startsWith("d"))
                .filter(entry -> !diamondToolBlocks.contains(entry.getId().getPath()))
                .map(DeferredHolder::get)
                .collect(Collectors.toList());


        DiamondToolNeeded = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> diamondToolBlocks.contains(entry.getId().getPath()))
                .map(DeferredHolder::get)
                .collect(Collectors.toList());
    }

    private static void createRegularFarms() {

        regularFarms = ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> !entry.getId().getPath().startsWith("d"))
                .filter(entry -> !DiamondToolNeeded.contains(entry.get()))
                .map(DeferredHolder::get)
                .collect(Collectors.toList());


        if (DiamondToolNeeded == null) {
            DiamondToolNeeded = new ArrayList<>();
        }

        ModBlocks.BLOCK_REGISTER.getEntries().stream()
                .filter(entry -> DiamondToolNeeded.contains(entry.get()))
                .map(DeferredHolder::get)
                .forEach(DiamondToolNeeded::add);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(SMELTABLE_RESULTS)
                .add(ModBlocks.COPPER_FARM.getKey(),
                        ModBlocks.DCOPPER_FARM.getKey(),
                        ModBlocks.COAL_FARM.getKey(),
                        ModBlocks.DCOAL_FARM.getKey(),
                        ModBlocks.IRON_FARM.getKey(),
                        ModBlocks.DIRON_FARM.getKey(),
                        ModBlocks.GOLD_FARM.getKey(),
                        ModBlocks.DGOLD_FARM.getKey(),
                        ModBlocks.NETHER_GOLD_FARM.getKey(),
                        ModBlocks.NETHERITE_FARM.getKey(),
                        ModBlocks.NETHER_QUARTZ_FARM.getKey(),
                        ModBlocks.REDSTONE_FARM.getKey(),
                        ModBlocks.DREDSTONE_FARM.getKey(),
                        ModBlocks.LAPIS_FARM.getKey(),
                        ModBlocks.DLAPIS_FARM.getKey(),
                        ModBlocks.SAND_FARM.getKey(),
                        ModBlocks.RSAND_FARM.getKey(),
                        ModBlocks.COBBLESTONE_FARM.getKey(),
                        ModBlocks.STONE_FARM.getKey());
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
