package com.awesomeshot5051.resourceFarm.blocks.tileentity.render;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.FakeWorldTileentity;
import com.awesomeshot5051.resourceFarm.recipe.ModDataComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;

public class BlockRendererBase<T extends FakeWorldTileentity> implements BlockEntityRenderer<T> {

    protected Minecraft minecraft;
    protected BlockEntityRendererProvider.Context renderer;

    public BlockRendererBase(BlockEntityRendererProvider.Context renderer) {
        this.renderer = renderer;
        minecraft = Minecraft.getInstance();
    }

    public static void setPickaxeType(@NotNull Block block, ItemStack pickaxeType) {
        ItemContainerContents pickContents = ItemContainerContents.fromItems(Collections.singletonList(pickaxeType));
        block.asItem().getDefaultInstance().set(ModDataComponents.PICK_TYPE, pickContents);
    }

    public static ItemStack getPickaxeType(Block block) {
        return Objects.requireNonNull(block.asItem().getDefaultInstance().get(ModDataComponents.PICK_TYPE)).copyOne();
    }

    @Override
    public void render(T tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

    }

    public EntityRendererProvider.Context createEntityRenderer() {
        return new EntityRendererProvider.Context(minecraft.getEntityRenderDispatcher(), minecraft.getItemRenderer(), minecraft.getBlockRenderer(), minecraft.gameRenderer.itemInHandRenderer, minecraft.getResourceManager(), minecraft.getEntityModels(), minecraft.font);
    }

    @Override
    public int getViewDistance() {
        return Main.CLIENT_CONFIG.blockRenderDistance.get();
    }
}
