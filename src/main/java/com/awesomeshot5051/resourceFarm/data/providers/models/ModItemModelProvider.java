package com.awesomeshot5051.resourceFarm.data.providers.models;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

//        // Generate models for all farm items
        basicItemWithFarmTexture(ModItems.BASALT_FARM.get());
        basicItemWithFarmTexture(ModItems.BLACKSTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.CALCITE_FARM.get());
        basicItemWithFarmTexture(ModItems.COAL_FARM.get());
        basicItemWithFarmTexture(ModItems.COBBLESTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.CONCRETE_FARM.get());
        basicItemWithFarmTexture(ModItems.CONCRETE_POWDER_FARM.get());
        basicItemWithFarmTexture(ModItems.COPPER_FARM.get());
        basicItemWithFarmTexture(ModItems.DCOAL_FARM.get());
        basicItemWithFarmTexture(ModItems.DEEPSLATE_FARM.get());
        basicItemWithFarmTexture(ModItems.DCOPPER_FARM.get());
        basicItemWithFarmTexture(ModItems.DDIAMOND_FARM.get());
        basicItemWithFarmTexture(ModItems.DEMERALD_FARM.get());
        basicItemWithFarmTexture(ModItems.DGOLD_FARM.get());
        basicItemWithFarmTexture(ModItems.DIRON_FARM.get());
        basicItemWithFarmTexture(ModItems.DLAPIS_FARM.get());
        basicItemWithFarmTexture(ModItems.DREDSTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.DIAMOND_FARM.get());
        basicItemWithFarmTexture(ModItems.DIRT_FARM.get());
        basicItemWithFarmTexture(ModItems.EMERALD_FARM.get());
        basicItemWithFarmTexture(ModItems.ESTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.GLOWSTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.GOLD_FARM.get());
        basicItemWithFarmTexture(ModItems.GRASS_FARM.get());
        basicItemWithFarmTexture(ModItems.GRAVEL_FARM.get());
        basicItemWithFarmTexture(ModItems.IRON_FARM.get());
        basicItemWithFarmTexture(ModItems.LAPIS_FARM.get());
        basicItemWithFarmTexture(ModItems.NETHER_GOLD_FARM.get());
        basicItemWithFarmTexture(ModItems.NETHERITE_FARM.get());
        basicItemWithFarmTexture(ModItems.NETHER_QUARTZ_FARM.get());
        basicItemWithFarmTexture(ModItems.NETHERRACK_FARM.get());
        basicItemWithFarmTexture(ModItems.OBSIDIAN_FARM.get());
        basicItemWithFarmTexture(ModItems.PURPUR_FARM.get());
        basicItemWithFarmTexture(ModItems.RSAND_FARM.get());
        basicItemWithFarmTexture(ModItems.REDSTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.SAND_FARM.get());
        basicItemWithFarmTexture(ModItems.SSTONE_FARM.get());
        basicItemWithFarmTexture(ModItems.SNOW_FARM.get());
        basicItemWithFarmTexture(ModItems.SSOIL_FARM.get());
        basicItemWithFarmTexture(ModItems.SSAND_FARM.get());
        basicItemWithFarmTexture(ModItems.STONE_FARM.get());
        basicItemWithFarmTexture(ModItems.TERRACOTTA_FARM.get());
        basicItemWithFarmTexture(ModItems.TUFF_FARM.get());
        basicItemWithFarmTexture(ModItems.ANDESITE_FARM.get());
        basicItemWithFarmTexture(ModItems.GRANITE_FARM.get());
//basicItem()
        // Add more items as needed
    }

    // Helper method to generate the item models with the farm texture
    private void basicItemWithFarmTexture(Item item) {
        getBuilder(item.toString()) // Extract item name correctly
                .parent(getExistingFile(mcLoc("block/block"))) // Set the parent to "item/generated"
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 45, 0)
                .translation(0, 0, 0)
                .scale(0.625f, 0.625f, 0.625f)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .rotation(0, 0, 0)
                .translation(0, 3, 0)
                .scale(0.25f, 0.25f, 0.25f)
                .end()
                .transform(ItemDisplayContext.HEAD)
                .rotation(0, 180, 0)
                .translation(0, 0, 0)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0, 180, 0)
                .translation(0, 0, 0)
                .scale(0.5f, 0.5f, 0.5f)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75, 315, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f, 0.375f, 0.375f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 315, 0)
                .translation(0, 0, 0)
                .scale(0.4f, 0.4f, 0.4f)
                .end();
    }
}
