package dev.dr0n1k.thermal_science.compat.jei.machine;

import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.dr0n1k.thermal_science.client.gui.MachineAssemblyScreen;
import dev.dr0n1k.thermal_science.util.recipes.AssemblyRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.catalyzedOutputTooltip;
import static dev.dr0n1k.thermal_science.Constants.ID_MACHINE_ASSEMBLY;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;

public class AssemblyRecipeCategory extends ThermalRecipeCategory<AssemblyRecipe> {
    public AssemblyRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<AssemblyRecipe> type) {
        super(guiHelper, icon, type);
        background = guiHelper.drawableBuilder(MachineAssemblyScreen.TEXTURE, 26, 11, 128, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_ASSEMBLY).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progress = guiHelper.createAnimatedDrawable(
                Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW),
                200, IDrawableAnimated.StartDirection.LEFT, false
        );
    }

    @Override
    public RecipeType<AssemblyRecipe> getRecipeType() {
        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AssemblyRecipe recipe, IFocusGroup focuses) {
        List<Ingredient> inputs = recipe.getInputItems();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());

        for (ItemStack stack : recipe.getOutputItems()) {
            outputs.add(cloneStack(stack));
        }
        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                outputs.get(i).setCount((int) chance);
            }
        }
        IRecipeSlotBuilder[] inputSlots = new IRecipeSlotBuilder[4];
        IRecipeSlotBuilder[] outputSlots = new IRecipeSlotBuilder[1];
        inputSlots[0] = builder.addSlot(RecipeIngredientRole.INPUT, 21, 23);
        inputSlots[1] = builder.addSlot(RecipeIngredientRole.INPUT, 43, 11);
        inputSlots[2] = builder.addSlot(RecipeIngredientRole.INPUT, 65, 23);
        inputSlots[3] = builder.addSlot(RecipeIngredientRole.INPUT, 43, 36);

        for (int i = 0; i < inputs.size(); ++i) {
            inputSlots[i].addIngredients(inputs.get(i));
        }

        outputSlots[0] = builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 23);
        for (int i = 0; i < outputs.size(); ++i) {
            outputSlots[i].addItemStack(outputs.get(i))
                    .addTooltipCallback(catalyzedOutputTooltip(recipe.getOutputItemChances().get(i), recipe.isCatalyzable()));
        }
    }

    @Override
    public void draw(AssemblyRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);
        progressBackground.draw(matrixStack, 88, 22);
        progress.draw(matrixStack, 88, 22);
    }
}
