package dev.dr0n1k.thermal_science.init;

import cofh.core.block.TileBlockActive4Way;
import cofh.lib.block.OreBlockCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.RegistrationHelper;
import dev.dr0n1k.thermal_science.block.CursedEarth;
import dev.dr0n1k.thermal_science.block.EnderLilly;
import dev.dr0n1k.thermal_science.block.entity.machine.MachineAssemblyTile;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.PlantType;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.BlockStatePropertiesCoFH.ACTIVE;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.util.RegistrationHelper.deepslate;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.lib.common.ThermalAugmentRules.MACHINE_NO_FLUID_VALIDATOR;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;
import static dev.dr0n1k.thermal_science.init.TScienceTileEntities.MACHINE_ASSEMBLY_TILE;
import static dev.dr0n1k.thermal_science.util.RegistrationHelper.*;

public class TScienceBlocks {

    static final BlockBehaviour.Properties MACHINE_PROPERTIES = BlockBehaviour.Properties.of(Material.METAL)
            .sound(SoundType.NETHERITE_BLOCK).strength(2.0f).lightLevel(lightValue(ACTIVE, 14));
    static final BlockBehaviour.Properties CROP_PROPERTIES = BlockBehaviour.Properties.of(Material.PLANT)
            .noCollission().randomTicks().strength(0.0f, 0.0f).sound(SoundType.CROP);

    public static void register() {
        registerMachines();
        registerResources();
        registerDecorations();
        registerGrowable();
        registerMisc();
    }

    private static void registerMachines() {
        IntSupplier machineAugs = () -> ThermalCoreConfig.machineAugments;

        registerAugmentableBlock(ID_MACHINE_ASSEMBLY,
                () -> new TileBlockActive4Way(MACHINE_PROPERTIES, MachineAssemblyTile.class, MACHINE_ASSEMBLY_TILE),
                machineAugs, MACHINE_NO_FLUID_VALIDATOR, () -> true, ID_THERMAL_SCIENCE
        );
    }

    private static void registerResources() {
        registerBlock("peridot_ore", () -> OreBlockCoFH.createStoneOre().xp(3, 7), () -> true);
        registerBlock(deepslate("peridot_ore"), () -> OreBlockCoFH.createDeepslateOre().xp(3, 7), () -> true);

        registerBlock("peridot_block", () -> storageBlock(MaterialColor.COLOR_LIGHT_GREEN), () -> true);
    }

    private static void registerDecorations() {
        BLOCKS.register(ID_REDROCK_COBBLESTONE, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED)
                        .strength(2.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_REDROCK_STONE, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED)
                        .strength(1.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_REDROCK_BRICKS, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED)
                        .strength(2.0f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));

        BLOCKS.register(ID_BASALT_COBBLESTONE, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                        .strength(2.0f, 5.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_BASALT_STONE, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                        .strength(1.5f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_BASALT_PAVER, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                        .strength(1.5f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_BASALT_BRICKS, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                        .strength(2.0f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_BASALT_CHISELED_BRICKS, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
                        .strength(2.0f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_MARBLE_STONE, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY)
                        .strength(1.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
        BLOCKS.register(ID_MARBLE_BRICKS, () -> new Block(
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY)
                        .strength(1.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()
        ));
    }

    private static void registerGrowable() {
        BLOCKS.register(ID_ENDER_LILLY, () -> new EnderLilly(CROP_PROPERTIES, PlantType.CROP, 0, 1.25f)
                .crop(ITEMS.getSup(ID_ENDER_LILLY)).seed(ITEMS.getSup(seeds(ID_ENDER_LILLY)))
        );
    }

    private static void registerMisc() {
        BLOCKS.register(ID_CURSED_EARTH, () -> new CursedEarth(
                BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5f).sound(SoundType.GRAVEL).randomTicks()
        ));
    }
}
