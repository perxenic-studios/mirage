package dev.perxenic.mirage.datagen.badlands_surface;

import net.minecraft.core.Holder;
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

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class BadlandsSurfacePlacedFeatures {
    static Holder<PlacedFeature> BADLANDS_GRASS;
    static Holder<PlacedFeature> BADLANDS_PINE;
    static Holder<PlacedFeature> BADLANDS_VEG_PATCH;
    static Holder<PlacedFeature> RANDOM_BADLANDS_PATCHES;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        BADLANDS_GRASS = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_badlands_grass")),
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

        BADLANDS_PINE = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_badlands_pine")),
                new PlacedFeature(
                        configuredFeatures.getOrThrow(TreeFeatures.PINE),
                        List.of(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
                                Blocks.SPRUCE_SAPLING.defaultBlockState(),
                                Vec3i.ZERO
                        )))
                )
        );
    }
}
