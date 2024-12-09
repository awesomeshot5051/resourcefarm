package com.awesomeshot5051.resourceFarm.items;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.datacomponents.FarmBlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.PickTypeData;
import com.awesomeshot5051.resourceFarm.datacomponents.VillagerBlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.VillagerData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, Main.MODID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    //    public static final DeferredHolder<Item, Item> INCUBATOR = ITEM_REGISTER.register("incubator", () -> ModBlocks.INCUBATOR.get().toItem());
    public static final DeferredHolder<Item, Item> INVENTORY_VIEWER = ITEM_REGISTER.register("inventory_viewer", () -> ModBlocks.INVENTORY_VIEWER.get().toItem());
    public static final DeferredHolder<Item, Item> COPPER_FARM = ITEM_REGISTER.register("copper_farm", () -> ModBlocks.COPPER_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DCOPPER_FARM = ITEM_REGISTER.register("dcopper_farm", () -> ModBlocks.DCOPPER_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> ICON_ITEM = ITEM_REGISTER.register("icon_item",
            () -> new IconItem(new Item.Properties()));
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<VillagerData>> VILLAGER_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("villager", () -> DataComponentType.<VillagerData>builder().persistent(VillagerData.CODEC).networkSynchronized(VillagerData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PickTypeData>> PICK_TYPE = DATA_COMPONENT_TYPE_REGISTER.register("pick_type", () -> DataComponentType.<PickTypeData>builder().persistent(PickTypeData.CODEC).networkSynchronized(PickTypeData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<VillagerBlockEntityData>> BLOCK_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("block_entity", () -> DataComponentType.<VillagerBlockEntityData>builder().networkSynchronized(VillagerBlockEntityData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FarmBlockEntityData>> FARM_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("block_entity", () -> DataComponentType.<FarmBlockEntityData>builder().networkSynchronized(FarmBlockEntityData.STREAM_CODEC).build());

    public static void init(IEventBus eventBus) {
        ITEM_REGISTER.register(eventBus);
        DATA_COMPONENT_TYPE_REGISTER.register(eventBus);
        REGISTRY.register(eventBus);
    }

}
