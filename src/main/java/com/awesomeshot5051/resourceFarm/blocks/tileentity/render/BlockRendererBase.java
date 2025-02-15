package com.awesomeshot5051.resourceFarm.blocks.tileentity.render;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;

public class BlockRendererBase<T extends FakeWorldTileentity> implements BlockEntityRenderer<T> {

    protected Minecraft minecraft;
    protected BlockEntityRendererProvider.Context renderer;

    public BlockRendererBase(BlockEntityRendererProvider.Context renderer) {
        this.renderer = renderer;
        minecraft = Minecraft.getInstance();
    }

    @Override
    public void render(T tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

    }

    public EntityRendererProvider.Context createEntityRenderer() {
        return new EntityRendererProvider.Context(minecraft.getEntityRenderDispatcher(), minecraft.getItemRenderer(), minecraft.getBlockRenderer(), minecraft.gameRenderer.itemInHandRenderer, minecraft.getResourceManager(), minecraft.getEntityModels(), minecraft.font);
    }

}
