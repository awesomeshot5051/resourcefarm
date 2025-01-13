package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.data.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.*;
import org.jetbrains.annotations.*;

import java.util.*;


public class EnchantmentRemovalRecipe extends ShapelessRecipe {

    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    ItemStack farm;
    private ItemContainerContents swordContents;
    private ItemStack result2;

    public EnchantmentRemovalRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        boolean isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public @NotNull RecipeSerializer<EnchantmentRemovalRecipe> getSerializer() {
        return ModRecipes.ENCHANTMENT_REMOVER_SERIALIZER.get();
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.@NotNull Provider registries) {

        List<Item> farmBlocks = new ArrayList<>();
        ItemEnchantments enchantments = ItemEnchantments.EMPTY;
        ItemEnchantments.Mutable storedEnchantments = new ItemEnchantments.Mutable(enchantments);
        for (var sidedBlock : ModItems.ITEM_REGISTER.getEntries()) {
            farmBlocks.add(sidedBlock.get());
        }
        List<ItemStack> ingredient = input.items();

        ItemContainerContents shovelContents = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_SHOVEL)));   // Placeholder for pick contents
        ItemContainerContents pickaxeContents = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_PICKAXE)));   // Placeholder for pick contents
        // Check the first and last ingredients for the PICK_TYPE component
        farm = new ItemStack(Items.AIR);
        for (ItemStack ingrnt : ingredient) {
            if (farmBlocks.contains(ingrnt.getItem())) {
                if (Objects.requireNonNull(ingrnt.get(ModDataComponents.PICK_TYPE)).getStackInSlot(0).is(ItemTags.SHOVELS)) {
                    shovelContents = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(ingrnt.getOrDefault(ModDataComponents.PICK_TYPE, shovelContents)).copyOne()));
                    farm = ingrnt.getItem().getDefaultInstance();
                    farm.remove(DataComponents.STORED_ENCHANTMENTS);
                    ItemStack shovel = shovelContents.getStackInSlot(0);
                    shovel.remove(DataComponents.ENCHANTMENTS);
                    shovelContents = ItemContainerContents.fromItems(Collections.singletonList(shovel));
                    farm.set(ModDataComponents.PICK_TYPE, shovelContents);
                }
            } else if (Objects.requireNonNull(ingrnt.get(ModDataComponents.PICK_TYPE)).getStackInSlot(0).is(ItemTags.PICKAXES)) {
                pickaxeContents = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(ingrnt.getOrDefault(ModDataComponents.PICK_TYPE, pickaxeContents)).copyOne()));
                farm = ingrnt.getItem().getDefaultInstance();
                farm.remove(DataComponents.STORED_ENCHANTMENTS);
                ItemStack pickaxe = pickaxeContents.getStackInSlot(0);
                pickaxe.remove(DataComponents.ENCHANTMENTS);
                pickaxeContents = ItemContainerContents.fromItems(Collections.singletonList(pickaxe));
                farm.set(ModDataComponents.PICK_TYPE, pickaxeContents);
            }

        }
        return farm;
    }

    public static class Serializer implements RecipeSerializer<EnchantmentRemovalRecipe> {
        public static final StreamCodec<RegistryFriendlyByteBuf, EnchantmentRemovalRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);
        private static final MapCodec<EnchantmentRemovalRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340779_) -> p_340779_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_301127_) -> p_301127_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_301133_) -> p_301133_.category), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_301142_) -> p_301142_.result), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((p_301021_) -> {
            Ingredient[] aingredient = p_301021_.toArray(Ingredient[]::new);
            if (aingredient.length == 0) {
                return DataResult.error(() -> "No ingredients for shapeless recipe");
            } else {
                return aingredient.length > ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth() ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth())) : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
            }
        }, DataResult::success).forGetter((p_300975_) -> p_300975_.ingredients)).apply(p_340779_, EnchantmentRemovalRecipe::new));

        public Serializer() {
        }

        private static EnchantmentRemovalRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll((p_319735_) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new EnchantmentRemovalRecipe(s, craftingbookcategory, itemstack, nonnulllist);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, EnchantmentRemovalRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

        public MapCodec<EnchantmentRemovalRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, EnchantmentRemovalRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }


}
