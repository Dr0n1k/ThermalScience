package dev.dr0n1k.thermal_science.block;

import cofh.lib.block.CropBlockCoFH;
import cofh.lib.tags.BlockTagsCoFH;
import cofh.thermal.core.ThermalCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

public class EnderLilly extends CropBlockCoFH {
    public EnderLilly(Properties builder, PlantType type, int growLight, float growMod) {
        super(builder, type, growLight, growMod);
    }

    private static boolean isEndStone(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.END_STONE);
    }

    private static boolean isPhytoSoil(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).is(ThermalCore.BLOCKS.getSup("phytosoil").get());
    }

    private static boolean isEnderium(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).is(BlockTagsCoFH.STORAGE_BLOCKS_ENDERIUM);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return 0;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return state.is(BlockTags.DIRT) || isEndStone(level, pos)
                || isEnderium(level, pos)
                || isPhytoSoil(level, pos);
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, BlockPos pos) {
        final BlockPos below = pos.below();

        return this.mayPlaceOn(level.getBlockState(below), level, below);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) {
            return;
        }

        if (level.getRawBrightness(pos, 0) >= growLight) {
            if (!canHarvest(state)) {
                int age = getAge(state);
                float growthChance = getGrowthSpeed(this, level, pos) * growMod;

                if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int) (25.0F / growthChance) + 1) == 0)) {
                    int newAge = age + 1 == getPostHarvestAge() ? getMaxAge() : age + 1;
                    level.setBlock(pos, getStateForAge(newAge), 2);
                    ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.isClientSide()) {
            if (this.getAge(state) == this.getMaxAge() && random.nextInt(5) != 0) {
                int deltaDX = random.nextInt(2) * 2 - 1;
                int deltaDZ = random.nextInt(2) * 2 - 1;
                double dx = ((double) random.nextFloat() * 1.0F * (float) deltaDX);
                double dy = ((double) random.nextFloat() - 0.5) * 0.125;
                double dz = ((double) random.nextFloat() * 1.0F * (float) deltaDZ);
                double x = (double) pos.getX() + 0.5 + 0.25 * (double) deltaDX;
                double y = (double) ((float) pos.getY() + random.nextFloat());
                double z = (double) pos.getZ() + 0.5 + 0.25 * (double) deltaDZ;
                level.addParticle(ParticleTypes.PORTAL, x, y, z, dx, dy, dz);
            }
        }
    }

    protected static float getGrowthSpeed(@NotNull Block block, BlockGetter level, BlockPos pos) {
        float baseChance = CropBlockCoFH.getGrowthSpeed(block, level, pos);

        if (isEndStone(level, pos.below())) {
            baseChance *= 3.25f;
        } else if (isPhytoSoil(level, pos.below())) {
            baseChance *= 5.25f;
        } else if (isEnderium(level, pos.below())) {
            baseChance *= 12.5f;
        } else {
            baseChance *= 0.25f;
        }

        return baseChance;
    }
}
