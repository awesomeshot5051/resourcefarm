package com.awesomeshot5051.resourceFarm.data.providers.models;

import com.awesomeshot5051.resourceFarm.blocks.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.neoforged.neoforge.registries.*;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "awesomeshot5051", existingFileHelper); // Replace with your mod ID
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCK_REGISTER.getEntries().forEach(blockEntry -> {
            Block block = blockEntry.get();
            String blockName = blockEntry.getId().getPath(); // Extract block name from the registry ID

            // Skip processing the inventory_viewer block
            if ("inventory_viewer".equals(blockName)) {
                return;
            }

            // Generate block states and models based on block properties
            if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                // For blocks with horizontal facing
                horizontalBlock(block, modLoc("block/" + blockName + "_side"),
                        mcLoc("block/" + blockName + "_front"),
                        modLoc("block/" + blockName + "_top"));
            } else if (block.defaultBlockState().hasProperty(BlockStateProperties.AXIS)) {
                // For blocks with axis properties, e.g., logs
                axisBlock((RotatedPillarBlock) block,
                        modLoc("block/" + blockName + "_side"),
                        modLoc("block/" + blockName + "_end"));
            } else {
                // Simple block with item model
                blockWithItem(blockEntry);
            }
        });
    }

    /**
     * Helper method to generate a block with an item model.
     */
    private void blockWithItem(DeferredHolder<Block, ?> deferredBlock) {
        Block block = deferredBlock.get();
        simpleBlockWithItem(block, cubeAll(block));
    }

    /**
     * Generates a horizontal block state with specified textures for side, front, and top.
     */
    private void horizontalBlock(Block block, ResourceLocation sideTexture, ResourceLocation frontTexture, ResourceLocation topTexture) {
        ModelFile model = models().cube("block/" + block.getRegistryName().getPath(),
                sideTexture, topTexture, sideTexture, frontTexture, sideTexture, sideTexture);
        getVariantBuilder(block).forAllStates(state -> {
            int rotation = switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                case NORTH -> 0;
                case EAST -> 90;
                case SOUTH -> 180;
                case WEST -> 270;
                default ->
                        throw new IllegalStateException("Unexpected value: " + state.getValue(BlockStateProperties.HORIZONTAL_FACING));
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(rotation)
                    .build();
        });
    }

    /**
     * Generates block states for blocks with axis properties, such as logs.
     */
    private void axisBlock(RotatedPillarBlock block, ResourceLocation sideTexture, ResourceLocation endTexture) {
        ModelFile sideModel = models().cubeColumn(block.getRegistryName().getPath() + "_side", sideTexture, endTexture);
        ModelFile endModel = models().cubeColumn(block.getRegistryName().getPath() + "_end", endTexture, endTexture);

        getVariantBuilder(block).forAllStates(state -> {
            Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
            return switch (axis) {
                case X -> ConfiguredModel.builder().modelFile(sideModel).rotationX(90).rotationY(90).build();
                case Y -> ConfiguredModel.builder().modelFile(endModel).build();
                case Z -> ConfiguredModel.builder().modelFile(sideModel).rotationX(90).build();
                default -> throw new IllegalStateException("Unexpected axis: " + axis);
            };
        });
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return null;
    }

    @Override
    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
        return null;
    }
}
