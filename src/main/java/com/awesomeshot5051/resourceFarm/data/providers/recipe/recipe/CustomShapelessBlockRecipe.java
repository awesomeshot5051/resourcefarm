package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.data.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.util.*;

import java.util.*;

import static com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.CustomBlockRecipe.*;

public class CustomShapelessBlockRecipe extends ShapelessRecipe {
    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;
    private ItemContainerContents pickContents;
    private ItemStack result2;

    public CustomShapelessBlockRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public RecipeSerializer<CustomShapelessBlockRecipe> getSerializer() {
        return ModRecipes.SHAPELESS_SERIALIZER.get();
    }


    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else if (!this.isSimple) {
            ArrayList<ItemStack> nonEmptyItems = new ArrayList<>(input.ingredientCount());

            for (ItemStack item : input.items()) {
                if (!item.isEmpty()) {
                    nonEmptyItems.add(item);
                }
            }

            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1 ? this.ingredients.getFirst().test(input.getItem(0)) : input.stackedContents().canCraft(this, null);
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        // Map to convert shovel tools to their respective pickaxe tools

        Map<Item, Item> shovelToPickaxeMap = Map.of(
                Items.WOODEN_SHOVEL, Items.WOODEN_PICKAXE,
                Items.STONE_SHOVEL, Items.STONE_PICKAXE,
                Items.IRON_SHOVEL, Items.IRON_PICKAXE,
                Items.GOLDEN_SHOVEL, Items.GOLDEN_PICKAXE,
                Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE,
                Items.NETHERITE_SHOVEL, Items.NETHERITE_PICKAXE
        );

        List<ItemStack> ingredients = input.items(); // Ingredients from the crafting input
        ItemStack resultItem = ItemStack.EMPTY;      // Default result

        ItemContainerContents pickContents = null;   // Placeholder for pick contents

        // Check the first and last ingredients for the PICK_TYPE component
        if (ingredients.getFirst().get(ModDataComponents.PICK_TYPE) != null) {
            pickContents = ItemContainerContents.fromItems(Collections.singletonList(
                    Objects.requireNonNull(ingredients.getFirst().get(ModDataComponents.PICK_TYPE)).copyOne()
            ));
        } else if (ingredients.getLast().get(ModDataComponents.PICK_TYPE) != null) {
            pickContents = ItemContainerContents.fromItems(Collections.singletonList(
                    Objects.requireNonNull(ingredients.getLast().get(ModDataComponents.PICK_TYPE)).copyOne()
            ));
        }

        // If pickContents is set, proceed to process the result
        if (pickContents != null) {
            ItemStack pickStack = pickContents.getStackInSlot(0); // Extract the ItemStack from pickContents
            ItemEnchantments enchantments = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            // Check if the pickContents holds a shovel, and map it to the respective pickaxe
            if (shovelToPickaxeMap.containsKey(pickStack.getItem())) {
                Item newTool = shovelToPickaxeMap.get(pickStack.getItem()); // Get corresponding pickaxe
                pickContents = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(newTool)));
            }

            // Create and configure the result item
            resultItem = getResultItem(registries).copy();
            result2.set(DataComponents.STORED_ENCHANTMENTS, enchantments);
            resultItem.set(pickTypeComponent, pickContents);
        }

        return resultItem;
    }


    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    @Override
    public RecipeType<?> getType() {
        return super.getType();
    }

    @Override
    public CraftingBookCategory category() {
        return category;
    }

    public static class Serializer implements RecipeSerializer<CustomShapelessBlockRecipe> {
        public static final StreamCodec<RegistryFriendlyByteBuf, CustomShapelessBlockRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);
        private static final MapCodec<CustomShapelessBlockRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340779_) -> p_340779_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_301127_) -> p_301127_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_301133_) -> p_301133_.category), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_301142_) -> p_301142_.result), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((p_301021_) -> {
            Ingredient[] aingredient = p_301021_.toArray(Ingredient[]::new);
            if (aingredient.length == 0) {
                return DataResult.error(() -> "No ingredients for shapeless recipe");
            } else {
                return aingredient.length > ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth() ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth())) : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
            }
        }, DataResult::success).forGetter((p_300975_) -> p_300975_.ingredients)).apply(p_340779_, CustomShapelessBlockRecipe::new));

        public Serializer() {
        }

        private static CustomShapelessBlockRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll((p_319735_) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new CustomShapelessBlockRecipe(s, craftingbookcategory, itemstack, nonnulllist);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CustomShapelessBlockRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

        public MapCodec<CustomShapelessBlockRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, CustomShapelessBlockRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
