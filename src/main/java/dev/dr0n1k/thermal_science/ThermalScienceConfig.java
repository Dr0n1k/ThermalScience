package dev.dr0n1k.thermal_science;

import cofh.core.config.IBaseConfig;
import dev.dr0n1k.thermal_science.block.CursedEarth;
import dev.dr0n1k.thermal_science.util.managers.AssemblyRecipeManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class ThermalScienceConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {
        builder.push("Utilities");
            builder.push("Cursed Earth");
            itemCreateCursedEarth = builder
                    .comment("Item to create Cursed Earth")
                    .define("item", "minecraft:wither_rose");
            builder.pop();
        builder.pop();

        builder.push("Machines");
            builder.push("Assembly");
                machineAssemblyPower = builder
                        .comment("This sets the base power consumption (RF/t) for the Assembly Machine.")
                        .defineInRange("Base Power", AssemblyRecipeManager.instance().getBasePower(), AssemblyRecipeManager.instance().getMinPower(), AssemblyRecipeManager.instance().getMaxPower());
            builder.pop();
        builder.pop();
    }

    @Override
    public void refresh() {
        if (itemCreateCursedEarth != null) {
            CursedEarth.itemCreate = itemCreateCursedEarth.get();
        }
    }

    private Supplier<String> itemCreateCursedEarth;
    private Supplier<Integer> machineAssemblyPower;
}
