package dev.dr0n1k.thermal_science.event;

import dev.dr0n1k.thermal_science.block.CursedEarth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.dr0n1k.thermal_science.Constants.ID_CURSED_EARTH;
import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;

@Mod.EventBusSubscriber(modid = ID_THERMAL_SCIENCE)
public class CommonEvents {

    @SubscribeEvent
    public static void onCurseDirt(PlayerInteractEvent.RightClickBlock event) {
        Player player  = event.getEntity();
        Level level = player.getLevel();
        BlockPos pos = event.getPos();
        Item item = Registry.ITEM.getOptional(new ResourceLocation(CursedEarth.itemCreate))
                .orElse(Items.WITHER_ROSE);

        if (!level.isClientSide() && event.getItemStack().is(item) &&
                level.getBlockState(pos).is(BlockTags.MINEABLE_WITH_SHOVEL)
        ) {
            level.setBlock(pos, BLOCKS.get(ID_CURSED_EARTH).defaultBlockState(), 3);
            event.getItemStack().shrink(1);
            event.setCanceled(true);
        }
    }
}
