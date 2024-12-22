package com.awesomeshot5051.resourceFarm.recipe.data.providers.models;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Main.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCK_REGISTER.getEntries().forEach(blockEntry -> {
            Block block = blockEntry.get();
            String blockName = blockEntry.getId().getPath(); // Extract block name from the registry ID

            // Exclude the inventory_viewer block
            if (blockName.equals("inventory_viewer")) {
                return; // Skip processing this block
            }

            if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                // If the block has horizontal facing, use directional block state
                directionalBlock(block, modLoc("block/" + blockName));
            } else {
                // Otherwise, use a simple block model with an item
                blockWithItem(blockEntry);
            }
        });
    }


    private void blockWithItem(DeferredHolder<Block, ?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void directionalBlock(Block block, ResourceLocation modelLocation) {
        getVariantBuilder(block).forAllStates(state -> {
            int yRotation = switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                case NORTH -> 0;
                case SOUTH -> 180;
                case WEST -> 270;
                case EAST -> 90;
                default ->
                        throw new IllegalStateException("Unexpected value: " + state.getValue(BlockStateProperties.HORIZONTAL_FACING));
            };

            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modelLocation))
                    .rotationY(yRotation)
                    .build();
        });
    }
}
