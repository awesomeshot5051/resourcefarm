package com.awesomeshot5051.resourceFarm.OreFarmDir;

import com.awesomeshot5051.resourceFarm.base.*;
import com.awesomeshot5051.resourceFarm.base.recipe.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.util.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.common.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class OreFarmBlockEntity extends AbstractFarmBlockEntity {
    public static final int INPUT_SLOTS_COUNT = 3;
    public static final int TOTAL_SLOT_COUNT = INPUT_SLOTS_COUNT + UPGRADE_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;
    public static final int TOOL_INPUT_SLOT = 0;
    public static final int INGREDIENT_INPUT_SLOT = 1;
    public static final int BASE_INPUT_SLOT = 2;
    private static final Component TITLE = ModBlocks.ORE_FARM_BLOCK.get().getName();
    private final CustomItemStackHandler inputSlots;

    public OreFarmBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.ORE_FARM_BLOCK_ENTITY.get(), pos, blockState);
        this.inputSlots = new CustomItemStackHandler(INPUT_SLOTS_COUNT, this);

        this.combinedInvWrapper = createCombinedWrapper();
    }

    @Override
    protected RecipeType<? extends Recipe<? extends RecipeInput>> getRecipeType() {
        return ModBlocks.ORE_FARM_TYPE.get();
    }

    @Override
    protected RecipeInput createRecipeInput() {
        return new ThreeSlotFarmInput(
                this.inputSlots.getStackInSlot(TOOL_INPUT_SLOT),
                this.inputSlots.getStackInSlot(INGREDIENT_INPUT_SLOT),
                this.inputSlots.getStackInSlot(BASE_INPUT_SLOT)
        );
    }

    @Override
    protected CustomItemStackHandler getInputSlotsHandler() {
        return this.inputSlots;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInv, @NotNull Player player) {
        return new OreFarmMenu(containerId, playerInv, this, this.data);
    }

    private List<OreFarmRecipe> getAllRecipes() {
        List<OreFarmRecipe> vanillaRecipes = new ArrayList<>();

        // Fetch vanilla recipes from the RecipeManager
        assert this.level != null;
        var recipes = this.level.getRecipeManager().getRecipesFor(ModBlocks.ORE_FARM_TYPE.get(), );

        // Loop through recipes and filter for ore-related ones
        for (RecipeHolder<?> recipeHolder : recipes) {
            // Ensure the recipe is an instance of OreFarmRecipe
            if (recipeHolder.getRecipe() instanceof OreFarmRecipe oreFarmRecipe) {
                // Check if the result item is an ore
                if (oreFarmRecipe.getResultItem(level.registryAccess()).is(Tags.Items.ORES)) {
                    vanillaRecipes.add(oreFarmRecipe);
                }
            }
        }

        // Combine with dynamically generated recipes
        vanillaRecipes.addAll(this.dynamicRecipes);

        return vanillaRecipes;
    }


    @Override
    protected void craftItem() {
        if (!(level instanceof ServerLevel serverLevel)) return;
        // --- Call common logic AFTER specific actions ---
        // This inserts the recipe results into the output slots
        super.craftItem();
    }

    @Override
    protected Optional<RecipeHolder<Recipe<RecipeInput>>> getCurrentRecipe() {
        // Use the recipe type and input provided by the subclass
        @SuppressWarnings("unchecked")
        RecipeType<Recipe<RecipeInput>> castedRecipeType = (RecipeType<Recipe<RecipeInput>>) this.getRecipeType();
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(castedRecipeType, this.createRecipeInput(), this.level);
    }
}
