package com.awesomeshot5051.resourceFarm;

import com.awesomeshot5051.corelib.CommonRegistry;
import com.awesomeshot5051.corelib.datacomponents.Upgrades;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.ModTileEntities;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.ModRecipes;
import com.awesomeshot5051.resourceFarm.datacomponents.ModDataComponents;
import com.awesomeshot5051.resourceFarm.events.BlockEvents;
import com.awesomeshot5051.resourceFarm.events.GuiEvents;
import com.awesomeshot5051.resourceFarm.gui.Containers;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.awesomeshot5051.resourceFarm.loottable.ModLootTables;
import com.awesomeshot5051.resourceFarm.sounds.ModSounds;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod(Main.MODID)
public class Main {


    public static final String MODID = "resource_farms";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ServerConfig SERVER_CONFIG;
    public static ClientConfig CLIENT_CONFIG;
    public static boolean dynamic_installed = ModList.get().isLoaded("integrateddynamics");
    public static boolean terminals_installed = ModList.get().isLoaded("integratedterminals");
    public static boolean ae2_installed = ModList.get().isLoaded("ae2");
    public static boolean eae2_installed = ModList.get().isLoaded("extendedae");
    public static boolean aae2_installed = ModList.get().isLoaded("advanced_ae");
    public static List<ItemStack> upgrades = new ArrayList<>();
    public static Map<ItemStack, Boolean> UPGRADES = new HashMap<>();
    public static NonNullList<ItemStack> Picktype;


    public Main(IEventBus eventBus) {
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(ModTileEntities::onRegisterCapabilities);


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
        ModBlocks.init(eventBus);
        ModItems.init(eventBus);

        ModTileEntities.init(eventBus);
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
