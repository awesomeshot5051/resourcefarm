package com.awesomeshot5051.resourceFarm.integration.jei;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.integration.jei.recipeCategories.FarmRecipeCategory;
import com.awesomeshot5051.resourceFarm.recipe.CustomBlockRecipe;
import com.awesomeshot5051.resourceFarm.recipe.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "jei_plugin");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        IRecipeManager recipeRegistry = jeiRuntime.getRecipeManager();
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
//        List<RecipeHolder<CraftingRecipe>> hiddenRecipes = new ArrayList<>();


//        recipeRegistry.hideRecipes(RecipeTypes.CRAFTING, hiddenRecipes);
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
        List<CustomBlockRecipe> farmblockrecipes = recipeManager.getAllRecipesFor(ModRecipes.FARM_RECIPE.get())
                .stream().map(RecipeHolder::value).collect(Collectors.toList());

        registration.addRecipes(FarmRecipeCategory.TYPE, farmblockrecipes);

    }

}