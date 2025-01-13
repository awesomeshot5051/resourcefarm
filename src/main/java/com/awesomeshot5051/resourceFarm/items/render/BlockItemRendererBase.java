package com.awesomeshot5051.resourceFarm.items.render;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.mojang.blaze3d.vertex.*;
import de.maxhenkel.corelib.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.model.data.*;

import java.util.function.*;

public class BlockItemRendererBase<T extends BlockEntityRenderer<U>, U extends FakeWorldTileentity> extends ItemRenderer {

    private final Function<BlockEntityRendererProvider.Context, T> rendererSupplier;
    private final Supplier<U> tileEntitySupplier;
    private T renderer;

    public BlockItemRendererBase(Function<BlockEntityRendererProvider.Context, T> rendererSupplier, Supplier<U> tileentitySupplier) {
        this.rendererSupplier = rendererSupplier;
        this.tileEntitySupplier = tileentitySupplier;
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext displayContext, PoseStack matrixStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        if (renderer == null) {
            renderer = rendererSupplier.apply(RendererProviders.createBlockEntityRendererContext());
        }
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            minecraft.getBlockRenderer().renderSingleBlock(blockItem.getBlock().defaultBlockState(), matrixStack, buffer, combinedLightIn, combinedOverlayIn, ModelData.EMPTY, RenderType.cutout());
        }
        assert minecraft.level != null;
        U be = VillagerBlockEntityData.getAndStoreBlockEntity(itemStack, minecraft.level.registryAccess(), minecraft.level, tileEntitySupplier);
        renderer.render(be, 0F, matrixStack, buffer, combinedLightIn, combinedOverlayIn);
    }

}
