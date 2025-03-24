package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;


import com.awesomeshot5051.corelib.block.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.client.*;
import com.awesomeshot5051.corelib.integration.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.data.providers.tags.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import net.minecraft.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;

import static net.minecraft.world.item.BlockItem.*;

public class FluixDustFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public FluixDustFarmBlock() {
        super(Properties.of().mapColor(MapColor.METAL).strength(2.5F).sound(SoundType.METAL).noOcclusion());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    @Override
    public Item toItem() {
        return new CustomRendererBlockItem(this, new Item.Properties()) {
            @OnlyIn(Dist.CLIENT)
            @Override
            public ItemRenderer createItemRenderer() {
                return new FluixDustFarmItemRenderer();
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FluixDustFarmTileentity farmTileEntity) {
            if (stack.has(ModDataComponents.UPGRADE)) {
                farmTileEntity.upgradeList = stack.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY).stream().toList();
                farmTileEntity.setChanged();
                for (ItemStack upgrade : farmTileEntity.upgradeList) {
                    updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, upgrade);
                }
                level.sendBlockUpdated(pos, state, state, 3);
            }

        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, components, tooltipFlag);
        FluixDustFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new FluixDustFarmTileentity(BlockPos.ZERO, ModBlocks.FLDU_FARM.get().defaultBlockState()));
        if (Screen.hasShiftDown()) {
            ItemContainerContents defaultType = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_PICKAXE)));
            ItemStack pickType = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(stack.getOrDefault(ModDataComponents.PICK_TYPE, defaultType)).copyOne())).copyOne();
            components.add(Component.literal("This farm has a " + convertToReadableName(pickType.getItem().getDefaultInstance().getDescriptionId()) + " on it.")
                    .withStyle(ChatFormatting.RED));
            if (stack.has(ModDataComponents.UPGRADE)) {
                for (ItemStack upgrade : stack.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY).stream().toList())
                    components.add(Component.literal(convertToReadableName(upgrade.getDescriptionId())));
            }
        } else {
            components.add(Component.literal("Hold §4Shift§r to see tool").withStyle(ChatFormatting.YELLOW));
        }


    }

    private String convertToReadableName(String block) {

        String readableName = block.replace("item.minecraft.", "").replace("item.resource_farms.", "").replace('_', ' ');

        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof FluixDustFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        List<ItemStack> fluixDust = new ArrayList<>(farm.fluixDustList);
        if (heldItem.is(ModItemTags.SLABS_AND_FLUX_CRYSTAL) && fluixDust.size() < 2) {
            fluixDust.add(heldItem.copyWithCount(1));
            heldItem.shrink(1);
            return ItemInteractionResult.CONSUME;
        }
        if (fluixDust.size() == 2 && AE2Check.containsAllItems(fluixDust, ModItemTags.SLABS_AND_FLUX_CRYSTAL, Objects.requireNonNull(tileEntity.getLevel()))) {
            ((FluixDustFarmTileentity) tileEntity).fluixDustList = fluixDust;
            return ItemInteractionResult.CONSUME;
        }

        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.FLDU_FARM::get);
            }
        });
        return ItemInteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, BlockState state, BlockEntityType<T> type) {
        return new SimpleBlockEntityTicker<>();
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FluixDustFarmTileentity(blockPos, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1F;
    }
}
