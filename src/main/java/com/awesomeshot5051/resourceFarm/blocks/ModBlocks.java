package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate.DeepslateCoalOreFarmBlock;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate.DeepslateCopperOreFarmBlock;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate.DeepslateGoldOreFarmBlock;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.regular.CoalOreFarmBlock;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.regular.CopperOreFarmBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MODID);


    public static final DeferredHolder<Block, InventoryViewerBlock> INVENTORY_VIEWER = BLOCK_REGISTER.register("inventory_viewer", InventoryViewerBlock::new);
    public static final DeferredHolder<Block, CopperOreFarmBlock> COPPER_FARM = BLOCK_REGISTER.register("copper_farm", CopperOreFarmBlock::new);
    public static final DeferredHolder<Block, CoalOreFarmBlock> COAL_FARM = BLOCK_REGISTER.register("coal_farm", CoalOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateCopperOreFarmBlock> DCOPPER_FARM = BLOCK_REGISTER.register("dcopper_farm", DeepslateCopperOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateCoalOreFarmBlock> DCOAL_FARM = BLOCK_REGISTER.register("dcoal_farm", DeepslateCoalOreFarmBlock::new);
    public static final DeferredHolder<Block, IronOreFarmBlock> IRON_FARM = BLOCK_REGISTER.register("iron_farm", IronOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateIronOreFarmBlock> DIRON_FARM = BLOCK_REGISTER.register("diron_farm", DeepslateIronOreFarmBlock::new);

    public static final DeferredHolder<Block, GoldOreFarmBlock> GOLD_FARM = BLOCK_REGISTER.register("gold_farm", GoldOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateGoldOreFarmBlock> DGOLD_FARM = BLOCK_REGISTER.register("dgold_farm", DeepslateGoldOreFarmBlock::new);

    public static final DeferredHolder<Block, RedstoneOreFarmBlock> REDSTONE_FARM = BLOCK_REGISTER.register("redstone_farm", RedstoneOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateRedstoneOreFarmBlock> DREDSTONE_FARM = BLOCK_REGISTER.register("dredstone_farm", DeepslateRedstoneOreFarmBlock::new);

    public static final DeferredHolder<Block, LapisOreFarmBlock> LAPIS_FARM = BLOCK_REGISTER.register("lapis_farm", LapisOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateLapisOreFarmBlock> DLAPIS_FARM = BLOCK_REGISTER.register("dlapis_farm", DeepslateLapisOreFarmBlock::new);

    public static final DeferredHolder<Block, EmeraldOreFarmBlock> EMERALD_FARM = BLOCK_REGISTER.register("emerald_farm", EmeraldOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateEmeraldOreFarmBlock> DEMERALD_FARM = BLOCK_REGISTER.register("demerald_farm", DeepslateEmeraldOreFarmBlock::new);

    public static final DeferredHolder<Block, DiamondOreFarmBlock> DIAMOND_FARM = BLOCK_REGISTER.register("diamond_farm", DiamondOreFarmBlock::new);
    public static final DeferredHolder<Block, DeepslateDiamondOreFarmBlock> DDIAMOND_FARM = BLOCK_REGISTER.register("ddiamond_farm", DeepslateDiamondOreFarmBlock::new);


    public static void init(IEventBus eventBus) {
        BLOCK_REGISTER.register(eventBus);
    }

}
