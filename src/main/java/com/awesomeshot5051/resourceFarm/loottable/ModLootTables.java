package com.awesomeshot5051.resourceFarm.loottable;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

public class ModLootTables {

    private static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTION_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Main.MODID);
    public static DeferredHolder<LootItemFunctionType<?>, LootItemFunctionType<CopyBlockEntityData>> COPY_BLOCK_ENTITY = LOOT_FUNCTION_TYPE_REGISTER.register("copy_block_entity", () -> new LootItemFunctionType<>(CopyBlockEntityData.CODEC));

    public static void init(IEventBus eventBus) {
        LOOT_FUNCTION_TYPE_REGISTER.register(eventBus);
    }

}

