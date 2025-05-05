package com.awesomeshot5051.resourceFarm.base;

import com.awesomeshot5051.resourceFarm.OreFarmDir.*;
import com.awesomeshot5051.resourceFarm.util.*;
import net.minecraft.core.registries.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.*;

import java.util.*;

public class DynamicRecipeStore {
    private final List<OreFarmRecipe> recipes = new ArrayList<>();

    public DynamicRecipeStore() {
    }

    // Method to add a recipe to the store
    public void addRecipe(OreFarmRecipe recipe) {
        recipes.add(recipe);
    }

    // Method to get all recipes in the store
    public List<OreFarmRecipe> getAllRecipes() {
        return recipes;
    }

    // Method to get recipes matching a specific input (dynamic lookup)
    public Optional<OreFarmRecipe> getRecipeForInput(Item input) {
        return recipes.stream()
                .filter(recipe -> recipe.getOreIngredient().test(new ItemStack(input)))
                .findFirst();
    }

    // Load vanilla ore recipes from the pre-generated data (during development)
    public void loadVanillaOreRecipes() {
        BuiltInRegistries.ITEM.stream()
                .filter(item -> item.builtInRegistryHolder().is(Tags.Items.ORES))
                .forEach(item -> {
                    OreFarmRecipe recipe = new OreFarmRecipe(
                            Ingredient.of(ItemTags.PICKAXES),
                            Ingredient.of(item),
                            List.of(
                                    new ChanceResult(new ItemStack(item), 1.0f),
                                    new ChanceResult(new ItemStack(Items.COBBLESTONE), 0.5f)
                            )
                    );
                    addRecipe(recipe);  // Add to dynamic recipe store
                });
    }

    // Load modded ore recipes dynamically during runtime
    public void loadModdedOreRecipes(List<OreFarmRecipe> moddedRecipes) {
        moddedRecipes.forEach(this::addRecipe);
    }
}
