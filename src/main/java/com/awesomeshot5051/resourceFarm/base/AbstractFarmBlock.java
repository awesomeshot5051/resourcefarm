package com.awesomeshot5051.resourceFarm.base;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;

/**
 * Abstract base class for farm blocks providing common interaction and removal logic.
 * Subclasses must implement newBlockEntity and should typically provide a BlockEntityTicker.
 */
public abstract class AbstractFarmBlock extends Block implements EntityBlock {

    protected AbstractFarmBlock(Properties properties) {
        super(properties);
    }

    /**
     * Helper method often used in getTicker implementations.
     * Provides a type-safe way to return a ticker function.
     * Consider making this static in a utility class or copying it into subclasses if preferred.
     *
     * @param pGivenType    The BlockEntityType provided to getTicker.
     * @param pExpectedType The specific BlockEntityType the ticker is for.
     * @param pTicker       The ticker function (lambda).
     * @param <E>           The specific BlockEntity type the ticker operates on.
     * @param <A>           The BlockEntity type parameter from getTicker.
     * @return The ticker function if types match, null otherwise.
     */
    @SuppressWarnings("unchecked") // Need to cast due to differing generic types E and A
    protected static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> pGivenType,
            BlockEntityType<E> pExpectedType,
            BlockEntityTicker<? super E> pTicker) {
        return pGivenType == pExpectedType ? (BlockEntityTicker<A>) pTicker : null;
    }

    // Subclasses MUST implement this to return their specific BlockEntity instance.
    @Override
    public abstract @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state);

    /**
     * Handles player right-click interaction to open the farm's menu.
     */
    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        // Execute on server side only
        if (level.isClientSide) {
            return InteractionResult.SUCCESS; // Indicate success on client to prevent item use animation
        }

        // Optional: Only allow interaction with main hand
        // if (player.getUsedItemHand() != InteractionHand.MAIN_HAND) {
        //     return InteractionResult.PASS;
        // }

        BlockEntity be = level.getBlockEntity(pos);
        // Check if the BlockEntity exists and is an instance of our abstract farm BE
        if (!(be instanceof AbstractFarmBlockEntity abstractBE)) {
            // Should not happen if block state mapping is correct, but good practice to check
            return InteractionResult.PASS;
        }

        // Open the menu for the player
        if (player instanceof ServerPlayer serverPlayer) {
            // The AbstractFarmBlockEntity must implement MenuProvider
            serverPlayer.openMenu(abstractBE, pos);
            // Optional: Add network hooks or stats here if needed
        } else {
            // Should not happen on server unless player entity is wrong type
            return InteractionResult.FAIL;
        }

        return InteractionResult.CONSUME; // Consume the interaction
    }

    /**
     * Handles dropping the inventory contents when the block is removed.
     */
    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
        // Only drop items if the block *type* actually changed, not just the state
        // And ensure we're not just being moved by a piston
        if (!state.is(newState.getBlock()) && !movedByPiston) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof AbstractFarmBlockEntity abstractBE) {
                // Delegate dropping logic to the BlockEntity
                abstractBE.drops();
            }
            // Note: We don't remove the BlockEntity here; Level.removeBlock does that.
        }
        // IMPORTANT: Always call super implementation
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    /**
     * Provides the BlockEntityTicker.
     * It's generally recommended for the CONCRETE block subclass to override this method.
     * This ensures the BlockEntityType check (<T> type vs specific BE type) is correct
     * and allows direct calling of the specific tick method (e.g., MyFarmBE::tick).
     * <p>
     * Example implementation in subclass (e.g., OreFarmBlock):
     * <pre>{@code
     * @Override
     * public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
     *     if (level.isClientSide()) {
     *         return null; // No client-side ticking needed usually
     *     }
     *     // Check expected type and return ticker function
     *     return createTickerHelper(blockEntityType, ModRegistry.CROP_FARM_BLOCK_ENTITY.get(),
     *                              (lvl, p, st, be) -> be.tick(lvl, p, st));
     * }
     * }</pre>
     * <p>
     * If no ticker is needed, the subclass doesn't need to override this.
     */
    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        // Default implementation returns null. Subclasses should override if ticking is required.
        return null;
    }
}
