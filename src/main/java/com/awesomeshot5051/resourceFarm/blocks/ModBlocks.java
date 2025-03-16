package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.resourceFarm.*;
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
import com.awesomeshot5051.resourceFarm.integration.integrateddynamics.*;
import com.awesomeshot5051.resourceFarm.integration.integratedterminals.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> DYNAMIC_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);
    public static final DeferredRegister<Block> TERMINALS_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);

    public static final DeferredHolder<Block, BasaltFarmBlock> BASALT_FARM = BLOCK_REGISTER.register("basalt_farm", BasaltFarmBlock::new);
    public static final DeferredHolder<Block, BlackstoneFarmBlock> BLACKSTONE_FARM = BLOCK_REGISTER.register("blackstone_farm", BlackstoneFarmBlock::new);
    public static final DeferredHolder<Block, CalciteFarmBlock> CALCITE_FARM = BLOCK_REGISTER.register("calcite_farm", CalciteFarmBlock::new);
    public static final DeferredHolder<Block, CoalOreFarmBlock> COAL_FARM = BLOCK_REGISTER.register("coal_farm", CoalOreFarmBlock::new);
    public static final DeferredHolder<Block, CobblestoneFarmBlock> COBBLESTONE_FARM = BLOCK_REGISTER.register("cobblestone_farm", CobblestoneFarmBlock::new);
    public static final DeferredHolder<Block, ConcreteFarmBlock> CONCRETE_FARM = BLOCK_REGISTER.register("concrete_farm", ConcreteFarmBlock::new);
    public static final DeferredHolder<Block, ConcretePowderFarmBlock> CONCRETE_POWDER_FARM = BLOCK_REGISTER.register("cpowder_farm", ConcretePowderFarmBlock::new);
    public static final DeferredHolder<Block, CopperOreFarmBlock> COPPER_FARM = BLOCK_REGISTER.register("copper_farm", CopperOreFarmBlock::new);
    public static final DeferredHolder<Block, ClayFarmBlock> CLAY_FARM = BLOCK_REGISTER.register("clay_farm", ClayFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateCoalOreFarmBlock> DCOAL_FARM = BLOCK_REGISTER.register("dcoal_farm", DeepslateCoalOreFarmBlock::new);
    public static final DeferredHolder<Block, DioriteFarmBlock> DIORITE_FARM = BLOCK_REGISTER.register("diorite_farm", DioriteFarmBlock::new);

    public static final DeferredHolder<Block, DeepslateFarmBlock> DEEPSLATE_FARM = BLOCK_REGISTER.register("deepslate_farm", DeepslateFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateCopperOreFarmBlock> DCOPPER_FARM = BLOCK_REGISTER.register("dcopper_farm", DeepslateCopperOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateDiamondOreFarmBlock> DDIAMOND_FARM = BLOCK_REGISTER.register("ddiamond_farm", DeepslateDiamondOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateEmeraldOreFarmBlock> DEMERALD_FARM = BLOCK_REGISTER.register("demerald_farm", DeepslateEmeraldOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateGoldOreFarmBlock> DGOLD_FARM = BLOCK_REGISTER.register("dgold_farm", DeepslateGoldOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateIronOreFarmBlock> DIRON_FARM = BLOCK_REGISTER.register("diron_farm", DeepslateIronOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateLapisOreFarmBlock> DLAPIS_FARM = BLOCK_REGISTER.register("dlapis_farm", DeepslateLapisOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateRedstoneOreFarmBlock> DREDSTONE_FARM = BLOCK_REGISTER.register("dredstone_farm", DeepslateRedstoneOreFarmBlock::new);
    public static final DeferredHolder<Block, DiamondOreFarmBlock> DIAMOND_FARM = BLOCK_REGISTER.register("diamond_farm", DiamondOreFarmBlock::new);
    public static final DeferredHolder<Block, DirtFarmBlock> DIRT_FARM = BLOCK_REGISTER.register("dirt_farm", DirtFarmBlock::new);
    public static final DeferredHolder<Block, EmeraldOreFarmBlock> EMERALD_FARM = BLOCK_REGISTER.register("emerald_farm", EmeraldOreFarmBlock::new);
    public static final DeferredHolder<Block, EndStoneFarmBlock> ESTONE_FARM = BLOCK_REGISTER.register("estone_farm", EndStoneFarmBlock::new);
    public static final DeferredHolder<Block, GlowstoneFarmBlock> GLOWSTONE_FARM = BLOCK_REGISTER.register("glowstone_farm", GlowstoneFarmBlock::new);
    public static final DeferredHolder<Block, GoldOreFarmBlock> GOLD_FARM = BLOCK_REGISTER.register("gold_farm", GoldOreFarmBlock::new);
    public static final DeferredHolder<Block, GrassFarmBlock> GRASS_FARM = BLOCK_REGISTER.register("grass_farm", GrassFarmBlock::new);
    public static final DeferredHolder<Block, GravelFarmBlock> GRAVEL_FARM = BLOCK_REGISTER.register("gravel_farm", GravelFarmBlock::new);
    public static final DeferredHolder<Block, IronOreFarmBlock> IRON_FARM = BLOCK_REGISTER.register("iron_farm", IronOreFarmBlock::new);
    public static final DeferredHolder<Block, LapisOreFarmBlock> LAPIS_FARM = BLOCK_REGISTER.register("lapis_farm", LapisOreFarmBlock::new);
    public static final DeferredHolder<Block, MudFarmBlock> MUD_FARM = BLOCK_REGISTER.register("mud_farm", MudFarmBlock::new);
    public static final DeferredHolder<Block, NetherGoldOreFarmBlock> NETHER_GOLD_FARM = BLOCK_REGISTER.register("nether_gold_farm", NetherGoldOreFarmBlock::new);
    public static final DeferredHolder<Block, NetheriteOreFarmBlock> NETHERITE_FARM = BLOCK_REGISTER.register("netherite_farm", NetheriteOreFarmBlock::new);
    public static final DeferredHolder<Block, NetherQuartzOreFarmBlock> NETHER_QUARTZ_FARM = BLOCK_REGISTER.register("nether_quartz_farm", NetherQuartzOreFarmBlock::new);
    public static final DeferredHolder<Block, NetherrackFarmBlock> NETHERRACK_FARM = BLOCK_REGISTER.register("netherrack_farm", NetherrackFarmBlock::new);
    public static final DeferredHolder<Block, ObsidianFarmBlock> OBSIDIAN_FARM = BLOCK_REGISTER.register("obsidian_farm", ObsidianFarmBlock::new);
    public static final DeferredHolder<Block, PurpurFarmBlock> PURPUR_FARM = BLOCK_REGISTER.register("purpur_farm", PurpurFarmBlock::new);
    public static final DeferredHolder<Block, RedSandFarmBlock> RSAND_FARM = BLOCK_REGISTER.register("rsand_farm", RedSandFarmBlock::new);
    public static final DeferredHolder<Block, RedstoneOreFarmBlock> REDSTONE_FARM = BLOCK_REGISTER.register("redstone_farm", RedstoneOreFarmBlock::new);
    public static final DeferredHolder<Block, SandFarmBlock> SAND_FARM = BLOCK_REGISTER.register("sand_farm", SandFarmBlock::new);
    public static final DeferredHolder<Block, SandstoneFarmBlock> SSTONE_FARM = BLOCK_REGISTER.register("sstone_farm", SandstoneFarmBlock::new);
    public static final DeferredHolder<Block, SnowFarmBlock> SNOW_FARM = BLOCK_REGISTER.register("snow_farm", SnowFarmBlock::new);
    public static final DeferredHolder<Block, SoulSoilFarmBlock> SSOIL_FARM = BLOCK_REGISTER.register("ssoil_farm", SoulSoilFarmBlock::new);
    public static final DeferredHolder<Block, SoulSandFarmBlock> SSAND_FARM = BLOCK_REGISTER.register("ssand_farm", SoulSandFarmBlock::new);
    public static final DeferredHolder<Block, StoneFarmBlock> STONE_FARM = BLOCK_REGISTER.register("stone_farm", StoneFarmBlock::new);
    public static final DeferredHolder<Block, TerracottaFarmBlock> TERRACOTTA_FARM = BLOCK_REGISTER.register("terracotta_farm", TerracottaFarmBlock::new);
    public static final DeferredHolder<Block, TuffFarmBlock> TUFF_FARM = BLOCK_REGISTER.register("tuff_farm", TuffFarmBlock::new);
    public static final DeferredHolder<Block, AndesiteFarmBlock> ANDESITE_FARM = BLOCK_REGISTER.register("andesite_farm", AndesiteFarmBlock::new);
    public static final DeferredHolder<Block, GraniteFarmBlock> GRANITE_FARM = BLOCK_REGISTER.register("granite_farm", GraniteFarmBlock::new);


    public static final DeferredHolder<Block, CrystalChorusFarmBlock> CCHORUS_FARM = DYNAMIC_REGISTER.register("cchorus_farm", CrystalChorusFarmBlock::new);
    public static final DeferredHolder<Block, CrystalMenrilFarmBlock> CMENRIL_FARM = DYNAMIC_REGISTER.register("cmenril_farm", CrystalMenrilFarmBlock::new);
    public static final DeferredHolder<Block, CrystalChorusBrickFarmBlock> CCBRICK_FARM = DYNAMIC_REGISTER.register("ccbrick_farm", CrystalChorusBrickFarmBlock::new);
    public static final DeferredHolder<Block, MenrilBrickFarmBlock> MBRICK_FARM = DYNAMIC_REGISTER.register("mbrick_farm", MenrilBrickFarmBlock::new);
    public static final DeferredHolder<Block, MenrilGlassFarmBlock> MGLASS_FARM = TERMINALS_REGISTER.register("mglass_farm", MenrilGlassFarmBlock::new);
    public static final DeferredHolder<Block, CrystalChorusGlassFarmBlock> CCGLASS_FARM = TERMINALS_REGISTER.register("ccglass_farm", CrystalChorusGlassFarmBlock::new);

    public static void init(IEventBus eventBus) {
        BLOCK_REGISTER.register(eventBus);
        if (Main.dynamic_installed) {
            DYNAMIC_REGISTER.register(eventBus);
        }
        if (Main.terminals_installed) {
            TERMINALS_REGISTER.register(eventBus);
        }
    }

}
