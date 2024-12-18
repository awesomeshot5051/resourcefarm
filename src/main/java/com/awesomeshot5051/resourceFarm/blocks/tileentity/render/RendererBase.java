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
//    protected static VillagerRenderState villagerRenderState;
    protected WeakReference<VillagerRenderer> villagerRendererCache = new WeakReference<>(null);
    private T tileEntity;

    public RendererBase(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
    }

//    protected static VillagerRenderState getVillagerRenderState(VillagerRenderer renderer, Villager villager) {
//        if (villagerRenderState == null) {
//            villagerRenderState = renderer.createRenderState();
//        }
//        renderer.extractRenderState(villager, villagerRenderState, 0F);
//        return villagerRenderState;
//    }

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

//    public <S extends EntityRenderState, L extends MobRenderer<?, ?, ?>> S getRenderState(L BlockInternalRender, S state) {
//        if (state == null) {
//            state = (S) BlockInternalRender.createRenderState();
//        }
//        return state;
//    }


    public Direction getDirection() {
        if (tileEntity != null && tileEntity.getLevel() != null) {
            tileEntity.getBlockPos();// Get the block state at the tile entity's position
            BlockState blockState = tileEntity.getLevel().getBlockState(tileEntity.getBlockPos());

            // Check if the block state has the FACING property
            if (blockState.hasProperty(FakeWorldTileentity.FACING)) {
                return blockState.getValue(FakeWorldTileentity.FACING);
            }
        }
        return Direction.NORTH; // Default or fallback direction
    }

//    public void renderSapling(PoseStack matrixStack, ItemStack pickaxeStack) {
//        Direction direction = getDirection();
//        PickaxeRendererUtil.renderSwingingPickaxe();
//
//    }

}
