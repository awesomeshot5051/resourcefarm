package com.awesomeshot5051.resourceFarm.data.providers.models;


import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.*;
import net.minecraft.data.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.*;

public class ModBlockModelProvider extends BaseBlockModelProvider {

    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModBlocks.BLOCK_REGISTER.getEntries().forEach(blockEntry -> {
            Block block = blockEntry.get();
            sideBottomTop(block.asItem());
        });
        if (Main.dynamic_installed) {
            ModBlocks.DYNAMIC_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                sideBottomTop(block.asItem());
            });
        }
        if (Main.terminals_installed) {
            ModBlocks.TERMINALS_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                sideBottomTop(block.asItem());
            });
        }
        if (Main.ae2_installed) {
            ModBlocks.AE2_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                sideBottomTop(block.asItem());
            });
        }
        if (Main.eae2_installed) {
            ModBlocks.EAE2_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                sideBottomTop(block.asItem());
            });
        }
    }
}