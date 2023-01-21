package dev.dr0n1k.thermal_science.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_SCIENCE)
public class TSciDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        TSciTagsProvider.Block blockTags = new TSciTagsProvider.Block(gen, fileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TSciTagsProvider.Item(gen, blockTags, fileHelper));
        gen.addProvider(event.includeServer(), new TSciLootTableProvider(gen));
        gen.addProvider(event.includeClient(), new TSciBlockStateProvider(gen, fileHelper));
        gen.addProvider(event.includeClient(), new TSciItemModelProvider(gen, fileHelper));
    }
}
