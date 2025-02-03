package com.awesomeshot5051.resourceFarm.data.providers.models;

import com.awesomeshot5051.resourceFarm.Main;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {


    }


    private void basicItemWithFarmTexture(Item item) {
        getBuilder(item.toString())
                .parent(getExistingFile(mcLoc("block/block")))
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 45, 0)
                .translation(0, 0, 0)
                .scale(0.625f, 0.625f, 0.625f)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .rotation(0, 0, 0)
                .translation(0, 3, 0)
                .scale(0.25f, 0.25f, 0.25f)
                .end()
                .transform(ItemDisplayContext.HEAD)
                .rotation(0, 180, 0)
                .translation(0, 0, 0)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0, 180, 0)
                .translation(0, 0, 0)
                .scale(0.5f, 0.5f, 0.5f)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75, 315, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f, 0.375f, 0.375f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 315, 0)
                .translation(0, 0, 0)
                .scale(0.4f, 0.4f, 0.4f)
                .end();
    }
}
