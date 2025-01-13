package com.awesomeshot5051.resourceFarm.blocks.overworld.ores.common.deepslate;


import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.overworld.ores.common.deepslate.*;
import com.awesomeshot5051.resourceFarm.data.*;
import com.awesomeshot5051.resourceFarm.datacomponents.*;
import com.awesomeshot5051.resourceFarm.enums.*;
import com.awesomeshot5051.resourceFarm.gui.*;
import com.awesomeshot5051.resourceFarm.items.render.overworld.ores.common.deepslate.*;
import de.maxhenkel.corelib.block.*;
import de.maxhenkel.corelib.blockentity.*;
import de.maxhenkel.corelib.client.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;

import javax.annotation.*;
import java.util.*;

import static net.minecraft.world.item.BlockItem.*;

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
//                DeepslateCoalOreFarmRenderer.setStaticFarmstack(shovelType.getStackInSlot(0));
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
