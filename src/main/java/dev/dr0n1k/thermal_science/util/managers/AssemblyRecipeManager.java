package dev.dr0n1k.thermal_science.util.managers;

import cofh.lib.api.fluid.IFluidStackHolder;
import cofh.lib.api.inventory.IItemStackHolder;
import cofh.lib.util.crafting.ComparableItemStack;
import cofh.thermal.lib.util.managers.AbstractManager;
import cofh.thermal.lib.util.managers.IRecipeManager;
import cofh.thermal.lib.util.recipes.IThermalInventory;
import cofh.thermal.lib.util.recipes.ThermalRecipe;
import cofh.thermal.lib.util.recipes.internal.BaseMachineRecipe;
import cofh.thermal.lib.util.recipes.internal.IMachineRecipe;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

import static dev.dr0n1k.thermal_science.init.TScienceRecipeTypes.ASSEMBLY_RECIPE;
import static java.util.Arrays.asList;

public class AssemblyRecipeManager extends AbstractManager implements IRecipeManager {

    private static final AssemblyRecipeManager INSTANCE = new AssemblyRecipeManager();
    protected static final int DEFAULT_ENERGY = 3200;

    protected Map<AssemblyRecipeManager.AssemblyMapWrapper, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();
    protected Set<ComparableItemStack> validItems = new ObjectOpenHashSet<>();
    protected int maxInputItems;
    protected int maxOutputItems;
    protected int maxOutputFluids;

    public static AssemblyRecipeManager instance() {
        return INSTANCE;
    }

    public AssemblyRecipeManager() {
        super(DEFAULT_ENERGY);
        this.maxInputItems = 4;
        this.maxOutputItems = 1;
        this.maxOutputFluids = 0;
    }

    // region IRecipeManager
    @Override
    public IMachineRecipe getRecipe(IThermalInventory inventory) {
        return getRecipe(inventory.inputSlots(), inventory.inputTanks());
    }

    @Override
    public List<IMachineRecipe> getRecipeList() {
        return new ArrayList<>(recipeMap.values());
    }
    // endregion

    // region RECIPES
    protected IMachineRecipe getRecipe(List<? extends IItemStackHolder> inputSlots, List<? extends IFluidStackHolder> inputTanks) {
        if (inputSlots.isEmpty()) {
            return null;
        }
        List<ComparableItemStack> convertedItems = new ArrayList<>(maxInputItems);
        for (int i = 0; i < maxInputItems; ++i) {
            if (!inputSlots.get(i).isEmpty()) {
                ComparableItemStack compStack = makeComparable(inputSlots.get(i).getItemStack());
                convertedItems.add(compStack);
            }
        }
        if (convertedItems.isEmpty()) {
            return null;
        }
        return recipeMap.get(new AssemblyMapWrapper(convertedItems));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids, BaseMachineRecipe.RecipeType type) {

        if (inputItems.isEmpty() || outputItems.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        for (ItemStack stack : inputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        for (ItemStack stack : outputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        List<ComparableItemStack> convertedItems = new ArrayList<>(inputItems.size());
        for (ItemStack stack : inputItems) {
            if (!inputItems.isEmpty()) {
                ComparableItemStack compStack = makeComparable(stack);
                validItems.add(compStack);
                convertedItems.add(compStack);
            }
        }
        energy = (int) (energy * getDefaultScale());

        IMachineRecipe recipe = new BaseMachineRecipe(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(new AssemblyMapWrapper(convertedItems), recipe);
        return recipe;
    }

    public void addRecipe(ThermalRecipe recipe, BaseMachineRecipe.RecipeType type) {
        switch (recipe.getInputItems().size()) {
            case 1 -> {
                for (ItemStack firstInput : recipe.getInputItems().get(0).getItems()) {
                    addRecipe(recipe.getEnergy(), recipe.getXp(), Collections.singletonList(firstInput), Collections.emptyList(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids(), type);
                }
            }
            case 2 -> {
                for (ItemStack firstInput : recipe.getInputItems().get(0).getItems()) {
                    for (ItemStack secondInput : recipe.getInputItems().get(1).getItems()) {
                        addRecipe(recipe.getEnergy(), recipe.getXp(), asList(firstInput, secondInput), Collections.emptyList(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids(), type);
                    }
                }
            }
            case 3 -> {
                for (ItemStack firstInput : recipe.getInputItems().get(0).getItems()) {
                    for (ItemStack secondInput : recipe.getInputItems().get(1).getItems()) {
                        for (ItemStack thirdInput : recipe.getInputItems().get(2).getItems()) {
                            addRecipe(recipe.getEnergy(), recipe.getXp(), asList(firstInput, secondInput, thirdInput), Collections.emptyList(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids(), type);
                        }
                    }
                }
            }
            case 4 -> {
                for (ItemStack firstInput : recipe.getInputItems().get(0).getItems()) {
                    for (ItemStack secondInput : recipe.getInputItems().get(1).getItems()) {
                        for (ItemStack thirdInput : recipe.getInputItems().get(2).getItems()) {
                            for (ItemStack fourInput : recipe.getInputItems().get(3).getItems()) {
                                addRecipe(recipe.getEnergy(), recipe.getXp(), asList(firstInput, secondInput, thirdInput, fourInput), Collections.emptyList(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids(), type);
                            }
                        }
                    }
                }
            }
            default -> {
            }
        }
    }
    // endregion

    @Override
    public void refresh(RecipeManager recipeManager) {
        clear();
        var recipes = recipeManager.byType(ASSEMBLY_RECIPE.get());
        for (var entry : recipes.entrySet()) {
            addRecipe(entry.getValue(), BaseMachineRecipe.RecipeType.STANDARD);
        }
    }

    public boolean validItem(ItemStack item) {
        return validItems.contains(makeComparable(item));
    }

    protected void clear() {
        recipeMap.clear();
        validItems.clear();
    }

    // region WRAPPER CLASS
    protected static class AssemblyMapWrapper {
        Set<Integer> itemHashes;
        int hashCode;

        AssemblyMapWrapper(List<ComparableItemStack> itemStacks) {
            this.itemHashes = new ObjectOpenHashSet<>(itemStacks.size());
            for (ComparableItemStack itemStack : itemStacks) {
                if (itemStack.hashCode() != 0) {
                    this.itemHashes.add(itemStack.hashCode());
                    hashCode += itemStack.hashCode();
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AssemblyRecipeManager.AssemblyMapWrapper that = (AssemblyRecipeManager.AssemblyMapWrapper) o;
            return itemHashes.size() == that.itemHashes.size() && itemHashes.containsAll(that.itemHashes);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }
    // endregion
}
