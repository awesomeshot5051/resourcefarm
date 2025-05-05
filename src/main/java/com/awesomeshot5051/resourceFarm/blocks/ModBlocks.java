package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.OreFarmDir.*;
import com.awesomeshot5051.resourceFarm.blocks.end.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.nether.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.nether.ores.veryrare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.nether.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.nether.soil.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.rare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.rare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.uncommon.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.uncommon.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.veryRare.deepslate.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.veryRare.regular.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.blocks.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.Fluix.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.Meteorite.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.Quartz.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.advancedae.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.extendedae.*;
import com.awesomeshot5051.resourceFarm.integration.integrateddynamics.*;
import com.awesomeshot5051.resourceFarm.integration.integratedterminals.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.common.extensions.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> DYNAMIC_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> TERMINALS_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> AE2_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> AAE2_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> EAE2_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    // Vanilla Minecraft farm blocks registration using BLOCK_REGISTER:

    // Basalt Farm Block: Automatically farms basalt, a volcanic rock found in basalt deltas.
    public static final DeferredHolder<Block, BasaltFarmBlock> BASALT_FARM = BLOCK_REGISTER.register("basalt_farm", BasaltFarmBlock::new);

    // Blackstone Farm Block: Produces blackstone, a hard block commonly found in the Nether.
    public static final DeferredHolder<Block, BlackstoneFarmBlock> BLACKSTONE_FARM = BLOCK_REGISTER.register("blackstone_farm", BlackstoneFarmBlock::new);

    // Calcite Farm Block: Generates calcite, a sedimentary rock often found in geodes.
    public static final DeferredHolder<Block, CalciteFarmBlock> CALCITE_FARM = BLOCK_REGISTER.register("calcite_farm", CalciteFarmBlock::new);

    // Coal Ore Farm Block: Automatically produces coal ore blocks.
    public static final DeferredHolder<Block, CoalOreFarmBlock> COAL_FARM = BLOCK_REGISTER.register("coal_farm", CoalOreFarmBlock::new);

    // Cobblestone Farm Block: Generates cobblestone, a staple building material.
    public static final DeferredHolder<Block, CobblestoneFarmBlock> COBBLESTONE_FARM = BLOCK_REGISTER.register("cobblestone_farm", CobblestoneFarmBlock::new);

    // Concrete Farm Block: Produces concrete blocks available in various colors.
    public static final DeferredHolder<Block, ConcreteFarmBlock> CONCRETE_FARM = BLOCK_REGISTER.register("concrete_farm", ConcreteFarmBlock::new);

    // Concrete Powder Farm Block: Automatically farms concrete powder, which hardens into concrete when water is applied.
    public static final DeferredHolder<Block, ConcretePowderFarmBlock> CONCRETE_POWDER_FARM = BLOCK_REGISTER.register("cpowder_farm", ConcretePowderFarmBlock::new);

    // Copper Ore Farm Block: Generates copper ore blocks.
    public static final DeferredHolder<Block, CopperOreFarmBlock> COPPER_FARM = BLOCK_REGISTER.register("copper_farm", CopperOreFarmBlock::new);

    // Clay Farm Block: Automatically produces clay, used in pottery and brick-making.
    public static final DeferredHolder<Block, ClayFarmBlock> CLAY_FARM = BLOCK_REGISTER.register("clay_farm", ClayFarmBlock::new);

    // Deepslate Coal Ore Farm Block: Farms coal ore embedded in deepslate, found deep underground.
    public static final DeferredHolder<Block, DeepslateCoalOreFarmBlock> DCOAL_FARM = BLOCK_REGISTER.register("dcoal_farm", DeepslateCoalOreFarmBlock::new);

    // Diorite Farm Block: Generates diorite, a type of volcanic rock used in construction.
    public static final DeferredHolder<Block, DioriteFarmBlock> DIORITE_FARM = BLOCK_REGISTER.register("diorite_farm", DioriteFarmBlock::new);

    // Deepslate Farm Block: Automatically farms deepslate, the dark stone found in deeper areas.
    public static final DeferredHolder<Block, DeepslateFarmBlock> DEEPSLATE_FARM = BLOCK_REGISTER.register("deepslate_farm", DeepslateFarmBlock::new);

    // Deepslate Copper Ore Farm Block: Produces deepslate copper ore blocks.
    public static final DeferredHolder<Block, DeepslateCopperOreFarmBlock> DCOPPER_FARM = BLOCK_REGISTER.register("dcopper_farm", DeepslateCopperOreFarmBlock::new);

    // Deepslate Diamond Ore Farm Block: Generates diamond ore within deepslate.
    public static final DeferredHolder<Block, DeepslateDiamondOreFarmBlock> DDIAMOND_FARM = BLOCK_REGISTER.register("ddiamond_farm", DeepslateDiamondOreFarmBlock::new);

    // Deepslate Emerald Ore Farm Block: Automatically produces emerald ore found in deepslate.
    public static final DeferredHolder<Block, DeepslateEmeraldOreFarmBlock> DEMERALD_FARM = BLOCK_REGISTER.register("demerald_farm", DeepslateEmeraldOreFarmBlock::new);

    // Deepslate Gold Ore Farm Block: Generates gold ore in its deepslate form.
    public static final DeferredHolder<Block, DeepslateGoldOreFarmBlock> DGOLD_FARM = BLOCK_REGISTER.register("dgold_farm", DeepslateGoldOreFarmBlock::new);

    // Deepslate Iron Ore Farm Block: Automatically farms deepslate iron ore.
    public static final DeferredHolder<Block, DeepslateIronOreFarmBlock> DIRON_FARM = BLOCK_REGISTER.register("diron_farm", DeepslateIronOreFarmBlock::new);

    // Deepslate Lapis Ore Farm Block: Produces lapis lazuli ore from deepslate.
    public static final DeferredHolder<Block, DeepslateLapisOreFarmBlock> DLAPIS_FARM = BLOCK_REGISTER.register("dlapis_farm", DeepslateLapisOreFarmBlock::new);

    // Deepslate Redstone Ore Farm Block: Automatically generates redstone ore embedded in deepslate.
    public static final DeferredHolder<Block, DeepslateRedstoneOreFarmBlock> DREDSTONE_FARM = BLOCK_REGISTER.register("dredstone_farm", DeepslateRedstoneOreFarmBlock::new);

    // Diamond Ore Farm Block: Produces diamond ore blocks used for crafting and tool upgrades.
    public static final DeferredHolder<Block, DiamondOreFarmBlock> DIAMOND_FARM = BLOCK_REGISTER.register("diamond_farm", DiamondOreFarmBlock::new);

    // Dirt Farm Block: Automatically farms dirt, a basic block used for landscaping and farming.
    public static final DeferredHolder<Block, DirtFarmBlock> DIRT_FARM = BLOCK_REGISTER.register("dirt_farm", DirtFarmBlock::new);

    // Emerald Ore Farm Block: Generates emerald ore, a valuable resource for trading.
    public static final DeferredHolder<Block, EmeraldOreFarmBlock> EMERALD_FARM = BLOCK_REGISTER.register("emerald_farm", EmeraldOreFarmBlock::new);

    // End Stone Farm Block: Automatically produces end stone, a block found in the End dimension.
    public static final DeferredHolder<Block, EndStoneFarmBlock> ESTONE_FARM = BLOCK_REGISTER.register("estone_farm", EndStoneFarmBlock::new);

    // Glowstone Farm Block: Generates glowstone, a light-emitting block from the Nether.
    public static final DeferredHolder<Block, GlowstoneFarmBlock> GLOWSTONE_FARM = BLOCK_REGISTER.register("glowstone_farm", GlowstoneFarmBlock::new);

    // Gold Ore Farm Block: Produces gold ore blocks for smelting and crafting.
    public static final DeferredHolder<Block, GoldOreFarmBlock> GOLD_FARM = BLOCK_REGISTER.register("gold_farm", GoldOreFarmBlock::new);

    // Grass Farm Block: Automatically farms grass blocks, used for landscaping and natural growth.
    public static final DeferredHolder<Block, GrassFarmBlock> GRASS_FARM = BLOCK_REGISTER.register("grass_farm", GrassFarmBlock::new);

    // Gravel Farm Block: Generates gravel, useful in crafting and concrete production.
    public static final DeferredHolder<Block, GravelFarmBlock> GRAVEL_FARM = BLOCK_REGISTER.register("gravel_farm", GravelFarmBlock::new);

    // Iron Ore Farm Block: Automatically produces iron ore, a key resource for tools and machines.
    public static final DeferredHolder<Block, IronOreFarmBlock> IRON_FARM = BLOCK_REGISTER.register("iron_farm", IronOreFarmBlock::new);

    // Lapis Ore Farm Block: Generates lapis lazuli ore, primarily used in enchanting.
    public static final DeferredHolder<Block, LapisOreFarmBlock> LAPIS_FARM = BLOCK_REGISTER.register("lapis_farm", LapisOreFarmBlock::new);

    // Mud Farm Block: Automatically farms mud, used in various crafting recipes.
    public static final DeferredHolder<Block, MudFarmBlock> MUD_FARM = BLOCK_REGISTER.register("mud_farm", MudFarmBlock::new);

    // Nether Gold Ore Farm Block: Produces gold ore specifically found in the Nether.
    public static final DeferredHolder<Block, NetherGoldOreFarmBlock> NETHER_GOLD_FARM = BLOCK_REGISTER.register("nether_gold_farm", NetherGoldOreFarmBlock::new);

    // Netherite Ore Farm Block: Automatically generates netherite ore, the base for crafting netherite items.
    public static final DeferredHolder<Block, NetheriteOreFarmBlock> NETHERITE_FARM = BLOCK_REGISTER.register("netherite_farm", NetheriteOreFarmBlock::new);

    // Nether Quartz Ore Farm Block: Generates quartz ore from the Nether.
    public static final DeferredHolder<Block, NetherQuartzOreFarmBlock> NETHER_QUARTZ_FARM = BLOCK_REGISTER.register("nether_quartz_farm", NetherQuartzOreFarmBlock::new);

    // Netherrack Farm Block: Automatically farms netherrack, a common block in the Nether.
    public static final DeferredHolder<Block, NetherrackFarmBlock> NETHERRACK_FARM = BLOCK_REGISTER.register("netherrack_farm", NetherrackFarmBlock::new);

    // Obsidian Farm Block: Produces obsidian, a blast-resistant block essential for Nether portals.
    public static final DeferredHolder<Block, ObsidianFarmBlock> OBSIDIAN_FARM = BLOCK_REGISTER.register("obsidian_farm", ObsidianFarmBlock::new);

    // Purpur Farm Block: Automatically generates purpur blocks, a decorative block found in End cities.
    public static final DeferredHolder<Block, PurpurFarmBlock> PURPUR_FARM = BLOCK_REGISTER.register("purpur_farm", PurpurFarmBlock::new);

    // Red Sand Farm Block: Produces red sand, a variant of sand found in specific biomes.
    public static final DeferredHolder<Block, RedSandFarmBlock> RSAND_FARM = BLOCK_REGISTER.register("rsand_farm", RedSandFarmBlock::new);

    // Redstone Ore Farm Block: Automatically generates redstone ore, vital for circuitry and power systems.
    public static final DeferredHolder<Block, RedstoneOreFarmBlock> REDSTONE_FARM = BLOCK_REGISTER.register("redstone_farm", RedstoneOreFarmBlock::new);

    // Sand Farm Block: Produces sand, a fundamental resource for glass and concrete.
    public static final DeferredHolder<Block, SandFarmBlock> SAND_FARM = BLOCK_REGISTER.register("sand_farm", SandFarmBlock::new);

    // Sandstone Farm Block: Automatically farms sandstone, a common building material.
    public static final DeferredHolder<Block, SandstoneFarmBlock> SSTONE_FARM = BLOCK_REGISTER.register("sstone_farm", SandstoneFarmBlock::new);

    // Snow Farm Block: Generates snow blocks for crafting or decorative uses.
    public static final DeferredHolder<Block, SnowFarmBlock> SNOW_FARM = BLOCK_REGISTER.register("snow_farm", SnowFarmBlock::new);

    // Soul Soil Farm Block: Automatically produces soul soil, used in growing certain Nether plants.
    public static final DeferredHolder<Block, SoulSoilFarmBlock> SSOIL_FARM = BLOCK_REGISTER.register("ssoil_farm", SoulSoilFarmBlock::new);

    // Soul Sand Farm Block: Generates soul sand, which slows down entities and is used for specific crops.
    public static final DeferredHolder<Block, SoulSandFarmBlock> SSAND_FARM = BLOCK_REGISTER.register("ssand_farm", SoulSandFarmBlock::new);

    // Stone Farm Block: Automatically farms stone, the most common building block.
    public static final DeferredHolder<Block, StoneFarmBlock> STONE_FARM = BLOCK_REGISTER.register("stone_farm", StoneFarmBlock::new);

    // Terracotta Farm Block: Produces terracotta, used for decorative building.
    public static final DeferredHolder<Block, TerracottaFarmBlock> TERRACOTTA_FARM = BLOCK_REGISTER.register("terracotta_farm", TerracottaFarmBlock::new);

    // Tuff Farm Block: Automatically generates tuff, a block found in caves.
    public static final DeferredHolder<Block, TuffFarmBlock> TUFF_FARM = BLOCK_REGISTER.register("tuff_farm", TuffFarmBlock::new);

    // Andesite Farm Block: Produces andesite, a type of volcanic rock used in construction.
    public static final DeferredHolder<Block, AndesiteFarmBlock> ANDESITE_FARM = BLOCK_REGISTER.register("andesite_farm", AndesiteFarmBlock::new);

    // Granite Farm Block: Automatically generates granite, another common igneous rock.
    public static final DeferredHolder<Block, GraniteFarmBlock> GRANITE_FARM = BLOCK_REGISTER.register("granite_farm", GraniteFarmBlock::new);


    // Integrated Dynamics mod farm blocks registration using DYNAMIC_REGISTER:

    // Crystal Chorus Farm Block: Automatically farms crystal chorus, a decorative block related to chorus plants.
    public static final DeferredHolder<Block, CrystalChorusFarmBlock> CCHORUS_FARM = DYNAMIC_REGISTER.register("cchorus_farm", CrystalChorusFarmBlock::new);

    // Crystal Menril Farm Block: Produces crystal menril, likely used in specialized crafting recipes.
    public static final DeferredHolder<Block, CrystalMenrilFarmBlock> CMENRIL_FARM = DYNAMIC_REGISTER.register("cmenril_farm", CrystalMenrilFarmBlock::new);

    // Crystal Chorus Brick Farm Block: Automatically generates bricks made from crystal chorus for unique building materials.
    public static final DeferredHolder<Block, CrystalChorusBrickFarmBlock> CCBRICK_FARM = DYNAMIC_REGISTER.register("ccbrick_farm", CrystalChorusBrickFarmBlock::new);

    // Menril Brick Farm Block: Produces menril brick blocks used in construction and decoration.
    public static final DeferredHolder<Block, MenrilBrickFarmBlock> MBRICK_FARM = DYNAMIC_REGISTER.register("mbrick_farm", MenrilBrickFarmBlock::new);

    // Integrated Terminals mod farm blocks registration using TERMINALS_REGISTER:

    // Menril Glass Farm Block: Automatically produces menril glass, offering a transparent building material.
    public static final DeferredHolder<Block, MenrilGlassFarmBlock> MGLASS_FARM = TERMINALS_REGISTER.register("mglass_farm", MenrilGlassFarmBlock::new);

    // Crystal Chorus Glass Farm Block: Generates glass made from crystal chorus for a unique aesthetic.
    public static final DeferredHolder<Block, CrystalChorusGlassFarmBlock> CCGLASS_FARM = TERMINALS_REGISTER.register("ccglass_farm", CrystalChorusGlassFarmBlock::new);

    // AE2 farm blocks registration (AE2_REGISTER):
    public static final DeferredHolder<Block, FlawedBuddingQuartzFarmBlock> FBQ_FARM = AE2_REGISTER.register("fbq_farm", FlawedBuddingQuartzFarmBlock::new);
    public static final DeferredHolder<Block, FlawlessBuddingQuartzFarmBlock> FLBQ_FARM = AE2_REGISTER.register("flbq_farm", FlawlessBuddingQuartzFarmBlock::new);
    public static final DeferredHolder<Block, ChargedCertusQuartzCrystalFarmBlock> CCQC_FARM = AE2_REGISTER.register("ccqc_farm", ChargedCertusQuartzCrystalFarmBlock::new);
    public static final DeferredHolder<Block, CertusQuartzCrystalFarmBlock> CQC_FARM = AE2_REGISTER.register("cqc_farm", CertusQuartzCrystalFarmBlock::new);
    public static final DeferredHolder<Block, FluixCrystalFarmBlock> FLCR_FARM = AE2_REGISTER.register("flcr_farm", FluixCrystalFarmBlock::new);
    public static final DeferredHolder<Block, FluixDustFarmBlock> FLDU_FARM = AE2_REGISTER.register("fldu_farm", FluixDustFarmBlock::new);
    public static final DeferredHolder<Block, QuartzGlassFarmBlock> QG_FARM = AE2_REGISTER.register("qg_farm", QuartzGlassFarmBlock::new);
    public static final DeferredHolder<Block, SkyStoneFarmBlock> SSB_FARM = AE2_REGISTER.register("ssb_farm", SkyStoneFarmBlock::new);
    public static final DeferredHolder<Block, MysteriousCubeFarmBlock> MC_FARM = AE2_REGISTER.register("mc_farm", MysteriousCubeFarmBlock::new);
    public static final DeferredHolder<Block, SiliconFarmBlock> SIL_FARM = AE2_REGISTER.register("sil_farm", SiliconFarmBlock::new);
    public static final DeferredHolder<Block, SingularityFarmBlock> SI_FARM = AE2_REGISTER.register("si_farm", SingularityFarmBlock::new);

    //Advanced AE Farm blocks registration (AAE2_REGISTER):

    public static final DeferredHolder<Block, QuantumAlloyFarmBlock> QA_FARM = AAE2_REGISTER.register("qa_farm", QuantumAlloyFarmBlock::new);
    public static final DeferredHolder<Block, ShatteredSingularityFarmBlock> SS_FARM = AAE2_REGISTER.register("ss_farm", ShatteredSingularityFarmBlock::new);
    public static final DeferredHolder<Block, QuantumInfusedDustFarmBlock> QID_FARM = AAE2_REGISTER.register("qid_farm", QuantumInfusedDustFarmBlock::new);


    // Extended AE farm blocks registration (EA2_REGISTER):
    public static final DeferredHolder<Block, EntroCrystalFarmBlock> ENTC_FARM = EAE2_REGISTER.register("entc_farm", EntroCrystalFarmBlock::new);
    //    public static final DeferredHolder<Block, EntroSeedFarmBlock> ENS_FARM = EA2_REGISTER.register("ens_farm", EntroSeedFarmBlock::new);
    public static final DeferredHolder<Block, EntroDustFarmBlock> ENTD_FARM = EAE2_REGISTER.register("entd_farm", EntroDustFarmBlock::new);
    public static final DeferredHolder<Block, EntroBlockFarmBlock> ENTB_FARM = EAE2_REGISTER.register("entb_farm", EntroBlockFarmBlock::new);
    //    public static final DeferredHolder<Block, EntroBuddingFullyFarmBlock> EBF_FARM = EA2_REGISTER.register("ebf_farm", EntroBuddingFullyFarmBlock::new);
    public static final DeferredHolder<Block, SiliconBlockFarmBlock> SIB_FARM = EAE2_REGISTER.register("sib_farm", SiliconBlockFarmBlock::new);

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Main.MODID);
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Main.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MODID);
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, Main.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Main.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Main.MODID);
    public static final DeferredBlock<OreFarmBlock> ORE_FARM_BLOCK = registerBlock("ore_farm_block", () ->
            new OreFarmBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OreFarmRecipe>> ORE_FARM_SERIALIZER =
            SERIALIZERS.register("ore_farm", OreFarmRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<OreFarmRecipe>> ORE_FARM_TYPE =
            TYPES.register("ore_farm", () -> new RecipeType<OreFarmRecipe>() {
                @Override
                public String toString() {
                    return "ore_farm";
                }
            });


//  All the registrations

    public static void init(IEventBus eventBus) {
        BLOCK_REGISTER.register(eventBus);
        if (Main.dynamic_installed) {
            DYNAMIC_REGISTER.register(eventBus);
        }
        if (Main.terminals_installed) {
            TERMINALS_REGISTER.register(eventBus);
        }
        if (Main.eae2_installed) {
            EAE2_REGISTER.register(eventBus);
        }
        if (Main.ae2_installed) {
            AE2_REGISTER.register(eventBus);
        }
        if (Main.aae2_installed) {
            AAE2_REGISTER.register(eventBus);
        }
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        MENUS.register(eventBus);
        // Custom Recipes
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static final Supplier<BlockEntityType<OreFarmBlockEntity>> ORE_FARM_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("ore_farm_block_entity", () -> BlockEntityType.Builder.
                    of(OreFarmBlockEntity::new,
                            ORE_FARM_BLOCK.get())
                    .build(null));

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final Supplier<MenuType<OreFarmMenu>> ORE_FARM_MENU = MENUS.register(
            "ore_farm_menu", () -> IMenuTypeExtension.create(OreFarmMenu::new));


}
