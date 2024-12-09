package com.awesomeshot5051.resourceFarm.blocks.tileentity;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.CopperOreFarmRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModTileEntities {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MODID);

    @OnlyIn(Dist.CLIENT)
    public static void clientSetup() {
        if (!Main.CLIENT_CONFIG.renderBlockContents.get()) {
            return;
        }
        //passive mobs
        BlockEntityRenderers.register(ModTileEntities.COPPER_FARM.get(), CopperOreFarmRenderer::new);
//        BlockEntityRenderers.register(ModTileEntities.DCOPPER_FARM.get(), DeepslateCopperOreFarmRenderer::new);

    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        //passive mobs

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_FARM.get(), (object, context) -> object.getItemHandler());
//        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DCOPPER_FARM.get(), (object, context) -> object.getItemHandler());
    }

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITY_REGISTER.register(eventBus);
    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InventoryViewerTileentity>> INVENTORY_VIEWER = BLOCK_ENTITY_REGISTER.register("inventory_viewer", () ->
            BlockEntityType.Builder.of(InventoryViewerTileentity::new, ModBlocks.INVENTORY_VIEWER.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperOreFarmTileentity>> COPPER_FARM = BLOCK_ENTITY_REGISTER.register("copper_farm", () ->
            BlockEntityType.Builder.of(CopperOreFarmTileentity::new, ModBlocks.COPPER_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateCopperOreFarmTileentity>> DCOPPER_FARM = BLOCK_ENTITY_REGISTER.register("dcopper_farm", () ->
            BlockEntityType.Builder.of(DeepslateCopperOreFarmTileentity::new, ModBlocks.DCOPPER_FARM.get()).build(null)
    );


}
