package com.awesomeshot5051.resourceFarm.datacomponents;

import com.awesomeshot5051.resourceFarm.entity.EasyVillagerEntity;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class PickTypeData {

    public static final Codec<PickTypeData> CODEC = CompoundTag.CODEC.xmap(PickTypeData::of, pickTypeData -> pickTypeData.nbt);
    public static final StreamCodec<RegistryFriendlyByteBuf, PickTypeData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public PickTypeData decode(RegistryFriendlyByteBuf buf) {
            return new PickTypeData(buf.readNbt());
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, PickTypeData villager) {
            buf.writeNbt(villager.nbt);
        }
    };
    private final CompoundTag nbt;
    private WeakReference<EasyVillagerEntity> villager = new WeakReference<>(null);

    private PickTypeData(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public static PickTypeData of(CompoundTag nbt) {
        return new PickTypeData(nbt.copy());
    }

    public static PickTypeData of(Villager villager) {
        CompoundTag nbt = new CompoundTag();
        villager.addAdditionalSaveData(nbt);
        return new PickTypeData(nbt);
    }

    @Nullable
    public static PickTypeData get(ItemStack stack) {
//        if (!(stack.getItem() instanceof Villager)) {
//            throw new IllegalArgumentException("Tried to set villager data to non-villager item (%s)".formatted(stack.getHoverName().getString()));
//        }
//        convert(stack);
        return stack.get(ModItems.PICK_TYPE);
    }

    public static PickTypeData getOrCreate(ItemStack stack) {
        PickTypeData pickTypeData = get(stack);
        if (pickTypeData == null) {
            pickTypeData = setEmptyVillagerTag(stack);
        }
        return pickTypeData;
    }

    public static EasyVillagerEntity createEasyVillager(ItemStack stack, Level level) {
        PickTypeData pickTypeData = getOrCreate(stack);
        return pickTypeData.createEasyVillager(level, stack);
    }

    //    public static ItemStack convert(HolderLookup.Provider provider, CompoundTag itemCompound) {
//        ItemStack stack = ItemStack.parseOptional(provider, itemCompound);
//        if (stack.isEmpty()) {
//            return stack;
//        }
//        if (!(stack.getItem() instanceof VillagerItem)) {
//            return stack;
//        }
//        if (stack.has(ModItems.VILLAGER_DATA_COMPONENT)) {
//            return stack;
//        }
//        if (!itemCompound.contains("tag", Tag.TAG_COMPOUND)) {
//            return stack;
//        }
//        CompoundTag tag = itemCompound.getCompound("tag");
//        if (!tag.contains("villager", Tag.TAG_COMPOUND)) {
//            return stack;
//        }
//        CompoundTag villagerTag = tag.getCompound("villager");
//        PickTypeData pickTypeData = PickTypeData.of(villagerTag);
//        stack.set(ModItems.VILLAGER_DATA_COMPONENT, pickTypeData);
//        return stack;
//    }
//
//    public static void convert(ItemStack stack) {
//        if (!(stack.getItem() instanceof VillagerItem)) {
//            return;
//        }
//        if (stack.has(ModItems.VILLAGER_DATA_COMPONENT)) {
//            return;
//        }
//        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
//        if (customData == null) {
//            setEmptyVillagerTag(stack);
//            return;
//        }
//        CompoundTag customTag = customData.copyTag();
//        if (!customTag.contains("villager", Tag.TAG_COMPOUND)) {
//            setEmptyVillagerTag(stack);
//            return;
//        }
//        CompoundTag villagerTag = customTag.getCompound("villager");
//        customTag.remove("villager");
//        if (customTag.isEmpty()) {
//            stack.remove(DataComponents.CUSTOM_DATA);
//        } else {
//            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(customTag));
//        }
//        PickTypeData pickTypeData = PickTypeData.of(villagerTag);
//        stack.set(ModItems.VILLAGER_DATA_COMPONENT, pickTypeData);
//    }
//
    private static PickTypeData setEmptyVillagerTag(ItemStack stack) {
        PickTypeData pickTypeData = new PickTypeData(new CompoundTag());
        stack.set(ModItems.PICK_TYPE, pickTypeData);
        return pickTypeData;
    }

    public EasyVillagerEntity getCacheVillager(Level level) {
        EasyVillagerEntity easyVillager = villager.get();
        if (easyVillager == null) {
            easyVillager = createEasyVillager(level, null);
            villager = new WeakReference<>(easyVillager);
        }
        return easyVillager;
    }

//    public static void applyToItem(ItemStack stack, Villager villager) {
//        if (stack.isEmpty()) {
//            return;
//        }
//        stack.set(ModItems.VILLAGER_DATA_COMPONENT, PickTypeData.of(villager));
//        if (villager.hasCustomName()) {
//            stack.set(DataComponents.CUSTOM_NAME, villager.getCustomName());
//        }
//    }
//
//    public static EasyVillagerEntity getCacheVillager(ItemStack stack, Level level) {
//        return getOrCreate(stack).getCacheVillager(level);
//    }
//
//    public static void convertInventory(CompoundTag tag, NonNullList<ItemStack> stacks, HolderLookup.Provider provider) {
//        ListTag listtag = tag.getList("Items", Tag.TAG_COMPOUND);
//
//        for (int i = 0; i < listtag.size(); i++) {
//            CompoundTag itemTag = listtag.getCompound(i);
//            int pos = itemTag.getByte("Slot") & 255;
//            if (pos < stacks.size()) {
//                ItemStack convert = convert(provider, itemTag);
//                stacks.set(pos, convert);
//            }
//        }
//    }

    public EasyVillagerEntity createEasyVillager(Level level, @Nullable ItemStack stack) {
        EasyVillagerEntity v = new EasyVillagerEntity(EntityType.VILLAGER, level);
        v.readAdditionalSaveData(nbt);
        if (stack != null) {
            Component customName = stack.get(DataComponents.CUSTOM_NAME);
            if (customName != null) {
                v.setCustomName(customName);
            }
        }
        v.hurtTime = 0;
        v.yHeadRot = 0F;
        v.yHeadRotO = 0F;
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PickTypeData villager1 = (PickTypeData) o;
        return Objects.equals(nbt, villager1.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbt);
    }
}
