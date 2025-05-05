package com.awesomeshot5051.resourceFarm.OreFarmDir;

import com.awesomeshot5051.resourceFarm.base.*;
import com.awesomeshot5051.resourceFarm.blocks.*;
import net.minecraft.network.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.neoforged.neoforge.items.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class OreFarmMenu extends AbstractFarmMenu<OreFarmBlockEntity> {

    // Client Constructor
    public OreFarmMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf extraData) {
        this(containerId, playerInv, getBlockEntity(playerInv, extraData));
    }

    public OreFarmMenu(int containerId, @NotNull Inventory playerInv, OreFarmBlockEntity blockEntity) {
        this(containerId, playerInv, blockEntity, new SimpleContainerData(2));
    }


    public OreFarmMenu(int containerId, @NotNull Inventory playerInv, OreFarmBlockEntity blockEntity, ContainerData data) {
        super(ModBlocks.ORE_FARM_MENU.get(), containerId, playerInv, blockEntity, data);
        addBlockEntitySlots();
    }

    private static OreFarmBlockEntity getBlockEntity(Inventory playerInv, RegistryFriendlyByteBuf extraData) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null");
        Objects.requireNonNull(extraData, "Extra Data cannot be null");
        final var blockEntity = playerInv.player.level().getBlockEntity(extraData.readBlockPos());
        if (blockEntity instanceof OreFarmBlockEntity be) {
            return be;
        }
        throw new IllegalStateException("Block entity is not correct type! " + blockEntity);
    }

    @Override
    protected void addBlockEntitySlots() {
        // Access the handlers from the block entity
        IItemHandler inputSlots = blockEntity.getInputSlotsHandler();
        IItemHandler upgradeSlots = blockEntity.getUpgradeSlotsItemHandler();
        IItemHandler outputSlots = blockEntity.getOutputSlotsItemHandler();

        // Input Slots
        this.addSlot(new SlotItemHandler(inputSlots, 0, 98, 14)); // Tool
        this.addSlot(new SlotItemHandler(inputSlots, 1, 116, 14)); // Ingredient
        this.addSlot(new SlotItemHandler(inputSlots, 2, 134, 14)); // Base

        // Upgrade Slots
        this.addSlot(new SlotItemHandler(upgradeSlots, 0, 98, 34));
        this.addSlot(new SlotItemHandler(upgradeSlots, 1, 116, 34));
        this.addSlot(new SlotItemHandler(upgradeSlots, 2, 134, 34));

        // Output Slots
        int outputSlotsYStart = 74;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 9; i++) {
                this.addSlot(new SlotItemHandler(outputSlots, i + j * 9, 8 + i * 18, outputSlotsYStart + j * 18));
            }
        }
    }

    @Override
    protected int getBlockEntitySlotCount() {
        return OreFarmBlockEntity.TOTAL_SLOT_COUNT;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(player, ModBlocks.ORE_FARM_BLOCK.get());
    }
}