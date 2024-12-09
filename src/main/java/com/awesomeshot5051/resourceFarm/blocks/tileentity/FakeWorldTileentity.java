package com.awesomeshot5051.resourceFarm.blocks.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class FakeWorldTileentity extends SyncableTileentity {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected boolean fakeWorld;
    protected BlockState defaultState;

    public FakeWorldTileentity(BlockEntityType<?> type, BlockState defaultState, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.defaultState = defaultState;
    }

    public void setFakeWorld(Level w) {
        level = w;
        fakeWorld = true;
    }

    public boolean isFakeWorld() {
        return fakeWorld;
    }

    @Override
    public BlockState getBlockState() {
        if (fakeWorld) {
            return defaultState;
        }
        return super.getBlockState();
    }

}
