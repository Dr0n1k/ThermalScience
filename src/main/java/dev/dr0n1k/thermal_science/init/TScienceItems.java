package dev.dr0n1k.thermal_science.init;

import cofh.core.item.BlockNamedItemCoFH;
import cofh.core.item.ItemCoFH;
import cofh.lib.item.ItemTierCoFH;
import cofh.lib.tags.ItemTagsCoFH;
import dev.dr0n1k.thermal_science.item.GlassCutter;
import dev.dr0n1k.thermal_science.tags.ItemTagsScience;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.lib.common.ThermalItemGroups.*;
import static dev.dr0n1k.thermal_science.Constants.*;
import static dev.dr0n1k.thermal_science.ThermalScience.*;
import static dev.dr0n1k.thermal_science.util.RegistrationHelper.*;

public class TScienceItems {
    public static void register() {
        registerResources();
        registerDecorations();
        registerGrowable();
        registerTools();
        registerMisc();
    }

    private static void registerResources() {
        ITEMS.register(ID_OBSIDIAN_DUST,
                () -> new ItemCoFH(new Item.Properties().tab(THERMAL_ITEMS)).setModId(ID_THERMAL_SCIENCE));

        registerGemSet("peridot", THERMAL_ITEMS, () -> true);
    }

    private static void registerDecorations() {
        ITEMS.register(ID_REDROCK_COBBLESTONE,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_REDROCK_COBBLESTONE), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_REDROCK_STONE,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_REDROCK_STONE), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_REDROCK_BRICKS,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_REDROCK_BRICKS), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));

        ITEMS.register(ID_BASALT_COBBLESTONE,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_BASALT_COBBLESTONE), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_BASALT_STONE,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_BASALT_STONE), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_BASALT_PAVER,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_BASALT_PAVER), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_BASALT_BRICKS,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_BASALT_BRICKS), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_BASALT_CHISELED_BRICKS,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_BASALT_CHISELED_BRICKS), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));

        ITEMS.register(ID_MARBLE_STONE,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_MARBLE_STONE), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(ID_MARBLE_BRICKS,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_MARBLE_BRICKS), new Item.Properties().tab(THERMAL_BLOCKS)).setModId(ID_THERMAL_SCIENCE));
    }

    private static void registerGrowable() {
        ITEMS.register(ID_ENDER_LILLY,
                () -> new ItemCoFH(new Item.Properties().tab(THERMAL_FOODS).rarity(Rarity.UNCOMMON)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(seeds(ID_ENDER_LILLY),
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_ENDER_LILLY), new Item.Properties().tab(THERMAL_FOODS).rarity(Rarity.UNCOMMON)).setModId(ID_THERMAL_SCIENCE));
    }

    private static void registerTools() {
        CreativeModeTab toolGroup = !TOOLS_COMPLEMENT_LOADED ? THERMAL_TOOLS : CreativeModeTab.TAB_TOOLS;
        CreativeModeTab combatGroup = !TOOLS_COMPLEMENT_LOADED ? THERMAL_TOOLS : CreativeModeTab.TAB_COMBAT;

        ITEMS.register(ID_GLASS_CUTTER,
                () -> new GlassCutter(new Item.Properties().stacksTo(1).tab(THERMAL_TOOLS).setNoRepair()).setModId(ID_THERMAL_SCIENCE));

        registerToolSet("ruby", TOOL_MATERIAL_RUBY, toolGroup, combatGroup);
        registerToolSet("sapphire", TOOL_MATERIAL_SAPPHIRE, toolGroup, combatGroup);
        registerToolSet("peridot", TOOL_MATERIAL_PERIDOT, toolGroup, combatGroup);
    }

    private static void registerMisc() {
        ITEMS.register(ID_CURSED_EARTH,
                () -> new BlockNamedItemCoFH(BLOCKS.get(ID_CURSED_EARTH), new Item.Properties().tab(THERMAL_BLOCKS).rarity(Rarity.UNCOMMON)) {
                    @Override
                    protected void tooltipDelegate(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                        tooltip.add(getInfoTextComponent("tooltip.thermal_science.cursed_earth.0"));
                    }
                }.setModId(ID_THERMAL_SCIENCE));
    }

    public static final Tier TOOL_MATERIAL_RUBY = new ItemTierCoFH(2, 1561, 8.0f, 2.0f, 14,
            () -> Ingredient.of(ItemTagsCoFH.GEMS_RUBY));

    public static final Tier TOOL_MATERIAL_SAPPHIRE = new ItemTierCoFH(2, 1561, 8.0f, 2.0f, 14,
            () -> Ingredient.of(ItemTagsCoFH.GEMS_SAPPHIRE));

    public static final Tier TOOL_MATERIAL_PERIDOT = new ItemTierCoFH(2, 1561, 8.0f, 2.0f, 14,
            () -> Ingredient.of(ItemTagsScience.GEMS_PERIDOT));
}
