package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import javax.annotation.*;
import java.util.*;

@SuppressWarnings("ALL")
public class CardUpgradeRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack;
    private final NonNullList<Ingredient> ingredients;
    private final Map<String, Criterion<?>> criteria;

    @Nullable
    private String group;

    public CardUpgradeRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this(category, new ItemStack(result, count));
    }

    public CardUpgradeRecipeBuilder(RecipeCategory p_250837_, ItemStack result) {

        this.ingredients = NonNullList.create();
        this.criteria = new LinkedHashMap<>();
        this.category = p_250837_;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
    }

    public static CardUpgradeRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
        return shapeless(category, result, 1);
    }

    public static CardUpgradeRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count) {
        return new CardUpgradeRecipeBuilder(category, result, count);
    }

    public static CardUpgradeRecipeBuilder shapeless(RecipeCategory p_252339_, ItemStack result) {
        return new CardUpgradeRecipeBuilder(p_252339_, result);
    }


    public CardUpgradeRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }


    public CardUpgradeRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public CardUpgradeRecipeBuilder requires(ItemLike item, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.requires(Ingredient.of(item));
        }

        return this;
    }


    public CardUpgradeRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public CardUpgradeRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public CardUpgradeRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public CardUpgradeRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        CardUpgradeRecipe shapelessrecipe = new CardUpgradeRecipe(Objects.requireNonNullElse(this.group, ""), RecipeBuilder.determineBookCategory(this.category), this.resultStack, this.ingredients);
        recipeOutput.accept(id, shapelessrecipe, advancement$builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));

    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
