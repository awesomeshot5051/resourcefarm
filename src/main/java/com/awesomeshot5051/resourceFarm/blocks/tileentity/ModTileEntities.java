package com.awesomeshot5051.resourceFarm.blocks.tileentity;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.end.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.ores.veryrare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.nether.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.rare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.uncommon.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.veryRare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.end.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.ores.veryrare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.nether.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.rare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.uncommon.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.veryrare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.soil.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.entity.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.capabilities.*;
import net.neoforged.neoforge.registries.*;


public class ModTileEntities {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MODID);

    @OnlyIn(Dist.CLIENT)
    public static void clientSetup() {

        BlockEntityRenderers.register(ModTileEntities.ANDESITE_FARM.get(), AndesiteFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.ESTONE_FARM.get(), EndStoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.NETHERRACK_FARM.get(), NetherrackFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SSTONE_FARM.get(), SandstoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.STONE_FARM.get(), StoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.OBSIDIAN_FARM.get(), ObsidianFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.BASALT_FARM.get(), BasaltFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.BLACKSTONE_FARM.get(), BlackstoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CALCITE_FARM.get(), CalciteFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.COAL_FARM.get(), CoalOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.COBBLESTONE_FARM.get(), CobblestoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CONCRETE_FARM.get(), ConcreteFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CLAY_FARM.get(), ClayFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.CONCRETE_POWDER_FARM.get(), ConcretePowderFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.COPPER_FARM.get(), CopperOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DCOAL_FARM.get(), DeepslateCoalOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DCOPPER_FARM.get(), DeepslateCopperOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DDIAMOND_FARM.get(), DeepslateDiamondOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DEMERALD_FARM.get(), DeepslateEmeraldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DGOLD_FARM.get(), DeepslateGoldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DIAMOND_FARM.get(), DiamondOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DEEPSLATE_FARM.get(), DeepslateFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DIRON_FARM.get(), DeepslateIronOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DIRT_FARM.get(), DirtFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DLAPIS_FARM.get(), DeepslateLapisOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.DREDSTONE_FARM.get(), DeepslateRedstoneOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.EMERALD_FARM.get(), EmeraldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GLOWSTONE_FARM.get(), GlowstoneFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GOLD_FARM.get(), GoldOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GRASS_FARM.get(), GrassFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GRAVEL_FARM.get(), GravelFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.GRANITE_FARM.get(), GraniteFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.IRON_FARM.get(), IronOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.LAPIS_FARM.get(), LapisOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.MUD_FARM.get(), MudFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.NETHER_QUARTZ_FARM.get(), NetherQuartzOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.NETHERITE_FARM.get(), NetheriteOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.PURPUR_FARM.get(), PurpurFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.REDSTONE_FARM.get(), RedstoneOreFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.RSAND_FARM.get(), RedSandFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SSAND_FARM.get(), SoulSandFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SSOIL_FARM.get(), SoulSoilFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SAND_FARM.get(), SandFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.SNOW_FARM.get(), SnowFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.TERRACOTTA_FARM.get(), TerracottaFarmRenderer::new);
        BlockEntityRenderers.register(ModTileEntities.TUFF_FARM.get(), TuffFarmRenderer::new);


    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ANDESITE_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DCOPPER_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DCOAL_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COAL_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IRON_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SAND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIAMOND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EMERALD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LAPIS_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, REDSTONE_FARM.get(), (object, context) -> object.getItemHandler());


        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIRON_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DGOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DDIAMOND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DEMERALD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DLAPIS_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DREDSTONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DEEPSLATE_FARM.get(), (object, context) -> object.getItemHandler());


        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCRETE_POWDER_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCRETE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CLAY_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COBBLESTONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHER_QUARTZ_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHER_GOLD_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHERITE_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RSAND_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GRAVEL_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GRANITE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DIRT_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GRASS_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SNOW_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, STONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MUD_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SSTONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, OBSIDIAN_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TUFF_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CALCITE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TERRACOTTA_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BASALT_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BLACKSTONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHERRACK_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GLOWSTONE_FARM.get(), (object, context) -> object.getItemHandler());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ESTONE_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PURPUR_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SSAND_FARM.get(), (object, context) -> object.getItemHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SSOIL_FARM.get(), (object, context) -> object.getItemHandler());

    }

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITY_REGISTER.register(eventBus);
    }    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClayFarmTileentity>> CLAY_FARM = BLOCK_ENTITY_REGISTER.register("clay_farm", () ->
            BlockEntityType.Builder.of(ClayFarmTileentity::new, ModBlocks.CLAY_FARM.get()).build(null)
    );





    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GraniteFarmTileentity>> GRANITE_FARM = BLOCK_ENTITY_REGISTER.register(
            "granite_farm", () -> BlockEntityType.Builder.of(GraniteFarmTileentity::new, ModBlocks.GRANITE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedSandFarmTileentity>> RSAND_FARM = BLOCK_ENTITY_REGISTER.register(
            "rsand_farm",
            () -> BlockEntityType.Builder.of(RedSandFarmTileentity::new, ModBlocks.RSAND_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GravelFarmTileentity>> GRAVEL_FARM = BLOCK_ENTITY_REGISTER.register(
            "gravel_farm", () -> BlockEntityType.Builder.of(GravelFarmTileentity::new, ModBlocks.GRAVEL_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrassFarmTileentity>> GRASS_FARM = BLOCK_ENTITY_REGISTER.register(
            "grass_farm",
            () -> BlockEntityType.Builder.of(GrassFarmTileentity::new, ModBlocks.GRASS_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DirtFarmTileentity>> DIRT_FARM = BLOCK_ENTITY_REGISTER.register(
            "dirt_farm",
            () -> BlockEntityType.Builder.of(DirtFarmTileentity::new, ModBlocks.DIRT_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SnowFarmTileentity>> SNOW_FARM = BLOCK_ENTITY_REGISTER.register(
            "snow_farm",
            () -> BlockEntityType.Builder.of(SnowFarmTileentity::new, ModBlocks.SNOW_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoulSandFarmTileentity>> SSAND_FARM = BLOCK_ENTITY_REGISTER.register(
            "ssand_farm", () -> BlockEntityType.Builder.of(SoulSandFarmTileentity::new, ModBlocks.SSAND_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateFarmTileentity>> DEEPSLATE_FARM = BLOCK_ENTITY_REGISTER.register(
            "deepslate_farm",
            () -> BlockEntityType.Builder.of(DeepslateFarmTileentity::new, ModBlocks.DEEPSLATE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoulSoilFarmTileentity>> SSOIL_FARM = BLOCK_ENTITY_REGISTER.register(
            "ssoil_farm", () -> BlockEntityType.Builder.of(SoulSoilFarmTileentity::new, ModBlocks.SSOIL_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteFarmTileentity>> ANDESITE_FARM = BLOCK_ENTITY_REGISTER.register(
            "andesite_farm", () -> BlockEntityType.Builder.of(AndesiteFarmTileentity::new, ModBlocks.ANDESITE_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TuffFarmTileentity>> TUFF_FARM = BLOCK_ENTITY_REGISTER.register(
            "tuff_farm",
            () -> BlockEntityType.Builder.of(TuffFarmTileentity::new, ModBlocks.TUFF_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetheriteOreFarmTileentity>> NETHERITE_FARM = BLOCK_ENTITY_REGISTER.register("deepslate_netherite_farm", () ->
            BlockEntityType.Builder.of(NetheriteOreFarmTileentity::new, ModBlocks.NETHERITE_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MudFarmTileentity>> MUD_FARM = BLOCK_ENTITY_REGISTER.register(
            "mud_farm", () -> BlockEntityType.Builder.of(MudFarmTileentity::new, ModBlocks.MUD_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SandstoneFarmTileentity>> SSTONE_FARM = BLOCK_ENTITY_REGISTER.register(
            "sstone_farm", () -> BlockEntityType.Builder.of(SandstoneFarmTileentity::new, ModBlocks.SSTONE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcreteFarmTileentity>> CONCRETE_FARM = BLOCK_ENTITY_REGISTER.register("concrete_farm", () ->
            BlockEntityType.Builder.of(ConcreteFarmTileentity::new, ModBlocks.CONCRETE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CobblestoneFarmTileentity>> COBBLESTONE_FARM = BLOCK_ENTITY_REGISTER.register("cobblestone_farm", () ->
            BlockEntityType.Builder.of(CobblestoneFarmTileentity::new, ModBlocks.COBBLESTONE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoneFarmTileentity>> STONE_FARM = BLOCK_ENTITY_REGISTER.register(
            "stone_farm",
            () -> BlockEntityType.Builder.of(StoneFarmTileentity::new, ModBlocks.STONE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeepslateGoldOreFarmTileentity>> DGOLD_FARM = BLOCK_ENTITY_REGISTER.register("dgold_farm", () ->
            BlockEntityType.Builder.of(DeepslateGoldOreFarmTileentity::new, ModBlocks.DGOLD_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IronOreFarmTileentity>> IRON_FARM = BLOCK_ENTITY_REGISTER.register("iron_farm", () ->
            BlockEntityType.Builder.of(IronOreFarmTileentity::new, ModBlocks.IRON_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ObsidianFarmTileentity>> OBSIDIAN_FARM = BLOCK_ENTITY_REGISTER.register(
            "obsidian_farm",
            () -> BlockEntityType.Builder.of(ObsidianFarmTileentity::new, ModBlocks.OBSIDIAN_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SandFarmTileentity>> SAND_FARM = BLOCK_ENTITY_REGISTER.register("sand_farm", () ->
            BlockEntityType.Builder.of(SandFarmTileentity::new, ModBlocks.SAND_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcretePowderFarmTileentity>> CONCRETE_POWDER_FARM = BLOCK_ENTITY_REGISTER.register("cpowder_farm", () ->
            BlockEntityType.Builder.of(ConcretePowderFarmTileentity::new, ModBlocks.CONCRETE_POWDER_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CalciteFarmTileentity>> CALCITE_FARM = BLOCK_ENTITY_REGISTER.register(
            "calcite_farm",
            () -> BlockEntityType.Builder.of(CalciteFarmTileentity::new, ModBlocks.CALCITE_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GoldOreFarmTileentity>> GOLD_FARM = BLOCK_ENTITY_REGISTER.register("gold_farm", () ->
            BlockEntityType.Builder.of(GoldOreFarmTileentity::new, ModBlocks.GOLD_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TerracottaFarmTileentity>> TERRACOTTA_FARM = BLOCK_ENTITY_REGISTER.register(
            "terracotta_farm", () -> BlockEntityType.Builder.of(TerracottaFarmTileentity::new, ModBlocks.TERRACOTTA_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DiamondOreFarmTileentity>> DIAMOND_FARM = BLOCK_ENTITY_REGISTER.register("diamond_farm", () ->
            BlockEntityType.Builder.of(DiamondOreFarmTileentity::new, ModBlocks.DIAMOND_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndStoneFarmTileentity>> ESTONE_FARM = BLOCK_ENTITY_REGISTER.register(
            "estone_farm",
            () -> BlockEntityType.Builder.of(EndStoneFarmTileentity::new, ModBlocks.ESTONE_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EmeraldOreFarmTileentity>> EMERALD_FARM = BLOCK_ENTITY_REGISTER.register("emerald_farm", () ->
            BlockEntityType.Builder.of(EmeraldOreFarmTileentity::new, ModBlocks.EMERALD_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PurpurFarmTileentity>> PURPUR_FARM = BLOCK_ENTITY_REGISTER.register(
            "purpur_farm",
            () -> BlockEntityType.Builder.of(PurpurFarmTileentity::new, ModBlocks.PURPUR_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LapisOreFarmTileentity>> LAPIS_FARM = BLOCK_ENTITY_REGISTER.register("lapis_farm", () ->
            BlockEntityType.Builder.of(LapisOreFarmTileentity::new, ModBlocks.LAPIS_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedstoneOreFarmTileentity>> REDSTONE_FARM = BLOCK_ENTITY_REGISTER.register("redstone_farm", () ->
            BlockEntityType.Builder.of(RedstoneOreFarmTileentity::new, ModBlocks.REDSTONE_FARM.get()).build(null)
    );


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


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasaltFarmTileentity>> BASALT_FARM = BLOCK_ENTITY_REGISTER.register(
            "basalt_farm",
            () -> BlockEntityType.Builder.of(BasaltFarmTileentity::new, ModBlocks.BASALT_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlackstoneFarmTileentity>> BLACKSTONE_FARM = BLOCK_ENTITY_REGISTER.register(
            "blackstone_farm",
            () -> BlockEntityType.Builder.of(BlackstoneFarmTileentity::new, ModBlocks.BLACKSTONE_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherrackFarmTileentity>> NETHERRACK_FARM = BLOCK_ENTITY_REGISTER.register(
            "netherrack_farm",
            () -> BlockEntityType.Builder.of(NetherrackFarmTileentity::new, ModBlocks.NETHERRACK_FARM.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlowstoneFarmTileentity>> GLOWSTONE_FARM = BLOCK_ENTITY_REGISTER.register(
            "glowstone_farm",
            () -> BlockEntityType.Builder.of(GlowstoneFarmTileentity::new, ModBlocks.GLOWSTONE_FARM.get()).build(null)
    );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherQuartzOreFarmTileentity>> NETHER_QUARTZ_FARM = BLOCK_ENTITY_REGISTER.register("nether_quartz_farm", () ->
            BlockEntityType.Builder.of(NetherQuartzOreFarmTileentity::new, ModBlocks.NETHER_QUARTZ_FARM.get()).build(null)
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherGoldOreFarmTileentity>> NETHER_GOLD_FARM = BLOCK_ENTITY_REGISTER.register("nether_gold_farm", () ->
            BlockEntityType.Builder.of(NetherGoldOreFarmTileentity::new, ModBlocks.NETHER_GOLD_FARM.get()).build(null)
    );


}
