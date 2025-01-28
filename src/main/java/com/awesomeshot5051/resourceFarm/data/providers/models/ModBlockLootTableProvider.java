//package com.awesomeshot5051.resourceFarm.data.providers.models;
//
//import com.awesomeshot5051.resourceFarm.blocks.*;
//import net.minecraft.core.*;
//import net.minecraft.data.loot.*;
//import net.minecraft.world.flag.*;
//import net.minecraft.world.level.block.*;
//
//import java.util.*;
//
//public class ModBlockLootTableProvider extends BlockLootSubProvider {
//    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
//        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
//    }
//
//    @Override
//    protected void generate() {
//        dropSelf(ModBlocks.BASALT_FARM.get());
//        dropSelf(ModBlocks.BLACKSTONE_FARM.get());
//        dropSelf(ModBlocks.CALCITE_FARM.get());
//        dropSelf(ModBlocks.COAL_FARM.get());
//        dropSelf(ModBlocks.COBBLESTONE_FARM.get());
//        dropSelf(ModBlocks.CONCRETE_FARM.get());
//        dropSelf(ModBlocks.CONCRETE_POWDER_FARM.get());
//        dropSelf(ModBlocks.COPPER_FARM.get());
//        dropSelf(ModBlocks.DCOAL_FARM.get());
//        dropSelf(ModBlocks.DEEPSLATE_FARM.get());
//        dropSelf(ModBlocks.DCOPPER_FARM.get());
//        dropSelf(ModBlocks.DDIAMOND_FARM.get());
//        dropSelf(ModBlocks.DEMERALD_FARM.get());
//        dropSelf(ModBlocks.DGOLD_FARM.get());
//        dropSelf(ModBlocks.DIRON_FARM.get());
//        dropSelf(ModBlocks.DLAPIS_FARM.get());
//        dropSelf(ModBlocks.DREDSTONE_FARM.get());
//        dropSelf(ModBlocks.DIAMOND_FARM.get());
//        dropSelf(ModBlocks.DIRT_FARM.get());
//        dropSelf(ModBlocks.EMERALD_FARM.get());
//        dropSelf(ModBlocks.ESTONE_FARM.get());
//        dropSelf(ModBlocks.GLOWSTONE_FARM.get());
//        dropSelf(ModBlocks.GOLD_FARM.get());
//        dropSelf(ModBlocks.GRASS_FARM.get());
//        dropSelf(ModBlocks.GRAVEL_FARM.get());
//        dropSelf(ModBlocks.IRON_FARM.get());
//        dropSelf(ModBlocks.LAPIS_FARM.get());
//        dropSelf(ModBlocks.NETHER_GOLD_FARM.get());
//        dropSelf(ModBlocks.NETHERITE_FARM.get());
//        dropSelf(ModBlocks.NETHER_QUARTZ_FARM.get());
//        dropSelf(ModBlocks.NETHERRACK_FARM.get());
//        dropSelf(ModBlocks.OBSIDIAN_FARM.get());
//        dropSelf(ModBlocks.PURPUR_FARM.get());
//        dropSelf(ModBlocks.RSAND_FARM.get());
//        dropSelf(ModBlocks.REDSTONE_FARM.get());
//        dropSelf(ModBlocks.SAND_FARM.get());
//        dropSelf(ModBlocks.SSTONE_FARM.get());
//        dropSelf(ModBlocks.SNOW_FARM.get());
//        dropSelf(ModBlocks.SSOIL_FARM.get());
//        dropSelf(ModBlocks.SSAND_FARM.get());
//        dropSelf(ModBlocks.STONE_FARM.get());
//        dropSelf(ModBlocks.TERRACOTTA_FARM.get());
//        dropSelf(ModBlocks.TUFF_FARM.get());
//        dropSelf(ModBlocks.ANDESITE_FARM.get());
//        dropSelf(ModBlocks.INVENTORY_VIEWER.get());
//        dropSelf(ModBlocks.GRANITE_FARM.get());
//        dropSelf(ModBlocks.IRON_FARM.get());
//    }
//
//
//    @Override
//    protected Iterable<Block> getKnownBlocks() {
//        return ModBlocks.BLOCK_REGISTER.getEntries().stream().map(Holder::value)::iterator;
//    }
//}
