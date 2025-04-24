package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;


import appeng.core.definitions.AEItems;
import com.awesomeshot5051.corelib.block.IItemBlock;
import com.awesomeshot5051.corelib.blockentity.SimpleBlockEntityTicker;
import com.awesomeshot5051.corelib.client.CustomRendererBlockItem;
import com.awesomeshot5051.corelib.client.ItemRenderer;
import com.awesomeshot5051.corelib.datacomponents.Upgrades;
import com.awesomeshot5051.corelib.integration.AE2Check;
import com.awesomeshot5051.resourceFarm.blocks.BlockBase;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.datacomponents.BlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.ModDataComponents;
import com.awesomeshot5051.resourceFarm.gui.OutputContainer;
import com.awesomeshot5051.resourceFarm.integration.ae2.AE2Blocks;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.awesomeshot5051.resourceFarm.integration.ae2.AE2Blocks.QUANTUM_INFUSION_BUCKET;
import static net.minecraft.world.item.BlockItem.TooltipContext;

public class QuantumAlloyFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public QuantumAlloyFarmBlock() {
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
                return new QuantumAlloyFarmItemRenderer();
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof QuantumAlloyFarmTileentity farmTileEntity) {
            if (stack.has(ModDataComponents.UPGRADE)) {
                farmTileEntity.upgradeList = stack.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY).stream().toList();
            }

            farmTileEntity.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, components, tooltipFlag);
        QuantumAlloyFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new QuantumAlloyFarmTileentity(BlockPos.ZERO, ModBlocks.QA_FARM.get().defaultBlockState()));
        if (Screen.hasShiftDown()) {
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
        return list.stream()
                .filter(stack -> stack.is(item))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof QuantumAlloyFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        List<ItemStack> quantumItemsNeeded = new ArrayList<>(farm.getQuantumAlloyRequirements());
        boolean updated = false;

// Helper method to get count of specific item in the list

// Add Copper Ingot
        if (!farm.checkPasses(farm)) {
            if (heldItem.is(Items.COPPER_INGOT)) {
                int current = getTotalCount(quantumItemsNeeded, Items.COPPER_INGOT);
                int toAdd = Math.min(4 - current, heldItem.getCount());

                if (toAdd > 0) {
                    quantumItemsNeeded.removeIf(stack -> stack.is(Items.COPPER_INGOT));
                    quantumItemsNeeded.add(new ItemStack(Items.COPPER_INGOT, current + toAdd));
                    heldItem.shrink(toAdd);
                    updated = true;
                }
            }

// Add Shattered Singularity
            else if (heldItem.is(AAEItems.SHATTERED_SINGULARITY.get())) {
                int current = getTotalCount(quantumItemsNeeded, AAEItems.SHATTERED_SINGULARITY.get());
                int toAdd = Math.min(4 - current, heldItem.getCount());

                if (toAdd > 0) {
                    quantumItemsNeeded.removeIf(stack -> stack.is(AAEItems.SHATTERED_SINGULARITY.get()));
                    quantumItemsNeeded.add(new ItemStack(AAEItems.SHATTERED_SINGULARITY.get(), current + toAdd));
                    heldItem.shrink(toAdd);
                    updated = true;
                }
            }

// Add Singularity
            else if (heldItem.is(AEItems.SINGULARITY.get())) {
                int current = getTotalCount(quantumItemsNeeded, AEItems.SINGULARITY.get());
                int toAdd = Math.min(4 - current, heldItem.getCount());

                if (toAdd > 0) {
                    quantumItemsNeeded.removeIf(stack -> stack.is(AEItems.SINGULARITY.get()));
                    quantumItemsNeeded.add(new ItemStack(AEItems.SINGULARITY.get(), current + toAdd));
                    heldItem.shrink(toAdd);
                    updated = true;
                }
            }

// Add Quantum Infusion Bucket (only once)
            else if (heldItem.is(QUANTUM_INFUSION_BUCKET.get()) &&
                    quantumItemsNeeded.stream().noneMatch(stack -> stack.is(QUANTUM_INFUSION_BUCKET.get()))) {
                quantumItemsNeeded.add(new ItemStack(QUANTUM_INFUSION_BUCKET.get()));
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
                farm.setQuantumAlloyRequirements(quantumItemsNeeded);

                boolean hasCopper = getTotalCount(quantumItemsNeeded, Items.COPPER_INGOT) == 4;
                boolean hasShattered = getTotalCount(quantumItemsNeeded, AAEItems.SHATTERED_SINGULARITY.get()) == 4;
                boolean hasSingularity = getTotalCount(quantumItemsNeeded, AEItems.SINGULARITY.get()) == 4;
                boolean hasInfusion = quantumItemsNeeded.stream().anyMatch(stack -> stack.is(QUANTUM_INFUSION_BUCKET.get()));

                if (hasCopper && hasShattered && hasSingularity && hasInfusion) {
                    return ItemInteractionResult.SUCCESS;
                }

                return ItemInteractionResult.CONSUME;
            }
        }
        if (AE2Check.containsAllItems(AE2Blocks.quantumAlloyRequirements, farm.getQuantumAlloyRequirements()) && Upgrades.getUpgradeStatus(farm.getUpgrades(), ModItems.INSCRIBER_UPGRADE.get().getDefaultInstance())) {
            if (heldItem.is(AAEItems.QUANTUM_PROCESSOR_PRESS.asItem()) && !farm.getInscriberInstalled()) {
                farm.setInscriberInstalled(heldItem.is(AAEItems.QUANTUM_PROCESSOR_PRESS.asItem()));
                heldItem.shrink(1);
                return ItemInteractionResult.CONSUME;
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
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.QA_FARM::get);
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
        return new QuantumAlloyFarmTileentity(blockPos, blockState);
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
