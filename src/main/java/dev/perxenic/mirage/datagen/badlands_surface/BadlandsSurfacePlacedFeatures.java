package dev.perxenic.mirage.datagen.badlands_surface;

import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirPlacedFeature;

public class BadlandsSurfacePlacedFeatures {
    static ResourceKey<PlacedFeature> BADLANDS_GRASS = mirPlacedFeature("placed_badlands_grass");
    static ResourceKey<PlacedFeature> BADLANDS_PINE = mirPlacedFeature("placed_badlands_pine");
    static ResourceKey<PlacedFeature> BADLANDS_VEGETATION = mirPlacedFeature("placed_badlands_vegetation");

    static ResourceKey<PlacedFeature> VEG_PATCH_CONTENT = mirPlacedFeature("placed_badlands_veg_patch_content");
    static ResourceKey<PlacedFeature> BADLANDS_VEG_PATCH = mirPlacedFeature("placed_badlands_veg_patch");

    static ResourceKey<PlacedFeature> DEAD_BUSH_PATCH = mirPlacedFeature("placed_dead_bush_badlands");
    static ResourceKey<PlacedFeature> CACTUS_PATCH = mirPlacedFeature("placed_cactus_patch_badlands");

    static ResourceKey<PlacedFeature> RANDOM_BADLANDS_PATCHES = mirPlacedFeature("placed_random_badlands_patches");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                BADLANDS_GRASS,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.BADLANDS_GRASS),
                        List.of(
                                RarityFilter.onAverageOnceEvery(6),
                                InSquarePlacement.spread(),
                                CountPlacement.of(UniformInt.of(5, 32)),
                                RandomOffsetPlacement.horizontal(TrapezoidInt.triangle(7)),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING)
                        )
                )
        );

        context.register(
                BADLANDS_PINE,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(TreeFeatures.PINE),
                        List.of(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
                                Blocks.SPRUCE_SAPLING.defaultBlockState(),
                                Vec3i.ZERO
                        )))
                )
        );

        context.register(
                BADLANDS_VEGETATION,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.BADLANDS_VEGETATION),
                        List.of()
                )
        );
        context.register(
                VEG_PATCH_CONTENT,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.VEG_PATCH_CONTENT),
                        List.of()
                )
        );

        context.register(
                BADLANDS_VEG_PATCH,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.BADLANDS_VEG_PATCH),
                        List.of(HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING))
                )
        );

        context.register(
                DEAD_BUSH_PATCH,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(VegetationFeatures.DEAD_BUSH),
                        List.of(
                                CountPlacement.of(UniformInt.of(3, 8)),
                                RandomOffsetPlacement.ofTriangle(5, 3),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        )
                )
        );
        context.register(
                CACTUS_PATCH,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(VegetationFeatures.CACTUS),
                        List.of(
                                CountPlacement.of(UniformInt.of(1, 6)),
                                RandomOffsetPlacement.horizontal(TrapezoidInt.triangle(6)),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                                BlockPredicateFilter.forPredicate(
                                        BlockPredicate.allOf(
                                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                                BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), Vec3i.ZERO)
                                        )
                                )
                        )
                )
        );

        context.register(
                RANDOM_BADLANDS_PATCHES,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.RANDOM_BADLANDS_PATCHES),
                        List.of(
                                CountPlacement.of(UniformInt.of(1, 5)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
    }
}
