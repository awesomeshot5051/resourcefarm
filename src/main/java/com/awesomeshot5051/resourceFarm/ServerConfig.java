package com.awesomeshot5051.resourceFarm;

import de.maxhenkel.corelib.config.ConfigBase;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig extends ConfigBase {
    //meat dropping mobs

    public final ModConfigSpec.IntValue copperGenerateTime;
    public final ModConfigSpec.IntValue coalGenerateTime;
    public final ModConfigSpec.BooleanValue universalReputation;

    public ServerConfig(ModConfigSpec.Builder builder) {
        super(builder);

        //neutral mobs
        copperGenerateTime = builder
                .comment("The time in ticks the enderman farm takes to spawn an enderman")
                .defineInRange("enderman_farm.spawn_time", 20, 20 * 9, 20 * 9);


        coalGenerateTime = builder
                .comment("The time in ticks the spider farm takes to spawn a spider")
                .defineInRange("spider_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        universalReputation = builder
                .comment(
                        "If the villager reputation should be the same for every player",
                        "This affects the prices of cured/converted villagers and the prices of the auto trader"
                )
                .define("villager.universal_reputation", true);
    }
}
