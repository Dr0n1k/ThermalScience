package dev.dr0n1k.thermal_science.init;

import cofh.lib.util.recipes.SerializableRecipeType;
import dev.dr0n1k.thermal_science.util.recipes.AssemblyRecipe;
import net.minecraftforge.registries.RegistryObject;

import static dev.dr0n1k.thermal_science.Constants.ID_ASSEMBLY_RECIPE;
import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;
import static dev.dr0n1k.thermal_science.ThermalScience.RECIPE_TYPES;

public class TScienceRecipeTypes {
    public static void register() {

    }

    public static final RegistryObject<SerializableRecipeType<AssemblyRecipe>> ASSEMBLY_RECIPE =
            RECIPE_TYPES.register(ID_ASSEMBLY_RECIPE, () -> new SerializableRecipeType<>(ID_THERMAL_SCIENCE, ID_ASSEMBLY_RECIPE));
}
