package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.corelib.block.*;
import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

import java.util.*;
import java.util.stream.*;

public class BlockBase extends HorizontalRotatableBlock {

    private static final VoxelShape SHAPE = VoxelUtils.combine(
            Block.box(0D, 0D, 0D, 16D, 1D, 16D),
            Block.box(0D, 15D, 0D, 16D, 16D, 16D),
            Block.box(0D, 0D, 0D, 1D, 16D, 16D),
            Block.box(15D, 0D, 0D, 16D, 16D, 16D),
            Block.box(0D, 0D, 0D, 16D, 16D, 1D),
            Block.box(0D, 0D, 15D, 16D, 16D, 16D)
    );


    public BlockBase(Properties properties) {
        super(properties);
    }

    public static String convertMinecraftToReadableName(String block) {

        String readableName = block.replace("item.minecraft.", "").replace("item.resource_farms.", "")
                .replace("item.integrationdynamics", "")
                .replace("item.ae2", "").replace("item.extendedae", "").replace("integratedterminals", "").replace('_', ' ');

        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public boolean overrideClick(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn) {
        return player.isShiftKeyDown() && player.getMainHandItem().isEmpty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
