package com.awesomeshot5051.resourceFarm.data;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.data.providers.models.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.*;
import com.awesomeshot5051.resourceFarm.data.providers.sound.*;
import com.awesomeshot5051.resourceFarm.data.providers.tags.BlockTagsProvider;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.data.event.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(@NotNull GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBlockModelProvider(packOutput, existingFileHelper));
//        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
//                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new BlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(
                event.includeClient(),
                new BaseSoundProvider(packOutput, existingFileHelper)
        );
    }
}