package com.awesomeshot5051.resourceFarm.gui;

import de.maxhenkel.corelib.inventory.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;

@SuppressWarnings("ALL")
public abstract class InputOutputContainer extends ModContainerBase {

    public InputOutputContainer(MenuType type, int id, Inventory playerInventory, Container inputInventory, Container outputInventory, ContainerLevelAccess access) {
        super(type, id, playerInventory, null, access);

        for (int i = 0; i < 4; i++) {
            addSlot(getInputSlot(inputInventory, i, 52 + i * 18, 20));
        }

        for (int i = 0; i < 4; i++) {
            addSlot(new LockedSlot(outputInventory, i, 52 + i * 18, 51, true, false));
        }

        addPlayerInventorySlots();
    }

    public InputOutputContainer(MenuType type, int id, Inventory playerInventory) {
        this(type, id, playerInventory, new SimpleContainer(4), new SimpleContainer(4), ContainerLevelAccess.NULL);
    }

    @Override
    public int getInvOffset() {
        return -2;
    }

    @Override
    public int getInventorySize() {
        return 8;
    }

    public abstract Slot getInputSlot(Container inventory, int id, int x, int y);

}
