package com.awesomeshot5051.resourceFarm;


import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

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
                    ModItems.AEITEMS.getEntries().forEach(
                            blockEntry -> {
                                Item item = blockEntry.get();
                                output.accept(new ItemStack(item));
                            }
                    );
                }
                if (Main.eae2_installed) {
                    ModBlocks.EAE2_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                    ModItems.EAEITEMS.getEntries().forEach(
                            blockEntry -> {
                                Item item = blockEntry.get();
                                output.accept(new ItemStack(item));
                            }
                    );
                }
                if (Main.aae2_installed) {
                    ModBlocks.AAE2_REGISTER.getEntries().forEach(blockEntry -> {
                        Block block = blockEntry.get();
                        output.accept(new ItemStack(block.asItem()));
                    });
                }
                ModItems.ITEMS.getEntries().forEach(
                        blockEntry -> {
                            Item item = blockEntry.get();
                            output.accept(new ItemStack(item));
                        }
                );
            })
            .title(Component.translatable("itemGroup.resource_farms"))
            .build());

    public static void init(IEventBus eventBus) {
        TAB_REGISTER.register(eventBus);
    }

}
