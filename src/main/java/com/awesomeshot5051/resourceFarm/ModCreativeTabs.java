package com.awesomeshot5051.resourceFarm;


import com.awesomeshot5051.resourceFarm.blocks.ModBlocks;
import com.awesomeshot5051.resourceFarm.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_RESOURCE_FARMS = TAB_REGISTER.register("assets/resource_farms", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.COPPER_FARM.get())) // Use your custom item here
            .displayItems((features, output) -> {


//                output.accept(new ItemStack(ModBlocks.INVENTORY_VIEWER.get()));
                /// Common Regular
                output.accept(new ItemStack(ModBlocks.COPPER_FARM.get()));
                output.accept(new ItemStack(ModBlocks.COAL_FARM.get()));
                output.accept(new ItemStack(ModBlocks.GOLD_FARM.get()));
                output.accept(new ItemStack(ModBlocks.IRON_FARM.get()));

                /// uncommon Regular
                output.accept(new ItemStack(ModBlocks.REDSTONE_FARM.get()));

                /// rare regular
                output.accept(new ItemStack(ModBlocks.LAPIS_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DIAMOND_FARM.get()));

                /// very rare regular
                output.accept(new ItemStack(ModBlocks.EMERALD_FARM.get()));


                /// common deepslate
                output.accept(new ItemStack(ModBlocks.DCOPPER_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DIRON_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DGOLD_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DCOAL_FARM.get()));


                /// uncommon deepslate
                output.accept(new ItemStack(ModBlocks.DREDSTONE_FARM.get()));


                /// rare deepslate
                output.accept(new ItemStack(ModBlocks.DLAPIS_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DDIAMOND_FARM.get()));

                /// very rare deepslate
                output.accept(new ItemStack(ModBlocks.DEMERALD_FARM.get()));


                output.accept(new ItemStack(ModBlocks.SAND_FARM.get()));
                output.accept(new ItemStack(ModBlocks.CONCRETE_POWDER_FARM.get()));

            })
            .title(Component.translatable("itemGroup.resource_farms"))
            .build());

    public static void init(IEventBus eventBus) {
        TAB_REGISTER.register(eventBus);
    }

}
