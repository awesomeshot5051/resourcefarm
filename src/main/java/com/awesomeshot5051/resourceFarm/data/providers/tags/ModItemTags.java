package com.awesomeshot5051.resourceFarm.data.providers.tags;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

public class ModItemTags {
    public static final TagKey<Item> SLABS_AND_FLUX_CRYSTAL = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Main.MODID, "slabs_and_flux_crystal"));
    public static final TagKey<Item> AE_SLABS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Main.MODID, "ae_slabs"));
    public static final TagKey<Item> ENTRO_CRYSTAL_REQUIRMENTS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Main.MODID, "entro_crystal_requirments"));

}
