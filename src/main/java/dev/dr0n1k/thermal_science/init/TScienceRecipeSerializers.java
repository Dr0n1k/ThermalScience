package dev.dr0n1k.thermal_science.init;

import cofh.thermal.lib.util.recipes.MachineRecipeSerializer;
import dev.dr0n1k.thermal_science.util.managers.AssemblyRecipeManager;
import dev.dr0n1k.thermal_science.util.recipes.AssemblyRecipe;
import net.minecraftforge.registries.RegistryObject;

import static dev.dr0n1k.thermal_science.Constants.ID_ASSEMBLY_RECIPE;
import static dev.dr0n1k.thermal_science.ThermalScience.RECIPE_SERIALIZERS;

public class TScienceRecipeSerializers {
    public static void register() {

    }

    public static final RegistryObject<MachineRecipeSerializer<AssemblyRecipe>> ASSEMBLY_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register(ID_ASSEMBLY_RECIPE,
                    () -> new MachineRecipeSerializer<>(AssemblyRecipe::new, AssemblyRecipeManager.instance().getDefaultEnergy())
            );
}
