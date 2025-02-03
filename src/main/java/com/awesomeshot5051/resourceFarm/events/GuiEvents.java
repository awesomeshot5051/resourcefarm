package com.awesomeshot5051.resourceFarm.events;

import net.minecraft.client.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.client.resources.sounds.*;
import net.minecraft.sounds.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.client.event.*;

@SuppressWarnings("ALL")
public class GuiEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onOpenScreen(ScreenEvent.Init.Post event) {
        if (!(event.getScreen() instanceof MerchantScreen merchantScreen)) {
            return;
        }
        if (Minecraft.getInstance().player == null) {
            return;
        }

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }


        Screen currentScreen = mc.screen;

        if (!(currentScreen instanceof MerchantScreen screen)) {
            return;
        }

        mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1F));
    }

}
