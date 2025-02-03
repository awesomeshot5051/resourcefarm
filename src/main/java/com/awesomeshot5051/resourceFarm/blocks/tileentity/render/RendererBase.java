package com.awesomeshot5051.resourceFarm.blocks.tileentity.render;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.FakeWorldTileentity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;

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
