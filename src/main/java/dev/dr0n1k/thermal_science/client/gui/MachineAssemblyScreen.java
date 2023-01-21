package dev.dr0n1k.thermal_science.client.gui;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import dev.dr0n1k.thermal_science.inventory.container.MachineAssemblyContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;

public class MachineAssemblyScreen extends MachineScreenReconfigurable<MachineAssemblyContainer> {

    public static final String TEX_PATH = ID_THERMAL_SCIENCE + ":textures/gui/container/assembly.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);
    public MachineAssemblyScreen(MachineAssemblyContainer container, Inventory inv, Component titleIn) {
        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal_science.machine_assembly"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal_science.machine_assembly");
        name = "assembly";
    }

    @Override
    public void init() {
        super.init();
        addElement(createInputSlot(this, 31, 34, tile));
        addElement(createInputSlot(this, 53, 22, tile));
        addElement(createInputSlot(this, 53, 47, tile));
        addElement(createInputSlot(this, 75, 34, tile));
        addElement(createLargeOutputSlot(this, 131, 34, tile));

        addElement(ThermalGuiHelper.createDefaultProgress(this, 97, 33, PROG_ARROW_RIGHT, tile));
    }
}
