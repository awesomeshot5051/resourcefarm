package com.awesomeshot5051.resourceFarm.data.providers.models;

import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.server.packs.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

public abstract class BaseBlockModelProvider extends BlockModelProvider {

    public BaseBlockModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @NotNull
    @Override
    public String getName() {
        return "Block model provider: " + modid;
    }

    public BlockModelBuilder sideBottomTop(Item item) {

        return withExistingParent(item.toString(), modLoc("block/farm"))
                .texture("front", "minecraft:block/stone")
                .texture("back", "minecraft:block/stone");
    }

    public BlockModelBuilder waterRenderer(Block block) {
        ResourceLocation fluidTexture = ResourceLocation.fromNamespaceAndPath("resource_farms", "textures/fluid/waterstill.png");
        ResourceLocation blockTexture = ResourceLocation.fromNamespaceAndPath("resource_farms", "textures/block/waterstill.png");

        String texturePath = textureExists(fluidTexture) ? "resource_farms:fluid/waterstill" : "resource_farms:block/waterstill";

        return withExistingParent(block.asItem().toString(), modLoc("block/farm"))
                .texture("front", texturePath)
                .texture("back", texturePath);
    }

    public boolean textureExists(ResourceLocation texture) {
        return existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
    }

}
