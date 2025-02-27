package com.awesomeshot5051.resourceFarm.blocks.overworld.soil;

import com.awesomeshot5051.corelib.block.*;
import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.corelib.client.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.render.overworld.soil.*;
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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.*;

import static net.minecraft.world.item.BlockItem.*;

public class ConcretePowderFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public static final EnumProperty<PickaxeType> PICKAXE_TYPE = EnumProperty.create("pickaxe_type", PickaxeType.class);
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);

    public ConcretePowderFarmBlock() {
        super(Properties.of().mapColor(MapColor.STONE).strength(2.5F).sound(SoundType.STONE).noOcclusion());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR);
    }

    @Override
    public Item toItem() {
        return new CustomRendererBlockItem(this, new Item.Properties()) {
            @OnlyIn(Dist.CLIENT)
            @Override
            public ItemRenderer createItemRenderer() {
                return new ConcretePowderFarmItemRenderer();
            }
        };
    }

    public void dyeBlock(BlockState state, Level worldIn, DyeColor dyeColor, BlockPos pos) {
        BlockState newState = state.setValue(COLOR, dyeColor);
        worldIn.setBlock(pos, newState, 3);
    }

    private void playSound(@NotNull Level level, BlockState state, SoundEvent sound, ConcretePowderFarmTileentity farm) {
        Vec3i vec3i = state.getValue(FACING).getNormal();
        double d0 = farm.getBlockPos().getX() + 0.5D + (double) vec3i.getX() / 2.0D;
        double d1 = farm.getBlockPos().getY() + 0.5D + (double) vec3i.getY() / 2.0D;
        double d2 = farm.getBlockPos().getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
        level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 5F, 10 * 0.1F + 0.9F);
    }

    private DyeColor getDyeColor(ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }
        if (stack.getItem().equals(Items.WHITE_DYE)) {
            return DyeColor.WHITE;
        } else if (stack.getItem().equals(Items.ORANGE_DYE)) {
            return DyeColor.ORANGE;
        } else if (stack.getItem().equals(Items.MAGENTA_DYE)) {
            return DyeColor.MAGENTA;
        } else if (stack.getItem().equals(Items.LIGHT_BLUE_DYE)) {
            return DyeColor.LIGHT_BLUE;
        } else if (stack.getItem().equals(Items.YELLOW_DYE)) {
            return DyeColor.YELLOW;
        } else if (stack.getItem().equals(Items.LIME_DYE)) {
            return DyeColor.LIME;
        } else if (stack.getItem().equals(Items.PINK_DYE)) {
            return DyeColor.PINK;
        } else if (stack.getItem().equals(Items.GRAY_DYE)) {
            return DyeColor.GRAY;
        } else if (stack.getItem().equals(Items.LIGHT_GRAY_DYE)) {
            return DyeColor.LIGHT_GRAY;
        } else if (stack.getItem().equals(Items.CYAN_DYE)) {
            return DyeColor.CYAN;
        } else if (stack.getItem().equals(Items.PURPLE_DYE)) {
            return DyeColor.PURPLE;
        } else if (stack.getItem().equals(Items.BLUE_DYE)) {
            return DyeColor.BLUE;
        } else if (stack.getItem().equals(Items.BROWN_DYE)) {
            return DyeColor.BROWN;
        } else if (stack.getItem().equals(Items.GREEN_DYE)) {
            return DyeColor.GREEN;
        } else if (stack.getItem().equals(Items.RED_DYE)) {
            return DyeColor.RED;
        } else if (stack.getItem().equals(Items.BLACK_DYE)) {
            return DyeColor.BLACK;
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            ItemContainerContents defaultType = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_SHOVEL)));
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
        super.appendHoverText(stack, context, components, tooltipFlag);
        ConcretePowderFarmTileentity trader = BlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new ConcretePowderFarmTileentity(BlockPos.ZERO, ModBlocks.CONCRETE_POWDER_FARM.get().defaultBlockState()));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ConcretePowderFarmTileentity farmTileEntity) {
            ItemContainerContents shovelType = stack.get(ModDataComponents.PICK_TYPE);
            if (stack.has(ModDataComponents.UPGRADE)) {
                farmTileEntity.upgradeList = stack.getOrDefault(ModDataComponents.UPGRADE, ItemContainerContents.EMPTY).stream().toList();
            }
            if (shovelType != null) {
                farmTileEntity.shovelType = shovelType.getStackInSlot(0);
                farmTileEntity.setChanged();
                updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, shovelType.getStackInSlot(0));
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof ConcretePowderFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        DyeColor dyeColor = getDyeColor(heldItem);
        if (dyeColor != null) {
            dyeBlock(state, worldIn, dyeColor, pos);
            return ItemInteractionResult.SUCCESS;
        }
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.CONCRETE_POWDER_FARM::get);
            }
        });
        return ItemInteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConcretePowderFarmTileentity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, BlockState state, BlockEntityType<T> p_155268_) {
        return new SimpleBlockEntityTicker<>();
    }

    private String convertToReadableName(String block) {

        String readableName = block.replace("item.minecraft.", "").replace("item.resource_farms.", "").replace('_', ' ');

        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
