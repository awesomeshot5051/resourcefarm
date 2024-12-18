package com.awesomeshot5051.resourceFarm.integration.jei.recipeCategories;

import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.awesomeshot5051.resourceFarm.recipe.CustomBlockRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class FarmRecipeCategory implements IRecipeCategory<CustomBlockRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("resource_farms", "farm_recipe");
    public static final RecipeType<CustomBlockRecipe> TYPE = RecipeType.create("resource_farms", "farm_recipe", CustomBlockRecipe.class);
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("resource_farms", "textures/gui/jei_background.png");
    private final IDrawable background;
    public IGuiHelper guiHelper;

    public FarmRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(BACKGROUND, 0, 0, 160, 125);
        this.guiHelper = guiHelper;
    }

    @Override
    public int getWidth() {
        return 10;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    public ResourceLocation getUid() {
        return UID;
    }


    public Class<? extends CustomBlockRecipe> getRecipeClass() {
        return CustomBlockRecipe.class;
    }

    @Override
    public RecipeType<CustomBlockRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("crafting");
    }


    @Override
    public @Nullable IDrawable getIcon() {
        return guiHelper.createDrawableItemStack(new ItemStack(ModItems.COPPER_FARM.get()));
    }


    public Ingredient getIngredients() {
        return Ingredient.of(ModItems.COPPER_FARM.get());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CustomBlockRecipe recipe, IFocusGroup focuses) {
        // Define how to layout recipe inputs/outputs
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 10).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 10).addItemStack(recipe.getResult());
    }
}
