package dev.perxenic.mirage.content.blocks;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import com.mojang.serialization.MapCodec;
import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.sounds.AmbientDesertBlockSoundsPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TallScorchedGrassBlock extends DryVegetationBlock implements BonemealableBlock {
    public static final MapCodec<TallScorchedGrassBlock> CODEC = simpleCodec(TallScorchedGrassBlock::new);
    private static final VoxelShape SHAPE = Block.column(14.0, 0.0, 16.0);

    public MapCodec<TallScorchedGrassBlock> codec() {
        return CODEC;
    }

    public TallScorchedGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        AmbientDesertBlockSoundsPlayer.playAmbientDryGrassSounds(level, pos, random);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return BonemealableBlock.hasSpreadableNeighbourPos(
                level,
                pos,
                MirageBlocks.TALL_SCORCHED_GRASS.get().defaultBlockState()
        ) && level.getBlockState(pos.above()).isAir() && level.isInsideBuildHeight(pos.above());
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BonemealableBlock.findSpreadableNeighbourPos(
                level,
                pos,
                MirageBlocks.TALL_SCORCHED_GRASS.get().defaultBlockState()
        ).ifPresent((blockPos) -> level.setBlockAndUpdate(
                blockPos,
                MirageBlocks.TALL_SCORCHED_GRASS.get().defaultBlockState())
        );
    }
}
