package com.awesomeshot5051.resourceFarm.integration.ae2.advancedae;


import appeng.core.definitions.AEItems;
import com.awesomeshot5051.corelib.block.IItemBlock;
import com.awesomeshot5051.corelib.blockentity.SimpleBlockEntityTicker;
import com.awesomeshot5051.corelib.client.CustomRendererBlockItem;
import com.awesomeshot5051.corelib.client.ItemRenderer;
import com.awesomeshot5051.corelib.datacomponents.Upgrades;
import com.awesomeshot5051.resourceFarm.blocks.BlockBase;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.datacomponents.BlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.ModDataComponents;
import com.awesomeshot5051.resourceFarm.gui.OutputContainer;
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

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

import static net.minecraft.world.item.BlockItem.updateCustomBlockEntityTag;
import static net.minecraft.world.item.Item.TooltipContext;

public class ShatteredSingularityFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public ShatteredSingularityFarmBlock() {
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
                return new ShatteredSingularityFarmItemRenderer();
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ShatteredSingularityFarmTileentity farmTileEntity) {
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
        ShatteredSingularityFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new ShatteredSingularityFarmTileentity(BlockPos.ZERO, ModBlocks.SS_FARM.get().defaultBlockState()));
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
        if (!(tileEntity instanceof ShatteredSingularityFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        List<ItemStack> itemsNeeded = new ArrayList<>(farm.getShatteredSingularityItemsList()); // track what player has added
        boolean updated = false;

// Helper to count how many items of a certain type exist in the list


// Required by default
        Map<Item, Integer> requiredItems = new HashMap<>();
        requiredItems.put(AEItems.SINGULARITY.get(), 1);
        requiredItems.put(AEItems.ENDER_DUST.get(), 2);
        requiredItems.put(AEItems.SKY_DUST.get(), 2);
        requiredItems.put(Items.LAVA_BUCKET, 1);

// Check for upgrade and add additional requirements if present
        boolean hasInscriberUpgrade = Upgrades.getUpgradeStatus(farm.getUpgrades(), ModItems.INSCRIBER_UPGRADE.toStack());
        if (hasInscriberUpgrade) {
            requiredItems.put(AEItems.ENGINEERING_PROCESSOR_PRESS.get(), 1);
            requiredItems.put(AEItems.LOGIC_PROCESSOR_PRESS.get(), 1);
        }

// Process each requirement type
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
            Item requiredItem = entry.getKey();
            int requiredAmount = entry.getValue();

            if (heldItem.is(requiredItem)) {
                int currentAmount = getTotalCount(itemsNeeded, requiredItem);
                int toAdd = Math.min(requiredAmount - currentAmount, heldItem.getCount());

                if (toAdd > 0) {
                    // Remove old stack if present
                    itemsNeeded.removeIf(stack -> stack.is(requiredItem));
                    // Add new combined stack
                    itemsNeeded.add(new ItemStack(requiredItem, currentAmount + toAdd));
                    heldItem.shrink(toAdd);

                    // Special case for lava bucket -> replace with empty bucket
                    if (requiredItem == Items.LAVA_BUCKET) {
                        int slot = player.getInventory().findSlotMatchingItem(heldItem);
                        if (slot != -1) {
                            player.getInventory().setItem(slot, new ItemStack(Items.BUCKET));
                        } else if (!player.getInventory().contains(new ItemStack(Items.BUCKET))) {
                            player.getInventory().add(new ItemStack(Items.BUCKET));
                        }
                    }

                    updated = true;
                    break; // Only process one item per interaction
                }
            }
        }

        if (updated) {
            farm.setShatteredSingularityItemsList(itemsNeeded);

            // Now verify that all requirements are satisfied
            boolean allRequirementsMet = requiredItems.entrySet().stream().allMatch(entry ->
                    getTotalCount(itemsNeeded, entry.getKey()) >= entry.getValue()
            );

            if (allRequirementsMet) {
                return ItemInteractionResult.SUCCESS;
            }

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
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.SS_FARM::get);
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
        return new ShatteredSingularityFarmTileentity(blockPos, blockState);
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
