package dev.dr0n1k.thermal_science.item;

import cofh.core.item.ItemCoFH;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class GlassCutter extends ItemCoFH {
    public GlassCutter(Properties builder) {
        super(builder);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity livingEntity) {
        if (isGlassBlock(state)) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(state.getBlock())));
        }

        return super.mineBlock(itemStack, level, state, pos, livingEntity);
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull BlockState state) {
        return isGlassBlock(state);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack itemStack, @NotNull BlockState state) {
        return isGlassBlock(state) ? 15.0f : super.getDestroySpeed(itemStack, state);
    }

    private static boolean isGlassBlock(BlockState state) {
        return state.is(Tags.Blocks.GLASS) || state.is(Tags.Blocks.GLASS_PANES);
    }
}
