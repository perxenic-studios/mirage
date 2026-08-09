package dev.perxenic.mirage.datagen.badlands_surface;

import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class BadlandsSurfaceConfiguredFeatures {
    static Holder<ConfiguredFeature<?, ?>> BADLANDS_GRASS;
    static Holder<ConfiguredFeature<?, ?>> BADLANDS_VEGETATION;

    static Holder<ConfiguredFeature<?, ?>> BADLANDS_VEG_PATCH;

    static Holder<ConfiguredFeature<?, ?>> RANDOM_BADLANDS_PATCHES;

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        BADLANDS_GRASS = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_badlands_grass")),
                weightedBlockState(WeightedList.<BlockState>builder()
                        .add(Blocks.SHORT_DRY_GRASS.defaultBlockState(), 1)
                        .add(Blocks.TALL_DRY_GRASS.defaultBlockState(), 1)
                        .add(MirageBlocks.SHORT_SCORCHED_GRASS.get().defaultBlockState(), 4)
                        .add(MirageBlocks.TALL_SCORCHED_GRASS.get().defaultBlockState(), 4)
                )
        );

        BADLANDS_VEGETATION = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_badlands_vegetation")),
                weightedBlockState(WeightedList.<BlockState>builder()
                        .add(Blocks.SHORT_DRY_GRASS.defaultBlockState(), 4)
                        .add(Blocks.TALL_DRY_GRASS.defaultBlockState(), 2)
                        .add(Blocks.DEAD_BUSH.defaultBlockState(), 8)
                        .add(Blocks.SHORT_GRASS.defaultBlockState(), 12)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 7)
                        .add(Blocks.BUSH.defaultBlockState(), 4)
                )
        );
    }

    public static ConfiguredFeature<SimpleBlockConfiguration, Feature<SimpleBlockConfiguration>> weightedBlockState(
            WeightedList.Builder<BlockState> blockStates
    ) {
        return new ConfiguredFeature<>(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(blockStates))
        );
    }
}
