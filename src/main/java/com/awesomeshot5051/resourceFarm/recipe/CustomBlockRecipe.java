package com.awesomeshot5051.resourceFarm.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider registries) {
        ItemStack pickStack = craftingInput.getItem(4);
        ItemStack oreStack = craftingInput.getItem(7);
        List<ItemStack> itemStacks = new ArrayList<>();
        if (pickStack.isCorrectToolForDrops(Block.byItem(oreStack.getItem()).defaultBlockState())) {
            itemStacks.add(getResultItem(registries));
            // Set the pick type in the result item's data
            pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickStack));
//            BlockRendererBase.setPickaxeType(Block.byItem(result.getItem().getDefaultInstance().getItem()), pickStack);
            result2 = getResultItem(registries).copy(); // Copy the result item to avoid modifying the original
            // Example: Setting the pickaxe type
//            NonNullList<ItemStack> p_00115 = NonNullList.withSize(1, pickStack);
//            ContainerHelper.saveAllItems(new CompoundTag(), p_00115, registries);
//            PickTypeData.getOrCreate(result);
        }
        result2.set(pickTypeComponent, pickContents);
//        result.set(ModDataComponents.PICK_TYPE, pickContents);

//        Main.LOGGER.debug("The pick type is...: {}", Objects.requireNonNull(result.get(ModDataComponents.PICK_TYPE)).getStackInSlot(0));
        super.assemble(craftingInput, registries);
        return result2;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return result;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingBookCategory category() {
        return category;
    }

    @Override
    public boolean isSpecial() {
        return true;
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