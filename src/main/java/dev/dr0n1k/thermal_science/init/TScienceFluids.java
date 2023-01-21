package dev.dr0n1k.thermal_science.init;

import dev.dr0n1k.thermal_science.fluid.MethaneFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

public class TScienceFluids {

    public static Supplier<ForgeFlowingFluid> METHANE_FLUID;

    public static void register() {
        METHANE_FLUID = MethaneFluid.instance().still();
    }
}
