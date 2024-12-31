package com.awesomeshot5051.resourceFarm.items;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

public class ModItems {

    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, Main.MODID);
    private static final DeferredRegister.Items ITEM_REGISTER = DeferredRegister.createItems(Main.MODID);
    //    public static final DeferredHolder<Item, BlockItem> INCUBATOR = ITEM_REGISTER.registerSimpleBlockItem("incubator", () -> ModBlocks.INCUBATOR);

    public static final DeferredHolder<Item, BlockItem> ZOMBIE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.IRON_FARM);
    public static final DeferredHolder<Item, BlockItem> INVENTORY_VIEWER = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.INVENTORY_VIEWER);
    public static final DeferredHolder<Item, BlockItem> ANDESITE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.ANDESITE_FARM);
    public static final DeferredHolder<Item, BlockItem> COPPER_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.COPPER_FARM);
    public static final DeferredHolder<Item, BlockItem> COAL_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.COAL_FARM);
    public static final DeferredHolder<Item, BlockItem> DCOPPER_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DCOPPER_FARM);
    public static final DeferredHolder<Item, BlockItem> DCOAL_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DCOAL_FARM);
    // Regular ores
    public static final DeferredHolder<Item, BlockItem> IRON_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.IRON_FARM);
    public static final DeferredHolder<Item, BlockItem> GOLD_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.GOLD_FARM);
    public static final DeferredHolder<Item, BlockItem> DIAMOND_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DIAMOND_FARM);
    public static final DeferredHolder<Item, BlockItem> EMERALD_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.EMERALD_FARM);
    public static final DeferredHolder<Item, BlockItem> LAPIS_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.LAPIS_FARM);
    public static final DeferredHolder<Item, BlockItem> REDSTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.REDSTONE_FARM);

    public static final DeferredHolder<Item, BlockItem> GRANITE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.GRANITE_FARM);

    public static final DeferredHolder<Item, BlockItem> SAND_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.SAND_FARM);
    public static final DeferredHolder<Item, BlockItem> CONCRETE_POWDER_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.CONCRETE_POWDER_FARM);
    public static final DeferredHolder<Item, BlockItem> CONCRETE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.CONCRETE_FARM);
    public static final DeferredHolder<Item, BlockItem> COBBLESTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.COBBLESTONE_FARM);


    // Deepslate variants
    public static final DeferredHolder<Item, BlockItem> DIRON_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DIRON_FARM);
    public static final DeferredHolder<Item, BlockItem> DGOLD_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DGOLD_FARM);
    public static final DeferredHolder<Item, BlockItem> DDIAMOND_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DDIAMOND_FARM);
    public static final DeferredHolder<Item, BlockItem> DEMERALD_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DEMERALD_FARM);
    public static final DeferredHolder<Item, BlockItem> DLAPIS_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DLAPIS_FARM);
    public static final DeferredHolder<Item, BlockItem> DREDSTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DREDSTONE_FARM);


    // Nether variants
    public static final DeferredHolder<Item, BlockItem> NETHER_QUARTZ_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.NETHER_QUARTZ_FARM);
    public static final DeferredHolder<Item, BlockItem> NETHER_GOLD_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.NETHER_GOLD_FARM);
    public static final DeferredHolder<Item, BlockItem> NETHERITE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.NETHERITE_FARM);
    public static final DeferredHolder<Item, BlockItem> BASALT_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.BASALT_FARM);
    public static final DeferredHolder<Item, BlockItem> BLACKSTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.BLACKSTONE_FARM);
    public static final DeferredHolder<Item, BlockItem> CALCITE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.CALCITE_FARM);
    public static final DeferredHolder<Item, BlockItem> DIRT_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DIRT_FARM);
    public static final DeferredHolder<Item, BlockItem> DEEPSLATE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.DEEPSLATE_FARM);
    public static final DeferredHolder<Item, BlockItem> ESTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.ESTONE_FARM);
    public static final DeferredHolder<Item, BlockItem> GLOWSTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.GLOWSTONE_FARM);
    public static final DeferredHolder<Item, BlockItem> GRASS_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.GRASS_FARM);
    public static final DeferredHolder<Item, BlockItem> GRAVEL_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.GRAVEL_FARM);
    public static final DeferredHolder<Item, BlockItem> NETHERRACK_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.NETHERRACK_FARM);
    public static final DeferredHolder<Item, BlockItem> OBSIDIAN_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.OBSIDIAN_FARM);
    public static final DeferredHolder<Item, BlockItem> PURPUR_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.PURPUR_FARM);
    public static final DeferredHolder<Item, BlockItem> RSAND_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.RSAND_FARM);
    public static final DeferredHolder<Item, BlockItem> SNOW_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.SNOW_FARM);
    public static final DeferredHolder<Item, BlockItem> STONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.STONE_FARM);
    public static final DeferredHolder<Item, BlockItem> SSTONE_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.SSTONE_FARM);
    public static final DeferredHolder<Item, BlockItem> SSOIL_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.SSOIL_FARM);
    public static final DeferredHolder<Item, BlockItem> SSAND_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.SSAND_FARM);
    public static final DeferredHolder<Item, BlockItem> TERRACOTTA_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.TERRACOTTA_FARM);
    public static final DeferredHolder<Item, BlockItem> TUFF_FARM = ITEM_REGISTER.registerSimpleBlockItem(ModBlocks.TUFF_FARM);
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<VillagerData>> VILLAGER_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("villager", () -> DataComponentType.<VillagerData>builder().persistent(VillagerData.CODEC).networkSynchronized(VillagerData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PickTypeData>> PICK_TYPE = DATA_COMPONENT_TYPE_REGISTER.register("pick_type", () -> DataComponentType.<PickTypeData>builder().persistent(PickTypeData.CODEC).networkSynchronized(PickTypeData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<VillagerBlockEntityData>> BLOCK_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("block_entity", () -> DataComponentType.<VillagerBlockEntityData>builder().networkSynchronized(VillagerBlockEntityData.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FarmBlockEntityData>> FARM_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("farm_entity", () -> DataComponentType.<FarmBlockEntityData>builder().networkSynchronized(FarmBlockEntityData.STREAM_CODEC).build());

    public static void init(IEventBus eventBus) {
        ITEM_REGISTER.register(eventBus);
        DATA_COMPONENT_TYPE_REGISTER.register(eventBus);
        REGISTRY.register(eventBus);
    }

}
