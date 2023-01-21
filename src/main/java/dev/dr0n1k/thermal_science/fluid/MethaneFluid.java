package dev.dr0n1k.thermal_science.fluid;

import cofh.lib.fluid.FluidCoFH;
import com.mojang.math.Vector3f;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static cofh.thermal.lib.common.ThermalItemGroups.THERMAL_ITEMS;
import static dev.dr0n1k.thermal_science.Constants.ID_FLUID_METHANE;
import static dev.dr0n1k.thermal_science.ThermalScience.*;

public class MethaneFluid extends FluidCoFH {

    private static final Material METHANE_FLUID = (new Material.Builder(MaterialColor.COLOR_PINK))
            .noCollider().nonSolid().replaceable().liquid().build();

    public static final RegistryObject<FluidType> TYPE = FLUID_TYPES.register(ID_FLUID_METHANE, () -> new FluidType(FluidType.Properties.create()
            .fallDistanceModifier(0f)
            .density(850)
            .viscosity(1000)
            .canDrown(true)
            .canSwim(false)
            .supportsBoating(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
    ));

    private static MethaneFluid INSTANCE;

    public static MethaneFluid instance() {
        if (INSTANCE == null) {
            INSTANCE = new MethaneFluid();
        }

        return INSTANCE;
    }

    protected MethaneFluid() {
        super(FLUIDS, ID_FLUID_METHANE);
        particleColor = new Vector3f(0.05f, 0.05f, 0.05f);
        block = BLOCKS.register(fluid(ID_FLUID_METHANE), () -> new FluidBlock(stillFluid, BlockBehaviour.Properties.of(METHANE_FLUID).noCollission().strength(100.0F).noLootTable()));
        bucket = ITEMS.register(bucket(ID_FLUID_METHANE), () -> new BucketItem(stillFluid, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(THERMAL_ITEMS)));
    }

    @Override
    protected Supplier<FluidType> type() {
        return TYPE;
    }

    @Override
    protected ForgeFlowingFluid.Properties fluidProperties() {
        return new ForgeFlowingFluid.Properties(type(), stillFluid, flowingFluid).block(block).bucket(bucket).levelDecreasePerBlock(2);
    }

    public static class FluidBlock extends LiquidBlock {
        public FluidBlock(Supplier<? extends FlowingFluid> fluidSup, Properties properties) {
            super(fluidSup, properties);
        }
    }
}
