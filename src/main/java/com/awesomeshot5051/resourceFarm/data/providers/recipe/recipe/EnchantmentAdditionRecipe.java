package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

import java.util.*;


public class EnchantmentAdditionRecipe extends ShapelessRecipe {
    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    List<Item> shovelFarms = new ArrayList<>(List.of(
            ModItems.CONCRETE_POWDER_FARM.get(),
            ModItems.DIRT_FARM.get(),
            ModItems.GRASS_FARM.get(),
            ModItems.GRAVEL_FARM.get(),
            ModItems.SAND_FARM.get(),
            ModItems.RSAND_FARM.get(),
            ModItems.SSAND_FARM.get(),
            ModItems.SSOIL_FARM.get(),
            ModItems.SNOW_FARM.get()
    ));
    List<Item> ALL_FARMS = new ArrayList<>();
    private ItemContainerContents swordContents;
    private ItemStack result2;

    public EnchantmentAdditionRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        boolean isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public RecipeSerializer<EnchantmentAdditionRecipe> getSerializer() {
        return ModRecipes.ENCHANTING_SERIALIZER.get();
    }

    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public boolean matches(CraftingInput input, Level level) {
        return super.matches(input, level);
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.@NotNull Provider registries) {
        List<ItemStack> ingredients = input.items();
        ItemStack resultItem;
        List<Item> farmBlocks = new ArrayList<>();
        ItemEnchantments enchantments = ItemEnchantments.EMPTY;
        for (var sidedBlock : ModItems.ITEM_REGISTER.getEntries()) {
            ALL_FARMS.add(sidedBlock.get());
        }

        List<ItemStack> Ingredients = input.items();

        ItemStack farm = Ingredients.stream()
                .filter(item -> ALL_FARMS.contains(item.getItem()))
                .findFirst()
                .orElse(ItemStack.EMPTY);
        ItemEnchantments.Mutable storedEnchantments = new ItemEnchantments.Mutable(enchantments);
        for (var sidedBlock : ModItems.ITEM_REGISTER.getEntries()) {
            if (!shovelFarms.contains(sidedBlock.get())) {
                farmBlocks.add(sidedBlock.get());
            }
        }

        ItemContainerContents pickContents = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_PICKAXE)));

        for (ItemStack ingredient : ingredients) {
            if (shovelFarms.contains(ingredient.getItem())) {
                pickContents = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(ingredient.getOrDefault(ModDataComponents.PICK_TYPE, ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_SHOVEL))))).copyOne()));
            } else if (farmBlocks.contains(ingredient.getItem())) {
                pickContents = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(ingredient.getOrDefault(ModDataComponents.PICK_TYPE, pickContents)).copyOne()));
            } else if (ingredient.getItem().getDefaultInstance().has(DataComponents.STORED_ENCHANTMENTS)) {
                storedEnchantments = new ItemEnchantments.Mutable(ingredient.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY));
            }
        }

        HolderLookup<Item> itemLookup = registries.lookup(Registries.ITEM).orElseThrow();
        HolderSet.Named<Item> pickEnchantables = itemLookup.get(ItemTags.MINING_ENCHANTABLE).orElseThrow();


        ItemStack pickStack = pickContents.getStackInSlot(0);
        ItemEnchantments enchantments2 = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

        boolean isCompatible = checkEnchantmentCompatibility(storedEnchantments.toImmutable(), pickEnchantables, pickStack) && isCompatible(storedEnchantments, pickStack);

        if (!isCompatible) {
            return new ItemStack(Items.AIR);
        }
        for (Holder<Enchantment> holder : storedEnchantments.keySet()) {

            int level = storedEnchantments.getLevel(holder);


            pickStack.enchant(holder, level);
        }

        enchantments2 = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);


        resultItem = getResultItem(registries).copy();
        pickStack.set(DataComponents.ENCHANTMENTS, enchantments2);
        pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickStack));
        resultItem.set(DataComponents.STORED_ENCHANTMENTS, enchantments2);
        resultItem.set(ModDataComponents.PICK_TYPE, pickContents);
        resultItem.set(DataComponents.CUSTOM_DATA, farm.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY));
        return resultItem;
    }

    private boolean isCompatible(ItemEnchantments.Mutable storedEnchantments, ItemStack swordStack) {
        if (storedEnchantments.keySet().stream().toList().getFirst().getDelegate().value().definition().supportedItems().unwrapKey().stream().findFirst().isPresent()) {
            return storedEnchantments.keySet().stream().toList().getFirst().getDelegate().value().definition().supportedItems().unwrapKey().stream().findFirst().get().equals(ItemTags.MINING_ENCHANTABLE) || storedEnchantments.keySet().stream().toList().getFirst().getDelegate().is(EnchantmentTags.MINING_EXCLUSIVE) || storedEnchantments.keySet().stream().toList().getFirst().getDelegate().value().definition().supportedItems().stream().toList().contains(swordStack.getItemHolder());
        }
        return false;
    }


    private boolean checkEnchantmentCompatibility(ItemEnchantments enchantments, HolderSet.Named<Item> swordEnchantables, ItemStack sword) {
        for (var enchantmentEntry : enchantments.entrySet()) {
            Holder<Enchantment> enchantmentHolder = enchantmentEntry.getKey();


            boolean isValid = swordEnchantables.stream().anyMatch(itemHolder -> {

                return enchantmentHolder.unwrap().map(enchantment -> EnchantmentHelper.canStoreEnchantments(sword), holder -> false);
            });

            if (!isValid) {
                return false;
            }
        }

        return true;
    }


    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }


    @Override
    public CraftingBookCategory category() {
        return category;
    }

    public static class Serializer implements RecipeSerializer<EnchantmentAdditionRecipe> {
        public static final StreamCodec<RegistryFriendlyByteBuf, EnchantmentAdditionRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);
        private static final MapCodec<EnchantmentAdditionRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340779_) -> p_340779_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_301127_) -> p_301127_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_301133_) -> p_301133_.category), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_301142_) -> p_301142_.result), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((p_301021_) -> {
            Ingredient[] aingredient = p_301021_.toArray(Ingredient[]::new);
            if (aingredient.length == 0) {
                return DataResult.error(() -> "No ingredients for shapeless recipe");
            } else {
                return aingredient.length > ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth() ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth())) : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
            }
        }, DataResult::success).forGetter((p_300975_) -> p_300975_.ingredients)).apply(p_340779_, EnchantmentAdditionRecipe::new));

        public Serializer() {
        }

        private static EnchantmentAdditionRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll((p_319735_) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new EnchantmentAdditionRecipe(s, craftingbookcategory, itemstack, nonnulllist);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, EnchantmentAdditionRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }

        public MapCodec<EnchantmentAdditionRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, EnchantmentAdditionRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
