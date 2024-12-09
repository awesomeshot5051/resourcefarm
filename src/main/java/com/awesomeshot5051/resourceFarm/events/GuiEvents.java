package com.awesomeshot5051.resourceFarm.events;

import com.awesomeshot5051.resourceFarm.ClientConfig;
import com.awesomeshot5051.resourceFarm.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

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
//        if (!Main.SERVER_CONFIG.tradeCycling.get()) {
//            return;
//        }

        ClientConfig.CycleTradesButtonLocation loc = Main.CLIENT_CONFIG.cycleTradesButtonLocation.get();

        if (loc.equals(ClientConfig.CycleTradesButtonLocation.NONE)) {
            return;
        }

        int posX;

        switch (loc) {
            case TOP_LEFT:
            default:
                posX = merchantScreen.getGuiLeft() + 107;
                break;
            case TOP_RIGHT:
                posX = merchantScreen.getGuiLeft() + 250;
                break;
        }

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }
//        if (event.getKey() != Main.CYCLE_TRADES_KEY.getKey().getValue() || event.getAction() != 0) {
//            return;
//        }


        Screen currentScreen = mc.screen;

        if (!(currentScreen instanceof MerchantScreen screen)) {
            return;
        }

        mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1F));
    }

}
