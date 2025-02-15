package com.awesomeshot5051.resourceFarm.blocks.tileentity.render;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;

import javax.annotation.*;
import java.lang.ref.*;

public class RendererBase<T extends FakeWorldTileentity> extends BlockRendererBase<T> {
    @Nullable

    protected WeakReference<VillagerRenderer> villagerRendererCache = new WeakReference<>(null);
    private T tileEntity;

    public RendererBase(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
    }


    @Override
    public void render(T tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(tileEntity, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        this.tileEntity = tileEntity;
    }

    protected VillagerRenderer getVillagerRenderer() {
        VillagerRenderer villagerRenderer = villagerRendererCache.get();
        if (villagerRenderer == null) {
            villagerRenderer = new VillagerRenderer(createEntityRenderer());
            villagerRendererCache = new WeakReference<>(villagerRenderer);
        }
        return villagerRenderer;
    }


    public Direction getDirection() {
        if (tileEntity != null && tileEntity.getLevel() != null) {
            tileEntity.getBlockPos();
            BlockState blockState = tileEntity.getLevel().getBlockState(tileEntity.getBlockPos());


            if (blockState.hasProperty(FakeWorldTileentity.FACING)) {
                return blockState.getValue(FakeWorldTileentity.FACING);
            }
        }
        return Direction.NORTH;
    }


}
