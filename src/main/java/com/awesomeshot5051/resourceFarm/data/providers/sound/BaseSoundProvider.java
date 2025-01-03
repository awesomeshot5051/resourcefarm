package com.awesomeshot5051.resourceFarm.data.providers.sound;

import com.awesomeshot5051.resourceFarm.Main;
import com.awesomeshot5051.resourceFarm.sounds.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;

public class BaseSoundProvider extends SoundDefinitionsProvider {
    // Parameters can be obtained from GatherDataEvent.
    public BaseSoundProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        // Accepts a Supplier<SoundEvent>, a SoundEvent, or a ResourceLocation as the first parameter.
        add(ModSounds.PICKAXE_SOUND, SoundDefinition.definition()
                // Add sound objects to the sound definition. Parameter is a vararg.
                .with(
                        // Accepts either a string or a ResourceLocation as the first parameter.
                        // The second parameter can be either SOUND or EVENT, and can be omitted if the former.
                        sound("resource_farms:pickaxe_sound", SoundDefinition.SoundType.SOUND)
                                // Sets the volume. Also has a double counterpart.
                                .volume(0.8f)
                                // Sets the pitch. Also has a double counterpart.
                                .pitch(1.2f)
                                // Sets the weight.
                                .weight(2)
                                // Sets the attenuation distance.
                                .attenuationDistance(8)
                                // Enables streaming.
                                // Also has a parameterless overload that defers to stream(true).
                                .stream(true)
                                // Enables preloading.
                                // Also has a parameterless overload that defers to preload(true).
                                .preload(true)
                )
                // Sets the subtitle.
                .subtitle("sound.resource_farms.pickaxe_sound")
                // Enables replacing.
                .replace(true)
        );
        add(ModSounds.SHOVEL_SOUND, SoundDefinition.definition()
                // Add sound objects to the sound definition. Parameter is a vararg.
                .with(
                        // Accepts either a string or a ResourceLocation as the first parameter.
                        // The second parameter can be either SOUND or EVENT, and can be omitted if the former.
                        sound("resource_farms:shovel_sound", SoundDefinition.SoundType.SOUND)
                                // Sets the volume. Also has a double counterpart.
                                .volume(0.8f)
                                // Sets the pitch. Also has a double counterpart.
                                .pitch(1.2f)
                                // Sets the weight.
                                .weight(2)
                                // Sets the attenuation distance.
                                .attenuationDistance(8)
                                // Enables streaming.
                                // Also has a parameterless overload that defers to stream(true).
                                .stream(true)
                                // Enables preloading.
                                // Also has a parameterless overload that defers to preload(true).
                                .preload(true)
                )
                // Sets the subtitle.
                .subtitle("sound.resource_farms.shovel_sound")
                // Enables replacing.
                .replace(true)
        );

    }
}