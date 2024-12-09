package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate.DeepslateCopperOreFarmBlock;
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
    public static final DeferredHolder<Block, DeepslateCopperOreFarmBlock> DCOPPER_FARM = BLOCK_REGISTER.register("dcopper_farm", DeepslateCopperOreFarmBlock::new);

    public static void init(IEventBus eventBus) {
        BLOCK_REGISTER.register(eventBus);
    }

}
