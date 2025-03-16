package com.awesomeshot5051.resourceFarm;

import com.awesomeshot5051.corelib.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.events.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.awesomeshot5051.resourceFarm.loottable.*;
import com.awesomeshot5051.resourceFarm.sounds.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.*;
import net.neoforged.fml.common.*;
import net.neoforged.fml.config.*;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.fml.loading.*;
import net.neoforged.neoforge.common.*;
import org.apache.logging.log4j.*;

import java.util.*;

@Mod(Main.MODID)
public class Main {


    public static final String MODID = "resource_farms";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ServerConfig SERVER_CONFIG;
    public static ClientConfig CLIENT_CONFIG;
    public static boolean dynamic_installed = ModList.get().isLoaded("integrateddynamics");
    public static boolean tunnels_installed = ModList.get().isLoaded("integratedtunnels");
    public static boolean terminals_installed = ModList.get().isLoaded("integratedterminals");
    public static List<ItemStack> upgrades = new ArrayList<>();
    public static Map<ItemStack, Boolean> UPGRADES = new HashMap<>();
    public static NonNullList<ItemStack> Picktype;
    public static Block CRYSTAL_CHORUS_BLOCK; //farm
    public static Block CRYSTAL_MENRIL_BlOCK; //farm
    public static Block MENRIL_BRICK; //farm
    public static Block CRYSTAL_CHORUS_BRICK; //farm
    public static Item CHORUS_CHUNK;
    public static Item MENRIL_CHUNK;
    public static Item PROTO_CHORUS;
    public static Block MENRIL_GLASS; //farm
    public static Block CHORUS_GLASS; //farm
    public static BlockState MENRIL_GROWN;

    static {
        if (Main.dynamic_installed) {
//            ResourceLocation menril_sapling_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "menril_sapling");
//            Block MENRIL_SAPLING = BuiltInRegistries.BLOCK.get(menril_sapling_rl);
//            MENRIL_GROWN = MENRIL_SAPLING.withPropertiesOf(MENRIL_SAPLING.getStateDefinition().getPossibleStates().get(1));
            ResourceLocation crystal_chorus_chunk_block = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_block");
            CRYSTAL_CHORUS_BLOCK = BuiltInRegistries.BLOCK.get(crystal_chorus_chunk_block);
            ResourceLocation crystal_menril_chunk_block = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_block");
            CRYSTAL_MENRIL_BlOCK = BuiltInRegistries.BLOCK.get(crystal_menril_chunk_block);
            ResourceLocation crystal_chorus_chunk = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_chunk");
            CHORUS_CHUNK = BuiltInRegistries.ITEM.get(crystal_chorus_chunk);
            ResourceLocation crystal_menril_chunk = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_chunk");
            MENRIL_CHUNK = BuiltInRegistries.ITEM.get(crystal_menril_chunk);
            ResourceLocation menril_brick_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_menril_brick");
            MENRIL_BRICK = BuiltInRegistries.BLOCK.get(menril_brick_rl);
            ResourceLocation crystal_chorus_brick_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "crystalized_chorus_brick");
            CRYSTAL_CHORUS_BRICK = BuiltInRegistries.BLOCK.get(crystal_chorus_brick_rl);
            ResourceLocation proto_chorus_rl = ResourceLocation.fromNamespaceAndPath("integrateddynamics", "proto_chorus");
            PROTO_CHORUS = BuiltInRegistries.ITEM.get(proto_chorus_rl);
            if (Main.terminals_installed) {
                ResourceLocation menril_glass = ResourceLocation.fromNamespaceAndPath("integratedterminals", "menril_glass");
                ResourceLocation chorus_glass = ResourceLocation.fromNamespaceAndPath("integratedterminals", "chorus_glass");
                CHORUS_GLASS = BuiltInRegistries.BLOCK.get(chorus_glass);
                MENRIL_GLASS = BuiltInRegistries.BLOCK.get(menril_glass);
            }

        }
    }
    public Main(IEventBus eventBus) {
        eventBus.addListener(this::commonSetup);

        eventBus.addListener(ModTileEntities::onRegisterCapabilities);

        ModBlocks.init(eventBus);
        ModItems.init(eventBus);

        ModTileEntities.init(eventBus);
        Containers.init(eventBus);
        ModCreativeTabs.init(eventBus);
        ModLootTables.init(eventBus);

        ModSounds.register(eventBus);
        ModRecipes.registerRecipes(eventBus);
        ModRecipes.registerTypes(eventBus);
        SERVER_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.SERVER, ServerConfig.class);
        CLIENT_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.CLIENT, ClientConfig.class);
        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(Main.this::clientSetup);

            Containers.initClient(eventBus);
        }
        ModDataComponents.register(eventBus);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(new BlockEvents());
        event.enqueueWork(() -> {
            for (var sidedBlock : ModItems.ITEMS.getEntries()) {
                upgrades.add(sidedBlock.get().getDefaultInstance());
            }
            Main.UPGRADES = Upgrades.createUpgradesMap(upgrades);
        });

    }

    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent event) {
        ModTileEntities.clientSetup();
        NeoForge.EVENT_BUS.register(new GuiEvents());
    }
}
