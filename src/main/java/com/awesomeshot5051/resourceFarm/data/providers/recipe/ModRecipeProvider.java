package com.awesomeshot5051.resourceFarm.data.providers.recipe;

import appeng.core.definitions.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.base.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.glodblock.github.extendedae.common.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;
import net.pedroksl.advanced_ae.common.definitions.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static com.awesomeshot5051.resourceFarm.blocks.ModBlocks.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final List<Supplier<Item>> SHOVEL_BLOCKS = List.of(
            ModItems.CONCRETE_POWDER_FARM,
            ModItems.CLAY_FARM,
            ModItems.DIRT_FARM,
            ModItems.GRASS_FARM,
            ModItems.GRAVEL_FARM,
            ModItems.SAND_FARM,
            ModItems.RSAND_FARM,
            ModItems.SSAND_FARM,
            ModItems.SSOIL_FARM,
            ModItems.SNOW_FARM,
            ModItems.MUD_FARM
    );
    public static final List<Supplier<Block>> ALL_FARMS = new ArrayList<>();
    public static final List<Supplier<Block>> SMELTABLE_RESULTS = List.of(
            ModBlocks.COPPER_FARM::get,
            ModBlocks.BASALT_FARM::get,
            ModBlocks.DCOPPER_FARM::get,
            ModBlocks.COAL_FARM::get,
            ModBlocks.DCOAL_FARM::get,
            ModBlocks.IRON_FARM::get,
            ModBlocks.DIRON_FARM::get,
            ModBlocks.DDIAMOND_FARM::get,
            ModBlocks.DIAMOND_FARM::get,
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
            ModBlocks.STONE_FARM::get,
            ModBlocks.CLAY_FARM::get
    );
    public static Block CRYSTAL_CHORUS_BLOCK; //farm
    public static Block CRYSTAL_MENRIL_BlOCK; //farm
    public static Block MENRIL_BRICK; //farm
    public static Block CRYSTAL_CHORUS_BRICK; //farm
    public static Item CHORUS_CHUNK;
    public static Item MENRIL_CHUNK;
    public static Item PROTO_CHORUS;
    public static Block MENRIL_GLASS; //farm
    public static Block CHORUS_GLASS;
    public static List<Supplier<Item>> PICKAXE_BLOCKS = new ArrayList<>();
    public static List<Supplier<Block>> AE_BLOCKS = new ArrayList<>();
    public static List<Supplier<Block>> INSCRIBER_BLOCKS = List.of(
            DDIAMOND_FARM::get,
            DIAMOND_FARM::get,
            GOLD_FARM::get,
            DGOLD_FARM::get,
            SIL_FARM::get,
            SI_FARM::get,
            CCQC_FARM::get,
            CQC_FARM::get,
            SSB_FARM::get,
            SS_FARM::get,
            QA_FARM::get,
            QID_FARM::get
    );

    static {
        for (var item : ModItems.ITEM_REGISTER.getEntries()) {
            Item registeredItem = item.get(); // Get the Item instance

            if (registeredItem instanceof BlockItem blockItem) {
                // Convert to Supplier<Block> and add to the list
                ALL_FARMS.add(blockItem::getBlock);
            }
        }
        if (Main.dynamic_installed) {
            for (var item : ModItems.DYNAMIC_REGISTER.getEntries()) {
                Item registeredItem = item.get(); // Get the Item instance

                if (registeredItem instanceof BlockItem blockItem) {
                    // Convert to Supplier<Block> and add to the list
                    ALL_FARMS.add(blockItem::getBlock);
                }
            }
        }
        if (Main.terminals_installed) {
            for (var item : ModItems.TERMINAL_REGISTER.getEntries()) {
                Item registeredItem = item.get(); // Get the Item instance

                if (registeredItem instanceof BlockItem blockItem) {
                    // Convert to Supplier<Block> and add to the list
                    ALL_FARMS.add(blockItem::getBlock);
                }
            }
        }
        if (Main.eae2_installed) {
            for (var item : ModItems.EAE2_REGISTER.getEntries()) {
                Item registeredItem = item.get(); // Get the Item instance

                if (registeredItem instanceof BlockItem blockItem) {
                    // Convert to Supplier<Block> and add to the list
                    ALL_FARMS.add(blockItem::getBlock);
                    AE_BLOCKS.add(blockItem::getBlock);
                }
            }
        }
        if (Main.ae2_installed) {
            for (var item : ModItems.AE2_REGISTER.getEntries()) {
                Item registeredItem = item.get(); // Get the Item instance

                if (registeredItem instanceof BlockItem blockItem) {
                    // Convert to Supplier<Block> and add to the list
                    ALL_FARMS.add(blockItem::getBlock);
                    AE_BLOCKS.add(blockItem::getBlock);
                }
            }
            if (Main.aae2_installed) {
                for (var item : ModItems.AAE2_REGISTER.getEntries()) {
                    Item registeredItem = item.get(); // Get the Item instance

                    if (registeredItem instanceof BlockItem blockItem) {
                        // Convert to Supplier<Block> and add to the list
                        ALL_FARMS.add(blockItem::getBlock);
                        AE_BLOCKS.add(blockItem::getBlock);
                    }
                }
            }
        }

        for (var item : ModItems.ITEM_REGISTER.getEntries()) {

            if (!SHOVEL_BLOCKS.contains(item)) {
                PICKAXE_BLOCKS.add(item::get);
            }
        }
        if (Main.dynamic_installed) {
            for (var item : ModItems.DYNAMIC_REGISTER.getEntries()) {

                if (!SHOVEL_BLOCKS.contains(item)) {
                    PICKAXE_BLOCKS.add(item::get);
                }
            }
        }
        if (Main.terminals_installed) {
            for (var item : ModItems.TERMINAL_REGISTER.getEntries()) {

                if (!SHOVEL_BLOCKS.contains(item)) {
                    PICKAXE_BLOCKS.add(item::get);
                }
            }
        }
        if (Main.ae2_installed) {
            for (var item : ModItems.AE2_REGISTER.getEntries()) {

                if (!SHOVEL_BLOCKS.contains(item)) {
                    PICKAXE_BLOCKS.add(item::get);
                }
            }
        }
        if (Main.eae2_installed) {
            for (var item : ModItems.EAE2_REGISTER.getEntries()) {

                if (!SHOVEL_BLOCKS.contains(item)) {
                    PICKAXE_BLOCKS.add(item::get);
                }
            }
        }
    }

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike),
                    has(itemlike)).save(
                    pRecipeOutput, Main.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
// ==========================================================================
// RECIPE REGISTRATION - FULL CODE (All 744 lines preserved)
// ==========================================================================

        /*
         * CUSTOM SHAPED RECIPES (Alphabetized by their resource IDs)
         * These recipes use CustomShapedRecipeBuilder and have been reordered
         * by the farm’s resource name (e.g. "andesite_farm", "basalt_farm", etc.).
         */

// --- andesite_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ANDESITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.ANDESITE)
                .unlockedBy("has_andesite", has(Items.ANDESITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "andesite_farm")); // Registers Andesite Farm

// --- basalt_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BASALT_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.BASALT)
                .unlockedBy("has_basalt", has(Items.BASALT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "basalt_farm")); // Registers Basalt Farm

// --- blackstone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACKSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.BLACKSTONE)
                .unlockedBy("has_blackstone", has(Items.BLACKSTONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "blackstone_farm")); // Registers Blackstone Farm

// --- calcite_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CALCITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.CALCITE)
                .unlockedBy("has_calcite", has(Items.CALCITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "calcite_farm")); // Registers Calcite Farm

// --- clay_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLAY_FARM.get())
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Ingredient.of(Items.CLAY))
                .unlockedBy("has_clay_block", has(Items.CLAY))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                        convertToRegistryName(ModBlocks.CLAY_FARM.get().getDescriptionId()))); // Registers Clay Farm

// --- coal_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COAL_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Ingredient.of(Items.COAL_ORE, Items.COAL_BLOCK))
                .unlockedBy("has_coal", has(Items.COAL_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "coal_farm")); // Registers Coal Farm
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COBBLESTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cobblestone_farm")); // Registers Coal Farm

// --- concrete_powder_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CONCRETE_POWDER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.SHOVELS)
                .define('C', Tags.Items.CONCRETE_POWDERS)
                .unlockedBy("has_sand", has(Items.GREEN_CONCRETE_POWDER))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cpowder_farm")); // Registers Concrete Powder Farm

// --- copper_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE,
                        Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.COPPER_ORE, Items.COPPER_INGOT))
                .unlockedBy("has_copper", has(Items.COPPER_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "copper_farm")); // Registers Copper Farm

// --- dcoal_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DCOAL_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Ingredient.of(Items.DEEPSLATE_COAL_ORE, Items.COAL_BLOCK))
                .unlockedBy("has_coal", has(Items.DEEPSLATE_COAL_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dcoal_farm")); // Registers Deepslate Coal Farm

// --- dcopper_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DCOPPER_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE,
                        Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT))
                .unlockedBy("has_deepcopper", has(Items.DEEPSLATE_COPPER_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dcopper_farm")); // Registers Deepslate Copper Farm

// --- deepslate_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEEPSLATE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.DEEPSLATE)
                .unlockedBy("has_deepslate", has(Items.DEEPSLATE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "deepslate_farm")); // Registers Deepslate Farm

// --- diorite_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIORITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.DIORITE)
                .unlockedBy("has_diorite", has(Items.DIORITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "diorite_farm")); // Registers Diorite Farm

// --- dirt_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRT_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Items.DIRT)
                .unlockedBy("has_dirt", has(Items.DIRT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dirt_farm")); // Registers Dirt Farm

// --- diamond_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIAMOND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("DCD")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DIAMOND_ORE)
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy("has_diamond", has(Items.DIAMOND_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "diamond_farm")); // Registers Diamond Farm

// --- ddiamond_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDIAMOND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("DCD")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_DIAMOND_ORE)
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy("has_deepslate_diamond", has(Items.DEEPSLATE_DIAMOND_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ddiamond_farm")); // Registers Deepslate Diamond Farm

// --- diron_farm (Iron Farm) ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.IRON_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ICI")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE,
                        Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.IRON_ORE)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_iron", has(Items.IRON_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "iron_farm")); // Registers Iron Farm

// --- diron_farm (Deepslate Iron Farm) ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRON_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ICI")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_IRON_ORE)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_deepslate_iron", has(Items.DEEPSLATE_IRON_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "diron_farm")); // Registers Deepslate Iron Farm

// --- emerald_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMERALD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ECE")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.EMERALD_ORE)
                .define('E', Items.EMERALD_BLOCK)
                .unlockedBy("has_emerald", has(Items.EMERALD_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "emerald_farm")); // Registers Emerald Farm

// --- demerald_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMERALD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ECE")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.DEEPSLATE_EMERALD_ORE)
                .define('E', Items.EMERALD_BLOCK)
                .unlockedBy("has_deepslate_emerald", has(Items.DEEPSLATE_EMERALD_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "demerald_farm")); // Registers Deepslate Emerald Farm

// --- gold_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.GOLD_ORE, Items.GOLD_INGOT))
                .unlockedBy("has_gold", has(Items.GOLD_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "gold_farm")); // Registers Gold Farm

// --- dgold_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DGOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT))
                .unlockedBy("has_deepslate_gold", has(Items.DEEPSLATE_GOLD_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dgold_farm")); // Registers Deepslate Gold Farm

// --- glowstone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GLOWSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.GLOWSTONE)
                .unlockedBy("has_glowstone", has(Items.GLOWSTONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "glowstone_farm")); // Registers Glowstone Farm

// --- granite_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRANITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.GRANITE)
                .unlockedBy("has_granite", has(Items.GRANITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "granite_farm")); // Registers Granite Farm

// --- grass_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRASS_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL,
                        Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_PICKAXE))
                .define('C', Items.GRASS_BLOCK)
                .unlockedBy("has_grass", has(Items.GRASS_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "grass_farm")); // Registers Grass Farm

// --- gravel_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRAVEL_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', Ingredient.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL,
                        Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_PICKAXE))
                .define('C', Items.GRAVEL)
                .unlockedBy("has_gravel", has(Items.GRAVEL))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "gravel_farm")); // Registers Gravel Farm

// --- lapis_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LAPIS_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE,
                        Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.LAPIS_ORE, Items.LAPIS_BLOCK))
                .unlockedBy("has_lapis", has(Items.LAPIS_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "lapis_farm")); // Registers Lapis Farm

// --- dlapis_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DLAPIS_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_LAPIS_ORE, Items.LAPIS_BLOCK))
                .unlockedBy("has_deepslate_lapis", has(Items.DEEPSLATE_LAPIS_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dlapis_farm")); // Registers Deepslate Lapis Farm

// --- mud_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MUD_FARM.get())
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GMG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('M', Items.MUD)
                .unlockedBy("has_mud", has(Items.MUD))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                        convertToRegistryName(ModBlocks.MUD_FARM.get().getDescriptionId()))); // Registers Mud Farm

// --- nether_gold_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_GOLD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.NETHER_GOLD_ORE)
                .unlockedBy("has_nether_gold", has(Items.NETHER_GOLD_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "nether_gold_farm")); // Registers Nether Gold Farm

// --- nether_quartz_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHER_QUARTZ_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.NETHER_QUARTZ_ORE)
                .unlockedBy("has_nether_quartz", has(Items.NETHER_QUARTZ_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "nether_quartz_farm")); // Registers Nether Quartz Farm

// --- netherite_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("NCN")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('N', Items.NETHERITE_INGOT)
                .define('C', Items.ANCIENT_DEBRIS)
                .unlockedBy("has_netherite_block", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "netherite_farm")); // Registers Netherite Farm

// --- netherrack_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NETHERRACK_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.NETHERRACK)
                .unlockedBy("has_netherrack", has(Items.NETHERRACK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "netherrack_farm")); // Registers Netherrack Farm

// --- obsidian_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OBSIDIAN_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "obsidian_farm")); // Registers Obsidian Farm

// --- purpur_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURPUR_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.PURPUR_BLOCK)
                .unlockedBy("has_purpur", has(Items.PURPUR_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "purpur_farm")); // Registers Purpur Farm

// --- redstone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REDSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.REDSTONE_ORE, Items.REDSTONE_BLOCK))
                .unlockedBy("has_redstone", has(Items.REDSTONE_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "redstone_farm")); // Registers Redstone Farm

// --- dredstone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DREDSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_REDSTONE_ORE, Items.REDSTONE_BLOCK))
                .unlockedBy("has_deepslate_redstone", has(Items.DEEPSLATE_REDSTONE_ORE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "dredstone_farm")); // Registers Deepslate Redstone Farm

// --- rsand_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RSAND_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Items.RED_SAND)
                .unlockedBy("has_red_sand", has(Items.RED_SAND))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "rsand_farm")); // Registers Red Sand Farm

// --- sand_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAND_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.SHOVELS)
                .define('C', Items.SAND)
                .unlockedBy("has_sand", has(Items.SAND))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "sand_farm")); // Registers Sand Farm

// --- sstone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.SANDSTONE)
                .unlockedBy("has_sandstone", has(Items.SANDSTONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "sstone_farm")); // Registers Sandstone Farm

// --- snow_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SNOW_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Items.SNOW_BLOCK)
                .unlockedBy("has_snow", has(Items.SNOW_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "snow_farm")); // Registers Snow Farm

// --- ssoil_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSOIL_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Items.SOUL_SOIL)
                .unlockedBy("has_soul_soil", has(Items.SOUL_SOIL))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ssoil_farm")); // Registers Soul Soil Farm

// --- ssand_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSAND_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.SHOVELS)
                .define('C', Items.SOUL_SAND)
                .unlockedBy("has_soul_sand", has(Items.SOUL_SAND))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "ssand_farm")); // Registers Soul Sand Farm

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENTC_FARM.get())
                .pattern("BBB")
                .pattern("BSB")
                .pattern("ACA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.PICKAXES)
                .define('C', Ingredient.of(EAESingletons.FULLY_ENTROIZED_FLUIX_BUDDING, EAESingletons.MOSTLY_ENTROIZED_FLUIX_BUDDING, EAESingletons.HALF_ENTROIZED_FLUIX_BUDDING, EAESingletons.HARDLY_ENTROIZED_FLUIX_BUDDING))
                .define('A', ModItems.EAE_CONDUIT)
                .unlockedBy("has_entroized_fluix_budding", has(EAESingletons.FULLY_ENTROIZED_FLUIX_BUDDING))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.ENTC_FARM.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENTB_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BEB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('E', EAESingletons.ENTRO_BLOCK)
                .unlockedBy("has_entro_block", has(EAESingletons.ENTRO_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.ENTB_FARM.get().getDescriptionId())));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENTD_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BEB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('E', Ingredient.of(EAESingletons.MOSTLY_ENTROIZED_FLUIX_BUDDING, EAESingletons.HALF_ENTROIZED_FLUIX_BUDDING, EAESingletons.HARDLY_ENTROIZED_FLUIX_BUDDING))
                .unlockedBy("has_entro_block", has(EAESingletons.HALF_ENTROIZED_FLUIX_BUDDING))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.ENTD_FARM.get().getDescriptionId())));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SIL_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BEB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('E', EAESingletons.SILICON_BLOCK)
                .unlockedBy("has_silicon_block", has(EAESingletons.SILICON_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.SIL_FARM.get().getDescriptionId())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLCR_FARM.get())
                .pattern("BBB")
                .pattern("BCB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('C', AEItems.FLUIX_CRYSTAL)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_fluix_crystal", has(AEItems.FLUIX_CRYSTAL))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.FLCR_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLDU_FARM.get())
                .pattern("BBB")
                .pattern("BDB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('D', AEItems.FLUIX_DUST)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_fluix_dust", has(AEItems.FLUIX_DUST))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.FLDU_FARM.get().getDescriptionId())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, QA_FARM.get())
                .pattern("BBB")
                .pattern("BDB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('D', AAEItems.QUANTUM_ALLOY)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_quantum_alloy", has(AAEItems.QUANTUM_ALLOY))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.QA_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, QID_FARM.get())
                .pattern("BBB")
                .pattern("BDB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('D', AAEItems.QUANTUM_INFUSED_DUST)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_quantum_infused_dust", has(AAEItems.QUANTUM_INFUSED_DUST))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.QID_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SS_FARM.get())
                .pattern("BBB")
                .pattern("BDB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('D', AAEItems.SHATTERED_SINGULARITY)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_shattered_singularity", has(AAEItems.SHATTERED_SINGULARITY))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.SS_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SI_FARM.get())
                .pattern("BBB")
                .pattern("BDB")
                .pattern("AAA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('D', AEItems.SINGULARITY)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_quantum_alloy", has(AEItems.SINGULARITY))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.SI_FARM.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MC_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("AMA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('M', AEBlocks.MYSTERIOUS_CUBE)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_mc_cube", has(AEBlocks.MYSTERIOUS_CUBE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.MC_FARM.get().getDescriptionId())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INSCRIBER_UPGRADE)
                .pattern("AAA")
                .pattern("AIA")
                .pattern("AAA")
                .define('A', ModItems.AE_CONDUIT)
                .define('I', AEBlocks.INSCRIBER)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_inscriber", has(AEBlocks.INSCRIBER))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.INSCRIBER_UPGRADE.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SSB_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ASA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('S', AEBlocks.SKY_STONE_BLOCK)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_sky_stone", has(AEBlocks.SKY_STONE_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.SSB_FARM.get().getDescriptionId())));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CQC_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ACA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', AEItems.CERTUS_QUARTZ_CRYSTAL)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_certus_crystal", has(AEItems.CERTUS_QUARTZ_CRYSTAL.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CQC_FARM.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CCQC_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ASA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('S', AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_charged_certus", has(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CCQC_FARM.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FBQ_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ACA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', AEBlocks.FLAWED_BUDDING_QUARTZ)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_flawed_budding_quartz", has(AEBlocks.FLAWED_BUDDING_QUARTZ))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.FBQ_FARM.get().getDescriptionId())));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLBQ_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ACA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', AEBlocks.FLAWLESS_BUDDING_QUARTZ)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_flawless_budding_quartz", has(AEBlocks.FLAWLESS_BUDDING_QUARTZ))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.FLBQ_FARM.get().getDescriptionId())));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.QG_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("ASA")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('S', AEBlocks.QUARTZ_GLASS)
                .define('A', ModItems.AE_CONDUIT)
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .unlockedBy("has_mc_cube", has(AEBlocks.QUARTZ_GLASS))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.QG_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AE_CONDUIT, 8)
                .pattern("CCC")
                .pattern("CQC")
                .pattern("CCC")
                .define('C', AEItems.CERTUS_QUARTZ_CRYSTAL)
                .define('Q', Items.QUARTZ)
                .unlockedBy("has_certus_quartz", has(AEItems.CERTUS_QUARTZ_CRYSTAL))
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.AE_CONDUIT.get().getDescriptionId())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EAE_CONDUIT, 8)
                .pattern("QCQ")
                .pattern("CAC")
                .pattern("QCQ")
                .define('Q', EAESingletons.ENTRO_CRYSTAL)
                .define('A', ModItems.AE_CONDUIT)
                .define('C', AEItems.CERTUS_QUARTZ_CRYSTAL)
                .unlockedBy("has_certus_quartz", has(AEItems.CERTUS_QUARTZ_CRYSTAL))
                .unlockedBy("has_ae_conduit", has(ModItems.AE_CONDUIT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.EAE_CONDUIT.get().getDescriptionId())));


        // --- stone_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.STONE)
                .unlockedBy("has_stone", has(Items.STONE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "stone_farm")); // Registers Stone Farm

// --- terracotta_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TERRACOTTA_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.TERRACOTTA)
                .unlockedBy("has_terracotta", has(Items.TERRACOTTA))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "terracotta_farm")); // Registers Terracotta Farm

// --- tuff_farm ---
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUFF_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', Items.TUFF)
                .unlockedBy("has_tuff", has(Items.TUFF))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "tuff_farm")); // Registers Tuff Farm

        /*
         * END of CustomShapedRecipeBuilder recipes.
         * (The remaining recipes below use other builders and retain their original order.)
         */

        /*
         * CUSTOM SHAPELESS RECIPES
         */
        CustomShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CONCRETE_FARM.get(), 1)
                .requires(ModBlocks.CONCRETE_POWDER_FARM.get())
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_cpowder_farm", has(ModItems.CONCRETE_POWDER_FARM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "concrete_farm")); // Registers Concrete Farm (shapeless)

        /*
         * UPGRADE RECIPE BUILDERS FOR PICKAXE & SHOVEL UPGRADES
         */

// -- Pickaxe Upgrades --
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Stone pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('S', Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE))
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_stone", has(Items.COBBLESTONE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_stone_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Iron pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("III")
                    .pattern("IFI")
                    .pattern("III")
                    .define('I', Items.IRON_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_iron", has(Items.IRON_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_iron_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Gold pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("GGG")
                    .pattern("GFG")
                    .pattern("GGG")
                    .define('G', Items.GOLD_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_gold_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Diamond pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("DDD")
                    .pattern("DFD")
                    .pattern("DDD")
                    .define('D', Items.DIAMOND)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_diamond_upgrade_recipe"));
        });
        PICKAXE_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Netherite pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern(" N ")
                    .pattern("NFN")
                    .pattern(" N ")
                    .define('N', Items.NETHERITE_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_netherite_upgrade_recipe"));
        });

// -- Shovel Upgrades --
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = Block.byItem(farmBlockSupplier.get());
            // Stone shovel upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('S', Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE))
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_stone", has(Items.COBBLESTONE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_stone_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Iron shovel upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("III")
                    .pattern("IFI")
                    .pattern("III")
                    .define('I', Items.IRON_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_iron", has(Items.IRON_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_iron_upgrade_recipe"));
        });
        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Gold shovel upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("GGG")
                    .pattern("GFG")
                    .pattern("GGG")
                    .define('G', Items.GOLD_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_gold_upgrade_recipe"));
        });

        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Diamond shovel upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("DDD")
                    .pattern("DFD")
                    .pattern("DDD")
                    .define('D', Items.DIAMOND)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_diamond_upgrade_recipe"));
        });

        SHOVEL_BLOCKS.forEach(farmBlockSupplier -> {
            Item farmBlock = farmBlockSupplier.get();
            // Netherite shovel upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern(" N ")
                    .pattern("NFN")
                    .pattern(" N ")
                    .define('N', Items.NETHERITE_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_shovel_netherite_upgrade_recipe"));
        });

        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Stone pickaxe upgrade recipe
            if (PICKAXE_BLOCKS.stream().anyMatch(farmBlockSupp -> farmBlockSupp.get().getDefaultInstance().is(farmBlock.asItem()))) {
                // Skip the block if it's in the pickaxe blocks list
                return;
            }

            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .define('S', Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE))
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_stone", has(Items.COBBLESTONE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_stone_upgrade_recipe"));
        });

        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            if (PICKAXE_BLOCKS.stream().anyMatch(farmBlockSupp -> farmBlockSupp.get().getDefaultInstance().is(farmBlock.asItem()))) {
                // Skip the block if it's in the pickaxe blocks list
                return;
            }

            // Iron pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("III")
                    .pattern("IFI")
                    .pattern("III")
                    .define('I', Items.IRON_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_iron", has(Items.IRON_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_iron_upgrade_recipe"));
        });
        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            if (PICKAXE_BLOCKS.stream().anyMatch(farmBlockSupp -> farmBlockSupp.get().getDefaultInstance().is(farmBlock.asItem()))) {
                // Skip the block if it's in the pickaxe blocks list
                return;
            }

            // Gold pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("GGG")
                    .pattern("GFG")
                    .pattern("GGG")
                    .define('G', Items.GOLD_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_gold_upgrade_recipe"));
        });
        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            if (PICKAXE_BLOCKS.stream().anyMatch(farmBlockSupp -> farmBlockSupp.get().getDefaultInstance().is(farmBlock.asItem()))) {
                // Skip the block if it's in the pickaxe blocks list
                return;
            }

            // Diamond pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern("DDD")
                    .pattern("DFD")
                    .pattern("DDD")
                    .define('D', Items.DIAMOND)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_diamond_upgrade_recipe"));
        });
        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            if (PICKAXE_BLOCKS.stream().anyMatch(farmBlockSupp -> farmBlockSupp.get().getDefaultInstance().is(farmBlock.asItem()))) {
                // Skip the block if it's in the pickaxe blocks list
                return;
            }

            // Netherite pickaxe upgrade recipe
            UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, farmBlock)
                    .pattern(" N ")
                    .pattern("NFN")
                    .pattern(" N ")
                    .define('N', Items.NETHERITE_INGOT)
                    .define('F', farmBlock.asItem())
                    .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_pickaxe_netherite_upgrade_recipe"));
        });


        /*
         * ENCHANTMENT RECIPES
         */
        ALL_FARMS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Enchantment addition recipe
            EnchantmentAdditionRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(Items.ENCHANTED_BOOK)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_enchanted_book", has(ItemTags.MINING_ENCHANTABLE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_enchant_upgrade_recipe"));
        });
        /*
        Enchantment Removal Recipes For All Farms
         */
        ALL_FARMS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Enchantment removal recipe
            EnchantmentRemovalRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_farm", has(farmBlock.asItem()))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId() + "_enchantment_removal_recipe")));
        });

        /*
         * SMELTER UPGRADE RECIPES
         */
        SMELTABLE_RESULTS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Smelter upgrade recipe
            CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(Items.BLAST_FURNACE)
                    .requires(Items.LAVA_BUCKET)
                    .requires(ModItems.SMELTER_UPGRADE)
                    .requires(farmBlock.asItem())
                    .unlockedBy("has_smelter_upgrade", has(ModItems.SMELTER_UPGRADE))
                    .unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_smelter_upgrade"));
        });

        INSCRIBER_BLOCKS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Enchantment removal recipe
            CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(farmBlock.asItem())
                    .requires(ModItems.INSCRIBER_UPGRADE)
                    .unlockedBy("has_farm", has(farmBlock.asItem()))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId() + "_inscriber_upgrade_recipe")));
        });

// --- Shaped Recipes for Upgrade Items ---
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
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                        convertToItemRegistryName(ModItems.SMELTER_UPGRADE.asItem().getDescriptionId())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REDSTONE_UPGRADE)
                .pattern("RRR")
                .pattern("OLO")
                .pattern("RRR")
                .define('O', Items.OBSERVER)
                .define('R', Items.REDSTONE)
                .define('L', Items.LEVER)
                .unlockedBy("has_observer", has(Items.OBSERVER))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                        convertToItemRegistryName(ModItems.REDSTONE_UPGRADE.asItem().getDescriptionId())));

// --- Redstone Upgrade for Farms ---
        ALL_FARMS.forEach(farmBlockSupplier -> {
            Block farmBlock = farmBlockSupplier.get();
            // Redstone upgrade recipe for farm
            CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, farmBlock)
                    .requires(ModItems.REDSTONE_UPGRADE)
                    .requires(farmBlock)
                    .requires(Items.REDSTONE)
                    .unlockedBy("has_redstone_upgrade", has(ModItems.REDSTONE_UPGRADE))
                    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID,
                            convertToRegistryName(farmBlock.getDescriptionId()) + "_redstone_upgrade"));
        });


// ==========================================================================
// END OF RECIPE REGISTRATION
// ==========================================================================


        //Integrated Dynamics Recipes
        ResourceLocation crystal_chorus_chunk_block = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_block");
        CRYSTAL_CHORUS_BLOCK = BuiltInRegistries.BLOCK.get(crystal_chorus_chunk_block);

        ResourceLocation crystal_menril_chunk_block = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_block");
        CRYSTAL_MENRIL_BlOCK = BuiltInRegistries.BLOCK.get(crystal_menril_chunk_block);

        ResourceLocation crystal_chorus_chunk = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_chunk");
        CHORUS_CHUNK = BuiltInRegistries.ITEM.get(crystal_chorus_chunk);

        ResourceLocation crystal_menril_chunk = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_chunk");
        MENRIL_CHUNK = BuiltInRegistries.ITEM.get(crystal_menril_chunk);

        ResourceLocation menril_brick_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_brick");
        MENRIL_BRICK = BuiltInRegistries.BLOCK.get(menril_brick_rl);

        ResourceLocation crystal_chorus_brick_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_brick");
        CRYSTAL_CHORUS_BRICK = BuiltInRegistries.BLOCK.get(crystal_chorus_brick_rl);

        ResourceLocation proto_chorus_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "proto_chorus");
        PROTO_CHORUS = BuiltInRegistries.ITEM.get(proto_chorus_rl);

        ResourceLocation menril_glass = ResourceLocation.fromNamespaceAndPath("integratedterminals", "menril_glass");
        MENRIL_GLASS = BuiltInRegistries.BLOCK.get(menril_glass);

        ResourceLocation chorus_glass = ResourceLocation.fromNamespaceAndPath("integratedterminals", "chorus_glass");
        CHORUS_GLASS = BuiltInRegistries.BLOCK.get(chorus_glass);


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CCHORUS_FARM.get())
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.PICKAXES)
                .define('C', CRYSTAL_CHORUS_BLOCK.asItem())
                .unlockedBy("has_crystalized_chorus", has(CRYSTAL_CHORUS_BLOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CCHORUS_FARM.get().getDescriptionId())));


        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CCBRICK_FARM.get())
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', CRYSTAL_CHORUS_BRICK.asItem())
                .unlockedBy("has_crystal_chorus_brick", has(CRYSTAL_CHORUS_BRICK.asItem()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CCBRICK_FARM.get().getDescriptionId())));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CMENRIL_FARM.get())
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('S', ItemTags.PICKAXES)
                .define('C', CRYSTAL_MENRIL_BlOCK.asItem())
                .unlockedBy("has_crystalized_menril_block", has(CRYSTAL_MENRIL_BlOCK))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CMENRIL_FARM.get().getDescriptionId())));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MBRICK_FARM.get())
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', MENRIL_BRICK.asItem())
                .unlockedBy("has_crystal_menril_brick", has(MENRIL_BRICK.asItem()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.MBRICK_FARM.get().getDescriptionId())));


        //Part of the Integrated Terminals mod
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MGLASS_FARM.get())
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', MENRIL_GLASS.asItem())
                .unlockedBy("has_menril_glass", has(MENRIL_GLASS.asItem()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.MGLASS_FARM.get().getDescriptionId())));
        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CCGLASS_FARM.get())
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GCG")
                .define('G', Tags.Items.GLASS_PANES)
                .define('P', ItemTags.PICKAXES)
                .define('C', CHORUS_GLASS.asItem())
                .unlockedBy("has_chorus_glass", has(CHORUS_GLASS.asItem()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.CCGLASS_FARM.get().getDescriptionId())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEBlocks.FLAWLESS_BUDDING_QUARTZ)
                .pattern("FFF")
                .pattern("FFF")
                .pattern("FFF")
                .define('F', AEBlocks.FLAWED_BUDDING_QUARTZ)
                .unlockedBy("has_flawed_budding_quartz", has(AEBlocks.FLAWED_BUDDING_QUARTZ))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(AEBlocks.FLAWLESS_BUDDING_QUARTZ.getEnglishName())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPEED_UPGRADE)
                .pattern("RRR")
                .pattern("RFR")
                .pattern("RNR")
                .define('R', Items.REDSTONE)
                .define('F', Items.FLINT_AND_STEEL)
                .define('N', Items.QUARTZ)
                .unlockedBy("has_flint_and_steel", has(Items.FLINT_AND_STEEL))
                .unlockedBy("has_netherrack", has(Items.REDSTONE))
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToItemRegistryName(ModItems.SPEED_UPGRADE.asItem().getDescriptionId())));

        CardUpgradeRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FLDU_FARM.get())
                .requires(ModBlocks.FLDU_FARM.get())
                .requires(ModItems.SPEED_UPGRADE)
                .requires(Items.SUGAR)
                .requires(Items.REDSTONE)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_speed_upgrade", has(ModItems.SPEED_UPGRADE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Main.MODID, convertToRegistryName(ModBlocks.FLDU_FARM.get().getDescriptionId() + "_speed_upgrade_recipe")));


        new DynamicRecipeStore().loadVanillaOreRecipes();
    }


    private String convertToItemRegistryName(String block) {
        return block.toLowerCase().replace(' ', '_').replace("item.resource_farms.", "");
    }

    private String convertToRegistryName(String block) {
        return block.toLowerCase().replace(' ', '_').replace("block.resource_farms.", "");
    }

}
