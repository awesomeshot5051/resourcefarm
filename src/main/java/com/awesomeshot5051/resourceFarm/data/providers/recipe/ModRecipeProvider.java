package com.awesomeshot5051.resourceFarm.data.providers.recipe;

import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.recipe.CustomShapedRecipeBuilder;
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
                .define('C', Items.COPPER_ORE)
                .unlockedBy("has_copper", has(Items.COPPER_ORE)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath("resource_farms","copper_farm"));
    }
}