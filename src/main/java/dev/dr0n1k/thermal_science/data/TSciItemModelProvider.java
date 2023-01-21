package dev.dr0n1k.thermal_science.data;

import cofh.lib.data.ItemModelProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import static cofh.thermal.core.util.RegistrationHelper.deepslate;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;

public class TSciItemModelProvider extends ItemModelProviderCoFH {
    public TSciItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ID_THERMAL_SCIENCE, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Thermal Science: Item Models";
    }

    @Override
    protected void registerModels() {
        // region Items
        this.basicItem(ITEMS.get(ID_GLASS_CUTTER));
        this.basicItem(ITEMS.get(ID_OBSIDIAN_DUST));

        this.registerStandardToolSet("ruby");
        this.registerStandardToolSet("sapphire");
        this.registerStandardToolSet("peridot");
        this.gemSet(ITEMS, "peridot");
        // endregion

        // region Blocks
        this.blockItem(BLOCKS.getSup(ID_REDROCK_COBBLESTONE));
        this.blockItem(BLOCKS.getSup(ID_REDROCK_STONE));
        this.blockItem(BLOCKS.getSup(ID_REDROCK_BRICKS));
        this.blockItem(BLOCKS.getSup(ID_BASALT_COBBLESTONE));
        this.blockItem(BLOCKS.getSup(ID_BASALT_STONE));
        this.blockItem(BLOCKS.getSup(ID_BASALT_PAVER));
        this.blockItem(BLOCKS.getSup(ID_BASALT_BRICKS));
        this.blockItem(BLOCKS.getSup(ID_BASALT_CHISELED_BRICKS));
        this.blockItem(BLOCKS.getSup(ID_MARBLE_STONE));
        this.blockItem(BLOCKS.getSup(ID_MARBLE_BRICKS));
        this.blockItem(BLOCKS.getSup("peridot_block"));
        this.blockItem(BLOCKS.getSup("peridot_ore"));
        this.blockItem(BLOCKS.getSup(deepslate("peridot_ore")));
        // endregion
    }

    // region HELPERS
    private void registerStandardToolSet(String prefix) {
        var reg = ITEMS;

        handheld(reg.getSup(prefix + "_shovel"));
        handheld(reg.getSup(prefix + "_pickaxe"));
        handheld(reg.getSup(prefix + "_axe"));
        handheld(reg.getSup(prefix + "_hoe"));

        handheld(reg.getSup(prefix + "_sword"));
    }
    // endregion
}
