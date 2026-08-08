package dev.perxenic.mirage.datagen.desert_underground;

import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class DesertUndergroundPlacedFeatures {
    static Holder<PlacedFeature> SANDY_STONE;

    static Holder<PlacedFeature> POINTED_SANDSTONE;
    static Holder<PlacedFeature> FLOOR_POINTED_SANDSTONE;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        SANDY_STONE = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_sandy_stone")),
                new PlacedFeature(
                        DesertUndergroundConfiguredFeatures.SANDY_STONE,
                        List.of(
                                CountPlacement.of(UniformInt.of(96, 128)),
                                InSquarePlacement.spread(),
                                HeightRangePlacement.of(
                                        TrapezoidHeight.of(
                                                VerticalAnchor.absolute(16),
                                                VerticalAnchor.absolute(256),
                                                96
                                        )
                                ),
                                BiomeFilter.biome()
                        )
                )
        );

        POINTED_SANDSTONE = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_pointed_sandstone")),
                pointedSandstoneFeature(
                        DesertUndergroundConfiguredFeatures.POINTED_SANDSTONE,
                        Direction.UP
                )
        );
        FLOOR_POINTED_SANDSTONE = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_floor_pointed_sandstone")),
                pointedSandstoneFeature(
                        DesertUndergroundConfiguredFeatures.FLOOR_POINTED_SANDSTONE,
                        Direction.DOWN
                )
        );
    }

    private static PlacedFeature pointedSandstoneFeature(
            Holder<ConfiguredFeature<?, ?>> feature,
            Direction scanDirection
    ) {
        return new PlacedFeature(
                feature,
                List.of(
                        CountPlacement.of(1024),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.BOTTOM,
                                VerticalAnchor.absolute(256)
                        ),
                        EnvironmentScanPlacement.scanningFor(
                                scanDirection,
                                BlockPredicate.matchesBlocks(
                                        scanDirection.getUnitVec3i(),
                                        MirageBlocks.SANDY_STONE.get()
                                ),
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                18
                        ),
                        BiomeFilter.biome()
                )
        );
    }
}
