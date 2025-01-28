package com.awesomeshot5051.resourceFarm.datacomponents;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;

import javax.annotation.*;
import java.lang.ref.*;
import java.util.*;
import java.util.function.*;


public class VillagerBlockEntityData {

    public static final StreamCodec<RegistryFriendlyByteBuf, VillagerBlockEntityData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public VillagerBlockEntityData decode(RegistryFriendlyByteBuf buf) {
            return new VillagerBlockEntityData(buf.readNbt());
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, VillagerBlockEntityData be) {
            buf.writeNbt(be.nbt);
        }
    };
    private final CompoundTag nbt;
    private WeakReference<FakeWorldTileentity> cache = new WeakReference<>(null);
    private WeakReference<FakeWorldTileentity> emptyCache = new WeakReference<>(null);

    private VillagerBlockEntityData(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public static VillagerBlockEntityData of(CompoundTag nbt) {
        return new VillagerBlockEntityData(nbt.copy());
    }

    @Nullable
    public static VillagerBlockEntityData get(ItemStack stack) {
        return stack.get(ModItems.BLOCK_ENTITY_DATA_COMPONENT);
    }

    public static <T extends FakeWorldTileentity> T getAndStoreBlockEntity(ItemStack stack, HolderLookup.Provider provider, @Nullable Level level, Supplier<T> blockEntitySupplier) {
        VillagerBlockEntityData data = get(stack);
        if (data == null) {
            CustomData beData = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY);
            data = new VillagerBlockEntityData(beData.copyTag());
            stack.set(ModItems.BLOCK_ENTITY_DATA_COMPONENT, data);
        }
        return data.getBlockEntity(provider, level, blockEntitySupplier);
    }

    public CompoundTag copy() {
        return nbt.copy();
    }

    public <T extends FakeWorldTileentity> T getBlockEntity(HolderLookup.Provider provider, @Nullable Level level, Supplier<T> blockEntitySupplier) {
        if (level == null) {
            T te = (T) emptyCache.get();
            if (te == null) {
                te = blockEntitySupplier.get();
                emptyCache = new WeakReference<>(te);
            }
            return te;
        }
        T te = (T) cache.get();
        if (te == null) {
            te = blockEntitySupplier.get();
            te.setFakeWorld(level);
            te.loadCustomOnly(nbt, provider);
            cache = new WeakReference<>(te);
        }
        return te;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VillagerBlockEntityData be = (VillagerBlockEntityData) o;
        return Objects.equals(nbt, be.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbt);
    }

}
