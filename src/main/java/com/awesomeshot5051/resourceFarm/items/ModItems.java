package com.awesomeshot5051.resourceFarm.items;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.datacomponents.BlockEntityData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("ALL")
public class ModItems {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, Main.MODID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister<Item> TERMINAL_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister<Item> DYNAMIC_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister<Item> AE2_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister<Item> AAE2_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister<Item> EAE2_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, Main.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);
    public static final DeferredRegister.Items AEITEMS = DeferredRegister.createItems(Main.MODID);
    public static final DeferredRegister.Items EAEITEMS = DeferredRegister.createItems(Main.MODID);


    public static final DeferredHolder<Item, Item> ANDESITE_FARM = ITEM_REGISTER.register("andesite_farm", () -> ModBlocks.ANDESITE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CLAY_FARM = ITEM_REGISTER.register("clay_farm", () -> ModBlocks.CLAY_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> COPPER_FARM = ITEM_REGISTER.register("copper_farm", () -> ModBlocks.COPPER_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> COAL_FARM = ITEM_REGISTER.register("coal_farm", () -> ModBlocks.COAL_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DCOPPER_FARM = ITEM_REGISTER.register("dcopper_farm", () -> ModBlocks.DCOPPER_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DCOAL_FARM = ITEM_REGISTER.register("dcoal_farm", () -> ModBlocks.DCOAL_FARM.get().toItem());

    public static final DeferredHolder<Item, Item> IRON_FARM = ITEM_REGISTER.register("iron_farm", () -> ModBlocks.IRON_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> GOLD_FARM = ITEM_REGISTER.register("gold_farm", () -> ModBlocks.GOLD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DIAMOND_FARM = ITEM_REGISTER.register("diamond_farm", () -> ModBlocks.DIAMOND_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DIORITE_FARM = ITEM_REGISTER.register("diorite_farm", () -> ModBlocks.DIORITE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> EMERALD_FARM = ITEM_REGISTER.register("emerald_farm", () -> ModBlocks.EMERALD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> LAPIS_FARM = ITEM_REGISTER.register("lapis_farm", () -> ModBlocks.LAPIS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> REDSTONE_FARM = ITEM_REGISTER.register("redstone_farm", () -> ModBlocks.REDSTONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> GRANITE_FARM = ITEM_REGISTER.register("granite_farm", () -> ModBlocks.GRANITE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SAND_FARM = ITEM_REGISTER.register("sand_farm", () -> ModBlocks.SAND_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CONCRETE_POWDER_FARM = ITEM_REGISTER.register("cpowder_farm", () -> ModBlocks.CONCRETE_POWDER_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CONCRETE_FARM = ITEM_REGISTER.register("concrete_farm", () -> ModBlocks.CONCRETE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> COBBLESTONE_FARM = ITEM_REGISTER.register("cobblestone_farm", () -> ModBlocks.COBBLESTONE_FARM.get().toItem());

    public static final DeferredHolder<Item, Item> DIRON_FARM = ITEM_REGISTER.register("diron_farm", () -> ModBlocks.DIRON_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DGOLD_FARM = ITEM_REGISTER.register("dgold_farm", () -> ModBlocks.DGOLD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DDIAMOND_FARM = ITEM_REGISTER.register("ddiamond_farm", () -> ModBlocks.DDIAMOND_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DEMERALD_FARM = ITEM_REGISTER.register("demerald_farm", () -> ModBlocks.DEMERALD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DLAPIS_FARM = ITEM_REGISTER.register("dlapis_farm", () -> ModBlocks.DLAPIS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DREDSTONE_FARM = ITEM_REGISTER.register("dredstone_farm", () -> ModBlocks.DREDSTONE_FARM.get().toItem());

    public static final DeferredHolder<Item, Item> NETHER_QUARTZ_FARM = ITEM_REGISTER.register("nether_quartz_farm", () -> ModBlocks.NETHER_QUARTZ_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> NETHER_GOLD_FARM = ITEM_REGISTER.register("nether_gold_farm", () -> ModBlocks.NETHER_GOLD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> NETHERITE_FARM = ITEM_REGISTER.register("netherite_farm", () -> ModBlocks.NETHERITE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> ICON_ITEM = ITEM_REGISTER.register("icon_item",
            () -> new IconItem(new Item.Properties()));
    public static final DeferredHolder<Item, Item> BASALT_FARM = ITEM_REGISTER.register("basalt_farm", () -> ModBlocks.BASALT_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> BLACKSTONE_FARM = ITEM_REGISTER.register("blackstone_farm", () -> ModBlocks.BLACKSTONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CALCITE_FARM = ITEM_REGISTER.register("calcite_farm", () -> ModBlocks.CALCITE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DIRT_FARM = ITEM_REGISTER.register("dirt_farm", () -> ModBlocks.DIRT_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> DEEPSLATE_FARM = ITEM_REGISTER.register("deepslate_farm", () -> ModBlocks.DEEPSLATE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> ESTONE_FARM = ITEM_REGISTER.register("estone_farm", () -> ModBlocks.ESTONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> GLOWSTONE_FARM = ITEM_REGISTER.register("glowstone_farm", () -> ModBlocks.GLOWSTONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> GRASS_FARM = ITEM_REGISTER.register("grass_farm", () -> ModBlocks.GRASS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> GRAVEL_FARM = ITEM_REGISTER.register("gravel_farm", () -> ModBlocks.GRAVEL_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> NETHERRACK_FARM = ITEM_REGISTER.register("netherrack_farm", () -> ModBlocks.NETHERRACK_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> OBSIDIAN_FARM = ITEM_REGISTER.register("obsidian_farm", () -> ModBlocks.OBSIDIAN_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> PURPUR_FARM = ITEM_REGISTER.register("purpur_farm", () -> ModBlocks.PURPUR_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> MUD_FARM = ITEM_REGISTER.register("mud_farm", () -> ModBlocks.MUD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> RSAND_FARM = ITEM_REGISTER.register("rsand_farm", () -> ModBlocks.RSAND_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SNOW_FARM = ITEM_REGISTER.register("snow_farm", () -> ModBlocks.SNOW_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> STONE_FARM = ITEM_REGISTER.register("stone_farm", () -> ModBlocks.STONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SSTONE_FARM = ITEM_REGISTER.register("sstone_farm", () -> ModBlocks.SSTONE_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SSOIL_FARM = ITEM_REGISTER.register("ssoil_farm", () -> ModBlocks.SSOIL_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SSAND_FARM = ITEM_REGISTER.register("ssand_farm", () -> ModBlocks.SSAND_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> TERRACOTTA_FARM = ITEM_REGISTER.register("terracotta_farm", () -> ModBlocks.TERRACOTTA_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> TUFF_FARM = ITEM_REGISTER.register("tuff_farm", () -> ModBlocks.TUFF_FARM.get().toItem());

    public static final DeferredHolder<Item, Item> CCHORUS_FARM = DYNAMIC_REGISTER.register("cchorus_farm", () -> ModBlocks.CCHORUS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CMENRIL_FARM = DYNAMIC_REGISTER.register("cmenril_farm", () -> ModBlocks.CMENRIL_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> MBRICK_FARM = DYNAMIC_REGISTER.register("mbrick_farm", () -> ModBlocks.MBRICK_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CCBRICK_FARM = DYNAMIC_REGISTER.register("ccbrick_farm", () -> ModBlocks.CCBRICK_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> MGLASS_FARM = TERMINAL_REGISTER.register("mglass_farm", () -> ModBlocks.MGLASS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CCGLASS_FARM = TERMINAL_REGISTER.register("ccglass_farm", () -> ModBlocks.CCGLASS_FARM.get().toItem());


    // ----------------------------
// AE2 Item Registrations
// ----------------------------

    public static final DeferredHolder<Item, Item> FBQ_FARM = AE2_REGISTER.register("fbq_farm", () -> ModBlocks.FBQ_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> FLBQ_FARM = AE2_REGISTER.register("flbq_farm", () -> ModBlocks.FLBQ_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CCQC_FARM = AE2_REGISTER.register("ccqc_farm", () -> ModBlocks.CCQC_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> CQC_FARM = AE2_REGISTER.register("cqc_farm", () -> ModBlocks.CQC_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> FLCR_FARM = AE2_REGISTER.register("flcr_farm", () -> ModBlocks.FLCR_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> FLDU_FARM = AE2_REGISTER.register("fldu_farm", () -> ModBlocks.FLDU_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> QG_FARM = AE2_REGISTER.register("qg_farm", () -> ModBlocks.QG_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SSB_FARM = AE2_REGISTER.register("ssb_farm", () -> ModBlocks.SSB_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> MC_FARM = AE2_REGISTER.register("mc_farm", () -> ModBlocks.MC_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SIL_FARM = AE2_REGISTER.register("sil_farm", () -> ModBlocks.SIL_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SI_FARM = AE2_REGISTER.register("si_farm", () -> ModBlocks.SI_FARM.get().toItem());

// ----------------------------
// Advanced AE (AAE2) Item Registrations
// ----------------------------

    public static final DeferredHolder<Item, Item> SS_FARM = AAE2_REGISTER.register("ss_farm", () -> ModBlocks.SS_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> QA_FARM = AAE2_REGISTER.register("qa_farm", () -> ModBlocks.QA_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> QID_FARM = AAE2_REGISTER.register("qid_farm", () -> ModBlocks.QID_FARM.get().toItem());


// ----------------------------
// Extended AE (EAE2) Item Registrations
// ----------------------------

    public static final DeferredHolder<Item, Item> ENTC_FARM = EAE2_REGISTER.register("entc_farm", () -> ModBlocks.ENTC_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> ENTD_FARM = EAE2_REGISTER.register("entd_farm", () -> ModBlocks.ENTD_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> ENTB_FARM = EAE2_REGISTER.register("entb_farm", () -> ModBlocks.ENTB_FARM.get().toItem());
    public static final DeferredHolder<Item, Item> SIB_FARM = EAE2_REGISTER.register("sib_farm", () -> ModBlocks.SIB_FARM.get().toItem());


    //    public static final DeferredItem<Item> XP_UPGRADE = ITEMS.register("xp_upgrade_card",
//            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SMELTER_UPGRADE = ITEMS.register("smelter_upgrade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade",
            () -> new Item(new Item.Properties()));
    //    public static final DeferredItem<Item> FORTUNE_UPGRADE = ITEMS.register("fortune_upgrade",
//            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REDSTONE_UPGRADE = ITEMS.register("redstone_upgrade",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INSCRIBER_UPGRADE = ITEMS.register("inscriber_upgrade", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AE_CONDUIT = AEITEMS.register("ae_conduit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EAE_CONDUIT = EAEITEMS.register("eae_conduit", () -> new Item(new Item.Properties()));

    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockEntityData>> BLOCK_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("block_entity", () -> DataComponentType.<BlockEntityData>builder().networkSynchronized(BlockEntityData.STREAM_CODEC).build());

    public static void init(IEventBus eventBus) {
        ITEM_REGISTER.register(eventBus);
        DATA_COMPONENT_TYPE_REGISTER.register(eventBus);
        if (Main.dynamic_installed) {
            DYNAMIC_REGISTER.register(eventBus);
        }
        if (Main.terminals_installed) {
            if (!TERMINAL_REGISTER.getEntries().isEmpty())
                TERMINAL_REGISTER.register(eventBus);
        }
        if (Main.ae2_installed) {
            AE2_REGISTER.register(eventBus);
            AEITEMS.register(eventBus);
        }
        if (Main.eae2_installed) {
            EAE2_REGISTER.register(eventBus);
            EAEITEMS.register(eventBus);
        }
        if (Main.aae2_installed) {
            AAE2_REGISTER.register(eventBus);
        }
        REGISTRY.register(eventBus);
        ITEMS.register(eventBus);
    }

}
