package com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate;


import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.blocks.BlockBase;
import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.DeepslateCoalOreFarmTileentity;
import com.awesomeshot5051.resourceFarm.datacomponents.FarmBlockEntityData;
import com.awesomeshot5051.resourceFarm.datacomponents.VillagerBlockEntityData;
import com.awesomeshot5051.resourceFarm.enums.PickaxeType;
import com.awesomeshot5051.resourceFarm.gui.OutputContainer;
import com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate.DeepslateCoalOreFarmItemRenderer;
import com.awesomeshot5051.resourceFarm.recipe.ModDataComponents;
import de.maxhenkel.corelib.block.IItemBlock;
import de.maxhenkel.corelib.blockentity.SimpleBlockEntityTicker;
import de.maxhenkel.corelib.client.CustomRendererBlockItem;
import de.maxhenkel.corelib.client.ItemRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static net.minecraft.world.item.BlockItem.setBlockEntityData;
import static net.minecraft.world.item.BlockItem.updateCustomBlockEntityTag;

public class DeepslateCoalOreFarmBlock extends BlockBase implements EntityBlock, IItemBlock {

    public static final EnumProperty<PickaxeType> PICKAXE_TYPE = EnumProperty.create("pickaxe_type", PickaxeType.class);

    public DeepslateCoalOreFarmBlock() {
        super(Properties.of().mapColor(MapColor.STONE).strength(2.5F).sound(SoundType.STONE).noOcclusion()); // Adjusted for enderman farm
//        Objects.requireNonNull(this.asItem().getDefaultInstance().get(ModDataComponents.PICK_TYPE)).getStackInSlot(0);
    }

    @Override
    public Item toItem() {
        return new CustomRendererBlockItem(this, new Item.Properties()) {
            @OnlyIn(Dist.CLIENT)
            @Override
            public ItemRenderer createItemRenderer() {
                return new DeepslateCoalOreFarmItemRenderer();
            }
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            if (stack.get(ModDataComponents.PICK_TYPE) != null) {
//                Objects.requireNonNull(stack.get(ModDataComponents.PICK_TYPE)).getStackInSlot(0);
                components.add(Component.literal("This block uses the " + Objects.requireNonNull(stack.get(ModDataComponents.PICK_TYPE)).getStackInSlot(0)));
            }
        }

        super.appendHoverText(stack, context, components, tooltipFlag);
        DeepslateCoalOreFarmTileentity trader = VillagerBlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new DeepslateCoalOreFarmTileentity(BlockPos.ZERO, ModBlocks.DCOAL_FARM.get().defaultBlockState()));
        DeepslateCoalOreFarmTileentity trader2 = FarmBlockEntityData.getAndStoreBlockEntity(stack, context.registries(), context.level(), () -> new DeepslateCoalOreFarmTileentity(BlockPos.ZERO, ModBlocks.DCOAL_FARM.get().defaultBlockState()));
        // Removed villager-related tooltip information

    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        // Get the block entity
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof DeepslateCoalOreFarmTileentity farmTileEntity) {
            // Check for the pick type in the item stack
            ItemContainerContents pickType = stack.get(ModDataComponents.PICK_TYPE);
            if (pickType != null) {
                farmTileEntity.pickType = pickType.getStackInSlot(0);
//                DeepslateCoalOreFarmRenderer.setStaticFarmstack(pickType.getStackInSlot(0));
                // Ensure the tile entity is marked as changed and synced
                farmTileEntity.setChanged();
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putString("id", farmTileEntity.pickType.getItem().builtInRegistryHolder().key().location().toString()); // Save the item ID
                compoundTag.putInt("count", farmTileEntity.pickType.getCount()); // Save the count
                setBlockEntityData(stack, blockEntity.getType(), compoundTag);
                updateCustomBlockEntityTag(level, placer instanceof Player ? (Player) placer : null, pos, pickType.getStackInSlot(0));
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (!(tileEntity instanceof DeepslateCoalOreFarmTileentity farm)) {// Check for EndermanFarmTileentity
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
                return new OutputContainer(id, playerInventory, farm.getOutputInventory(), ContainerLevelAccess.create(worldIn, pos), ModBlocks.DCOAL_FARM::get); // Adjust for enderman farm
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
//        Objects.requireNonNull(this.asItem().getDefaultInstance().get(ModDataComponents.PICK_TYPE)).getStackInSlot(0);
//        this.asItem().getDefaultInstance().get(ModDataComponents.PICK_TYPE).getStackInSlot(0);
        Main.LOGGER.info("Success!");
        return new DeepslateCoalOreFarmTileentity(blockPos, blockState); // Spawn EndermanFarmTileentity
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
