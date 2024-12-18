package com.awesomeshot5051.resourceFarm.integration.jei;

import com.awesomeshot5051.resourceFarm.recipe.CustomBlockRecipe;
import com.awesomeshot5051.resourceFarm.recipe.ModRecipes;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;

public class RecipeTypes {

    public static final RecipeType<RecipeHolder<CustomBlockRecipe>> FARM_RECIPE =
            RecipeType.createFromVanilla(ModRecipes.FARM_RECIPE.get());


}