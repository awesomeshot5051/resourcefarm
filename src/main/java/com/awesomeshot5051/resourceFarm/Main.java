package com.awesomeshot5051.resourceFarm;

import com.awesomeshot5051.corelib.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.events.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.integration.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.awesomeshot5051.resourceFarm.loottable.*;
import com.awesomeshot5051.resourceFarm.sounds.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
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
    public static boolean terminals_installed = ModList.get().isLoaded("integratedterminals");
    public static boolean ae2_installed = ModList.get().isLoaded("ae2");
    public static boolean eae2_installed = ModList.get().isLoaded("extendedae");
    public static boolean aae2_installed = ModList.get().isLoaded("advanced_ae");
    public static List<ItemStack> upgrades = new ArrayList<>();
    public static Map<ItemStack, Boolean> UPGRADES = new HashMap<>();
    public static NonNullList<ItemStack> Picktype;


    public Main(IEventBus eventBus) {
        // 🔧 Register configs FIRST
        SERVER_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.SERVER, ServerConfig.class);
        CLIENT_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.CLIENT, ClientConfig.class);

        // 🎧 Add main event listeners
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(ModTileEntities::onRegisterCapabilities);
        eventBus.addListener(IMC::enqueueIMC);

        // 📦 Register core content
        ModDataComponents.register(eventBus); // Data components need to come early
        ModBlocks.init(eventBus);
        ModItems.init(eventBus);
        ModTileEntities.init(eventBus); // Register tile entities after blocks and items

        // 🎨 Register non-core systems
        ModSounds.register(eventBus);
        ModLootTables.init(eventBus);
        ModRecipes.registerTypes(eventBus); // Types before recipes
        ModRecipes.registerRecipes(eventBus);
        ModCreativeTabs.init(eventBus);
        Containers.init(eventBus);

        // 🖥️ Client-only code
        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(Main.this::clientSetup);
            Containers.initClient(eventBus); // Must match order with init
        }
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
