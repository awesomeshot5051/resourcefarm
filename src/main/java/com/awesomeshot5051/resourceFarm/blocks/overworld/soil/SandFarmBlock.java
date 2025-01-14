package com.awesomeshot5051.resourceFarm.blocks.overworld.soil;

import com.awesomeshot5051.resourceFarm.blocks.BlockBase;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.soil.SandFarmTileentity;
import com.awesomeshot5051.resourceFarm.data.ModDataComponents;
import com.awesomeshot5051.resourceFarm.datacomponents.FarmBlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.VillagerBlockEntityData;
import com.awesomeshot5051.resourceFarm.enums.PickaxeType;
import com.awesomeshot5051.resourceFarm.gui.OutputContainer;
import com.awesomeshot5051.resourceFarm.items.render.overworld.soil.SandFarmItemRenderer;
import com.awesomeshot5051.resourceFarm.sounds.ModSounds;
import de.maxhenkel.corelib.block.IItemBlock;
import de.maxhenkel.corelib.blockentity.SimpleBlockEntityTicker;
import de.maxhenkel.corelib.client.CustomRendererBlockItem;
import de.maxhenkel.corelib.client.ItemRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.minecraft.world.item.BlockItem.updateCustomBlockEntityTag;

@SuppressWarnings("ALL")
public class SandFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public static final EnumProperty<PickaxeType> PICKAXE_TYPE = EnumProperty.create("pickaxe_type", PickaxeType.class);

    public SandFarmBlock() {
        super(Properties.of().mapColor(MapColor.STONE).strength(2.5F).sound(SoundType.STONE).noOcclusion());
    }

    @Override
    public Item toItem() {
        return new CustomRendererBlockItem(this, new Item.Properties()) {
            @OnlyIn(Dist.CLIENT)
            @Override
            public ItemRenderer createItemRenderer() {
                return new SandFarmItemRenderer();
            }
        };
    }

    private void playSound(@NotNull Level level, BlockState state, SoundEvent sound, SandFarmTileentity farm) {
        Vec3i vec3i = state.getValue(FACING).getNormal();
        double d0 = farm.getBlockPos().getX() + 0.5D + (double) vec3i.getX() / 2.0D;
        double d1 = farm.getBlockPos().getY() + 0.5D + (double) vec3i.getY() / 2.0D;
        double d2 = farm.getBlockPos().getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
        level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 5F, 10 * 0.1F + 0.9F);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            ItemContainerContents defaultType = ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.WOODEN_SHOVEL)));
            ItemStack pickType = ItemContainerContents.fromItems(Collections.singletonList(Objects.requireNonNull(stack.getOrDefault(ModDataComponents.PICK_TYPE, defaultType)).copyOne())).copyOne();
            components.add(Component.literal("This farm has a " + convertToReadableName(pickType.getItem().getDefaultInstance().getDescriptionId()) + " on it.")
                    .withStyle(ChatFormatting.RED));
        } else {
            components.add(Component.literal("Hold §4Shift§r to see tool").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, context, components, tooltipFlag);
        SandFarmTileentity trader = VillagerBlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new SandFarmTileentity(BlockPos.ZERO, ModBlocks.SAND_FARM.get().defaultBlockState()));
        SandFarmTileentity trader2 = FarmBlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new SandFarmTileentity(BlockPos.ZERO, ModBlocks.SAND_FARM.get().defaultBlockState()));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SandFarmTileentity farmTileEntity) {
            ItemContainerContents shovelType = stack.get(ModDataComponents.PICK_TYPE);
            if (shovelType != null) {
                farmTileEntity.shovelType = shovelType.getStackInSlot(0);
                farmTileEntity.setChanged();
                updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, shovelType.getStackInSlot(0));
                level.sendBlockUpdated(pos, state, state, 3);
            }
            playSound(level, state, ModSounds.SHOVEL_SOUND.get(), farmTileEntity);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof SandFarmTileentity farm)) {
            return super.useItemOn(heldItem, state, worldIn, pos, player, handIn, hit);
        }
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable(state.getBlock().getDescriptionId());
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.SAND_FARM::get);
            }
        });
        return ItemInteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SandFarmTileentity(blockPos, blockState); // Spawn EndermanFarmTileentity
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level1, BlockState state, BlockEntityType<T> p_155268_) {
        return new SimpleBlockEntityTicker<>();
    }

    private String convertToReadableName(String block) {
        // Remove "item.minecraft." and replace underscores with spaces
        String readableName = block.replace("item.minecraft.", "").replace('_', ' ');
        // Capitalize the first letter of each word
        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
