package com.awesomeshot5051.resourceFarm.integration.ae2;


import appeng.core.definitions.*;
import com.awesomeshot5051.corelib.block.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.client.*;
import com.awesomeshot5051.corelib.datacomponents.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.datacomponents.BlockEntityData;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.*;
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

public class SingularityFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public SingularityFarmBlock() {
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
                return new SingularityFarmItemRenderer();
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SingularityFarmTileentity farmTileEntity) {
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
        SingularityFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new SingularityFarmTileentity(BlockPos.ZERO, ModBlocks.SI_FARM.get().defaultBlockState()));
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

    int getTotalCount(List<ItemStack> list, Item item) {
        return list.stream().filter(stack -> stack.is(item)).mapToInt(ItemStack::getCount).sum();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof SingularityFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        List<ItemStack> singularityItemsNeeded = new ArrayList<>(farm.getSingularityRequirements());
        boolean updated = false;
        if (Upgrades.getUpgradeStatus(farm.getUpgrades(), ModItems.INSCRIBER_UPGRADE.toStack())) {
// Matter Ball logic
            if (heldItem.is(AEItems.MATTER_BALL.get())) {
                int currentCount = singularityItemsNeeded.stream()
                        .filter(stack -> stack.is(AEItems.MATTER_BALL.get()))
                        .mapToInt(ItemStack::getCount)
                        .sum();

                int heldCount = heldItem.getCount();
                int total = currentCount + heldCount;

                if (currentCount < 64) {
                    int toAdd = Math.min(64 - currentCount, heldCount);
                    if (toAdd > 0) {
                        // Remove old Matter Ball entry if it exists
                        singularityItemsNeeded.removeIf(stack -> stack.is(AEItems.MATTER_BALL.get()));
                        // Add updated stack
                        singularityItemsNeeded.add(new ItemStack(AEItems.MATTER_BALL.get(), currentCount + toAdd));
                        heldItem.shrink(toAdd);
                        updated = true;
                    }
                }
            }

// Lava Bucket logic
            else if (heldItem.is(Items.LAVA_BUCKET) &&
                    singularityItemsNeeded.stream().noneMatch(stack -> stack.is(Items.LAVA_BUCKET))) {
                singularityItemsNeeded.add(new ItemStack(Items.LAVA_BUCKET));
                heldItem.shrink(1);

                int slotIndex = player.getInventory().findSlotMatchingItem(heldItem);
                if (slotIndex != -1) {
                    player.getInventory().setItem(slotIndex, new ItemStack(Items.BUCKET));
                } else if (!player.getInventory().contains(new ItemStack(Items.BUCKET))) {
                    player.getInventory().add(new ItemStack(Items.BUCKET));
                }

                updated = true;
            }

            if (updated) {
                farm.setSingularityRequirements(singularityItemsNeeded);

                boolean hasMatterBall = singularityItemsNeeded.stream()
                        .anyMatch(stack -> stack.is(AEItems.MATTER_BALL.get()) && stack.getCount() == 64);

                boolean hasLavaBucket = singularityItemsNeeded.stream()
                        .anyMatch(stack -> stack.is(Items.LAVA_BUCKET));

                if (hasMatterBall && hasLavaBucket) {
                    return ItemInteractionResult.SUCCESS;
                }

//                return ItemInteractionResult.CONSUME;
            }

        }
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.SI_FARM::get);
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
        return new SingularityFarmTileentity(blockPos, blockState);
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
