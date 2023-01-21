package dev.dr0n1k.thermal_science.init;

import dev.dr0n1k.thermal_science.util.managers.AssemblyRecipeManager;

import static cofh.thermal.lib.common.ThermalRecipeManagers.registerManager;

public class TScienceRecipeManagers {
    public static void register() {
        registerManager(AssemblyRecipeManager.instance());
    }
}
