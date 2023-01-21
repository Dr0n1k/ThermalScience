package dev.dr0n1k.thermal_science.util;

import cofh.core.item.BlockItemCoFH;
import cofh.core.item.ItemCoFH;
import cofh.lib.item.*;
import cofh.lib.util.constants.ModIds;
import cofh.thermal.core.ThermalCore;
import cofh.thermal.lib.item.BlockItemAugmentable;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static cofh.lib.util.Constants.TRUE;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.lib.common.ThermalItemGroups.THERMAL_BLOCKS;
import static cofh.thermal.lib.common.ThermalItemGroups.THERMAL_DEVICES;
import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;
import static dev.dr0n1k.thermal_science.ThermalScience.BLOCKS;
import static dev.dr0n1k.thermal_science.ThermalScience.ITEMS;

public class RegistrationHelper {
    // region Standard Tool Set
    public static void registerToolSet(String prefix, Tier tier, CreativeModeTab toolGroup, CreativeModeTab combatGroup) {
        ITEMS.register(prefix + "_shovel", () -> new ShovelItemCoFH(tier, 2.5f, -3.0f,
                new Item.Properties().tab(toolGroup)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(prefix + "_pickaxe", () -> new PickaxeItemCoFH(tier, 2, -2.8f,
                new Item.Properties().tab(toolGroup)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(prefix + "_axe", () -> new AxeItemCoFH(tier, 6, -3.0f,
                new Item.Properties().tab(toolGroup)).setModId(ID_THERMAL_SCIENCE));
        ITEMS.register(prefix + "_hoe", () -> new HoeItemCoFH(tier, -tier.getLevel(), 0f,
                new Item.Properties().tab(toolGroup)).setModId(ID_THERMAL_SCIENCE));

        ITEMS.register(prefix + "_sword", () -> new SwordItemCoFH(tier, 4, -2.4f,
                new Item.Properties().tab(combatGroup)).setModId(ID_THERMAL_SCIENCE));
    }
    // endregion

    // region GEM SETS
    public static void registerGemSet(String prefix, CreativeModeTab group, Rarity rarity, Supplier<Boolean> showInGroups) {
        registerGemSet(prefix, group, rarity, showInGroups, false);
    }

    public static void registerGemSet(String prefix, CreativeModeTab group, Supplier<Boolean> showInGroups) {
        registerGemSet(prefix, group, Rarity.COMMON, showInGroups, false);
    }

    public static void registerVanillaGemSet(String prefix, CreativeModeTab group) {
        registerGemSet(prefix, group, Rarity.COMMON, TRUE, true);
    }

    public static void registerGemSet(String prefix, CreativeModeTab group, Rarity rarity, Supplier<Boolean> showInGroups, boolean vanilla) {
        if (!vanilla) {
            ITEMS.register(prefix, () -> new ItemCoFH(new Item.Properties().tab(group).rarity(rarity)).setShowInGroups(showInGroups));
        }

        ITEMS.register(prefix + "_dust", () -> new ItemCoFH(new Item.Properties().tab(group).rarity(rarity)).setShowInGroups(showInGroups));
        ITEMS.register(prefix + "_gear", () -> new ItemCoFH(new Item.Properties().tab(group).rarity(rarity)).setShowInGroups(showInGroups));
    }
    // endregion
    // region BLOCKS
    public static void registerBlock(String name, Supplier<Block> sup) {
        registerBlock(name, sup, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Supplier<Boolean> showInGroups) {
        registerBlock(name, sup, showInGroups, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group) {
        registerBlock(name, sup, group, TRUE, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, Supplier<Boolean> showInGroups) {
        registerBlock(name, sup, group, showInGroups, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Rarity rarity) {
        registerBlock(name, sup, rarity, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Rarity rarity, Supplier<Boolean> showInGroups) {
        registerBlock(name, sup, rarity, showInGroups, ID_THERMAL_SCIENCE);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, Rarity rarity, Supplier<Boolean> showInGroups) {
        registerBlock(name, sup, group, rarity, showInGroups, ID_THERMAL_SCIENCE);
    }

    // MOD ID
    public static void registerBlock(String name, Supplier<Block> sup, String modId) {
        registerBlock(name, sup, THERMAL_BLOCKS, Rarity.COMMON, TRUE, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, String modId) {
        registerBlock(name, sup, group, Rarity.COMMON, TRUE, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Supplier<Boolean> showInGroups, String modId) {
        registerBlock(name, sup, THERMAL_BLOCKS, Rarity.COMMON, showInGroups, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, Supplier<Boolean> showInGroups, String modId) {
        registerBlock(name, sup, group, Rarity.COMMON, showInGroups, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Rarity rarity, String modId) {
        registerBlock(name, sup, THERMAL_BLOCKS, rarity, TRUE, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, Rarity rarity, String modId) {
        registerBlock(name, sup, group, rarity, TRUE, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Rarity rarity, Supplier<Boolean> showInGroups, String modId) {
        registerBlock(name, sup, THERMAL_BLOCKS, rarity, showInGroups, modId);
    }

    public static void registerBlock(String name, Supplier<Block> sup, CreativeModeTab group, Rarity rarity, Supplier<Boolean> showInGroups, String modId) {
        registerBlockAndItem(name, sup, () -> new BlockItemCoFH(BLOCKS.get(name), new Item.Properties().tab(group).rarity(rarity)).setShowInGroups(showInGroups).setModId(modId));
    }

    public static void registerBlockOnly(String name, Supplier<Block> sup) {
        BLOCKS.register(name, sup);
    }

    public static void registerBlockAndItem(String name, Supplier<Block> blockSup, Supplier<Item> itemSup) {
        BLOCKS.register(name, blockSup);
        ITEMS.register(name, itemSup);
    }

    // SHORTCUTS
    public static Block storageBlock() {
        return new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops());
    }

    public static Block storageBlock(MaterialColor color) {
        return new Block(BlockBehaviour.Properties.of(Material.METAL, color)
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops());
    }
    // endregion

    // region AUGMENTABLE BLOCKS
    public static void registerAugmentableBlock(String name, Supplier<Block> sup, IntSupplier numSlots, BiPredicate<ItemStack, List<ItemStack>> validAugment, Supplier<Boolean> showInGroups) {
        registerAugmentableBlock(name, sup, numSlots, validAugment, showInGroups, ID_THERMAL_SCIENCE);
    }

    public static void registerAugmentableBlock(String name, Supplier<Block> sup, IntSupplier numSlots, BiPredicate<ItemStack, List<ItemStack>> validAugment, String modId) {
        registerAugmentableBlock(name, sup, numSlots, validAugment, THERMAL_DEVICES, Rarity.COMMON, TRUE, modId);
    }

    public static void registerAugmentableBlock(String name, Supplier<Block> sup, IntSupplier numSlots, BiPredicate<ItemStack, List<ItemStack>> validAugment, Supplier<Boolean> showInGroups, String modId) {
        registerAugmentableBlock(name, sup, numSlots, validAugment, THERMAL_DEVICES, Rarity.COMMON, showInGroups, modId);
    }

    public static void registerAugmentableBlock(String name, Supplier<Block> sup, IntSupplier numSlots, BiPredicate<ItemStack, List<ItemStack>> validAugment, CreativeModeTab group, Rarity rarity, Supplier<Boolean> showInGroups, String modId) {
        BLOCKS.register(name, sup);
        ITEMS.register(name, (Supplier<Item>) () -> new BlockItemAugmentable(BLOCKS.get(name), new Item.Properties().tab(group).rarity(rarity)).setNumSlots(numSlots).setAugValidator(validAugment).setShowInGroups(showInGroups).setModId(modId));
    }
    // endregion
}
