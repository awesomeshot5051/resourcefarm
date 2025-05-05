package com.awesomeshot5051.resourceFarm.OreFarmDir;

import com.awesomeshot5051.resourceFarm.base.*;
import com.awesomeshot5051.resourceFarm.base.recipe.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.util.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.stream.*;

public record OreFarmRecipe(
        Ingredient toolInput,
        Ingredient primaryInput,
        List<ChanceResult> outputItems
) implements Recipe<TwoSlotFarmInput>, AbstractFarmBlockEntity.ICraftingFarmRecipe<TwoSlotFarmInput> {

    @Override
    public boolean matches(@NotNull TwoSlotFarmInput recipeInput, @NotNull Level level) {
        if (level.isClientSide()) return false;
        return this.toolInput.test(recipeInput.tool())
                && this.primaryInput.test(recipeInput.primary());
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.toolInput);
        list.add(this.primaryInput);
        return list;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull TwoSlotFarmInput recipeInput, HolderLookup.@NotNull Provider provider) {
        return this.getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return outputItems.getFirst().stack().copy();
    }

    @Override
    public List<ItemStack> getResults() {
        return getRollResults().stream()
                .map(ChanceResult::stack)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChanceResult> getRollResults() {
        return this.outputItems;
    }

    public Ingredient getOreIngredient() {
        return primaryInput;
    }

    @Override
    public List<ItemStack> rollResults(RandomSource rand) {
        List<ItemStack> results = new ArrayList<>();
        List<ChanceResult> rollResults = getRollResults();
        for (ChanceResult output : rollResults) {
            ItemStack stack = output.rollOutput(rand);
            if (!stack.isEmpty())
                results.add(stack);
        }
        return results;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModBlocks.ORE_FARM_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModBlocks.ORE_FARM_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<OreFarmRecipe> {
        public static final MapCodec<OreFarmRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(OreFarmRecipe::toolInput),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(OreFarmRecipe::primaryInput),
                Codec.list(ChanceResult.CODEC).fieldOf("output").forGetter(OreFarmRecipe::outputItems)
        ).apply(inst, OreFarmRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, OreFarmRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, OreFarmRecipe::toolInput,
                        Ingredient.CONTENTS_STREAM_CODEC, OreFarmRecipe::primaryInput,
                        ChanceResult.STREAM_CODEC.apply(ByteBufCodecs.list()), OreFarmRecipe::outputItems,
                        OreFarmRecipe::new);

        @Override
        public @NotNull MapCodec<OreFarmRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, OreFarmRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
