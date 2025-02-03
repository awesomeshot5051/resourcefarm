package com.awesomeshot5051.resourceFarm.data.providers.sound;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.sounds.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;

public class BaseSoundProvider extends SoundDefinitionsProvider {

    public BaseSoundProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    public void registerSounds() {

        add(ModSounds.PICKAXE_SOUND, SoundDefinition.definition()

                .with(


                        sound("resource_farms:pickaxe_sound", SoundDefinition.SoundType.SOUND)

                                .volume(0.8f)

                                .pitch(1.2f)

                                .weight(2)

                                .attenuationDistance(8)


                                .stream(true)


                                .preload(true)
                )

                .subtitle("sound.resource_farms.pickaxe_sound")

                .replace(true)
        );
        add(ModSounds.SHOVEL_SOUND, SoundDefinition.definition()

                .with(


                        sound("resource_farms:shovel_sound", SoundDefinition.SoundType.SOUND)

                                .volume(0.8f)

                                .pitch(1.2f)

                                .weight(2)

                                .attenuationDistance(8)


                                .stream(true)


                                .preload(true)
                )

                .subtitle("sound.resource_farms.shovel_sound")

                .replace(true)
        );

    }
}