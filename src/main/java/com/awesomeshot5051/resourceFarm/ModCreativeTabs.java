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
                output.accept(new ItemStack(ModBlocks.COPPER_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DCOPPER_FARM.get()));
                output.accept(new ItemStack(ModBlocks.COAL_FARM.get()));
                output.accept(new ItemStack(ModBlocks.DCOAL_FARM.get()));
            })
            .title(Component.translatable("itemGroup.resource_farms"))
            .build());

    public static void init(IEventBus eventBus) {
        TAB_REGISTER.register(eventBus);
    }

}
