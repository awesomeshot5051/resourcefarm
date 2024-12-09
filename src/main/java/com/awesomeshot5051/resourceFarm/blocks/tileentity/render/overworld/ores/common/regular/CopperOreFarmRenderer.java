package com.awesomeshot5051.resourceFarm.blocks.tileentity.render.overworld.ores.common.regular;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.regular.CopperOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.render.RendererBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

import static com.awesomeshot5051.resourceFarm.blocks.render.PickaxeRendererUtil.renderSwingingPickaxe;


public class CopperOreFarmRenderer extends RendererBase<CopperOreFarmTileentity> {
    public static ItemStack farmStack2;
    private final BlockRenderDispatcher blockRenderDispatcher;
    public ItemStack farmstack;

    public CopperOreFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }

    public CopperOreFarmRenderer(BlockEntityRendererProvider.Context renderer, ItemStack farmstack) {
        super(renderer);
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
        this.farmstack = farmstack;
    }

    public static void setStaticFarmstack(ItemStack farmstack) {
        farmStack2 = farmstack;
    }

    public static ItemStack getFarmStack2() {
        return farmStack2;
    }

    public ItemStack getFarmstack() {
        return farmstack;
    }

    public void setFarmstack() {
        this.farmstack = farmStack2;
    }

    @Override
    public void render(CopperOreFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack pickContents = getFarmStack2();
        if (pickContents == null || pickContents.isEmpty()) {
            pickContents = new ItemStack(Items.STONE_PICKAXE);
            setStaticFarmstack(pickContents);
            setFarmstack();
        }
        if (getFarmstack() != null && getFarmstack() != this.farmstack) {
            setFarmstack();
        }
        matrixStack.pushPose();
        matrixStack.scale(.5f, .5f, .5f);
        matrixStack.translate(.5, 0, 0.5);
        // Render the Copper Ore Block
        blockRenderDispatcher.renderSingleBlock(
                Blocks.COPPER_ORE.defaultBlockState(),
                matrixStack,
                buffer,
                combinedLight,
                combinedOverlay,
                ModelData.EMPTY,
                RenderType.SOLID
        );


        matrixStack.popPose();
        // Render the Pickaxe
        renderSwingingPickaxe(farm, matrixStack, buffer, combinedLight, combinedOverlay, pickContents, getDirection(), farm.getTimer());
    }


    public void renderBreakingAnimation(BlockState blockState, PoseStack matrixStack, MultiBufferSource buffer, int breakStage, int combinedLight, int combinedOverlay) {
        if (breakStage < 0 || breakStage > 9) return; // Ensure valid stage

        // Get breaking texture
        TextureAtlasSprite breakingSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(ResourceLocation.fromNamespaceAndPath("minecraft", "block/destroy_stage_" + breakStage));

        // Use RenderType for breaking overlay
        RenderType breakingRenderType = RenderType.crumbling(breakingSprite.atlasLocation());
        // Render breaking texture over the block
        blockRenderDispatcher.getModelRenderer().renderModel(
                matrixStack.last(),
                buffer.getBuffer(breakingRenderType),
                blockState,
                blockRenderDispatcher.getBlockModel(blockState),
                1.0F, 1.0F, 1.0F,  // Color
                combinedLight,
                combinedOverlay,
                ModelData.EMPTY,
                breakingRenderType);
    }
}
