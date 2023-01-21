package dev.dr0n1k.thermal_science.data;

import cofh.lib.tags.ItemTagsCoFH;
import dev.dr0n1k.thermal_science.tags.BlockTagsScience;
import dev.dr0n1k.thermal_science.tags.ItemTagsScience;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;

public class TSciTagsProvider {
    public static class Item extends ItemTagsProvider {
        public Item(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, blockTagsProvider, ID_THERMAL_SCIENCE, existingFileHelper);
        }

        public String getName() {
            return "Thermal Science: Item Tags";
        }

        @Override
        protected void addTags() {
            this.tag(ItemTagsScience.DUSTS_OBSIDIAN).add(ITEMS.get(ID_OBSIDIAN_DUST));
            this.tag(ItemTagsScience.GEMS_PERIDOT).add(ITEMS.get("peridot"));
            this.tag(ItemTagsScience.DUSTS_PERIDOT).add(ITEMS.get("peridot_dust"));
            this.tag(ItemTagsScience.GEARS_PERIDOT).add(ITEMS.get("peridot_gear"));

            this.tag(Tags.Items.DUSTS)
                    .addTag(ItemTagsScience.DUSTS_OBSIDIAN)
                    .addTag(ItemTagsScience.DUSTS_PERIDOT);
            this.tag(Tags.Items.GEMS)
                    .addTag(ItemTagsScience.GEMS_PERIDOT);
            this.tag(ItemTagsCoFH.GEARS)
                    .addTag(ItemTagsScience.GEARS_PERIDOT);

            this.tag(ItemTagsScience.ORES_PERIDOT).add(
                    ITEMS.get("peridot_ore"),
                    ITEMS.get("deepslate_peridot_ore")
            );
            this.tag(ItemTagsScience.STORAGE_BLOCKS_PERIDOT).add(ITEMS.get("peridot_block"));

            this.tag(Tags.Items.ORES).addTag(ItemTagsScience.ORES_PERIDOT);
            this.tag(Tags.Items.STORAGE_BLOCKS).addTag(ItemTagsScience.STORAGE_BLOCKS_PERIDOT);

            this.tag(Tags.Items.COBBLESTONE).add(
                    ITEMS.get(ID_REDROCK_COBBLESTONE),
                    ITEMS.get(ID_BASALT_COBBLESTONE)
            );
            this.tag(Tags.Items.STONE).add(
                    ITEMS.get(ID_REDROCK_STONE),
                    ITEMS.get(ID_BASALT_STONE),
                    ITEMS.get(ID_MARBLE_STONE),
                    ITEMS.get(ID_BASALT_PAVER)
            );
            this.tag(ItemTags.STONE_BRICKS).add(
                    ITEMS.get(ID_REDROCK_BRICKS),
                    ITEMS.get(ID_BASALT_BRICKS),
                    ITEMS.get(ID_BASALT_CHISELED_BRICKS),
                    ITEMS.get(ID_MARBLE_BRICKS)
            );
        }
    }

    public static class Block extends BlockTagsProvider {
        public Block(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, ID_THERMAL_SCIENCE, existingFileHelper);
        }

        public String getName() {
            return "Thermal Science: Block Tags";
        }

        @Override
        protected void addTags() {
            this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BLOCKS.get(ID_CURSED_EARTH));
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_ASSEMBLY));

            this.tag(BlockTagsScience.ORES_PERIDOT).add(
                    BLOCKS.get("peridot_ore"),
                    BLOCKS.get("deepslate_peridot_ore")
            );
            this.tag(BlockTagsScience.STORAGE_BLOCKS_PERIDOT).add(BLOCKS.get("peridot_block"));

            this.tag(Tags.Blocks.ORES).addTag(BlockTagsScience.ORES_PERIDOT);
            this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(BlockTagsScience.STORAGE_BLOCKS_PERIDOT);
            this.tag(BlockTags.BEACON_BASE_BLOCKS).addTag(BlockTagsScience.STORAGE_BLOCKS_PERIDOT);

            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    BLOCKS.get("peridot_ore"),
                    BLOCKS.get("deepslate_peridot_ore"),
                    BLOCKS.get("peridot_block"),

                    BLOCKS.get(ID_REDROCK_COBBLESTONE),
                    BLOCKS.get(ID_REDROCK_STONE),
                    BLOCKS.get(ID_REDROCK_BRICKS),
                    BLOCKS.get(ID_BASALT_COBBLESTONE),
                    BLOCKS.get(ID_BASALT_STONE),
                    BLOCKS.get(ID_BASALT_PAVER),
                    BLOCKS.get(ID_BASALT_BRICKS),
                    BLOCKS.get(ID_BASALT_CHISELED_BRICKS),
                    BLOCKS.get(ID_MARBLE_STONE),
                    BLOCKS.get(ID_MARBLE_BRICKS)
            );
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                    BLOCKS.get(ID_REDROCK_COBBLESTONE),
                    BLOCKS.get(ID_REDROCK_STONE),
                    BLOCKS.get(ID_REDROCK_BRICKS),
                    BLOCKS.get(ID_MARBLE_STONE),
                    BLOCKS.get(ID_MARBLE_BRICKS)
            );
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                    BLOCKS.get(ID_BASALT_COBBLESTONE),
                    BLOCKS.get(ID_BASALT_STONE),
                    BLOCKS.get(ID_BASALT_PAVER),
                    BLOCKS.get(ID_BASALT_BRICKS),
                    BLOCKS.get(ID_BASALT_CHISELED_BRICKS),
                    BLOCKS.get("peridot_ore"),
                    BLOCKS.get("deepslate_peridot_ore"),
                    BLOCKS.get("peridot_block")
            );

            this.tag(Tags.Blocks.COBBLESTONE).add(
                    BLOCKS.get(ID_REDROCK_COBBLESTONE),
                    BLOCKS.get(ID_BASALT_COBBLESTONE)
            );
            this.tag(Tags.Blocks.STONE).add(
                    BLOCKS.get(ID_REDROCK_STONE),
                    BLOCKS.get(ID_BASALT_STONE),
                    BLOCKS.get(ID_MARBLE_STONE),
                    BLOCKS.get(ID_BASALT_PAVER)
            );
            this.tag(BlockTags.STONE_BRICKS).add(
                    BLOCKS.get(ID_REDROCK_BRICKS),
                    BLOCKS.get(ID_BASALT_BRICKS),
                    BLOCKS.get(ID_BASALT_CHISELED_BRICKS),
                    BLOCKS.get(ID_MARBLE_BRICKS)
            );
            this.tag(BlockTags.BASE_STONE_OVERWORLD).add(
                    BLOCKS.get(ID_REDROCK_STONE),
                    BLOCKS.get(ID_BASALT_STONE),
                    BLOCKS.get(ID_MARBLE_STONE)
            );
        }
    }
}
