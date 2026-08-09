package dev.perxenic.mirage.datagen.badlands_surface;

import dev.perxenic.mirage.datagen.MiragePlacedFeatures;
import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class BadlandsSurfaceConfiguredFeatures {
    static Holder<ConfiguredFeature<?, ?>> BADLANDS_GRASS;
    static Holder<ConfiguredFeature<?, ?>> BADLANDS_VEGETATION;

    static Holder<ConfiguredFeature<?, ?>> VEG_PATCH_CONTENT;
    static Holder<ConfiguredFeature<?, ?>> BADLANDS_VEG_PATCH;

    static Holder<ConfiguredFeature<?, ?>> RANDOM_BADLANDS_PATCHES;

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

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

        VEG_PATCH_CONTENT = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_badlands_veg_patch_content")),
                new ConfiguredFeature<>(
                        Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(
                                List.of(
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(MiragePlacedFeatures.SINGLE_CACTUS_KEY),
                                                1/10f
                                        ),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(BadlandsSurfacePlacedFeatures.BADLANDS_PINE),
                                                1/30f
                                        )
                                ),
                                placedFeatures.getOrThrow(BadlandsSurfacePlacedFeatures.BADLANDS_VEGETATION)
                        )
                )
        );

        BADLANDS_VEG_PATCH = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_badlands_veg_patch")),
                new ConfiguredFeature<>(
                        Feature.VEGETATION_PATCH,
                        new VegetationPatchConfiguration(
                                Tags.Blocks.SANDS_RED,
                                new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(Blocks.COARSE_DIRT.defaultBlockState(), 5)
                                        .add(Blocks.RED_SAND.defaultBlockState(), 1)
                                ),
                                placedFeatures.getOrThrow(BadlandsSurfacePlacedFeatures.VEG_PATCH_CONTENT),
                                CaveSurface.FLOOR,
                                ConstantInt.of(1),
                                1/10f,
                                5,
                                3/10f,
                                UniformInt.of(2, 3),
                                4/10f
                        )
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
