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
import static com.awesomeshot5051.resourceFarm.integration.ae2.AE2Blocks.*;

public class ItemTagsProvider extends IntrinsicHolderTagsProvider<Item> implements ResourceFarmDataProvider {

    public ItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries,
                            ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.ITEM, registries, block -> block.builtInRegistryHolder().key(), Main.MODID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(AE_SLABS)
                .add(SKY_STONE_SLAB.get().asItem(),
                        SMOOTH_SKY_STONE_SLAB.get().asItem(),
                        SKY_STONE_BRICK_SLAB.get().asItem(),
                        SKY_STONE_SMALL_BRICK_SLAB.get().asItem(),
                        FLUIX_SLAB.get().asItem(),
                        QUARTZ_SLAB.get().asItem(),
                        CUT_QUARTZ_SLAB.get().asItem(),
                        SMOOTH_QUARTZ_SLAB.get().asItem(),
                        QUARTZ_BRICK_SLAB.get().asItem(),
                        CHISELED_QUARTZ_SLAB.get().asItem(),
                        QUARTZ_PILLAR_SLAB.get().asItem());
        this.tag(SLABS_AND_FLUX_CRYSTAL)
                .addTag(ItemTags.SLABS)
                .add(AE2Blocks.FLUIX_CRYSTAL.get())
                .addTag(AE_SLABS);
        this.tag(ENTRO_CRYSTAL_REQUIRMENTS)
                .add(ENTRO_SEED.get(),
                        FLUIX_BLOCK.get().asItem());
    }


}
