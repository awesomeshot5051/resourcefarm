package com.awesomeshot5051.resourceFarm.data.providers.tags;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;

import java.util.concurrent.*;

import static com.awesomeshot5051.resourceFarm.data.providers.tags.ModItemTags.*;

public class ItemTagsProvider extends IntrinsicHolderTagsProvider<Item> implements ResourceFarmDataProvider {

    public ItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                            ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.ITEM, registries, block -> block.builtInRegistryHolder().key(), Main.MODID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(SLABS_AND_FLUX_CRYSTAL)
                .addTag(ItemTags.SLABS)
                .add(AE2Blocks.FLUIX_CRYSTAL.get());
    }


}
