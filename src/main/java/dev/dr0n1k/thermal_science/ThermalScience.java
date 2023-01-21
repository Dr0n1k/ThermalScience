package dev.dr0n1k.thermal_science;

import cofh.core.config.ConfigManager;
import cofh.lib.util.DeferredRegisterCoFH;
import dev.dr0n1k.thermal_science.client.gui.MachineAssemblyScreen;
import dev.dr0n1k.thermal_science.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;

import static dev.dr0n1k.thermal_science.Constants.ID_THERMAL_SCIENCE;
import static dev.dr0n1k.thermal_science.init.TScienceContainers.MACHINE_ASSEMBLY_CONTAINER;

@Mod(ID_THERMAL_SCIENCE)
public class ThermalScience {

    public static final DeferredRegisterCoFH<Block> BLOCKS = DeferredRegisterCoFH.create(ForgeRegistries.BLOCKS, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<Item> ITEMS = DeferredRegisterCoFH.create(ForgeRegistries.ITEMS, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<Fluid> FLUIDS = DeferredRegisterCoFH.create(ForgeRegistries.FLUIDS, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<FluidType> FLUID_TYPES = DeferredRegisterCoFH.create(ForgeRegistries.Keys.FLUID_TYPES, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegisterCoFH.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegisterCoFH.create(ForgeRegistries.RECIPE_SERIALIZERS, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<RecipeType<?>> RECIPE_TYPES = DeferredRegisterCoFH.create(ForgeRegistries.RECIPE_TYPES, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<PlacedFeature> PLACED_FEATURES = DeferredRegisterCoFH.create(Registry.PLACED_FEATURE_REGISTRY, ID_THERMAL_SCIENCE);
    public static final DeferredRegisterCoFH<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegisterCoFH.create(Registry.CONFIGURED_FEATURE_REGISTRY, ID_THERMAL_SCIENCE);

    public static ConfigManager CONFIG_MANAGER = new ConfigManager();
    public static boolean TOOLS_COMPLEMENT_LOADED;

    public ThermalScience() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TOOLS_COMPLEMENT_LOADED = false;

        CONFIG_MANAGER.register(modEventBus)
                .addCommonConfig(new ThermalScienceConfig());

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registrySetup);

        TScienceBlocks.register();
        TScienceItems.register();
        TScienceFluids.register();
        TScienceRecipeSerializers.register();
        TScienceRecipeTypes.register();
        TScienceContainers.register();
        TScienceTileEntities.register();
        TScienceRecipeManagers.register();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(this::registerGuiFactories);
    }

    private void registerGuiFactories() {
        MenuScreens.register(MACHINE_ASSEMBLY_CONTAINER.get(), MachineAssemblyScreen::new);
    }

    private void registrySetup(final NewRegistryEvent event) {
        CONFIG_MANAGER.setupClient();
        CONFIG_MANAGER.setupServer();
        CONFIG_MANAGER.setupCommon();
    }
}
