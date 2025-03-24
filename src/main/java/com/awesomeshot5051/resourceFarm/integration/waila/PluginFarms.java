package com.awesomeshot5051.resourceFarm.integration.waila;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import snownee.jade.api.*;

@WailaPlugin
public class PluginFarms implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // Always register for base blocks
        for (var sidedBlock : ModBlocks.BLOCK_REGISTER.getEntries()) {
            registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, sidedBlock.get().getClass());
            registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, sidedBlock.get().getClass());
        }

        // Only register for Dynamic blocks if the mod is installed
        if (Main.dynamic_installed) {
            for (var dynamicBlock : ModBlocks.DYNAMIC_REGISTER.getEntries()) {
                registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, dynamicBlock.get().getClass());
                registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, dynamicBlock.get().getClass());
            }
        }

        // Only register for Terminals blocks if the mod is installed
        if (Main.terminals_installed) {
            for (var terminalsBlock : ModBlocks.TERMINALS_REGISTER.getEntries()) {
                registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, terminalsBlock.get().getClass());
                registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, terminalsBlock.get().getClass());
            }
        }

        // Only register for AE2 blocks if the mod is installed
        if (Main.ae2_installed) {
            for (var ae2Block : ModBlocks.AE2_REGISTER.getEntries()) {
                registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, ae2Block.get().getClass());
                registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, ae2Block.get().getClass());
            }
        }

        // Only register for Extended AE blocks if the mod is installed
        if (Main.eae2_installed) {
            for (var eae2Block : ModBlocks.EAE2_REGISTER.getEntries()) {
                registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, eae2Block.get().getClass());
                registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, eae2Block.get().getClass());
            }
        }
    }


}
