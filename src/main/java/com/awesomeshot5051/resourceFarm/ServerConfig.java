package com.awesomeshot5051.resourceFarm;

import de.maxhenkel.corelib.config.ConfigBase;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig extends ConfigBase {
    //meat dropping mobs

    public final ModConfigSpec.IntValue copperGenerateTime;
    public final ModConfigSpec.IntValue coalGenerateTime;
    public final ModConfigSpec.IntValue ironGenerateTime;
    public final ModConfigSpec.IntValue goldGenerateTime;
    public final ModConfigSpec.IntValue diamondGenerateTime;
    public final ModConfigSpec.IntValue emeraldGenerateTime;
    public final ModConfigSpec.IntValue redstoneGenerateTime;
    public final ModConfigSpec.IntValue lapisGenerateTime;
    public final ModConfigSpec.IntValue quartzGenerateTime;
    public final ModConfigSpec.IntValue netheriteGenerateTime;
    public final ModConfigSpec.IntValue sandGenerateTime;

    public final ModConfigSpec.BooleanValue universalReputation;

    public ServerConfig(ModConfigSpec.Builder builder) {
        super(builder);

        //neutral mobs
        copperGenerateTime = builder
                .comment("The time in ticks the copper farm takes to generate resources")
                .defineInRange("copper_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        coalGenerateTime = builder
                .comment("The time in ticks the coal farm takes to generate resources")
                .defineInRange("coal_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);


        ironGenerateTime = builder
                .comment("The time in ticks the iron farm takes to generate resources")
                .defineInRange("iron_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        goldGenerateTime = builder
                .comment("The time in ticks the gold farm takes to generate resources")
                .defineInRange("gold_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        diamondGenerateTime = builder
                .comment("The time in ticks the diamond farm takes to generate resources")
                .defineInRange("diamond_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        emeraldGenerateTime = builder
                .comment("The time in ticks the emerald farm takes to generate resources")
                .defineInRange("emerald_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        redstoneGenerateTime = builder
                .comment("The time in ticks the redstone farm takes to generate resources")
                .defineInRange("redstone_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        lapisGenerateTime = builder
                .comment("The time in ticks the lapis farm takes to generate resources")
                .defineInRange("lapis_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        quartzGenerateTime = builder
                .comment("The time in ticks the quartz farm takes to generate resources")
                .defineInRange("quartz_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        netheriteGenerateTime = builder
                .comment("The time in ticks the netherite farm takes to generate resources")
                .defineInRange("netherite_farm.generate_time", 20 * 30, 20 * 30, 20 * 30);

        sandGenerateTime = builder
                .comment("The time in ticks the sand farm takes to generate resources")
                .defineInRange("sand_farm.generate_time", 20 * 20, 20 * 20, 20 * 20);


        universalReputation = builder
                .comment(
                        "If the villager reputation should be the same for every player",
                        "This affects the prices of cured/converted villagers and the prices of the auto trader"
                )
                .define("villager.universal_reputation", true);
    }
}
