package dev.dr0n1k.thermal_science.data;

import cofh.lib.data.BlockStateProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import static cofh.thermal.core.util.RegistrationHelper.deepslate;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;

public class TSciBlockStateProvider extends BlockStateProviderCoFH {
    public TSciBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ID_THERMAL_SCIENCE, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Thermal Science: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(BLOCKS.get(ID_REDROCK_COBBLESTONE));
        this.simpleBlock(BLOCKS.get(ID_REDROCK_STONE));
        this.simpleBlock(BLOCKS.get(ID_REDROCK_BRICKS));
        this.simpleBlock(BLOCKS.get(ID_BASALT_COBBLESTONE));
        this.simpleBlock(BLOCKS.get(ID_BASALT_STONE));
        this.simpleBlock(BLOCKS.get(ID_BASALT_PAVER));
        this.simpleBlock(BLOCKS.get(ID_BASALT_BRICKS));
        this.simpleBlock(BLOCKS.get(ID_BASALT_CHISELED_BRICKS));
        this.simpleBlock(BLOCKS.get(ID_MARBLE_STONE));
        this.simpleBlock(BLOCKS.get(ID_MARBLE_BRICKS));
        this.simpleBlock(BLOCKS.get("peridot_block"));
        this.simpleBlock(BLOCKS.get("peridot_ore"));
        this.simpleBlock(BLOCKS.get(deepslate("peridot_ore")));
    }
}
