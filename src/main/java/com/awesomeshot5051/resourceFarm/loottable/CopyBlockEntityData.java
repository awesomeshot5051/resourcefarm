package com.awesomeshot5051.resourceFarm.loottable;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.nbt.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CopyBlockEntityData extends LootItemConditionalFunction {

    public static final MapCodec<CopyBlockEntityData> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, CopyBlockEntityData::new));

    protected CopyBlockEntityData(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (blockEntity == null) {
            return stack;
        }
        CompoundTag compoundtag = blockEntity.saveCustomAndMetadata(context.getLevel().registryAccess());
        BlockItem.setBlockEntityData(stack, blockEntity.getType(), compoundtag);
        stack.applyComponents(blockEntity.collectComponents());
        return stack;
    }

    @Override
    public @NotNull LootItemFunctionType<CopyBlockEntityData> getType() {
        return ModLootTables.COPY_BLOCK_ENTITY.get();
    }

}
