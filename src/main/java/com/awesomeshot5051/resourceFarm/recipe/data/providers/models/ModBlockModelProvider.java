package com.awesomeshot5051.resourceFarm.recipe.data.providers.models;


import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;

public class ModBlockModelProvider extends BaseBlockModelProvider {

    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        sideBottomTop(ModItems.BASALT_FARM.get());
        sideBottomTop(ModItems.BLACKSTONE_FARM.get());
        sideBottomTop(ModItems.CALCITE_FARM.get());
        sideBottomTop(ModItems.COAL_FARM.get());
        sideBottomTop(ModItems.COBBLESTONE_FARM.get());
        sideBottomTop(ModItems.CONCRETE_FARM.get());
        sideBottomTop(ModItems.CONCRETE_POWDER_FARM.get());
        sideBottomTop(ModItems.COPPER_FARM.get());
        sideBottomTop(ModItems.DCOAL_FARM.get());
        sideBottomTop(ModItems.DEEPSLATE_FARM.get());
        sideBottomTop(ModItems.DCOPPER_FARM.get());
        sideBottomTop(ModItems.DDIAMOND_FARM.get());
        sideBottomTop(ModItems.DEMERALD_FARM.get());
        sideBottomTop(ModItems.DGOLD_FARM.get());
        sideBottomTop(ModItems.DIRON_FARM.get());
        sideBottomTop(ModItems.DLAPIS_FARM.get());
        sideBottomTop(ModItems.DREDSTONE_FARM.get());
        sideBottomTop(ModItems.DIAMOND_FARM.get());
        sideBottomTop(ModItems.DIRT_FARM.get());
        sideBottomTop(ModItems.EMERALD_FARM.get());
        sideBottomTop(ModItems.ESTONE_FARM.get());
        sideBottomTop(ModItems.GLOWSTONE_FARM.get());
        sideBottomTop(ModItems.GOLD_FARM.get());
        sideBottomTop(ModItems.GRASS_FARM.get());
        sideBottomTop(ModItems.GRAVEL_FARM.get());
        sideBottomTop(ModItems.IRON_FARM.get());
        sideBottomTop(ModItems.LAPIS_FARM.get());
        sideBottomTop(ModItems.NETHER_GOLD_FARM.get());
        sideBottomTop(ModItems.NETHERITE_FARM.get());
        sideBottomTop(ModItems.NETHER_QUARTZ_FARM.get());
        sideBottomTop(ModItems.NETHERRACK_FARM.get());
        sideBottomTop(ModItems.OBSIDIAN_FARM.get());
        sideBottomTop(ModItems.PURPUR_FARM.get());
        sideBottomTop(ModItems.RSAND_FARM.get());
        sideBottomTop(ModItems.REDSTONE_FARM.get());
        sideBottomTop(ModItems.SAND_FARM.get());
        sideBottomTop(ModItems.SSTONE_FARM.get());
        sideBottomTop(ModItems.SNOW_FARM.get());
        sideBottomTop(ModItems.SSOIL_FARM.get());
        sideBottomTop(ModItems.SSAND_FARM.get());
        sideBottomTop(ModItems.STONE_FARM.get());
        sideBottomTop(ModItems.TERRACOTTA_FARM.get());
        sideBottomTop(ModItems.TUFF_FARM.get());
        sideBottomTop(ModItems.GRANITE_FARM.get());
        sideBottomTop(ModItems.ANDESITE_FARM.get());
    }
}