package com.awesomeshot5051.resourceFarm.blocks.tileentity.render;

import com.awesomeshot5051.corelib.blockentity.*;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;

import javax.annotation.*;

public class RendererBase<T extends FakeWorldTileentity> extends BlockRendererBase<T> {
    @Nullable
    private T tileEntity;

    public RendererBase(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
    }


    @Override
    public void render(T tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(tileEntity, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        this.tileEntity = tileEntity;
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

    public void renderSapling(PoseStack matrixStack) {
        Direction direction = getDirection();
        renderProperly(matrixStack, direction);
    }

    public void renderProperly(PoseStack matrixStack, Direction direction) {
        matrixStack.pushPose();
        switch (direction) {
            case NORTH -> matrixStack.translate(0.5F, .2F, (double) 0.5F);
            case WEST -> matrixStack.translate(1.0F, .2F, (double) 0.0F);
            case EAST -> matrixStack.translate(0.0F, .2F, 1.1);
            case SOUTH -> matrixStack.translate(0.0F, .2F, (double) 0.0F);
        }

        matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }

}
