package com.awesomeshot5051.resourceFarm.recipe;

import com.awesomeshot5051.resourceFarm.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "resource_farms");
    public static final Supplier<RecipeSerializer<CustomBlockRecipe>> CUSTOM_SERIALIZER =
            RECIPE_SERIALIZERS.register("custom_serializer", CustomBlockRecipe.Serializer::new);
    // Register the custom recipe serializer
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "resource_farms");
    public static final Supplier<RecipeType<CustomBlockRecipe>> FARM_RECIPE =
            RECIPE_TYPES.register(
                    "farm_recipe",
                    // We need the qualifying generic here due to generics being generics.
                    () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Main.MODID, "farm_recipe"))
            );

    public static void registerRecipes(IEventBus eventBus) {
        // Example of registering your custom recipe using a custom recipe serializer

        RECIPE_SERIALIZERS.register(eventBus);
    }

    public static void registerTypes(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
