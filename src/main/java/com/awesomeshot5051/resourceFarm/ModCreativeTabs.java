package com.awesomeshot5051.resourceFarm;


import com.awesomeshot5051.resourceFarm.blocks.*;
import com.awesomeshot5051.resourceFarm.items.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

public class ModCreativeTabs {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_RESOURCE_FARMS = TAB_REGISTER.register("assets/resource_farms", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.COPPER_FARM.get()))
            .displayItems((features, output) -> {
                ModBlocks.BLOCK_REGISTER.getEntries().forEach(blockEntry -> {
                    Block block = blockEntry.get();
                    output.accept(new ItemStack(block.asItem()));
                });
                if (Main.dynamic_installed) {
                    ModBlocks.DYNAMIC_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                }
                if (Main.terminals_installed) {
                    ModBlocks.TERMINALS_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                }
                if (Main.ae2_installed) {
                    ModBlocks.AE2_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                }
                if (Main.eae2_installed) {
                    ModBlocks.EAE2_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                }
                output.accept(new ItemStack(ModItems.SMELTER_UPGRADE.get()));
                output.accept(new ItemStack(ModItems.REDSTONE_UPGRADE.get()));
            })
            .title(Component.translatable("itemGroup.resource_farms"))
            .build());

    public static void init(IEventBus eventBus) {
        TAB_REGISTER.register(eventBus);
    }

}
