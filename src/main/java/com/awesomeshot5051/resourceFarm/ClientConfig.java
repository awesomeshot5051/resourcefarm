package com.awesomeshot5051.resourceFarm;

import com.awesomeshot5051.corelib.config.*;
import net.neoforged.neoforge.common.*;

public class ClientConfig extends ConfigBase {


    public final ModConfigSpec.BooleanValue pickaxeSoundRendered;

    public ClientConfig(ModConfigSpec.Builder builder) {
        super(builder);
        pickaxeSoundRendered = builder
                .comment("Whether or not the pickaxe mining sounds will be played")
                .define("pickaxeSound", true);

    }
}
