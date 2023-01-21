package dev.dr0n1k.thermal_science.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static cofh.lib.util.constants.ModIds.ID_COFH_CORE;
import static cofh.lib.util.constants.ModIds.ID_FORGE;

public class ItemTagsScience {

    public static final TagKey<Item> GEMS_PERIDOT = forgeTag("gems/peridot");
    public static final TagKey<Item> DUSTS_PERIDOT = forgeTag("dusts/peridot");
    public static final TagKey<Item> DUSTS_OBSIDIAN = forgeTag("dusts/obsidian");
    public static final TagKey<Item> GEARS_PERIDOT = forgeTag("gears/peridot");
    public static final TagKey<Item> ORES_PERIDOT = forgeTag("ores/peridot");
    public static final TagKey<Item> STORAGE_BLOCKS_PERIDOT = forgeTag("storage_blocks/peridot");

    public static final TagKey<Item> TOOLS_RUBY = forgeTag("tools/ruby");
    public static final TagKey<Item> TOOLS_SAPPHIRE = forgeTag("tools/sapphire");
    public static final TagKey<Item> TOOLS_PERIDOT = forgeTag("tools/peridot");

    // region HELPERS
    private static TagKey<Item> cofhTag(String name) {

        return net.minecraft.tags.ItemTags.create(new ResourceLocation(ID_COFH_CORE, name));
    }

    private static TagKey<Item> forgeTag(String name) {

        return net.minecraft.tags.ItemTags.create(new ResourceLocation(ID_FORGE, name));
    }
    // endregion
}
