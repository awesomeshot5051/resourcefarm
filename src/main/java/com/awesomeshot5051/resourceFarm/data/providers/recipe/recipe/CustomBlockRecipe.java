package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

import java.util.*;

@SuppressWarnings("ALL")
public class CustomBlockRecipe extends ShapedRecipe {
    public static final DataComponentType<ItemContainerContents> pickTypeComponent = ModDataComponents.PICK_TYPE.get();

    public final ShapedRecipePattern pattern;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;
    final ItemStack result;
    private ItemContainerContents pickContents;
    private ItemStack result2;

    public CustomBlockRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        super(group, category, pattern, result, showNotification);
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.result2 = result;
        this.showNotification = showNotification;
    }

    public CustomBlockRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        this(group, category, pattern, result, true);
    }

    @Override
    public RecipeSerializer<CustomBlockRecipe> getSerializer() {
        return ModRecipes.SHAPED_SERIALIZER.get();
    }

    public boolean matches(CraftingInput input, Level level) {
        return this.pattern.matches(input);
    }

    public ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, result.getDescriptionId());
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider registries) {
        ItemStack pickStack = craftingInput.getItem(4);
        ItemStack oreStack = craftingInput.getItem(7);
        ItemEnchantments enchantments = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        List<ItemStack> itemStacks = new ArrayList<>();
        if (isCorrectToolForDrop(pickStack, Block.byItem(oreStack.getItem()))) {
            itemStacks.add(getResultItem(registries));

            pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickStack));


            result2 = getResultItem(registries).copy();

            result2.set(DataComponents.STORED_ENCHANTMENTS, enchantments);
            result2.set(pickTypeComponent, pickContents);


            super.assemble(craftingInput, registries);
            return result2;
        }
        return ItemStack.EMPTY;
    }

    public boolean isCorrectToolForDrop(ItemStack pickStack, Block ore) {
        return !ore.defaultBlockState().is(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.withDefaultNamespace("incorrect_for_" + convertToItemRegistryName(pickStack) + "_tool")));
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return this.result;
    }

    public String convertToItemRegistryName(ItemStack pick) {
        return pick.getDescriptionId().replace("item.minecraft.", "").replace("_pickaxe", "");
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return super.getType();
    }

    @Override
    public CraftingBookCategory category() {
        return category;
    }


    public static class Serializer implements RecipeSerializer<CustomBlockRecipe> {
        public static final MapCodec<CustomBlockRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340778_) -> p_340778_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_311729_) -> p_311729_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_311732_) -> p_311732_.category), ShapedRecipePattern.MAP_CODEC.forGetter((p_311733_) -> p_311733_.pattern), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_311730_) -> p_311730_.result), Codec.BOOL.optionalFieldOf("show_notification", true).forGetter((p_311731_) -> p_311731_.showNotification)).apply(p_340778_, CustomBlockRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, CustomBlockRecipe> STREAM_CODEC = StreamCodec.of(CustomBlockRecipe.Serializer::toNetwork, CustomBlockRecipe.Serializer::fromNetwork);

        public Serializer() {
        }

        private static CustomBlockRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern customBlockRecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            return new CustomBlockRecipe(s, craftingbookcategory, customBlockRecipepattern, itemstack, flag);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CustomBlockRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
        }

        public MapCodec<CustomBlockRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, CustomBlockRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }


}