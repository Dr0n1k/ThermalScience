package dev.dr0n1k.thermal_science.data;

import cofh.lib.data.RecipeProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class TSciRecipeProvider extends RecipeProviderCoFH {
    public TSciRecipeProvider(DataGenerator generator) {
        super(generator, ID_THERMAL);
    }

    @Override
    public @NotNull String getName() {
        return "Thermal Science: Recipes";
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        generateCraftingRecipes(consumer);
    }

    private void generateCraftingRecipes(Consumer<FinishedRecipe> consumer) {

    }
}
