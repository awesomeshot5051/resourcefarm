package com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.awesomeshot5051.resourceFarm.items.*;
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
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.stream.*;

@SuppressWarnings("ALL")
public class UpgradeRecipe extends ShapedRecipe {
    public static final DataComponentType<ItemContainerContents> pickTypeComponent = ModDataComponents.PICK_TYPE.get();

    public final ShapedRecipePattern pattern;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;
    final ItemStack result;
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
    List<Item> shovels = List.of(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.GOLDEN_SHOVEL, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL);
    List<Item> pickaxes = List.of(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE);
    List<Item> ALL_FARMS = new ArrayList<>();
    private ItemContainerContents pickContents;
    private ItemStack result2;

    public UpgradeRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        super(group, category, pattern, result, showNotification);
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.result2 = result;
        this.showNotification = showNotification;
    }

    public UpgradeRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        this(group, category, pattern, result, true);
    }

    public static List<Item> getPlanks() {


        return BuiltInRegistries.ITEM.getTag(ItemTags.PLANKS)
                .stream()
                .flatMap(holderSet -> holderSet.stream().map(Holder::value))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeSerializer<UpgradeRecipe> getSerializer() {
        return ModRecipes.UPGRADE_SERIALIZER.get();
    }

    public boolean matches(CraftingInput input, Level level) {
        return this.pattern.matches(input);
    }

    public ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, result.getDescriptionId());
    }

    private boolean areAllModifiersEqual(List<ItemStack> modifier) {
        return modifier.size() == 4
                && modifier.get(0).toString().equals(modifier.get(1).toString())
                && modifier.get(0).toString().equals(modifier.get(2).toString())
                && modifier.get(0).toString().equals(modifier.get(3).toString());
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider registries) {
        Map<Ingredient, Item> materialToPickaxeMap = Map.of(
                Ingredient.of(Items.OAK_PLANKS, Items.SPRUCE_PLANKS, Items.BIRCH_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.DARK_OAK_PLANKS, Items.MANGROVE_PLANKS, Items.BAMBOO_PLANKS, Items.CHERRY_PLANKS), Items.WOODEN_PICKAXE,
                Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE), Items.STONE_PICKAXE,
                Ingredient.of(Items.IRON_INGOT), Items.IRON_PICKAXE,
                Ingredient.of(Items.GOLD_INGOT), Items.GOLDEN_PICKAXE,
                Ingredient.of(Items.DIAMOND), Items.DIAMOND_PICKAXE,
                Ingredient.of(Items.NETHERITE_INGOT), Items.NETHERITE_PICKAXE
        );
        Map<Ingredient, Item> materialToShovelMap = Map.of(
                Ingredient.of(Items.OAK_PLANKS, Items.SPRUCE_PLANKS, Items.BIRCH_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.DARK_OAK_PLANKS, Items.MANGROVE_PLANKS, Items.BAMBOO_PLANKS, Items.CHERRY_PLANKS), Items.WOODEN_SHOVEL,
                Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE), Items.STONE_SHOVEL,
                Ingredient.of(Items.IRON_INGOT), Items.IRON_SHOVEL,
                Ingredient.of(Items.GOLD_INGOT), Items.GOLDEN_SHOVEL,
                Ingredient.of(Items.DIAMOND), Items.DIAMOND_SHOVEL,
                Ingredient.of(Items.NETHERITE_INGOT), Items.NETHERITE_SHOVEL
        );
        List<ItemStack> ingredients = craftingInput.items();
        ItemStack pickStack;
        for (var sidedBlock : ModItems.ITEM_REGISTER.getEntries()) {
            ALL_FARMS.add(sidedBlock.get());
        }
        ItemStack farm = ingredients.stream()
                .filter(item -> ALL_FARMS.contains(item.getItem()))
                .findFirst()
                .orElse(ItemStack.EMPTY);
        List<ItemStack> modifer = new ArrayList<>(List.of(craftingInput.getItem(1), craftingInput.getItem(3), craftingInput.getItem(5), craftingInput.getItem(7)));
        if (areAllModifiersEqual(modifer)) {
            List<ItemStack> itemStacks = new ArrayList<>();
            ItemEnchantments itemenchantments = ItemEnchantments.EMPTY;
            if (craftingInput.getItem(4).get(ModDataComponents.PICK_TYPE) != null) {
                pickStack = craftingInput.getItem(4).get(ModDataComponents.PICK_TYPE).copyOne();
            } else if (shovelFarms.contains(craftingInput.getItem(4).getItem())) {
                pickStack = new ItemStack(Items.WOODEN_SHOVEL);
            } else {
                pickStack = new ItemStack(Items.WOODEN_PICKAXE);
            }
            if (shovelFarms.contains(craftingInput.getItem(4).getItem())) {
                if (isHigherShovelType(pickStack, modifer.getFirst())) {
                    itemStacks.add(getResultItem(registries));
                    for (Map.Entry<Ingredient, Item> entry : materialToShovelMap.entrySet()) {
                        if (entry.getKey().test(modifer.getFirst())) {

                            itemenchantments = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
                            pickStack = new ItemStack(entry.getValue());
                            pickStack.set(DataComponents.ENCHANTMENTS, itemenchantments);
                            break;
                        }
                    }

                    pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickStack));
                    result2 = getResultItem(registries).copy();
                } else {
                    return ItemStack.EMPTY;
                }
            } else {
                if (isHigherPickType(pickStack, modifer.getFirst())) {
                    itemStacks.add(getResultItem(registries));
                    for (Map.Entry<Ingredient, Item> entry : materialToPickaxeMap.entrySet()) {
                        if (entry.getKey().test(modifer.getFirst())) {

                            itemenchantments = pickStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
                            pickStack = new ItemStack(entry.getValue());
                            pickStack.set(DataComponents.ENCHANTMENTS, itemenchantments);
                            break;
                        }
                    }

                    pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickStack));
                    result2 = getResultItem(registries).copy();
                    result2.set(DataComponents.STORED_ENCHANTMENTS, itemenchantments);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        } else {
            return new ItemStack(Items.AIR);
        }

        result2.set(ModDataComponents.PICK_TYPE, pickContents);
        result2.set(ModDataComponents.UPGRADE, farm.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY));
        super.assemble(craftingInput, registries);
        return result2;
    }

    private boolean isHigherPickType(ItemStack basePickaxeType, ItemStack modifierPickaxeType) {
        Map<Item, Ingredient> PickaxeToMaterialMap = Map.of(Items.WOODEN_PICKAXE, Ingredient.of(Items.OAK_PLANKS, Items.SPRUCE_PLANKS, Items.BIRCH_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.DARK_OAK_PLANKS, Items.MANGROVE_PLANKS, Items.BAMBOO_PLANKS, Items.CHERRY_PLANKS), Items.GOLDEN_PICKAXE, Ingredient.of(Items.GOLD_INGOT), Items.STONE_PICKAXE, Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE), Items.IRON_PICKAXE, Ingredient.of(Items.IRON_INGOT), Items.DIAMOND_PICKAXE, Ingredient.of(Items.DIAMOND), Items.NETHERITE_PICKAXE, Ingredient.of(Items.NETHERITE_INGOT));
        Item modifierMaterialType = null;
        for (Map.Entry<Item, Ingredient> entry : PickaxeToMaterialMap.entrySet()) {
            if (entry.getValue().test(modifierPickaxeType)) {
                modifierMaterialType = entry.getKey();
            }
        }
        if (modifierMaterialType.getDefaultInstance().is(Items.NETHERITE_PICKAXE)) {
            if (isNextPickaxeInRank(basePickaxeType, modifierMaterialType.getDefaultInstance())) {
                return PickaxeType.getRank(modifierMaterialType) > PickaxeType.getRank(basePickaxeType.getItem());
            }
        } else {
            return PickaxeType.getRank(modifierMaterialType) > PickaxeType.getRank(basePickaxeType.getItem());
        }
        return false;
    }

    private boolean isNextShovelInRank(ItemStack baseShovelType, ItemStack modifierShovelType) {
        Item baseItem = baseShovelType.getItem();
        Item modifierItem = modifierShovelType.getItem();
        int baseIndex = shovels.indexOf(baseItem);
        if (baseIndex != -1 && baseIndex < shovels.size() - 1) {
            return shovels.get(baseIndex + 1).equals(modifierItem);
        }
        return false;
    }

    private boolean isNextPickaxeInRank(ItemStack basePickaxeType, ItemStack modifierPickaxeType) {
        Item baseItem = basePickaxeType.getItem();
        Item modifierItem = modifierPickaxeType.getItem();
        int baseIndex = pickaxes.indexOf(baseItem);
        if (baseIndex != -1 && baseIndex < pickaxes.size() - 1) {
            return pickaxes.get(baseIndex + 1).equals(modifierItem);
        }
        return false;
    }

    private boolean isHigherShovelType(ItemStack baseShovelType, ItemStack modifierShovelType) {
        Map<Item, Ingredient> ShovelToMaterialMap = Map.of(Items.WOODEN_SHOVEL, Ingredient.of(Items.OAK_PLANKS, Items.SPRUCE_PLANKS, Items.BIRCH_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.DARK_OAK_PLANKS, Items.MANGROVE_PLANKS, Items.BAMBOO_PLANKS, Items.CHERRY_PLANKS), Items.GOLDEN_SHOVEL, Ingredient.of(Items.GOLD_INGOT), Items.STONE_SHOVEL, Ingredient.of(Items.COBBLESTONE, Items.COBBLED_DEEPSLATE), Items.IRON_SHOVEL, Ingredient.of(Items.IRON_INGOT), Items.DIAMOND_SHOVEL, Ingredient.of(Items.DIAMOND), Items.NETHERITE_SHOVEL, Ingredient.of(Items.NETHERITE_INGOT));
        Item modifierMaterialType = null;
        for (Map.Entry<Item, Ingredient> entry : ShovelToMaterialMap.entrySet()) {
            if (entry.getValue().test(modifierShovelType)) {
                modifierMaterialType = entry.getKey();
            }
        }
        if (modifierMaterialType.getDefaultInstance().is(Items.NETHERITE_SHOVEL)) {
            if (isNextShovelInRank(baseShovelType, modifierMaterialType.getDefaultInstance())) {
                return ShovelType.getRank(modifierMaterialType) > ShovelType.getRank(baseShovelType.getItem());
            }
        } else {
            return ShovelType.getRank(modifierMaterialType) > ShovelType.getRank(baseShovelType.getItem());
        }
        return false;
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return this.result;
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


    public static class Serializer implements RecipeSerializer<UpgradeRecipe> {
        public static final MapCodec<UpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340778_) -> p_340778_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_311729_) -> p_311729_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_311732_) -> p_311732_.category), ShapedRecipePattern.MAP_CODEC.forGetter((p_311733_) -> p_311733_.pattern), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_311730_) -> p_311730_.result), Codec.BOOL.optionalFieldOf("show_notification", true).forGetter((p_311731_) -> p_311731_.showNotification)).apply(p_340778_, UpgradeRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, UpgradeRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        public Serializer() {
        }

        private static UpgradeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern UpgradeRecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            return new UpgradeRecipe(s, craftingbookcategory, UpgradeRecipepattern, itemstack, flag);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, UpgradeRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
        }

        public MapCodec<UpgradeRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, UpgradeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }


}