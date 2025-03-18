package com.awesomeshot5051.resourceFarm.data.providers.models;

import com.awesomeshot5051.resourceFarm.*;
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
            String blockName = blockEntry.getId().getPath();
            if (blockName.equals("inventory_viewer")) {
                return;
            }
            if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                directionalBlock(block, modLoc("block/" + blockName));
            } else {
                blockWithItem(blockEntry);
            }
        });
        if (Main.dynamic_installed) {
            ModBlocks.DYNAMIC_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                String blockName = blockEntry.getId().getPath();
                if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    directionalBlock(block, modLoc("block/" + blockName));
                } else {
                    blockWithItem(blockEntry);
                }
            });
        }
        if (Main.terminals_installed) {
            ModBlocks.TERMINALS_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                String blockName = blockEntry.getId().getPath();
                if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    directionalBlock(block, modLoc("block/" + blockName));
                } else {
                    blockWithItem(blockEntry);
                }
            });
        }
        if (Main.ae2_installed) {
            ModBlocks.AE2_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                String blockName = blockEntry.getId().getPath();
                if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    directionalBlock(block, modLoc("block/" + blockName));
                } else {
                    blockWithItem(blockEntry);
                }
            });
        }
        if (Main.eae2_installed) {
            ModBlocks.EAE2_REGISTER.getEntries().forEach(blockEntry -> {
                Block block = blockEntry.get();
                String blockName = blockEntry.getId().getPath();
                if (block.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    directionalBlock(block, modLoc("block/" + blockName));
                } else {
                    blockWithItem(blockEntry);
                }
            });
        }
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
