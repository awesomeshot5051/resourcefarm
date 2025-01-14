package com.awesomeshot5051.resourceFarm.integration.theoneprobe;

import mcjty.theoneprobe.api.*;

import java.util.function.*;

public class TheOneProbeModule implements Function<ITheOneProbe, Void> {

    @Override
    public Void apply(ITheOneProbe input) {
        input.registerProvider(new TileInfoProvider());
        return null;
    }

}