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
