package dev.perxenic.mirage.datagen.badlands_surface;

import dev.perxenic.mirage.datagen.MiragePlacedFeatures;
import dev.perxenic.mirage.datagen.helpers.RandomFeatureBuilder;
import dev.perxenic.mirage.registry.MirageBlocks;
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
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.neoforged.neoforge.common.Tags;

import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirConfiguredFeature;

public class BadlandsSurfaceConfiguredFeatures {
    static ResourceKey<ConfiguredFeature<?, ?>> RANDOM_GRASS = bsConfiguredFeature("random_grass");
    static ResourceKey<ConfiguredFeature<?, ?>> RANDOM_VEGETATION = bsConfiguredFeature("random_vegetation");

    static ResourceKey<ConfiguredFeature<?, ?>> VEG_PATCH_ITEM = bsConfiguredFeature("veg_patch_item");
    static ResourceKey<ConfiguredFeature<?, ?>> VEGETATION_PATCH = bsConfiguredFeature("vegetation_patch");

    static ResourceKey<ConfiguredFeature<?, ?>> RANDOM_PATCH = bsConfiguredFeature("random_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(
                RANDOM_GRASS,
                weightedBlockState(WeightedList.<BlockState>builder()
                        .add(Blocks.SHORT_DRY_GRASS.defaultBlockState(), 1)
                        .add(Blocks.TALL_DRY_GRASS.defaultBlockState(), 1)
                        .add(MirageBlocks.SHORT_SCORCHED_GRASS.get().defaultBlockState(), 4)
                        .add(MirageBlocks.TALL_SCORCHED_GRASS.get().defaultBlockState(), 4)
                )
        );

        context.register(
                RANDOM_VEGETATION,
                weightedBlockState(WeightedList.<BlockState>builder()
                        .add(Blocks.SHORT_DRY_GRASS.defaultBlockState(), 4)
                        .add(Blocks.TALL_DRY_GRASS.defaultBlockState(), 2)
                        .add(Blocks.DEAD_BUSH.defaultBlockState(), 8)
                        .add(Blocks.SHORT_GRASS.defaultBlockState(), 12)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 7)
                        .add(Blocks.BUSH.defaultBlockState(), 4)
                )
        );

        context.register(
                VEG_PATCH_ITEM,
                new RandomFeatureBuilder(placedFeatures, BadlandsSurfacePlacedFeatures.RANDOM_VEGETATION)
                        .add(MiragePlacedFeatures.SINGLE_CACTUS, 1/10f)
                        .add(BadlandsSurfacePlacedFeatures.PATCH_TREE, 1/30f)
                        .build()
        );

        context.register(
                VEGETATION_PATCH,
                new ConfiguredFeature<>(
                        Feature.VEGETATION_PATCH,
                        new VegetationPatchConfiguration(
                                Tags.Blocks.SANDS_RED,
                                new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(Blocks.COARSE_DIRT.defaultBlockState(), 5)
                                        .add(Blocks.RED_SAND.defaultBlockState(), 1)
                                ),
                                placedFeatures.getOrThrow(BadlandsSurfacePlacedFeatures.VEG_PATCH_ITEM),
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

        context.register(
                RANDOM_PATCH,
                new RandomFeatureBuilder(placedFeatures, MiragePlacedFeatures.NO_OP)
                        .add(BadlandsSurfacePlacedFeatures.DEAD_BUSH_PATCH, 1/2f)
                        .add(BadlandsSurfacePlacedFeatures.CACTUS_PATCH, 3/10f)
                        .add(BadlandsSurfacePlacedFeatures.VEGETATION_PATCH, 1/4f)
                        .add(BadlandsSurfacePlacedFeatures.RANDOM_GRASS, 8/10f)
                        .build()
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

    private static ResourceKey<ConfiguredFeature<?, ?>> bsConfiguredFeature(String id) {
        return mirConfiguredFeature(BadlandsSurface.BS_ID + "/" + id);
    }
}
