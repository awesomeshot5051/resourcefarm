package com.awesomeshot5051.resourceFarm.integration.theoneprobe;

import com.awesomeshot5051.corelib.blockentity.*;
import com.awesomeshot5051.resourceFarm.*;
import com.awesomeshot5051.resourceFarm.integration.ae2.Fluix.*;
import mcjty.theoneprobe.api.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;
import java.util.stream.*;

import static com.awesomeshot5051.resourceFarm.blocks.BlockBase.*;

public class TileInfoProvider implements IProbeInfoProvider {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Main.MODID, "probeinfoprovider");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof FarmTileentity v) {
            addFarm(v, iProbeInfo);
        }
//        if (te instanceof FluidFarmTileentity v) {
//            addFarm(v, iProbeInfo);
//        }
    }


    private void addFarm(FarmTileentity farmTileentity, IProbeInfo iProbeInfo) {
        if (farmTileentity instanceof FluixCrystalFarmTileentity blockEntity && !farmTileentity.checkPasses(farmTileentity)) {
            IProbeInfo info = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            if (Screen.hasShiftDown()) {
                for (ItemStack item : blockEntity.getAE2ItemsList()) {
                    info.item(item).text(convertMinecraftToReadableName(item.toString()));
                }
            } else {
                info.text("Items Missing!").text("Hold Shift for more info");
            }
        }
        if (farmTileentity != null) {
            IProbeInfo info = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            ItemStack pickType = farmTileentity.getPickType();
            if (farmTileentity.getPickType() == ItemStack.EMPTY) {
                pickType = farmTileentity.getShovelType();
            }
            if (!farmTileentity.getCustomData().isEmpty()) {
                info.text(Component.literal(
                        Arrays.stream(farmTileentity.getCustomData()
                                        .toString()
                                        .replace("{}", " ")
                                        .replace("{Upgrade:\"", "")
                                        .replace("\"}", "")
                                        .split("_"))
                                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                                .collect(Collectors.joining(" "))
                ));
            }
            info.item(pickType).text(convertToReadableName(pickType.getDescriptionId()));
        }
    }

    private String convertToReadableName(String block) {

        String readableName = block.replace("item.minecraft.", "").replace('_', ' ');

        return Arrays.stream(readableName.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}
