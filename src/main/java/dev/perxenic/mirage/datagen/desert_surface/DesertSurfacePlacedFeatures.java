package dev.perxenic.mirage.datagen.desert_surface;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirPlacedFeature;

public class DesertSurfacePlacedFeatures {
    static ResourceKey<PlacedFeature> SMALL_ROCK = dsPlacedFeature("small_rock");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                SMALL_ROCK,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(DesertSurfaceConfiguredFeatures.SMALL_ROCK),
                        List.of(
                                NoiseBasedCountPlacement.of(4, 8, -0.15),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                                RandomOffsetPlacement.vertical(new WeightedListInt(
                                        WeightedList.<IntProvider>builder()
                                                .add(ConstantInt.of(-1), 1)
                                                .add(ConstantInt.of(0), 3)
                                                .build()
                                ))
                        )
                )
        );
    }

    private static ResourceKey<PlacedFeature> dsPlacedFeature(String id) {
        return mirPlacedFeature(DesertSurface.DS_ID + "/" + id);
    }
}
