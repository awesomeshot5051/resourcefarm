package com.awesomeshot5051.resourceFarm.events;

import com.awesomeshot5051.resourceFarm.blocks.BlockBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class BlockEvents {


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
