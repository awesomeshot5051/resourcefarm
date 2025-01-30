package com.awesomeshot5051.resourceFarm.integration.theoneprobe;

import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.blocks.tileentity.*;
import mcjty.theoneprobe.api.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;
import java.util.stream.*;

public class TileInfoProvider implements IProbeInfoProvider {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Main.MODID, "probeinfoprovider");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof VillagerTileentity v) {
            addVillager(v, iProbeInfo);
        }
    }

    private void addVillager(VillagerTileentity villager, IProbeInfo iProbeInfo) {
        if (villager != null) {
            IProbeInfo info = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            ItemStack pickType = villager.getPickType();
            if (villager.getPickType() == ItemStack.EMPTY) {
                pickType = villager.getShovelType();
            }
            info.item(pickType).text(convertToReadableName(pickType.getDescriptionId()));
        }
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
