package com.awesomeshot5051.resourceFarm.integration.ae2.Fluix;


import com.awesomeshot5051.corelib.block.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.client.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.*;
import net.minecraft.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.sounds.*;
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
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.fluids.*;
import net.neoforged.neoforge.fluids.capability.*;
import org.jetbrains.annotations.*;

import javax.annotation.*;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.*;

import static com.awesomeshot5051.corelib.integration.AE2Check.*;
import static net.minecraft.world.item.BlockItem.*;

public class FluixCrystalFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public FluixCrystalFarmBlock() {
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
                return new FluixCrystalFarmItemRenderer();
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FluixCrystalFarmTileentity farmTileEntity) {
            ItemContainerContents pickType = stack.get(ModDataComponents.PICK_TYPE);
            if (stack.has(ModDataComponents.UPGRADE)) {
                farmTileEntity.upgradeList = stack.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY).stream().toList();
                farmTileEntity.setChanged();
                for (ItemStack upgrade : farmTileEntity.upgradeList) {
                    updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, upgrade);
                }
                level.sendBlockUpdated(pos, state, state, 3);
            }
            if (pickType != null) {
                farmTileEntity.pickType = pickType.getStackInSlot(0);
                farmTileEntity.setChanged();
                updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, pickType.getStackInSlot(0));
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, components, tooltipFlag);
        FluixCrystalFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new FluixCrystalFarmTileentity(BlockPos.ZERO, ModBlocks.FLCR_FARM.get().defaultBlockState()));
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
    protected @NotNull ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof FluixCrystalFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        List<ItemStack> itemsNeeded = new ArrayList<>(farm.ae2ItemsList);

        // IMPLEMENT: Handle water buckets by inserting fluid into the tank.
        if (heldItem.getItem() instanceof BucketItem bucketItem && !(heldItem.getItem() instanceof MobBucketItem)) {
            Fluid fluidInBucket = bucketItem.content;
            // Only process if the bucket is filled (not empty)
            if (fluidInBucket != Fluids.EMPTY) {
                FluidStack fluidStack = new FluidStack(fluidInBucket, 1000); // Buckets contain 1000 mB
                int filledAmount = farm.fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
                if (filledAmount > 0) {
                    if (!worldIn.isClientSide) {
                        // Replace filled bucket with an empty bucket, unless in creative mode
                        if (!player.getAbilities().instabuild) {
                            heldItem.shrink(1);
                            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                            if (!player.addItem(emptyBucket)) {
                                player.drop(emptyBucket, false);
                            }
                        }
                    }
                    var sound = bucketItem.content.getFluidType().getSound(player, worldIn, pos, SoundActions.BUCKET_EMPTY);
                    var soundSource = SoundSource.BLOCKS;
                    if (sound != null) {
                        worldIn.playSound(player, pos, sound, soundSource, 1.0F, 1.0F);
                    }
                    itemsNeeded.add(heldItem.copyWithCount(1));
                    return ItemInteractionResult.sidedSuccess(worldIn.isClientSide);
                }
            }
        }

        // Process other items (except water buckets, which are handled above)
        if (!itemsNeeded.contains(heldItem) && itemsNeeded.size() < 4 && !heldItem.is(Items.AIR)) {
            if (heldItem.is(AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get()) &&
                    itemsNeeded.stream().noneMatch(stack -> stack.is(AE2Blocks.CHARGED_CERTUS_QUARTZ_CRYSTAL.get()))) {
                itemsNeeded.add(heldItem.copyWithCount(1));
                heldItem.shrink(1);
            } else if (heldItem.is(Items.REDSTONE) &&
                    itemsNeeded.stream().noneMatch(stack -> stack.is(Items.REDSTONE))) {
                itemsNeeded.add(heldItem.copyWithCount(1));
                heldItem.shrink(1);
            } else if (heldItem.is(Items.QUARTZ) &&
                    itemsNeeded.stream().noneMatch(stack -> stack.is(Items.QUARTZ))) {
                itemsNeeded.add(heldItem.copyWithCount(1));
                heldItem.shrink(1);
            }
            // Only update the farm list if we haven't reached 4 items
            if (itemsNeeded.size() != 4) {
                farm.ae2ItemsList = itemsNeeded;
            }
            if (itemsNeeded.size() == 4 && containsAllItems(AE2Blocks.itemsRequiredForFC, itemsNeeded)) {
                farm.ae2Items = ItemContainerContents.fromItems(itemsNeeded);
                farm.ae2ItemsList = itemsNeeded;
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.CONSUME;
        }
        if (itemsNeeded.size() == 4 && containsAllItems(AE2Blocks.itemsRequiredForFC, itemsNeeded)) {
            farm.ae2ItemsList = itemsNeeded;
        }

        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.FLCR_FARM::get);
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
        return new FluixCrystalFarmTileentity(blockPos, blockState);
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
