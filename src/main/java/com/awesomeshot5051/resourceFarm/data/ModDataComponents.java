package com.awesomeshot5051.resourceFarm.data;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.component.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class ModDataComponents {
    // Define the ResourceKey for the DataComponentType registry
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final Supplier<DataComponentType<ItemContainerContents>> PICK_TYPE = REGISTRAR.registerComponentType(
            "pick_type",
            builder -> builder
                    // The codec to read/write the data to disk
                    .persistent(ItemContainerContents.CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> PICK_TYPE = register("pick_type",
//            builder -> builder.persistent(ItemContainerContents.CODEC));
//    public static

//    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
//                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
//        return REGISTRAR.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
//    }

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}