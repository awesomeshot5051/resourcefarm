package com.awesomeshot5051.resourceFarm.integration.jei;

import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.*;

public class RecipeTypes<C extends ShapedRecipe> {

    public static final RecipeType<RecipeHolder<CustomBlockRecipe>> FARM_RECIPE =
            RecipeType.createFromVanilla(ModRecipes.FARM_RECIPE.get());
    public static final RecipeType<RecipeHolder<CustomShapelessBlockRecipe>> SHAPELESS_FARM_RECIPE =
            RecipeType.createFromVanilla(ModRecipes.SHAPELESS_FARM_RECIPE.get());

}