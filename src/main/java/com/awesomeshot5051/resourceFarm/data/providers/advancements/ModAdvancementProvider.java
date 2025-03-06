package com.awesomeshot5051.resourceFarm.data.providers.advancements;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.advancements.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.common.data.AdvancementProvider;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static final class ModAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = Advancement.Builder.advancement();
            builder.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"));
            builder.display(new ItemStack(ModItems.IRON_FARM), Component.literal("UNLIMITED NETHERITE!"),
                    Component.literal("Craft the Netherite Farm. Next stop, the Netherite Beacon!"), null, AdvancementType.CHALLENGE, true, true, true
            );
            builder.rewards(AdvancementRewards.Builder.experience(1000000000));
            builder.addCriterion("craft_netherite_farm", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_FARM.get()));
            builder.requirements(AdvancementRequirements.allOf(List.of("craft_netherite_farm")));
            builder.save(saver, ResourceLocation.fromNamespaceAndPath(Main.MODID, "unlimited_netherite"), existingFileHelper);
        }
    }
}