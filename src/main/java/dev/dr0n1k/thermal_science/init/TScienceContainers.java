package dev.dr0n1k.thermal_science.init;

import dev.dr0n1k.thermal_science.inventory.container.MachineAssemblyContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

import static cofh.core.util.ProxyUtils.getClientPlayer;
import static cofh.core.util.ProxyUtils.getClientWorld;
import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static dev.dr0n1k.thermal_science.Constants.ID_MACHINE_ASSEMBLY;

public class TScienceContainers {
    public static void register() {}

    public static final RegistryObject<MenuType<MachineAssemblyContainer>> MACHINE_ASSEMBLY_CONTAINER =
            CONTAINERS.register(ID_MACHINE_ASSEMBLY, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineAssemblyContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
}
