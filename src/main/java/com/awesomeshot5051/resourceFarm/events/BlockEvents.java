package com.awesomeshot5051.resourceFarm.events;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.common.util.*;
import net.neoforged.neoforge.event.entity.player.*;


public class BlockEvents {
    public BlockEvents() {
        registerDispenserBehaviors();
    }

    private void registerDispenserBehaviors() {
        // Register behavior for dyes
        for (var dyeItem : new Item[]{
                Items.WHITE_DYE, Items.ORANGE_DYE, Items.MAGENTA_DYE, Items.LIGHT_BLUE_DYE,
                Items.YELLOW_DYE, Items.LIME_DYE, Items.PINK_DYE, Items.GRAY_DYE, Items.LIGHT_GRAY_DYE,
                Items.CYAN_DYE, Items.PURPLE_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.GREEN_DYE,
                Items.RED_DYE, Items.BLACK_DYE}) {
            DispenserBlock.registerBehavior(dyeItem, new DyeDispenseItemBehavior());
        }

        // Register behavior for redstone, quartz, charged certus quartz, and water bucket
        for (var item : new Item[]{
                Items.REDSTONE, Items.QUARTZ, AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get()}) {
            DispenserBlock.registerBehavior(item, new FluixCrystalDispenseBehavior());
        }
        // Register behavior for water bucket
        DispenserBlock.registerBehavior(Items.WATER_BUCKET, new FluixCrystalDispenseBehavior());
    }

    @SubscribeEvent
    public void onBlockClick(PlayerInteractEvent.RightClickBlock event) {
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (!(state.getBlock() instanceof BlockBase block)) {
            return;
        }
        if (block.overrideClick(state, event.getLevel(), event.getPos(), event.getEntity(), event.getHand())) {
            event.setUseBlock(TriState.TRUE);
        }
    }
}
