package com.awesomeshot5051.resourceFarm.datacomponents;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.FakeWorldTileentity;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.function.Supplier;

public class FarmBlockEntityData {

    public static final StreamCodec<RegistryFriendlyByteBuf, FarmBlockEntityData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public FarmBlockEntityData decode(RegistryFriendlyByteBuf buf) {
            return new FarmBlockEntityData(buf.readNbt());
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, FarmBlockEntityData be) {
            buf.writeNbt(be.nbt);
        }
    };
    private final CompoundTag nbt;
    private WeakReference<FakeWorldTileentity> cache = new WeakReference<>(null);
    private WeakReference<FakeWorldTileentity> emptyCache = new WeakReference<>(null);

    private FarmBlockEntityData(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public static FarmBlockEntityData of(CompoundTag nbt) {
        return new FarmBlockEntityData(nbt.copy());
    }

    @Nullable
    public static FarmBlockEntityData get(ItemStack stack) {
        return stack.get(ModItems.FARM_ENTITY_DATA_COMPONENT);
    }

    public static <T extends FakeWorldTileentity> T getAndStoreBlockEntity(ItemStack stack, HolderLookup.Provider provider, @Nullable Level level, Supplier<T> blockEntitySupplier) {
        FarmBlockEntityData data = get(stack);
        if (data == null) {
            CustomData beData = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY);
            data = new FarmBlockEntityData(beData.copyTag());
            stack.set(ModItems.FARM_ENTITY_DATA_COMPONENT, data);
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

        FarmBlockEntityData be = (FarmBlockEntityData) o;
        return Objects.equals(nbt, be.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbt);
    }

}
