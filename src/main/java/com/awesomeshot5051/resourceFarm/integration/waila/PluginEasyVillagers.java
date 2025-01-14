package com.awesomeshot5051.resourceFarm.integration.waila;

import com.awesomeshot5051.plantfarms.blocks.overworld.aboveGround.Trees.*;
import snownee.jade.api.*;

@WailaPlugin
public class PluginEasyVillagers implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(HUDHandlerVillager.INSTANCE, BirchFarmBlock.class);
        registration.registerBlockComponent(HUDHandlerVillager.INSTANCE, SpruceFarmBlock.class);

        registration.registerBlockIcon(HUDHandlerVillager.INSTANCE, BirchFarmBlock.class);
        registration.registerBlockIcon(HUDHandlerVillager.INSTANCE, SpruceFarmBlock.class);
    }

}
