package com.awesomeshot5051.resourceFarm.sounds;

import com.awesomeshot5051.resourceFarm.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Main.MODID);

    public static final Supplier<SoundEvent> PICKAXE_SOUND = registerSoundEvent("pickaxe_sound");
    public static final Supplier<SoundEvent> SHOVEL_SOUND = registerSoundEvent("shovel_sound");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Main.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
