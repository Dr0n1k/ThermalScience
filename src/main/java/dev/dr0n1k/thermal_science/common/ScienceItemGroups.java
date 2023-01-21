package dev.dr0n1k.thermal_science.common;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalIDs.ID_ENDERIUM_BLOCK;

public class ScienceItemGroups {
    private ScienceItemGroups() {
    }

    public static final Comparator<ItemStack> MOD_ID_COMPARISON = (stackA, stackB) -> {
        String modA = stackA.getItem().getCreatorModId(stackA);
        String modB = stackB.getItem().getCreatorModId(stackB);
        return modA == null || modB == null ? 0 : modA.compareTo(modB);
    };

    public static final CreativeModeTab THERMAL_SCIENCE = new CreativeModeTab(-1, ID_THERMAL + ".science") {
        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> list) {
            super.fillItemList(list);
            list.sort(MOD_ID_COMPARISON);
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ITEMS.get(ID_ENDERIUM_BLOCK));
        }
    };
}
