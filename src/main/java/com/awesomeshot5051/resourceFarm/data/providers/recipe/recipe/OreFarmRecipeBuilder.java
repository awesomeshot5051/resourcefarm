package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.OreFarmDir.*;
import com.awesomeshot5051.resourceFarm.util.*;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class OreFarmRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Ingredient toolInput;
    private final Ingredient ingredientInput;
    private final Ingredient baseInput;
    private final List<ChanceResult> outputItems = new ArrayList<>();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @NotNull
    private String group;

    // Private constructor, use static factory methods
    private OreFarmRecipeBuilder(RecipeCategory category, Ingredient toolInput, Ingredient ingredientInput, Ingredient baseInput, ChanceResult firstOutput) {
        this.category = category;
        this.toolInput = toolInput;
        this.ingredientInput = ingredientInput;
        this.baseInput = baseInput;
        this.outputItems.add(firstOutput); // Add the mandatory first output
    }
    // --- Static Factory Methods ---

    public static OreFarmRecipeBuilder create(RecipeCategory category, Ingredient toolInput, Ingredient ingredientInput, Ingredient baseInput, ChanceResult firstOutput) {
        return new OreFarmRecipeBuilder(category, toolInput, ingredientInput, baseInput, firstOutput);
    }

    // Convenience factory methods using ItemLike/Tags if desired
    public static OreFarmRecipeBuilder create(RecipeCategory category, ItemLike toolInput, Ingredient ingredientInput, ItemLike baseInput, ChanceResult firstOutput) {
        return create(category, Ingredient.of(toolInput), ingredientInput, Ingredient.of(baseInput), firstOutput);
    }

    public static OreFarmRecipeBuilder create(RecipeCategory category, TagKey<Item> toolInput, Ingredient ingredientInput, TagKey<Item> baseInput, ChanceResult firstOutput) {
        return create(category, Ingredient.of(toolInput), ingredientInput, Ingredient.of(baseInput), firstOutput);
    }

    public static OreFarmRecipeBuilder create(RecipeCategory category, TagKey<Item> toolInput, Ingredient ingredientInput, Ingredient baseInput, ChanceResult firstOutput) {
        return create(category, Ingredient.of(toolInput), ingredientInput, baseInput, firstOutput);
    }


    // --- Builder Methods ---

    public OreFarmRecipeBuilder addOutput(ChanceResult output) {
        this.outputItems.add(output);
        return this;
    }

    // Convenience method for adding outputs
    public OreFarmRecipeBuilder addOutput(ItemLike itemProvider, int count, float chance) {
        return addOutput(new ChanceResult(new ItemStack(itemProvider, count), chance));
    }

    public OreFarmRecipeBuilder addOutput(ItemLike itemProvider, float chance) {
        return addOutput(itemProvider, 1, chance);
    }

    // --- Implementing RecipeBuilder ---

    @Override
    public @NotNull OreFarmRecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    @NotNull
    public OreFarmRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        // The primary result is considered the item from the first output entry
        if (this.outputItems.isEmpty()) {
            throw new IllegalStateException("Cannot get result from a recipe builder with no outputs");
        }
        return this.outputItems.getFirst().stack().getItem();
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        if (this.outputItems.isEmpty()) {
            throw new IllegalStateException("Cannot save recipe " + id + " with no outputs defined.");
        }
        Advancement.Builder advancementBuilder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancementBuilder::addCriterion);

        OreFarmRecipe recipe = new OreFarmRecipe(
                this.toolInput,
                this.ingredientInput,
                List.copyOf(this.outputItems)
        );

        AdvancementHolder advancementHolder = advancementBuilder.build(
                id.withPrefix("recipes/" + this.category.getFolderName() + "/"));
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(Main.MODID, id.getPath()), recipe, advancementHolder);
    }
}
