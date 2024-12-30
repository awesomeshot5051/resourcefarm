package com.awesomeshot5051.resourceFarm.integration.jei.recipeCategories;

import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.items.*;
import mezz.jei.api.gui.builder.*;
import mezz.jei.api.gui.drawable.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

public class FarmRecipeCategory implements IRecipeCategory<CustomBlockRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("resource_farms", "farm_recipe");
    public static final RecipeType<CustomBlockRecipe> TYPE = RecipeType.create("resource_farms", "farm_recipe", CustomBlockRecipe.class);
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("resource_farms", "textures/gui/jei_background.png");
    private final IDrawable background;
    public IGuiHelper guiHelper;

    public FarmRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.background = guiHelper.createDrawableItemLike(Blocks.CRAFTING_TABLE);
        guiHelper.createCraftingGridHelper();
    }

    @Override
    public int getWidth() {
        return 116;
    }

    @Override
    public int getHeight() {
        return 54;
    }

    public ResourceLocation getUid() {
        return UID;
    }


    public Class<? extends CustomBlockRecipe> getRecipeClass() {
        return CustomBlockRecipe.class;
    }

    @Override
    public @NotNull RecipeType<CustomBlockRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("gui.jei.category.craftingTable");
    }


    @Override
    public @Nullable IDrawable getIcon() {
        return guiHelper.createDrawableItemStack(new ItemStack(ModItems.COPPER_FARM.get()));
    }


    public Ingredient getIngredients() {
        return Ingredient.of(ModItems.COPPER_FARM.get());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CustomBlockRecipe recipe, @NotNull IFocusGroup focuses) {
        int xStart = 10; // Starting x position
        int yStart = 0; // Starting y position
        int spacing = 20; // Space between slots
        int gridSize = 3; // Number of slots per row and column
        int i = 0; // Ingredient index

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (i < recipe.getIngredients().size()) { // Ensure we don't go out of bounds
                    int x = xStart + col * spacing;
                    int y = yStart + row * spacing;
                    builder.addInputSlot(x, y).addIngredients(recipe.getIngredients().get(i));
                    i++; // Move to the next ingredient
                }
            }
        }
//        builder.addInputSlot(10, 10).addIngredients(recipe.getIngredients().get(1));
//        builder.addInputSlot(20, 10).addIngredients(recipe.getIngredients().get(2));
        builder.addOutputSlot(90, 10).addItemStack(recipe.getResult());
    }
}
