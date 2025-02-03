package com.awesomeshot5051.resourceFarm.integration.jei;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.integration.jei.recipeCategories.*;
import mezz.jei.api.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.registration.*;
import net.minecraft.client.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;

import javax.annotation.*;
import java.util.*;


@SuppressWarnings("ALL")
public class JEIIntegration implements IModPlugin {

    public static List<ItemStack> convertIngredientsToItemStacks(NonNullList<Ingredient> ingredients) {
        List<ItemStack> items = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            items = Arrays.stream(ingredient.getItems()).toList();
        }


        return items;
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();


        registration.addRecipeCategories(
                new FarmRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<RecipeHolder<CustomBlockRecipe>> recipes = recipeManager.getAllRecipesFor(ModRecipes.FARM_RECIPE.get());

        List<CustomBlockRecipe> customBlockRecipes = recipeManager.getAllRecipesFor(ModRecipes.FARM_RECIPE.get())
                .stream()
                .map(RecipeHolder::value)
                .toList();

        registration.addRecipes(FarmRecipeCategory.TYPE, customBlockRecipes);
    }


}
