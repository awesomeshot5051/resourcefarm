package com.awesomeshot5051.resourceFarm.blocks.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.Optional;

public class SyncableTileentity extends BlockEntity {

    public SyncableTileentity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // Utility method to validate and parse the pick type
    public static Optional<ItemStack> loadPickType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("PickType")) {
            Tag pickTypeTag = compound.get("PickType");
            if (pickTypeTag != null && isValidPickaxe(pickTypeTag.toString()) || isValidShovel(pickTypeTag.toString())) {
                return ItemStack.parse(provider, pickTypeTag);
            }
        }
        return Optional.empty();
    }

    private static boolean isValidShovel(String itemId) {
        return itemId.contains("minecraft:wooden_shovel") ||
                itemId.contains("minecraft:stone_shovel") ||
                itemId.contains("minecraft:iron_shovel") ||
                itemId.contains("minecraft:diamond_shovel") ||
                itemId.contains("minecraft:golden_shovel") ||
                itemId.contains("minecraft:netherite_shovel");
    }

    // Helper method to check if an item is a valid pickaxe
    private static boolean isValidPickaxe(String itemId) {
        return itemId.contains("minecraft:wooden_pickaxe") ||
                itemId.contains("minecraft:stone_pickaxe") ||
                itemId.contains("minecraft:iron_pickaxe") ||
                itemId.contains("minecraft:diamond_pickaxe") ||
                itemId.contains("minecraft:golden_pickaxe") ||
                itemId.contains("minecraft:netherite_pickaxe");
    }

    public void sync() {
        if (level instanceof ServerLevel serverLevel) {
            LevelChunk chunk = serverLevel.getChunkAt(getBlockPos());
            if (chunk.getLevel().getChunkSource() instanceof ServerChunkCache chunkCache) {
                chunkCache.chunkMap.getPlayers(chunk.getPos(), false).forEach(this::syncContents);
            }
        }
    }

    public void syncContents(ServerPlayer player) {
        player.connection.send(getUpdatePacket());
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag updateTag = new CompoundTag();
        saveAdditional(updateTag, provider);
        return updateTag;
    }

}
