package dev.dr0n1k.thermal_science.util.recipes;

import cofh.lib.fluid.FluidIngredient;
import cofh.thermal.lib.util.recipes.ThermalRecipe;
import dev.dr0n1k.thermal_science.util.managers.AssemblyRecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static dev.dr0n1k.thermal_science.init.TScienceRecipeSerializers.ASSEMBLY_RECIPE_SERIALIZER;
import static dev.dr0n1k.thermal_science.init.TScienceRecipeTypes.ASSEMBLY_RECIPE;

public class AssemblyRecipe extends ThermalRecipe {
    public AssemblyRecipe(ResourceLocation recipeId, int energy, float xp, List<Ingredient> inputItems, List<FluidIngredient> inputFluids, List<ItemStack> outputItems, List<Float> outputItemChances, List<FluidStack> outputFluids) {
        super(recipeId, energy, xp, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
        if (this.energy <= 0) {
            int defaultEnergy = AssemblyRecipeManager.instance().getDefaultEnergy();
            this.energy = defaultEnergy;
        }
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ASSEMBLY_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ASSEMBLY_RECIPE.get();
    }
}
