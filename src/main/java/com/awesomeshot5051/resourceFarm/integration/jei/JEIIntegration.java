package com.awesomeshot5051.resourceFarm.integration.jei;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.integration.jei.recipeCategories.*;
import com.awesomeshot5051.resourceFarm.recipe.*;
import mezz.jei.api.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.registration.*;
import net.minecraft.client.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.crafting.*;

import javax.annotation.*;
import java.util.*;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        // Register your custom recipe category
        registration.addRecipeCategories(
                new FarmRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;

        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<RecipeHolder<CustomBlockRecipe>> recipes = recipeManager.getAllRecipesFor(ModRecipes.FARM_RECIPE.get());
        // Extract all recipes of your custom type
        List<CustomBlockRecipe> customBlockRecipes = recipeManager.getAllRecipesFor(ModRecipes.FARM_RECIPE.get())
                .stream()
                .map(RecipeHolder::value) // Extract the CustomBlockRecipe
                .toList();

        // Register your custom recipes in JEI under your custom RecipeType
        registration.addRecipes(FarmRecipeCategory.TYPE, customBlockRecipes);
    }


    private ShapedRecipe toShapedRecipe(CustomBlockRecipe customRecipe) {
        return new ShapedRecipe(
                "", // No group required
                customRecipe.category(),
                customRecipe.pattern,
                customRecipe.getResult()
        );
    }

}
