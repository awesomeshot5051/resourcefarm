package com.awesomeshot5051.resourceFarm.data.providers.recipe;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public static final List<Supplier<Block>> PICKAXE_BLOCKS = List.of(
            ModBlocks.ANDESITE_FARM::get,
            ModBlocks.COPPER_FARM::get,
            ModBlocks.COAL_FARM::get,
            ModBlocks.DCOPPER_FARM::get,
            ModBlocks.DCOAL_FARM::get,
            ModBlocks.IRON_FARM::get,
            ModBlocks.GOLD_FARM::get,
            ModBlocks.DIAMOND_FARM::get,
            ModBlocks.EMERALD_FARM::get,
            ModBlocks.LAPIS_FARM::get,
            ModBlocks.REDSTONE_FARM::get,
            ModBlocks.DIRON_FARM::get,
            ModBlocks.DGOLD_FARM::get,
            ModBlocks.DDIAMOND_FARM::get,
            ModBlocks.DEMERALD_FARM::get,
            ModBlocks.DLAPIS_FARM::get,
            ModBlocks.DREDSTONE_FARM::get,
            ModBlocks.NETHERITE_FARM::get,
            ModBlocks.NETHER_QUARTZ_FARM::get,
            ModBlocks.NETHER_GOLD_FARM::get,
            ModBlocks.DEEPSLATE_FARM::get,
            ModBlocks.BASALT_FARM::get,
            ModBlocks.BLACKSTONE_FARM::get,
            ModBlocks.CALCITE_FARM::get,
            ModBlocks.COBBLESTONE_FARM::get,
            ModBlocks.GRANITE_FARM::get,
            ModBlocks.STONE_FARM::get,
            ModBlocks.SSTONE_FARM::get,
            ModBlocks.TUFF_FARM::get
    );
    public static final List<Supplier<Block>> SHOVEL_BLOCKS = List.of(
            ModBlocks.CONCRETE_POWDER_FARM::get,
            ModBlocks.DIRT_FARM::get,
            ModBlocks.GRASS_FARM::get,
            ModBlocks.GRAVEL_FARM::get,
            ModBlocks.SAND_FARM::get,
            ModBlocks.RSAND_FARM::get,
            ModBlocks.SSAND_FARM::get,
            ModBlocks.SSOIL_FARM::get,
            ModBlocks.SNOW_FARM::get
    );
    public static final List<Supplier<Block>> ALL_FARMS = Stream
            .concat(SHOVEL_BLOCKS.stream(), PICKAXE_BLOCKS.stream())
            .collect(Collectors.toList());
    public static final List<Supplier<Block>> SMELTABLE_RESULTS = List.of(
            ModBlocks.COPPER_FARM::get,
            ModBlocks.BASALT_FARM::get,
            ModBlocks.DCOPPER_FARM::get,
            ModBlocks.COAL_FARM::get,
            ModBlocks.DCOAL_FARM::get,
            ModBlocks.IRON_FARM::get,
            ModBlocks.DIRON_FARM::get,
            ModBlocks.GOLD_FARM::get,
            ModBlocks.DGOLD_FARM::get,
            ModBlocks.NETHER_GOLD_FARM::get,
            ModBlocks.NETHERITE_FARM::get,
            ModBlocks.NETHER_QUARTZ_FARM::get,
            ModBlocks.REDSTONE_FARM::get,
            ModBlocks.DREDSTONE_FARM::get,
            ModBlocks.LAPIS_FARM::get,
            ModBlocks.DLAPIS_FARM::get,
            ModBlocks.SAND_FARM::get,
            ModBlocks.RSAND_FARM::get,
            ModBlocks.COBBLESTONE_FARM::get,
            ModBlocks.STONE_FARM::get
    );

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.COPPER_ORE, Items.COPPER_INGOT))
                .unlockedBy("has_copper", has(Items.COPPER_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "copper_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DCOPPER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT))
                .unlockedBy("has_deepcopper", has(Items.DEEPSLATE_COPPER_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dcopper_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COAL_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.COAL_ORE, Items.COAL_BLOCK))
                .unlockedBy("has_coal", has(Items.COAL_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "coal_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DCOAL_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_COAL_ORE, Items.COAL_BLOCK))
                .unlockedBy("has_coal", has(Items.DEEPSLATE_COAL_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dcoal_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.IRON_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ICI")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.IRON_ORE)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_iron", has(Items.IRON_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "iron_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRON_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ICI")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_IRON_ORE)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_deepslate_iron", has(Items.DEEPSLATE_IRON_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "diron_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.GOLD_ORE, Items.GOLD_INGOT))
                .unlockedBy("has_gold", has(Items.GOLD_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "gold_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DGOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("GCG")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_GOLD_ORE))
                .define('G', Items.GOLD_BLOCK)
                .unlockedBy("has_deepslate_gold", has(Items.DEEPSLATE_GOLD_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dgold_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REDSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.REDSTONE_ORE, Items.REDSTONE_BLOCK))
                .unlockedBy("has_redstone", has(Items.REDSTONE_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "redstone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DREDSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_REDSTONE_ORE, Items.REDSTONE_BLOCK))
                .unlockedBy("has_deepslate_redstone", has(Items.DEEPSLATE_REDSTONE_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dredstone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LAPIS_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.LAPIS_ORE, Items.LAPIS_BLOCK))
                .unlockedBy("has_lapis", has(Items.LAPIS_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "lapis_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DLAPIS_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_LAPIS_ORE, Items.LAPIS_BLOCK))
                .unlockedBy("has_deepslate_lapis", has(Items.DEEPSLATE_LAPIS_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dlapis_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMERALD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ECE")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.EMERALD_ORE)
                .define('E', Items.EMERALD_BLOCK)
                .unlockedBy("has_emerald", has(Items.EMERALD_BLOCK)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "emerald_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMERALD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ECE")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_EMERALD_ORE)
                .define('E', Items.EMERALD_BLOCK)
                .unlockedBy("has_deepslate_emerald", has(Items.DEEPSLATE_EMERALD_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "demerald_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIAMOND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("DCD")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DIAMOND_ORE)
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy("has_diamond", has(Items.DIAMOND_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "diamond_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DDIAMOND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("DCD")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_DIAMOND_ORE)
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy("has_deepslate_diamond", has(Items.DEEPSLATE_DIAMOND_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ddiamond_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.SAND)
                .unlockedBy("has_sand", has(Items.SAND)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "sand_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CONCRETE_POWDER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Tags.Items.CONCRETE_POWDERS)
                .unlockedBy("has_sand", has(Items.GREEN_CONCRETE_POWDER)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cpowder_farm"));
        CustomShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CONCRETE_FARM.get(), 1)
                .requires(ModBlocks.CONCRETE_POWDER_FARM.get())
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_cpowder_farm", has(ModItems.CONCRETE_POWDER_FARM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "concrete_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COBBLESTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cobblestone_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("NCN")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('N', Items.NETHERITE_INGOT)
                .define('C', Items.ANCIENT_DEBRIS)
                .unlockedBy("has_netherite_block", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "netherite_farm"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BASALT_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.BASALT)
                .unlockedBy("has_basalt", has(Items.BASALT)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "basalt_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACKSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.BLACKSTONE)
                .unlockedBy("has_blackstone", has(Items.BLACKSTONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "blackstone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CALCITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.CALCITE)
                .unlockedBy("has_calcite", has(Items.CALCITE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "calcite_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEEPSLATE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE)
                .unlockedBy("has_deepslate", has(Items.DEEPSLATE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "deepslate_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRT_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.DIRT)
                .unlockedBy("has_dirt", has(Items.DIRT)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dirt_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ESTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.END_STONE)
                .unlockedBy("has_end_stone", has(Items.END_STONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "estone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLOWSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "glowstone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRASS_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.GRASS_BLOCK)
                .unlockedBy("has_grass", has(Items.GRASS_BLOCK)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "grass_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRAVEL_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.GRAVEL)
                .unlockedBy("has_gravel", has(Items.GRAVEL)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "gravel_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_GOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.NETHER_GOLD_ORE)
                .unlockedBy("has_nether_gold", has(Items.NETHER_GOLD_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "nether_gold_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_QUARTZ_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.NETHER_QUARTZ_ORE)
                .unlockedBy("has_nether_quartz", has(Items.NETHER_QUARTZ_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "nether_quartz_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHERRACK_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.NETHERRACK)
                .unlockedBy("has_netherrack", has(Items.NETHERRACK)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "netherrack_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OBSIDIAN_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "obsidian_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURPUR_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.PURPUR_BLOCK)
                .unlockedBy("has_purpur", has(Items.PURPUR_BLOCK)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "purpur_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RSAND_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.RED_SAND)
                .unlockedBy("has_red_sand", has(Items.RED_SAND)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "rsand_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.SANDSTONE)
                .unlockedBy("has_sandstone", has(Items.SANDSTONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "sstone_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SNOW_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.SNOW_BLOCK)
                .unlockedBy("has_snow", has(Items.SNOW_BLOCK)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "snow_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSOIL_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.SOUL_SOIL)
                .unlockedBy("has_soul_soil", has(Items.SOUL_SOIL)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ssoil_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSAND_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL))
                .define('C', Items.SOUL_SAND)
                .unlockedBy("has_soul_sand", has(Items.SOUL_SAND)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ssand_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.STONE)
                .unlockedBy("has_stone", has(Items.STONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "stone_farm"));
        CustomShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.STONE_FARM.get())
                .requires(ModItems.SMELTER_UPGRADE)
                .requires(ModItems.COBBLESTONE_FARM.get())
                .unlockedBy("has_smelter_upgrade", has(ModItems.SMELTER_UPGRADE))
                .unlockedBy("has_cobblestone_farm", has(ModItems.COBBLESTONE_FARM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "stone_farm_alternate"));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TERRACOTTA_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.TERRACOTTA)
                .unlockedBy("has_terracotta", has(Items.TERRACOTTA)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "terracotta_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUFF_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.TUFF)
                .unlockedBy("has_tuff", has(Items.TUFF)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "tuff_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ANDESITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.ANDESITE)
                .unlockedBy("has_andesite", has(Items.ANDESITE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "andesite_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRANITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.GRANITE)
                .unlockedBy("has_granite", has(Items.GRANITE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "granite_farm"));
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('S', Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE))
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_stone", has(Items.COBBLESTONE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_stone_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("III")
                    .pattern("IFI")
                    .pattern("III")
                    .define('I', Items.IRON_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_iron", has(Items.IRON_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_iron_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("GGG")
                    .pattern("GFG")
                    .pattern("GGG")
                    .define('G', Items.GOLD_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_gold_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("DDD")
                    .pattern("DFD")
                    .pattern("DDD")
                    .define('D', Items.DIAMOND)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_diamond_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern(" N ")
                    .pattern("NFN")
                    .pattern(" N ")
                    .define('N', Items.NETHERITE_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_netherite_upgrade_recipe"));
        });


        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('S', Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE))
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_stone", has(Items.COBBLESTONE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_stone_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("III")
                    .pattern("IFI")
                    .pattern("III")
                    .define('I', Items.IRON_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_iron", has(Items.IRON_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_iron_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("GGG")
                    .pattern("GFG")
                    .pattern("GGG")
                    .define('G', Items.GOLD_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_gold_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("DDD")
                    .pattern("DFD")
                    .pattern("DDD")
                    .define('D', Items.DIAMOND)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_diamond_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern(" N ")
                    .pattern("NFN")
                    .pattern(" N ")
                    .define('N', Items.NETHERITE_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_netherite_upgrade_recipe"));
        });
        ALL_FARMS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            EnchantmentAdditionRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(Items.ENCHANTED_BOOK)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_enchanted_book", has(ItemTags.MINING_ENCHANTABLE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId()) + "_enchant_upgrade_recipe"));
        });
        ALL_FARMS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            EnchantmentRemovalRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_farm", has(farmBlock.asItem()))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId() + "_enchantment_removal_recipe")));
        });
        SMELTABLE_RESULTS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(Items.BLAST_FURNACE)
                    .requires(Items.LAVA_BUCKET)
                    .requires(ModItems.SMELTER_UPGRADE)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_smelter_upgrade", has(ModItems.SMELTER_UPGRADE))
                    .unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(farmBlock.getDescriptionId() + "_smelter_upgrade")));
        });
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SMELTER_UPGRADE)
                .pattern("IBI")
                .pattern("IFI")
                .pattern("INI")
                .define('I', Items.IRON_INGOT)
                .define('F', Items.FLINT_AND_STEEL)
                .define('N', Items.NETHERRACK)
                .define('B', Ingredient.of(Items.BLAZE_POWDER, Items.BLAZE_ROD))
                .unlockedBy("has_netherrack", has(Items.NETHERRACK))
                .unlockedBy("has_flint_and_steel", has(Items.FLINT_AND_STEEL))
                .unlockedBy("has_netherrack", has(Items.NETHERRACK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.SMELTER_UPGRADE.asItem().getDescriptionId())));
        CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STONE_FARM.get())
                .requires(ModItems.REDSTONE_UPGRADE)
                .requires(ModItems.STONE_FARM.get())
                .requires(Items.REDSTONE)
                .unlockedBy("has_redstone_upgrade", has(ModItems.REDSTONE_UPGRADE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.REDSTONE_UPGRADE.asItem().getDescriptionId())));
    }

    private String convertToItemRegistryName(String block) {
        return block.toLowerCase().replace(' ', '_').replace("item.resource_farms.", "");
    }

    private String convertToRegistryName(String block) {
        return block.toLowerCase().replace(' ', '_').replace("block.resource_farms.", "");
    }

}
