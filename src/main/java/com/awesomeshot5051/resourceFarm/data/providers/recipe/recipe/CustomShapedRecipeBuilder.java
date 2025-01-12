package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.google.common.collect.*;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

import javax.annotation.Nullable;
import javax.annotation.*;
import java.util.*;

public class CustomShapedRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack; // Neo: add stack result support
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final boolean showNotification = true;
    @Nullable
    private String group;

    public CustomShapedRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this(category, new ItemStack(result, count));
    }

    public CustomShapedRecipeBuilder(RecipeCategory p_249996_, ItemStack result) {
//        this(p_249996_, result);
        this.category = p_249996_;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
    }

    public static CustomShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
        return shaped(category, result, 1);
    }

    public static CustomShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
        return new CustomShapedRecipeBuilder(category, result, count);
    }

    public static CustomShapedRecipeBuilder shaped(RecipeCategory p_251325_, ItemStack result) {
        return new CustomShapedRecipeBuilder(p_251325_, result);
    }


    @Nonnull
    public CustomShapedRecipeBuilder define(Character symbol, TagKey<Item> tag) {
        return this.define(symbol, Ingredient.of(tag));
    }


    @Nonnull
    public CustomShapedRecipeBuilder define(Character symbol, ItemLike item) {
        return this.define(symbol, Ingredient.of(item));
    }


    @Nonnull
    public CustomShapedRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredient);
            return this;
        }
    }


    @Nonnull
    public CustomShapedRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.getFirst().length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    @Override
    public CustomShapedRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }


    @Override
    public RecipeBuilder group(@org.jetbrains.annotations.Nullable String s) {
        return this;
    }

    @Override
    @Nonnull
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        ShapedRecipePattern shapedrecipepattern = this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        CustomBlockRecipe shapedrecipe = new CustomBlockRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                shapedrecipepattern,
                this.resultStack,
                this.showNotification
        );

        recipeOutput.accept(id, shapedrecipe, advancement$builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private ShapedRecipePattern ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        } else {
            return ShapedRecipePattern.of(this.key, this.rows);
        }
    }
}
