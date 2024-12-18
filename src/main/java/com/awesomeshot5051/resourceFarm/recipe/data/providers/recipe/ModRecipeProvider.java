package com.awesomeshot5051.resourceFarm.recipe.data.providers.recipe;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.awesomeshot5051.resourceFarm.recipe.CustomShapedRecipeBuilder;
import com.awesomeshot5051.resourceFarm.recipe.CustomShapelessRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
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
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.IRON_ORE, Items.IRON_INGOT))
                .unlockedBy("has_iron", has(Items.IRON_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "iron_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRON_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_IRON_ORE, Items.IRON_INGOT))
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
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT))
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
                .define('C', Items.GREEN_CONCRETE_POWDER)
                .unlockedBy("has_sand", has(Items.GREEN_CONCRETE_POWDER)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cpowder_farm"));
        CustomShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CONCRETE_FARM.get(), 1)
                .requires(ModBlocks.CONCRETE_POWDER_FARM.get())
                .requires(Items.WATER_BUCKET)// Define what 'C' is
                .unlockedBy("has_cpowder_farm", has(ModItems.CONCRETE_POWDER_FARM.get()))  // Unlock condition
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "concrete_farm"));

        CustomShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COBBLESTONE_FARM.get())
                .pattern("BBB")
                .pattern("BPB")
                .pattern("BCB")
                .define('B', Tags.Items.GLASS_PANES)
                .define('P', Ingredient.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE))
                .define('C', Ingredient.of(Items.STONE, Items.COBBLESTONE))
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms", "cobblestone_farm"));

    }

}
