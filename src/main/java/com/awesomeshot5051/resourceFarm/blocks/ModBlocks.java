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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCK_REGISTER = DeferredRegister.createBlocks(Main.MODID);


    public static final DeferredHolder<Block, InventoryViewerBlock> INVENTORY_VIEWER = BLOCK_REGISTER.registerBlock("inventory_viewer", InventoryViewerBlock::new);
    public static final DeferredHolder<Block, BasaltFarmBlock> BASALT_FARM = BLOCK_REGISTER.registerBlock("basalt_farm", BasaltFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, BlackstoneFarmBlock> BLACKSTONE_FARM = BLOCK_REGISTER.registerBlock("blackstone_farm", BlackstoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, CalciteFarmBlock> CALCITE_FARM = BLOCK_REGISTER.registerBlock("calcite_farm", CalciteFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, CoalOreFarmBlock> COAL_FARM = BLOCK_REGISTER.registerBlock("coal_farm", CoalOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, CobblestoneFarmBlock> COBBLESTONE_FARM = BLOCK_REGISTER.registerBlock("cobblestone_farm", CobblestoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, ConcreteFarmBlock> CONCRETE_FARM = BLOCK_REGISTER.registerBlock("concrete_farm", ConcreteFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, ConcretePowderFarmBlock> CONCRETE_POWDER_FARM = BLOCK_REGISTER.registerBlock("cpowder_farm", ConcretePowderFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, CopperOreFarmBlock> COPPER_FARM = BLOCK_REGISTER.registerBlock("copper_farm", CopperOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateCoalOreFarmBlock> DCOAL_FARM = BLOCK_REGISTER.registerBlock("dcoal_farm", DeepslateCoalOreFarmBlock::new, BlockBehaviour.Properties.of());

    public static final DeferredHolder<Block, DeepslateFarmBlock> DEEPSLATE_FARM = BLOCK_REGISTER.registerBlock("deepslate_farm", DeepslateFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateCopperOreFarmBlock> DCOPPER_FARM = BLOCK_REGISTER.registerBlock("dcopper_farm", DeepslateCopperOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateDiamondOreFarmBlock> DDIAMOND_FARM = BLOCK_REGISTER.registerBlock("ddiamond_farm", DeepslateDiamondOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateEmeraldOreFarmBlock> DEMERALD_FARM = BLOCK_REGISTER.registerBlock("demerald_farm", DeepslateEmeraldOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateGoldOreFarmBlock> DGOLD_FARM = BLOCK_REGISTER.registerBlock("dgold_farm", DeepslateGoldOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateIronOreFarmBlock> DIRON_FARM = BLOCK_REGISTER.registerBlock("diron_farm", DeepslateIronOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateLapisOreFarmBlock> DLAPIS_FARM = BLOCK_REGISTER.registerBlock("dlapis_farm", DeepslateLapisOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DeepslateRedstoneOreFarmBlock> DREDSTONE_FARM = BLOCK_REGISTER.registerBlock("dredstone_farm", DeepslateRedstoneOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DiamondOreFarmBlock> DIAMOND_FARM = BLOCK_REGISTER.registerBlock("diamond_farm", DiamondOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, DirtFarmBlock> DIRT_FARM = BLOCK_REGISTER.registerBlock("dirt_farm", DirtFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, EmeraldOreFarmBlock> EMERALD_FARM = BLOCK_REGISTER.registerBlock("emerald_farm", EmeraldOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, EndStoneFarmBlock> ESTONE_FARM = BLOCK_REGISTER.registerBlock("estone_farm", EndStoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, GlowstoneFarmBlock> GLOWSTONE_FARM = BLOCK_REGISTER.registerBlock("glowstone_farm", GlowstoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, GoldOreFarmBlock> GOLD_FARM = BLOCK_REGISTER.registerBlock("gold_farm", GoldOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, GrassFarmBlock> GRASS_FARM = BLOCK_REGISTER.registerBlock("grass_farm", GrassFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, GravelFarmBlock> GRAVEL_FARM = BLOCK_REGISTER.registerBlock("gravel_farm", GravelFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, IronOreFarmBlock> IRON_FARM = BLOCK_REGISTER.registerBlock("iron_farm", IronOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, LapisOreFarmBlock> LAPIS_FARM = BLOCK_REGISTER.registerBlock("lapis_farm", LapisOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, NetherGoldOreFarmBlock> NETHER_GOLD_FARM = BLOCK_REGISTER.registerBlock("nether_gold_farm", NetherGoldOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, NetheriteOreFarmBlock> NETHERITE_FARM = BLOCK_REGISTER.registerBlock("netherite_farm", NetheriteOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, NetherQuartzOreFarmBlock> NETHER_QUARTZ_FARM = BLOCK_REGISTER.registerBlock("nether_quartz_farm", NetherQuartzOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, NetherrackFarmBlock> NETHERRACK_FARM = BLOCK_REGISTER.registerBlock("netherrack_farm", NetherrackFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, ObsidianFarmBlock> OBSIDIAN_FARM = BLOCK_REGISTER.registerBlock("obsidian_farm", ObsidianFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, PurpurFarmBlock> PURPUR_FARM = BLOCK_REGISTER.registerBlock("purpur_farm", PurpurFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, RedSandFarmBlock> RSAND_FARM = BLOCK_REGISTER.registerBlock("rsand_farm", RedSandFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, RedstoneOreFarmBlock> REDSTONE_FARM = BLOCK_REGISTER.registerBlock("redstone_farm", RedstoneOreFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, SandFarmBlock> SAND_FARM = BLOCK_REGISTER.registerBlock("sand_farm", SandFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, SandstoneFarmBlock> SSTONE_FARM = BLOCK_REGISTER.registerBlock("sstone_farm", SandstoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, SnowFarmBlock> SNOW_FARM = BLOCK_REGISTER.registerBlock("snow_farm", SnowFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, SoulSoilFarmBlock> SSOIL_FARM = BLOCK_REGISTER.registerBlock("ssoil_farm", SoulSoilFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, SoulSandFarmBlock> SSAND_FARM = BLOCK_REGISTER.registerBlock("ssand_farm", SoulSandFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, StoneFarmBlock> STONE_FARM = BLOCK_REGISTER.registerBlock("stone_farm", StoneFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, TerracottaFarmBlock> TERRACOTTA_FARM = BLOCK_REGISTER.registerBlock("terracotta_farm", TerracottaFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, TuffFarmBlock> TUFF_FARM = BLOCK_REGISTER.registerBlock("tuff_farm", TuffFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, AndesiteFarmBlock> ANDESITE_FARM = BLOCK_REGISTER.registerBlock("andesite_farm", AndesiteFarmBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<Block, GraniteFarmBlock> GRANITE_FARM = BLOCK_REGISTER.registerBlock("granite_farm", GraniteFarmBlock::new, BlockBehaviour.Properties.of());

    public static void init(IEventBus eventBus) {
        BLOCK_REGISTER.register(eventBus);
    }

}
