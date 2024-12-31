package com.awesomeshot5051.resourceFarm.data.providers.models;

import net.minecraft.client.data.models.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.server.packs.*;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.*;

import java.util.logging.*;

public abstract class BaseBlockModelProvider extends BlockModelGenerators {

    public BaseBlockModelProvider(PackOutput output, String modid) {
        super(output, modid,);
    }


    public BlockModelBuilder sideBottomTop(Item item) {
        // Create the block model
        return withExistingParent(item.toString(), modLoc("block/farm"))
                .texture("front", "minecraft:block/stone")
                .texture("back", "minecraft:block/stone");
    }


    public boolean textureExists(ResourceLocation texture) {
        return existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
    }
}
