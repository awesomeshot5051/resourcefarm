package com.awesomeshot5051.resourceFarm.integration.waila;

import com.awesomeshot5051.resourceFarm.blocks.*;
import snownee.jade.api.*;

@WailaPlugin
public class PluginFarms implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        for (var sidedBlock : ModBlocks.BLOCK_REGISTER.getEntries()) {
            registration.registerBlockComponent(HUDHandlerFarms.INSTANCE, sidedBlock.get().getClass());
        }
        for (var sidedBlock : ModBlocks.BLOCK_REGISTER.getEntries()) {
            registration.registerBlockIcon(HUDHandlerFarms.INSTANCE, sidedBlock.get().getClass());
        }
    }

}
