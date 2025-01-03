package com.awesomeshot5051.resourceFarm;

//import com.awesomeshot5051.resourceFarm.advancements.resourceFarmTriggerInstance;

import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.data.*;
import com.awesomeshot5051.resourceFarm.data.providers.recipe.recipe.*;
import com.awesomeshot5051.resourceFarm.events.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.*;
import com.awesomeshot5051.resourceFarm.loottable.*;
import com.awesomeshot5051.resourceFarm.sounds.*;
import de.maxhenkel.corelib.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.fml.config.*;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.fml.loading.*;
import net.neoforged.neoforge.common.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.*;

@Mod(Main.MODID)
public class Main {


    public static final String MODID = "resource_farms";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ServerConfig SERVER_CONFIG;
    public static ClientConfig CLIENT_CONFIG;
//    public static KeyMapping PICKUP_KEY;
//    public static KeyMapping CYCLE_TRADES_KEY;

    public Main(IEventBus eventBus) {
        eventBus.addListener(this::commonSetup);
//        eventBus.addListener(this::onRegisterPayloadHandler);
//        MobFarmsTriggerInstance.resourceFarmTrigger.TRIGGER_TYPES.register(eventBus);
        eventBus.addListener(ModTileEntities::onRegisterCapabilities);

        ModBlocks.init(eventBus);
        ModItems.init(eventBus);
        ModTileEntities.init(eventBus);
        Containers.init(eventBus);
        ModCreativeTabs.init(eventBus);
        ModLootTables.init(eventBus);
//        eventBus.addListener(DataGenerators::gatherData);
        ModSounds.register(eventBus);
        ModRecipes.registerRecipes(eventBus);
        ModRecipes.registerTypes(eventBus);
        SERVER_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.SERVER, ServerConfig.class);
        CLIENT_CONFIG = CommonRegistry.registerConfig(MODID, ModConfig.Type.CLIENT, ClientConfig.class);
        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(Main.this::clientSetup);
//            eventBus.addListener(Main.this::onRegisterClientExtensions);
            Containers.initClient(eventBus);
        }
        ModDataComponents.register(eventBus);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
//        NeoForge.EVENT_BUS.register(new ModEvents());
//        NeoForge.EVENT_BUS.register(new BlockEvents());
        // Register your custom dispenser behavior for dye items
    }


    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent event) {
        ModTileEntities.clientSetup();
//
        NeoForge.EVENT_BUS.register(new ModSoundEvents());
        NeoForge.EVENT_BUS.register(new GuiEvents());
    }

//    public void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
//        PayloadRegistrar registrar = event.registrar(MODID).versioned("0");
//        CommonRegistry.registerMessage(registrar, MessageVillagerParticles.class);
//        CommonRegistry.registerMessage(registrar, MessagePickUpVillager.class);
//        CommonRegistry.registerMessage(registrar, MessageSelectTrade.class);
//        CommonRegistry.registerMessage(registrar, MessageCycleTrades.class);
//    }

//    @OnlyIn(Dist.CLIENT)
//    public void onRegisterKeyBinds(RegisterKeyMappingsEvent event) {
//        PICKUP_KEY = new KeyMapping("key.mob_farms.pick_up", GLFW.GLFW_KEY_V, "category.mob_farms");
//        CYCLE_TRADES_KEY = new KeyMapping("key.mob_farms.cycle_trades", GLFW.GLFW_KEY_C, "category.mob_farms");
//        event.register(PICKUP_KEY);
//        event.register(CYCLE_TRADES_KEY);
//    }

}
