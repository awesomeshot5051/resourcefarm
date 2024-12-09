package com.awesomeshot5051.resourceFarm.gui;


import com.awesomeshot5051.resourceFarm.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Containers {

    private static final DeferredRegister<MenuType<?>> MENU_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.MENU, Main.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<OutputContainer>> OUTPUT_CONTAINER = MENU_TYPE_REGISTER.register("output", () ->
            IMenuTypeExtension.create((windowId, inv, data) -> new OutputContainer(windowId, inv))
    );
    public static final DeferredHolder<MenuType<?>, MenuType<InventoryViewerContainer>> INVENTORY_VIEWER_CONTAINER = MENU_TYPE_REGISTER.register("inventory_viewer", () ->
            IMenuTypeExtension.create((windowId, inv, data) -> new InventoryViewerContainer(windowId, inv, data.readBlockPos()))
    );
//    public static final DeferredHolder<MenuType<?>, MenuType<ConverterContainer>> CONVERTER_CONTAINER = MENU_TYPE_REGISTER.register("converter", () ->
//            IMenuTypeExtension.create((windowId, inv, data) -> new ConverterContainer(windowId, inv))
//    );

    public static void init(IEventBus eventBus) {
        MENU_TYPE_REGISTER.register(eventBus);
    }

    public static void initClient(IEventBus eventBus) {
        eventBus.addListener(Containers::onRegisterScreens);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRegisterScreens(RegisterMenuScreensEvent containers) {
        containers.register(OUTPUT_CONTAINER.get(), OutputScreen::new);
        containers.register(INVENTORY_VIEWER_CONTAINER.get(), InventoryViewerScreen::new);
//        containers.register(CONVERTER_CONTAINER.get(), ConverterScreen::new);
    }

}
