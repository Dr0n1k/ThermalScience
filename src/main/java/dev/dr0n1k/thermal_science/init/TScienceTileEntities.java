package dev.dr0n1k.thermal_science.init;

import dev.dr0n1k.thermal_science.block.entity.machine.MachineAssemblyTile;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import static dev.dr0n1k.thermal_science.Constants.ID_MACHINE_ASSEMBLY;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCK_ENTITIES;

public class TScienceTileEntities {
    public static void register() {

    }

    public static final RegistryObject<BlockEntityType<?>> MACHINE_ASSEMBLY_TILE = BLOCK_ENTITIES.register(ID_MACHINE_ASSEMBLY,
            () -> BlockEntityType.Builder.of(MachineAssemblyTile::new, BLOCKS.get(ID_MACHINE_ASSEMBLY)).build(null));
}
