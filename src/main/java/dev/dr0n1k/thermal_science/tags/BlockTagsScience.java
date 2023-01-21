package dev.dr0n1k.thermal_science.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static cofh.lib.util.constants.ModIds.ID_COFH_CORE;
import static cofh.lib.util.constants.ModIds.ID_FORGE;

public class BlockTagsScience {
    public static final TagKey<Block> ORES_PERIDOT = forgeTag("ores/peridot");
    public static final TagKey<Block> STORAGE_BLOCKS_PERIDOT = forgeTag("storage_blocks/peridot");

    // region HELPERS
    private static TagKey<Block> cofhTag(String name) {
        return net.minecraft.tags.BlockTags.create(new ResourceLocation(ID_COFH_CORE, name));
    }

    private static TagKey<Block> forgeTag(String name) {
        return net.minecraft.tags.BlockTags.create(new ResourceLocation(ID_FORGE, name));
    }
    // endregion
}
