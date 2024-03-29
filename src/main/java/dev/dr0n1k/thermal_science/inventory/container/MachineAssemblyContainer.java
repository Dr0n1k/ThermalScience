package dev.dr0n1k.thermal_science.inventory.container;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.ReconfigurableTile4Way;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static dev.dr0n1k.thermal_science.init.TScienceContainers.MACHINE_ASSEMBLY_CONTAINER;

public class MachineAssemblyContainer extends TileContainer {
    public final ReconfigurableTile4Way tile;
    public MachineAssemblyContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {
        super(MACHINE_ASSEMBLY_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 31, 34));
        addSlot(new SlotCoFH(tileInv, 1, 53, 22));
        addSlot(new SlotCoFH(tileInv, 2, 53, 47));
        addSlot(new SlotCoFH(tileInv, 3, 75, 34));

        addSlot(new SlotRemoveOnly(tileInv, 4, 131, 34));
        addSlot(new SlotCoFH(tileInv, 5, 8, 53));

        bindAugmentSlots(tileInv, 6, this.tile.augSize());
        bindPlayerInventory(inventory);
    }
}
