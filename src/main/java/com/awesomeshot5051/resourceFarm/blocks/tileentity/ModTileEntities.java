package com.awesomeshot5051.resourceFarm.blocks.tileentity;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.common.regular.NetherGoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.common.regular.NetherQuartzOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.veryrare.regular.NetheriteOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateGoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateIronOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.GoldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.IronOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.deepslate.DeepslateDiamondOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.deepslate.DeepslateLapisOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.regular.DiamondOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.regular.LapisOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.deepslate.DeepslateRedstoneOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.regular.RedstoneOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.deepslate.DeepslateEmeraldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.regular.EmeraldOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.CobblestoneFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.ConcreteFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.ConcretePowderFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.SandFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.common.regular.NetherQuartzOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.veryrare.regular.NetheriteOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateCoalOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateCopperOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateGoldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.DeepslateIronOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.CoalOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.CopperOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.GoldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.IronOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.deepslate.DeepslateDiamondOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.deepslate.DeepslateLapisOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.regular.DiamondOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.regular.LapisOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.deepslate.DeepslateRedstoneOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.regular.RedstoneOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.deepslate.DeepslateEmeraldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.regular.EmeraldOreFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.CobblestoneFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.ConcreteFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.ConcretePowderFarmRenderer;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.SandFarmRenderer;
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
        BlockEntityRenderers.register(ModTileEntities.DCOPPER_FARM.get(), DeepslateCopperOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DCOAL_FARM.get(), DeepslateCoalOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.COAL_FARM.get(), CoalOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DIRON_FARM.get(), DeepslateIronOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DGOLD_FARM.get(), DeepslateGoldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DDIAMOND_FARM.get(), DeepslateDiamondOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DEMERALD_FARM.get(), DeepslateEmeraldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DLAPIS_FARM.get(), DeepslateLapisOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DREDSTONE_FARM.get(), DeepslateRedstoneOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.IRON_FARM.get(), IronOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GOLD_FARM.get(), GoldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DIAMOND_FARM.get(), DiamondOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.EMERALD_FARM.get(), EmeraldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.LAPIS_FARM.get(), LapisOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.REDSTONE_FARM.get(), RedstoneOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SAND_FARM.get(), SandFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CONCRETE_POWDER_FARM.get(), ConcretePowderFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CONCRETE_FARM.get(), ConcreteFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.COBBLESTONE_FARM.get(), CobblestoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.NETHERITE_FARM.get(), NetheriteOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.NETHER_QUARTZ_FARM.get(), NetherQuartzOreFarmRenderer::new);


    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        //passive mobs

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DCOPPER_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DCOAL_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COAL_FARM.get(), (object, context) -> object.getItemHandler());
        // Regular ores
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IRON_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SAND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIAMOND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EMERALD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LAPIS_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, REDSTONE_FARM.get(), (object, context) -> object.getItemHandler());

// Deepslate variants
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIRON_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DGOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DDIAMOND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DEMERALD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DLAPIS_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DREDSTONE_FARM.get(), (object, context) -> object.getItemHandler());


        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCRETE_POWDER_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCRETE_FARM.get(), (object, context) -> object.getItemHandler());


        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COBBLESTONE_FARM.get(), (object, context) -> object.getItemHandler());
// Nether variants
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHER_QUARTZ_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHER_GOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHERITE_FARM.get(), (object, context) -> object.getItemHandler());
    }

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITY_REGISTER.register(eventBus);
    }


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcreteFarmTileentity>> CONCRETE_FARM = BLOCK_ENTITY_REGISTER.register("concrete_farm", () ->
            BlockEntityType.Builder.of(ConcreteFarmTileentity::new, ModBlocks.CONCRETE_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CobblestoneFarmTileentity>> COBBLESTONE_FARM = BLOCK_ENTITY_REGISTER.register("cobblestone_farm", () ->
            BlockEntityType.Builder.of(CobblestoneFarmTileentity::new, ModBlocks.COBBLESTONE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateGoldOreFarmTileentity>> DGOLD_FARM = BLOCK_ENTITY_REGISTER.register("dgold_farm", () ->
            BlockEntityType.Builder.of(DeepslateGoldOreFarmTileentity::new, ModBlocks.DGOLD_FARM.get()).build(null)
    );    // Regular ores


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IronOreFarmTileentity>> IRON_FARM = BLOCK_ENTITY_REGISTER.register("iron_farm", () ->
            BlockEntityType.Builder.of(IronOreFarmTileentity::new, ModBlocks.IRON_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SandFarmTileentity>> SAND_FARM = BLOCK_ENTITY_REGISTER.register("sand_farm", () ->
            BlockEntityType.Builder.of(SandFarmTileentity::new, ModBlocks.SAND_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcretePowderFarmTileentity>> CONCRETE_POWDER_FARM = BLOCK_ENTITY_REGISTER.register("cpowder_farm", () ->
            BlockEntityType.Builder.of(ConcretePowderFarmTileentity::new, ModBlocks.CONCRETE_POWDER_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GoldOreFarmTileentity>> GOLD_FARM = BLOCK_ENTITY_REGISTER.register("gold_farm", () ->
            BlockEntityType.Builder.of(GoldOreFarmTileentity::new, ModBlocks.GOLD_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DiamondOreFarmTileentity>> DIAMOND_FARM = BLOCK_ENTITY_REGISTER.register("diamond_farm", () ->
            BlockEntityType.Builder.of(DiamondOreFarmTileentity::new, ModBlocks.DIAMOND_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EmeraldOreFarmTileentity>> EMERALD_FARM = BLOCK_ENTITY_REGISTER.register("emerald_farm", () ->
            BlockEntityType.Builder.of(EmeraldOreFarmTileentity::new, ModBlocks.EMERALD_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LapisOreFarmTileentity>> LAPIS_FARM = BLOCK_ENTITY_REGISTER.register("lapis_farm", () ->
            BlockEntityType.Builder.of(LapisOreFarmTileentity::new, ModBlocks.LAPIS_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedstoneOreFarmTileentity>> REDSTONE_FARM = BLOCK_ENTITY_REGISTER.register("redstone_farm", () ->
            BlockEntityType.Builder.of(RedstoneOreFarmTileentity::new, ModBlocks.REDSTONE_FARM.get()).build(null)
    );
    //    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetheriteOreFarmTileentity>> NETHERITE_FARM = BLOCK_ENTITY_REGISTER.register("netherite_farm", () ->
//            BlockEntityType.Builder.of(NetheriteOreFarmTileentity::new, ModBlocks.NETHERITE_FARM.get()).build(null)
//    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InventoryViewerTileentity>> INVENTORY_VIEWER = BLOCK_ENTITY_REGISTER.register("inventory_viewer", () ->
            BlockEntityType.Builder.of(InventoryViewerTileentity::new, ModBlocks.INVENTORY_VIEWER.get()).build(null)
    );
    // Deepslate variants
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateIronOreFarmTileentity>> DIRON_FARM = BLOCK_ENTITY_REGISTER.register("diron_farm", () ->
            BlockEntityType.Builder.of(DeepslateIronOreFarmTileentity::new, ModBlocks.DIRON_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperOreFarmTileentity>> COPPER_FARM = BLOCK_ENTITY_REGISTER.register("copper_farm", () ->
            BlockEntityType.Builder.of(CopperOreFarmTileentity::new, ModBlocks.COPPER_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateDiamondOreFarmTileentity>> DDIAMOND_FARM = BLOCK_ENTITY_REGISTER.register("ddiamond_farm", () ->
            BlockEntityType.Builder.of(DeepslateDiamondOreFarmTileentity::new, ModBlocks.DDIAMOND_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateEmeraldOreFarmTileentity>> DEMERALD_FARM = BLOCK_ENTITY_REGISTER.register("demerald_farm", () ->
            BlockEntityType.Builder.of(DeepslateEmeraldOreFarmTileentity::new, ModBlocks.DEMERALD_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateCopperOreFarmTileentity>> DCOPPER_FARM = BLOCK_ENTITY_REGISTER.register("dcopper_farm", () ->
            BlockEntityType.Builder.of(DeepslateCopperOreFarmTileentity::new, ModBlocks.DCOPPER_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateLapisOreFarmTileentity>> DLAPIS_FARM = BLOCK_ENTITY_REGISTER.register("dlapis_farm", () ->
            BlockEntityType.Builder.of(DeepslateLapisOreFarmTileentity::new, ModBlocks.DLAPIS_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateRedstoneOreFarmTileentity>> DREDSTONE_FARM = BLOCK_ENTITY_REGISTER.register("dredstone_farm", () ->
            BlockEntityType.Builder.of(DeepslateRedstoneOreFarmTileentity::new, ModBlocks.DREDSTONE_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateCoalOreFarmTileentity>> DCOAL_FARM = BLOCK_ENTITY_REGISTER.register("dcoal_farm", () ->
            BlockEntityType.Builder.of(DeepslateCoalOreFarmTileentity::new, ModBlocks.DCOAL_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CoalOreFarmTileentity>> COAL_FARM = BLOCK_ENTITY_REGISTER.register("coal_farm", () ->
            BlockEntityType.Builder.of(CoalOreFarmTileentity::new, ModBlocks.COAL_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetheriteOreFarmTileentity>> NETHERITE_FARM = BLOCK_ENTITY_REGISTER.register("deepslate_netherite_farm", () ->
            BlockEntityType.Builder.of(NetheriteOreFarmTileentity::new, ModBlocks.NETHERITE_FARM.get()).build(null)
    );
    // Nether variants
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherQuartzOreFarmTileentity>> NETHER_QUARTZ_FARM = BLOCK_ENTITY_REGISTER.register("nether_quartz_farm", () ->
            BlockEntityType.Builder.of(NetherQuartzOreFarmTileentity::new, ModBlocks.NETHER_QUARTZ_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherGoldOreFarmTileentity>> NETHER_GOLD_FARM = BLOCK_ENTITY_REGISTER.register("nether_gold_farm", () ->
            BlockEntityType.Builder.of(NetherGoldOreFarmTileentity::new, ModBlocks.NETHER_GOLD_FARM.get()).build(null)
    );


}
