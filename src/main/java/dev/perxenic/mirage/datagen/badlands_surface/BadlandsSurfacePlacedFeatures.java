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
    static ResourceKey<PlacedFeature> RANDOM_GRASS = bsPlacedFeature("random_grass");
    static ResourceKey<PlacedFeature> PATCH_TREE = bsPlacedFeature("patch_tree");
    static ResourceKey<PlacedFeature> RANDOM_VEGETATION = bsPlacedFeature("random_vegetation");

    static ResourceKey<PlacedFeature> VEG_PATCH_ITEM = bsPlacedFeature("veg_patch_item");

    static ResourceKey<PlacedFeature> VEGETATION_PATCH = bsPlacedFeature("vegetation_patch");
    static ResourceKey<PlacedFeature> DEAD_BUSH_PATCH = bsPlacedFeature("dead_bush_patch");
    static ResourceKey<PlacedFeature> CACTUS_PATCH = bsPlacedFeature("cactus_patch");

    static ResourceKey<PlacedFeature> RANDOM_PATCH = bsPlacedFeature("random_patch");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                RANDOM_GRASS,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.RANDOM_GRASS),
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
                PATCH_TREE,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(TreeFeatures.PINE),
                        List.of(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
                                Blocks.SPRUCE_SAPLING.defaultBlockState(),
                                Vec3i.ZERO
                        )))
                )
        );

        context.register(
                RANDOM_VEGETATION,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.RANDOM_VEGETATION),
                        List.of()
                )
        );
        context.register(
                VEG_PATCH_ITEM,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.VEG_PATCH_ITEM),
                        List.of()
                )
        );

        context.register(
                VEGETATION_PATCH,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.VEGETATION_PATCH),
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
                RANDOM_PATCH,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(BadlandsSurfaceConfiguredFeatures.RANDOM_PATCH),
                        List.of(
                                CountPlacement.of(UniformInt.of(1, 5)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
    }

    private static ResourceKey<PlacedFeature> bsPlacedFeature(String id) {
        return mirPlacedFeature(BadlandsSurface.BS_ID + "/" + id);
    }
}
