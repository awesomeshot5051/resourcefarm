package com.awesomeshot5051.resourceFarm.blocks;

import com.awesomeshot5051.corelib.block.*;
import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

public class BlockBase extends HorizontalRotatableBlock {

    private static final VoxelShape SHAPE = VoxelUtils.combine(
            Block.box(0D, 0D, 0D, 16D, 1D, 16D),
            Block.box(0D, 15D, 0D, 16D, 16D, 16D),
            Block.box(0D, 0D, 0D, 1D, 16D, 16D),
            Block.box(15D, 0D, 0D, 16D, 16D, 16D),
            Block.box(0D, 0D, 0D, 16D, 16D, 1D),
            Block.box(0D, 0D, 15D, 16D, 16D, 16D)
    );
    public static NonNullList<ItemStack> Picktype;

    public BlockBase(Properties properties) {
        super(properties);
    }


//    public static void setPickType(ItemStack shovelType) {
//        Picktype = NonNullList.withSize(1, shovelType);
//    }

    public boolean overrideClick(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn) {
        return player.isShiftKeyDown() && player.getMainHandItem().isEmpty();
    }
//    public static void playRandomVillagerSound(Level world, BlockPos pos, SoundEvent soundEvent) {
//        if (world.getGameTime() % Main.SERVER_CONFIG.villagerSoundAmount.get() == 0 && world.random.nextInt(40) == 0) {
//            playVillagerSound(world, pos, soundEvent);
//        }
//    }
//
//    public static void playRandomVillagerSound(Player player, SoundEvent soundEvent) {
//        if (player.level().getGameTime() % Main.SERVER_CONFIG.villagerSoundAmount.get() == 0 && player.level().random.nextInt(40) == 0) {
//            player.playNotifySound(soundEvent, SoundSource.BLOCKS, 1F, 1F);
//        }
//    }
//
//    public static void playVillagerSound(Level world, BlockPos pos, SoundEvent soundEvent) {
//        world.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, soundEvent, SoundSource.BLOCKS, 1F, 1F);
//    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
