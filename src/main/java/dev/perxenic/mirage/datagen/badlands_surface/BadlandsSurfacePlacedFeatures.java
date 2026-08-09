package dev.perxenic.mirage.datagen.badlands_surface;

import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
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

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                BADLANDS_GRASS,
                new PlacedFeature(
                        BadlandsSurfaceConfiguredFeatures.BADLANDS_GRASS,
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
                new PlacedFeature(BadlandsSurfaceConfiguredFeatures.BADLANDS_VEGETATION, List.of())
        );
        context.register(
                VEG_PATCH_CONTENT,
                new PlacedFeature(BadlandsSurfaceConfiguredFeatures.VEG_PATCH_CONTENT, List.of())
        );

        context.register(
                BADLANDS_VEG_PATCH,
                new PlacedFeature(
                        BadlandsSurfaceConfiguredFeatures.BADLANDS_VEG_PATCH,
                        List.of(HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING))
                )
        );
    }
}
