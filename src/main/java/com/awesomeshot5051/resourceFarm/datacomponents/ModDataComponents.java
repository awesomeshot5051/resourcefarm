package com.awesomeshot5051.resourceFarm.datacomponents;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.component.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredRegister.DataComponents AE2_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredRegister.DataComponents EAE2_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final Supplier<DataComponentType<ItemContainerContents>> PICK_TYPE = REGISTRAR.registerComponentType(
            "pick_type",
            builder -> builder

                    .persistent(ItemContainerContents.CODEC)

                    .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
    public static Supplier<DataComponentType<ItemContainerContents>> UPGRADE = REGISTRAR.registerComponentType(
            "upgrade",
            builder ->
                    builder.persistent(ItemContainerContents.CODEC)
                            .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
    public static Supplier<DataComponentType<ItemContainerContents>> AE2ITEMS = AE2_REGISTRAR.registerComponentType(
            "ae2_items",
            builder ->
                    builder.persistent(ItemContainerContents.CODEC)
                            .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
    public static Supplier<DataComponentType<ItemContainerContents>> EAE2ITEMS = EAE2_REGISTRAR.registerComponentType(
            "eae2_items",
            builder ->
                    builder.persistent(ItemContainerContents.CODEC)
                            .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
        if (Main.ae2_installed) {
            AE2_REGISTRAR.register(eventBus);
            if (Main.eae2_installed) {
                EAE2_REGISTRAR.register(eventBus);
            }
        }

    }
}