package com.awesomeshot5051.resourceFarm.blocks.overworld.rock.common;


import com.awesomeshot5051.corelib.block.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.client.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.rock.common.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.render.overworld.rock.common.*;
import net.minecraft.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
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

public class StoneFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public StoneFarmBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.5F).sound(SoundType.METAL).noOcclusion()); // Adjusted for stone farm
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
                return new StoneFarmItemRenderer(); // Custom stone farm renderer
            }
        };
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof StoneFarmTileentity farmTileEntity) {
            farmTileEntity.upgradeEnabled = stack.has(DataComponents.CUSTOM_DATA);
            farmTileEntity.customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
            ItemContainerContents pickType = stack.get(ModDataComponents.PICK_TYPE);
            if (pickType != null) {
                farmTileEntity.pickType = pickType.getStackInSlot(0);
                farmTileEntity.setChanged();
                updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, pickType.getStackInSlot(0));
                level.sendBlockUpdated(pos, state, state, 3);
            }

        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, components, tooltipFlag);
        StoneFarmTileentity trader = VillagerBlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new StoneFarmTileentity(BlockPos.ZERO, ModBlocks.STONE_FARM.get().defaultBlockState()));
        if (Screen.hasShiftDown()) {
            ItemContainerContents defaultType = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_PICKAXE)));
            ItemStack pickType = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(stack.getOrDefault(ModDataComponents.PICK_TYPE, defaultType)).copyOne())).copyOne();
            components.add(Component.literal("This farm has a " + convertToReadableName(pickType.getItem().getDefaultInstance().getDescriptionId()) + " on it.")
                    .withStyle(ChatFormatting.RED));
        } else {
            components.add(Component.literal("Hold §4Shift§r to see tool").withStyle(ChatFormatting.YELLOW));
        }
        components.add(Component.literal(
                Arrays.stream(stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY)
                                .toString()
                                .replace("{}", " ")
                                .replace("{Upgrade:\"", "") // Remove the prefix
                                .replace("\"}", "") // Remove the suffix
                                .split("_")) // Split by underscores
                        .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1)) // Capitalize each word
                        .collect(Collectors.joining(" ")) // Join words back with spaces
        ));


    }

    private String convertToReadableName(String block) {
        // Remove "item.minecraft." and replace underscores with spaces
        String readableName = block.replace("item.minecraft.", "").replace('_', ' ');
        // Capitalize the first letter of each word
        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof StoneFarmTileentity farm)) { // Check for StoneFarmTileentity
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }

        // Directly open the container without villager checks
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.STONE_FARM::get); // Adjust for stone farm
            }
        });
        return ItemInteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, BlockState state, BlockEntityType<T> type) {
        return new SimpleBlockEntityTicker<>(); // Keeps default behavior
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StoneFarmTileentity(blockPos, blockState); // Spawn StoneFarmTileentity
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
