package dev.dr0n1k.thermal_science.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;

public class CursedEarth extends Block {

    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 15);
    public static String itemCreate;

    public CursedEarth(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DECAY, 0));
    }

    @Override
    public void tick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(DECAY) < 15) {
            trySpreadCurse(level, state, pos, random);
        }

        BlockPos spawnPos = pos.above();
        if (level.getMaxLocalRawBrightness(spawnPos) >= 9 || !isEmptyState(level.getBlockState(spawnPos))) {
            return;
        }

        AABB area = new AABB(pos).inflate(7, 4, 7);
        if (level.getEntitiesOfClass(LivingEntity.class, area, entity -> entity instanceof Monster).size() < 8) {
            for (int i = 0; i < 3; ++i) {
                var data = NaturalSpawner.getRandomSpawnMobAt(level, level.structureManager(), level.getChunkSource().getGenerator(), MobCategory.MONSTER, random, spawnPos);
                if (data.isPresent()) {
                    var spawns = data.get();
                    BlockPos.MutableBlockPos spawnAtPos = new BlockPos.MutableBlockPos();
                    spawnAtPos.set(spawnPos);
                    if (!NaturalSpawner.isValidSpawnPostitionForType(level, MobCategory.MONSTER, level.structureManager(), level.getChunkSource().getGenerator(), spawns, spawnAtPos, 1)) {
                        continue;
                    }

                    var entity = spawns.type.create(level);
                    if (!(entity instanceof Mob mob)) {
                        continue;
                    }

                    mob.moveTo(spawnPos.above(), 0, 0);
                    if (!ForgeEventFactory.doSpecialSpawn(mob, level, (float) mob.getX(), (float) mob.getY(), (float) mob.getZ(), null, MobSpawnType.NATURAL)) {
                        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos.above()), MobSpawnType.NATURAL, null, null);
                        level.addFreshEntity(mob);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide() && !isMoving) {
            level.scheduleTick(pos, this, 1 + level.getRandom().nextInt(8));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.isClientSide()) {
            int particles = random.nextInt(4) + 2;
            for (int i = 0; i < particles; ++i) {
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + random.nextDouble(), pos.getY() + 1, pos.getZ() + random.nextDouble(), 0, 0, 0);
            }
        }
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader level, BlockPos pos, Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DECAY);
    }

    private boolean isEmptyState(BlockState state) {
        return !state.getMaterial().blocksMotion();
    }

    private void trySpreadCurse(Level level, BlockState state, BlockPos pos, RandomSource random) {
        List<BlockPos> list = new ArrayList<>();
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                for (int z = -1; z <= 1; ++z) {
                    if (x != 0 || y != 0 || z != 0) {
                        list.add(pos.offset(x, y, z));
                    }
                }
            }
        }

        Collections.shuffle(list);
        for (BlockPos target : list) {
            if (!level.isLoaded(target)) {
                continue;
            }

            BlockState targetState = level.getBlockState(target);
            if (targetState.is(BlockTags.MINEABLE_WITH_SHOVEL) && isEmptyState(level.getBlockState(target.above()))) {
                level.setBlock(target, state.setValue(DECAY, state.getValue(DECAY) + 1), 3);
                level.scheduleTick(target, this, 1 + random.nextInt(8));
                level.scheduleTick(pos, this, 1 + random.nextInt(8));
                return;
            }
        }
    }
}
