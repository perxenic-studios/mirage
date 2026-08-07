package dev.perxenic.mirage.content.blocks;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.PointedDripstoneBlock.THICKNESS;
import static net.minecraft.world.level.block.PointedDripstoneBlock.TIP_DIRECTION;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
//TODO: Format and documentation pass
public class PointedStoneBlock extends Block {
    private static final int DELAY_BEFORE_FALLING = 2;
    private static final double MIN_TRIDENT_VELOCITY_TO_BREAK = 0.6;
    private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0F;
    private static final int STALACTITE_MAX_DAMAGE = 40;
    private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
    private static final double STALAGMITE_FALL_DISTANCE_OFFSET = 2.5;
    private static final int STALAGMITE_FALL_DAMAGE_MODIFIER = 2;
    private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778F;
    private static final int MAX_GROWTH_LENGTH = 7;
    private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
    private static final VoxelShape SHAPE_TIP_MERGE;
    private static final VoxelShape SHAPE_TIP_UP;
    private static final VoxelShape SHAPE_TIP_DOWN;
    private static final VoxelShape SHAPE_FRUSTUM;
    private static final VoxelShape SHAPE_MIDDLE;
    private static final VoxelShape SHAPE_BASE;
    private static final float MAX_HORIZONTAL_OFFSET;

    public PointedStoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TIP_DIRECTION, Direction.UP)
                .setValue(THICKNESS, DripstoneThickness.TIP)
                .setValue(BlockStateProperties.WATERLOGGED, false)
        );
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, THICKNESS, BlockStateProperties.WATERLOGGED);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return isValidPointedStonePlacement(level, pos, state.getValue(TIP_DIRECTION));
    }

    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random
    ) {
        if (state.getValue(BlockStateProperties.WATERLOGGED))
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        if (directionToNeighbour != Direction.UP && directionToNeighbour != Direction.DOWN) return state;
        else {
            Direction tipDirection = state.getValue(TIP_DIRECTION);
            if (tipDirection == Direction.DOWN && ticks.getBlockTicks().hasScheduledTick(pos, this)) {
                return state;
            } else if (directionToNeighbour == tipDirection.getOpposite() && !this.canSurvive(state, level, pos)) {
                if (tipDirection == Direction.DOWN) ticks.scheduleTick(pos, this, DELAY_BEFORE_FALLING);
                else ticks.scheduleTick(pos, this, 1);

                return state;
            } else {
                boolean mergeOpposingTips = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness newThickness = calculateThickness(level, pos, tipDirection, mergeOpposingTips);
                return state.setValue(THICKNESS, newThickness);
            }
        }
    }

    protected void onProjectileHit(Level level, BlockState state, BlockHitResult blockHit, Projectile projectile) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos blockPos = blockHit.getBlockPos();
        if (projectile.mayInteract(serverLevel, blockPos) &&
                projectile.mayBreak(serverLevel) &&
                projectile instanceof ThrownTrident &&
                projectile.getDeltaMovement().length() > MIN_TRIDENT_VELOCITY_TO_BREAK
        ) level.destroyBlock(blockPos, true);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP)
            entity.causeFallDamage(
                    fallDistance + STALAGMITE_FALL_DISTANCE_OFFSET,
                    STALAGMITE_FALL_DAMAGE_MODIFIER,
                    level.damageSources().stalagmite()
            );
        else super.fallOn(level, state, pos, entity, fallDistance);
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (isStalagmite(state) && !this.canSurvive(state, level, pos)) level.destroyBlock(pos, true);
        else spawnFallingStalactite(state, level, pos);
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < GROWTH_PROBABILITY_PER_RANDOM_TICK && isStalactiteStartPos(state, level, pos))
            growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction defaultTipDirection = context.getNearestLookingVerticalDirection().getOpposite();
        Direction tipDirection = calculateTipDirection(level, pos, defaultTipDirection);

        if (tipDirection == null) return null;

        boolean mergeOpposingTips = !context.isSecondaryUseActive();
        DripstoneThickness thickness = calculateThickness(level, pos, tipDirection, mergeOpposingTips);

        return this.defaultBlockState()
                .setValue(TIP_DIRECTION, tipDirection)
                .setValue(THICKNESS, thickness)
                .setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ?
                Fluids.WATER.getSource(false) :
                super.getFluidState(state);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = switch (state.getValue(THICKNESS)) {
            case TIP_MERGE -> SHAPE_TIP_MERGE;
            case TIP -> state.getValue(TIP_DIRECTION) == Direction.DOWN ? SHAPE_TIP_DOWN : SHAPE_TIP_UP;
            case FRUSTUM -> SHAPE_FRUSTUM;
            case MIDDLE -> SHAPE_MIDDLE;
            case BASE -> SHAPE_BASE;
        };
        return shape.move(state.getOffset(pos));
    }

    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    protected float getMaxHorizontalOffset() {
        return MAX_HORIZONTAL_OFFSET;
    }

    private void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos fallPos = pos.mutable();

        for (BlockState fallState = state; isStalactite(fallState); fallState = level.getBlockState(fallPos)) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, fallPos, fallState);
            if (isTip(fallState, true)) {
                int size = Math.min(1 + pos.getY() - fallPos.getY(), MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION);
                float damagePerFallDistance = STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE * size;
                entity.setHurtsEntities(damagePerFallDistance, STALACTITE_MAX_DAMAGE);
                break;
            }

            fallPos.move(Direction.DOWN);
        }
    }

    @VisibleForTesting
    public void growStalactiteOrStalagmiteIfPossible(BlockState stalactiteStartState, ServerLevel level, BlockPos stalactiteStartPos, RandomSource random) {
        BlockState rootState = level.getBlockState(stalactiteStartPos.above(1));
        BlockState stateAbove = level.getBlockState(stalactiteStartPos.above(2));
        if (canGrow(rootState, stateAbove)) {
            BlockPos stalactiteTipPos = findTip(stalactiteStartState, level, stalactiteStartPos, MAX_GROWTH_LENGTH, false);
            if (stalactiteTipPos != null) {
                BlockState stalactiteTipState = level.getBlockState(stalactiteTipPos);
                if (canTipGrow(stalactiteTipState, level, stalactiteTipPos)) {
                    if (random.nextBoolean()) {
                        grow(level, stalactiteTipPos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, stalactiteTipPos);
                    }
                }
            }
        }

    }

    private void growStalagmiteBelow(ServerLevel level, BlockPos posAboveStalagmite) {
        BlockPos.MutableBlockPos pos = posAboveStalagmite.mutable();

        for (int i = 0; i < MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING; ++i) {
            pos.move(Direction.DOWN);
            BlockState state = level.getBlockState(pos);
            if (!state.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection(state, Direction.UP) && canTipGrow(state, level, pos)) {
                grow(level, pos, Direction.UP);
                return;
            }

            if (isValidPointedStonePlacement(level, pos, Direction.UP) && !level.isWaterAt(pos.below())) {
                grow(level, pos.below(), Direction.UP);
                return;
            }
        }

    }

    private void grow(ServerLevel level, BlockPos growFromPos, Direction growToDirection) {
        BlockPos targetPos = growFromPos.relative(growToDirection);
        BlockState existingStateAtTargetPos = level.getBlockState(targetPos);
        if (isUnmergedTipWithDirection(existingStateAtTargetPos, growToDirection.getOpposite())) {
            createMergedTips(existingStateAtTargetPos, level, targetPos);
        } else if (existingStateAtTargetPos.isAir() || existingStateAtTargetPos.is(Blocks.WATER)) {
            createPointedStone(level, targetPos, growToDirection, DripstoneThickness.TIP);
        }

    }

    private void createPointedStone(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
        BlockState state = defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
        level.setBlock(pos, state, 3);
    }

    private void createMergedTips(BlockState tipState, LevelAccessor level, BlockPos tipPos) {
        BlockPos stalactitePos;
        BlockPos stalagmitePos;
        if (tipState.getValue(TIP_DIRECTION) == Direction.UP) {
            stalagmitePos = tipPos;
            stalactitePos = tipPos.above();
        } else {
            stalactitePos = tipPos;
            stalagmitePos = tipPos.below();
        }

        createPointedStone(level, stalactitePos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createPointedStone(level, stalagmitePos, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    private @Nullable BlockPos findTip(BlockState pointedStoneState, LevelAccessor level, BlockPos pointedStonePos, int maxSearchLength, boolean includeMergedTip) {
        if (isTip(pointedStoneState, includeMergedTip)) {
            return pointedStonePos;
        } else {
            Direction searchDirection = pointedStoneState.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> pathPredicate = (pos, state) -> state.is(this) && state.getValue(TIP_DIRECTION) == searchDirection;
            return findBlockVertical(level, pointedStonePos, searchDirection.getAxisDirection(), pathPredicate, (pointedStone) -> isTip(pointedStone, includeMergedTip), maxSearchLength).orElse(null);
        }
    }

    private @Nullable Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction defaultTipDirection) {
        Direction tipDirection;
        if (isValidPointedStonePlacement(level, pos, defaultTipDirection)) {
            tipDirection = defaultTipDirection;
        } else {
            if (!isValidPointedStonePlacement(level, pos, defaultTipDirection.getOpposite())) {
                return null;
            }

            tipDirection = defaultTipDirection.getOpposite();
        }

        return tipDirection;
    }

    private DripstoneThickness calculateThickness(LevelReader level, BlockPos pos, Direction tipDirection, boolean mergeOpposingTips) {
        Direction baseDirection = tipDirection.getOpposite();
        BlockState inFrontState = level.getBlockState(pos.relative(tipDirection));
        if (!isPointedStoneWithDirection(inFrontState, baseDirection)) {
            if (!isPointedStoneWithDirection(inFrontState, tipDirection)) {
                return DripstoneThickness.TIP;
            } else {
                DripstoneThickness inFrontThickness = inFrontState.getValue(THICKNESS);
                if (inFrontThickness != DripstoneThickness.TIP && inFrontThickness != DripstoneThickness.TIP_MERGE) {
                    BlockState behindState = level.getBlockState(pos.relative(baseDirection));
                    return !isPointedStoneWithDirection(behindState, tipDirection) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
                } else {
                    return DripstoneThickness.FRUSTUM;
                }
            }
        } else {
            return !mergeOpposingTips && inFrontState.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        }
    }

    private boolean canTipGrow(BlockState tipState, ServerLevel level, BlockPos tipPos) {
        Direction growDirection = tipState.getValue(TIP_DIRECTION);
        BlockPos growPos = tipPos.relative(growDirection);
        BlockState stateAtGrowPos = level.getBlockState(growPos);
        if (!stateAtGrowPos.getFluidState().isEmpty()) {
            return false;
        } else {
            return stateAtGrowPos.isAir() || isUnmergedTipWithDirection(stateAtGrowPos, growDirection.getOpposite());
        }
    }

    private boolean isValidPointedStonePlacement(LevelReader level, BlockPos pos, Direction tipDirection) {
        BlockPos behindPos = pos.relative(tipDirection.getOpposite());
        BlockState behindState = level.getBlockState(behindPos);
        return behindState.isFaceSturdy(level, behindPos, tipDirection) || isPointedStoneWithDirection(behindState, tipDirection);
    }

    private boolean isTip(BlockState state, boolean includeMergedTip) {
        if (!state.is(this)) {
            return false;
        } else {
            DripstoneThickness thickness = state.getValue(THICKNESS);
            return thickness == DripstoneThickness.TIP || includeMergedTip && thickness == DripstoneThickness.TIP_MERGE;
        }
    }

    private boolean isUnmergedTipWithDirection(BlockState state, Direction tipDirection) {
        return isTip(state, false) && state.getValue(TIP_DIRECTION) == tipDirection;
    }

    private boolean isStalactite(BlockState state) {
        return isPointedStoneWithDirection(state, Direction.DOWN);
    }

    private boolean isStalagmite(BlockState state) {
        return isPointedStoneWithDirection(state, Direction.UP);
    }

    private boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
        return isStalactite(state) && !level.getBlockState(pos.above()).is(this);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    private boolean isPointedStoneWithDirection(BlockState blockState, Direction tipDirection) {
        return blockState.is(this) && blockState.getValue(TIP_DIRECTION) == tipDirection;
    }

    private boolean canGrow(BlockState rootState, BlockState aboveState) {
        FluidState fluidState = aboveState.getFluidState();
        return rootState.is(this) && fluidState.is(Fluids.WATER) && fluidState.isSource();
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection axisDirection, BiPredicate<BlockPos, BlockState> pathPredicate, Predicate<BlockState> targetPredicate, int maxSteps) {
        Direction direction = Direction.get(axisDirection, Direction.Axis.Y);
        BlockPos.MutableBlockPos mutablePos = pos.mutable();

        for (int i = 1; i < maxSteps; ++i) {
            mutablePos.move(direction);
            BlockState state = level.getBlockState(mutablePos);
            if (targetPredicate.test(state)) {
                return Optional.of(mutablePos.immutable());
            }

            if (level.isOutsideBuildHeight(mutablePos.getY()) || !pathPredicate.test(mutablePos, state)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    static {
        SHAPE_TIP_MERGE = Block.column(6.0F, 0.0F, 16.0F);
        SHAPE_TIP_UP = Block.column(6.0F, 0.0F, 11.0F);
        SHAPE_TIP_DOWN = Block.column(6.0F, 5.0F, 16.0F);
        SHAPE_FRUSTUM = Block.column(8.0F, 0.0F, 16.0F);
        SHAPE_MIDDLE = Block.column(10.0F, 0.0F, 16.0F);
        SHAPE_BASE = Block.column(12.0F, 0.0F, 16.0F);
        MAX_HORIZONTAL_OFFSET = (float) SHAPE_BASE.min(Direction.Axis.X);
    }
}
