package com.awesomeshot5051.resourceFarm.gui;

import com.awesomeshot5051.resourceFarm.blocks.tileentity.InventoryViewerTileentity;
import com.awesomeshot5051.resourceFarm.entity.EasyVillagerEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ModInventorySlot extends Slot {

    protected final InventoryViewerTileentity inventoryViewer;

    public ModInventorySlot(Container c, int index, int xPos, int yPos, InventoryViewerTileentity inventoryViewer) {
        super(c, index, xPos, yPos);
        this.inventoryViewer = inventoryViewer;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        EasyVillagerEntity v = inventoryViewer.getVillagerEntity();
        if (v == null) {
            return false;
        }
        return super.mayPlace(stack) && v.wantsToPickUp(stack);
    }

}
