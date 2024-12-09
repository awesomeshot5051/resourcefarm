package com.awesomeshot5051.resourceFarm;

import de.maxhenkel.corelib.config.ConfigBase;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig extends ConfigBase {
    //meat dropping mobs
    public final ModConfigSpec.BooleanValue dropsCookedMeat;


    //passive mobs
    public final ModConfigSpec.IntValue chickenSpawnTime;
    public final ModConfigSpec.IntValue codSpawnTime;
    public final ModConfigSpec.IntValue cowSpawnTime;
    public final ModConfigSpec.IntValue glowSquidSpawnTime;
    public final ModConfigSpec.IntValue horseSpawnTime;
    public final ModConfigSpec.IntValue mooshroomSpawnTime;
    public final ModConfigSpec.IntValue parrotSpawnTime;
    public final ModConfigSpec.IntValue pigSpawnTime;
    public final ModConfigSpec.IntValue pufferfishSpawnTime;
    public final ModConfigSpec.IntValue rabbitSpawnTime;
    public final ModConfigSpec.IntValue salmonSpawnTime;
    public final ModConfigSpec.IntValue sheepSpawnTime;
    public final ModConfigSpec.IntValue snowGolemSpawnTime;
    public final ModConfigSpec.IntValue squidSpawnTime;
    public final ModConfigSpec.IntValue striderSpawnTime;
    public final ModConfigSpec.IntValue tropicalFishSpawnTime;
    public final ModConfigSpec.IntValue turtleSpawnTime;

    //neutral mobs
    public final ModConfigSpec.IntValue spiderSpawnTime;
    public final ModConfigSpec.IntValue copperGenerateTime;
    public final ModConfigSpec.IntValue goatSpawnTime;
    public final ModConfigSpec.IntValue golemSpawnTime;
    public final ModConfigSpec.IntValue piglinSpawnTime;
    public final ModConfigSpec.IntValue zombifiedPiglinSpawnTime;


    //aggressive mobs
    public final ModConfigSpec.IntValue blazeSpawnTime;
    public final ModConfigSpec.IntValue creeperSpawnTime;
    public final ModConfigSpec.IntValue drownedSpawnTime;
    public final ModConfigSpec.IntValue elderGuardianSpawnTime;
    public final ModConfigSpec.IntValue evokerSpawnTime;
    public final ModConfigSpec.IntValue ghastSpawnTime;
    public final ModConfigSpec.IntValue guardianSpawnTime;
    public final ModConfigSpec.IntValue hoglinSpawnTime;
    public final ModConfigSpec.IntValue illusionerSpawnTime;
    public final ModConfigSpec.IntValue magmaCubeSpawnTime;
    public final ModConfigSpec.IntValue phantomSpawnTime;
    public final ModConfigSpec.IntValue pillagerSpawnTime;
    public final ModConfigSpec.IntValue ravagerSpawnTime;
    public final ModConfigSpec.IntValue shulkerSpawnTime;
    public final ModConfigSpec.IntValue skeletonSpawnTime;
    public final ModConfigSpec.IntValue slimeSpawnTime;
    public final ModConfigSpec.IntValue vexSpawnTime;
    public final ModConfigSpec.IntValue vindicatorSpawnTime;
    public final ModConfigSpec.IntValue wardenSpawnTime;
    public final ModConfigSpec.IntValue witchSpawnTime;
    public final ModConfigSpec.IntValue witherSkeletonSpawnTime;
    public final ModConfigSpec.IntValue zoglinSpawnTime;
    public final ModConfigSpec.IntValue zombieSpawnTime;
    public final ModConfigSpec.BooleanValue universalReputation;
    public final ModConfigSpec.IntValue witherSpawnTime;


    public ServerConfig(ModConfigSpec.Builder builder) {
        super(builder);


        dropsCookedMeat = builder
                .comment("Whether the mob drops cooked meat")
                .define("dropsCookedMeat", true); // defaulting to false


        //passive mobs
        chickenSpawnTime = builder.comment("The time in ticks the chicken farm takes to spawn a chicken")
                .defineInRange("chicken_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        codSpawnTime = builder.comment("The time in ticks the cod farm takes to spawn a cod")
                .defineInRange("cod_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        cowSpawnTime = builder.comment("The time in ticks the cow farm takes to spawn a cow")
                .defineInRange("cow_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        glowSquidSpawnTime = builder.comment("The time in ticks the glow squid farm takes to spawn a glow squid")
                .defineInRange("glow_squid_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        horseSpawnTime = builder.comment("The time in ticks the horse farm takes to spawn a horse")
                .defineInRange("horse_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        mooshroomSpawnTime = builder.comment("The time in ticks the mooshroom farm takes to spawn a mooshroom")
                .defineInRange("mooshroom_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        parrotSpawnTime = builder.comment("The time in ticks the parrot farm takes to spawn a parrot")
                .defineInRange("parrot_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        pigSpawnTime = builder.comment("The time in ticks the pig farm takes to spawn a pig")
                .defineInRange("pig_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        pufferfishSpawnTime = builder.comment("The time in ticks the pufferfish farm takes to spawn a pufferfish")
                .defineInRange("pufferfish_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        rabbitSpawnTime = builder.comment("The time in ticks the rabbit farm takes to spawn a rabbit")
                .defineInRange("rabbit_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        salmonSpawnTime = builder.comment("The time in ticks the salmon farm takes to spawn a salmon")
                .defineInRange("salmon_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        sheepSpawnTime = builder.comment("The time in ticks the sheep farm takes to spawn a sheep")
                .defineInRange("sheep_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        snowGolemSpawnTime = builder.comment("The time in ticks the snow golem farm takes to spawn a snow golem")
                .defineInRange("snow_golem_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        squidSpawnTime = builder.comment("The time in ticks the squid farm takes to spawn a squid")
                .defineInRange("squid_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        striderSpawnTime = builder.comment("The time in ticks the strider farm takes to spawn a strider")
                .defineInRange("strider_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        tropicalFishSpawnTime = builder.comment("The time in ticks the tropical fish farm takes to spawn a tropical fish")
                .defineInRange("tropical_fish_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        turtleSpawnTime = builder.comment("The time in ticks the turtle farm takes to spawn a turtle")
                .defineInRange("turtle_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        //neutral mobs
        copperGenerateTime = builder
                .comment("The time in ticks the enderman farm takes to spawn an enderman")
                .defineInRange("enderman_farm.spawn_time", 20, 20 * 9, 20 * 9);

        goatSpawnTime = builder
                .comment("The time in ticks the goat farm takes to spawn a goat")
                .defineInRange("goat_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        golemSpawnTime = builder
                .comment("The time in ticks the iron farm takes to spawn an iron golem")
                .defineInRange("iron_golem_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        piglinSpawnTime = builder
                .comment("The time in ticks the piglin farm takes to spawn a piglin")
                .defineInRange("piglin_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        spiderSpawnTime = builder
                .comment("The time in ticks the spider farm takes to spawn a spider")
                .defineInRange("spider_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);
        zombifiedPiglinSpawnTime = builder
                .comment("The time in ticks the zombified piglin farm takes to spawn a zombified piglin")
                .defineInRange("zombified_piglin_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);


        //aggressive mobs
        blazeSpawnTime = builder
                .comment("The time in ticks the blaze farm takes to spawn a blaze")
                .defineInRange("blaze_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        creeperSpawnTime = builder
                .comment("The time in ticks the creeper farm takes to spawn a creeper")
                .defineInRange("creeper_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        drownedSpawnTime = builder
                .comment("The time in ticks the drowned farm takes to spawn a drowned")
                .defineInRange("drowned_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        elderGuardianSpawnTime = builder
                .comment("The time in ticks the elder guardian farm takes to spawn an elder guardian")
                .defineInRange("elder_guardian_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        evokerSpawnTime = builder
                .comment("The time in ticks the evoker farm takes to spawn an evoker")
                .defineInRange("evoker_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        ghastSpawnTime = builder
                .comment("The time in ticks the ghast farm takes to spawn a ghast")
                .defineInRange("ghast_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        guardianSpawnTime = builder
                .comment("The time in ticks the guardian farm takes to spawn a guardian")
                .defineInRange("guardian_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        hoglinSpawnTime = builder
                .comment("The time in ticks the hoglin farm takes to spawn a hoglin")
                .defineInRange("hoglin_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        illusionerSpawnTime = builder
                .comment("The time in ticks the illusioner farm takes to spawn an illusioner")
                .defineInRange("illusioner_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        magmaCubeSpawnTime = builder
                .comment("The time in ticks the magma cube farm takes to spawn a magma cube")
                .defineInRange("magma_cube_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        phantomSpawnTime = builder
                .comment("The time in ticks the phantom farm takes to spawn a phantom")
                .defineInRange("phantom_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        pillagerSpawnTime = builder
                .comment("The time in ticks the pillager farm takes to spawn a pillager")
                .defineInRange("pillager_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        ravagerSpawnTime = builder
                .comment("The time in ticks the ravager farm takes to spawn a ravager")
                .defineInRange("ravager_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        shulkerSpawnTime = builder
                .comment("The time in ticks the shulker farm takes to spawn a shulker")
                .defineInRange("shulker_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        skeletonSpawnTime = builder
                .comment("The time in ticks the skeleton farm takes to spawn a skeleton")
                .defineInRange("skeleton_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        slimeSpawnTime = builder
                .comment("The time in ticks the slime farm takes to spawn a slime")
                .defineInRange("slime_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        vexSpawnTime = builder
                .comment("The time in ticks the vex farm takes to spawn a vex")
                .defineInRange("vex_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        vindicatorSpawnTime = builder
                .comment("The time in ticks the vindicator farm takes to spawn a vindicator")
                .defineInRange("vindicator_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        wardenSpawnTime = builder
                .comment("The time in ticks the warden farm takes to spawn a warden")
                .defineInRange("warden_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        witchSpawnTime = builder
                .comment("The time in ticks the witch farm takes to spawn a witch")
                .defineInRange("witch_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        witherSkeletonSpawnTime = builder
                .comment("The time in ticks the wither skeleton farm takes to spawn a wither skeleton")
                .defineInRange("wither_skeleton_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);
        witherSpawnTime = builder
                .comment("The time in ticks the wither skeleton farm takes to spawn a wither skeleton")
                .defineInRange("wither_skeleton_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        zoglinSpawnTime = builder
                .comment("The time in ticks the zoglin farm takes to spawn a zoglin")
                .defineInRange("zoglin_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        zombieSpawnTime = builder
                .comment("The time in ticks the zombie farm takes to spawn a zombie")
                .defineInRange("zombie_farm.spawn_time", 20 * 3, 20 * 3, 20 * 3);

        universalReputation = builder
                .comment(
                        "If the villager reputation should be the same for every player",
                        "This affects the prices of cured/converted villagers and the prices of the auto trader"
                )
                .define("villager.universal_reputation", true);

    }
}
