package dev.dr0n1k.thermal_science.block.entity.machine;

import cofh.lib.api.StorageGroup;
import cofh.lib.client.sounds.ConditionalSoundInstance;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.item.SlotSealItem;
import cofh.thermal.lib.tileentity.MachineTileBase;
import dev.dr0n1k.thermal_science.inventory.container.MachineAssemblyContainer;
import dev.dr0n1k.thermal_science.util.managers.AssemblyRecipeManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static cofh.core.util.helpers.ItemHelper.itemsEqual;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_SMELTER;
import static dev.dr0n1k.thermal_science.init.TScienceTileEntities.MACHINE_ASSEMBLY_TILE;

public class MachineAssemblyTile extends MachineTileBase {

    protected ItemStorageCoFH[] inputSlots = new ItemStorageCoFH[4];

    public MachineAssemblyTile(BlockPos pos, BlockState state) {
        super(MACHINE_ASSEMBLY_TILE.get(), pos, state);
        inputSlots[0] = new ItemStorageCoFH(
                item -> item.getItem() instanceof SlotSealItem ||
                        filter.valid(item) && AssemblyRecipeManager.instance().validItem(item)
                && !itemsEqual(item, inputSlots[1].getItemStack())
                && !itemsEqual(item, inputSlots[2].getItemStack())
                && !itemsEqual(item, inputSlots[3].getItemStack())
        );
        inputSlots[1] = new ItemStorageCoFH(
                item -> item.getItem() instanceof SlotSealItem ||
                        filter.valid(item) && AssemblyRecipeManager.instance().validItem(item)
                                && !itemsEqual(item, inputSlots[0].getItemStack())
                                && !itemsEqual(item, inputSlots[2].getItemStack())
                                && !itemsEqual(item, inputSlots[3].getItemStack())
        );
        inputSlots[2] = new ItemStorageCoFH(
                item -> item.getItem() instanceof SlotSealItem ||
                        filter.valid(item) && AssemblyRecipeManager.instance().validItem(item)
                                && !itemsEqual(item, inputSlots[0].getItemStack())
                                && !itemsEqual(item, inputSlots[1].getItemStack())
                                && !itemsEqual(item, inputSlots[3].getItemStack())
        );
        inputSlots[3] = new ItemStorageCoFH(
                item -> item.getItem() instanceof SlotSealItem ||
                        filter.valid(item) && AssemblyRecipeManager.instance().validItem(item)
                                && !itemsEqual(item, inputSlots[0].getItemStack())
                                && !itemsEqual(item, inputSlots[1].getItemStack())
                                && !itemsEqual(item, inputSlots[2].getItemStack())
        );
        for (int i = 0; i < 4; ++i) {
            inventory.addSlot(inputSlots[i], StorageGroup.INPUT);
        }
        inventory.addSlots(StorageGroup.OUTPUT, 1);
        inventory.addSlot(chargeSlot, StorageGroup.INTERNAL);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {
        return AssemblyRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {
        curRecipe = AssemblyRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

//    @Override
//    protected void resolveInputs() {
//        //Input Items
//        for (int i = 0; i < 4; ++i) {
//            inputSlots[i].modify(-itemInputCounts.get(i));
//        }
//    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {
        if (!cacheRecipe()) {
            return false;
        }
        List<? extends ItemStorageCoFH> slotInputs = inputSlots();
        for (int i = 0; i < slotInputs.size() && i < itemInputCounts.size(); ++i) {
            int inputCount = itemInputCounts.get(i);
            if (slotInputs.get(i).getItemStack().getCount() < inputCount) {
                return false;
            }
        }
        return true;
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new MachineAssemblyContainer(i, level, getBlockPos(), inventory, player);
    }

    @Override
    protected Object getSound() {
        return new ConditionalSoundInstance(SOUND_MACHINE_SMELTER.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
    }
}
