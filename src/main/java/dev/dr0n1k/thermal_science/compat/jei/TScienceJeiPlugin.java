package dev.dr0n1k.thermal_science.compat.jei;

import dev.dr0n1k.thermal_science.client.gui.MachineAssemblyScreen;
import dev.dr0n1k.thermal_science.compat.jei.machine.AssemblyRecipeCategory;
import dev.dr0n1k.thermal_science.util.recipes.AssemblyRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;
import static dev.dr0n1k.thermal_science.init.TScienceRecipeTypes.ASSEMBLY_RECIPE;

@JeiPlugin
public class TScienceJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            return;
        }

        registration.addRecipes(ASSEMBLY_TYPE, recipeManager.getAllRecipesFor(ASSEMBLY_RECIPE.get()));

        registration.addIngredientInfo(new ItemStack(ITEMS.getSup(ID_CURSED_EARTH).get()), VanillaTypes.ITEM_STACK,
                getTextComponent("info.thermal_science.cursed_earth.desc"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AssemblyRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_ASSEMBLY)), ASSEMBLY_TYPE));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        int progressY = 35;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineAssemblyScreen.class, 94, progressY, progressW, progressH, ASSEMBLY_TYPE);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ID_THERMAL_SCIENCE, "machine");
    }

    // region HELPERS
    private RecipeManager getRecipeManager() {
        RecipeManager recipeManager = null;
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            recipeManager = level.getRecipeManager();
        }
        return recipeManager;
    }
    // endregion

    // region RECIPE TYPES
    public static final RecipeType<AssemblyRecipe> ASSEMBLY_TYPE = new RecipeType<>(ASSEMBLY_RECIPE.getId(), AssemblyRecipe.class);
    // endregion
}
