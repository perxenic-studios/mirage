package dev.perxenic.mirage.datagen.desert_surface;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockBlobConfiguration;

import static dev.perxenic.mirage.Mirage.mirageLoc;
import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirConfiguredFeature;

public class DesertSurfaceConfiguredFeatures {
    static ResourceKey<ConfiguredFeature<?, ?>> SMALL_ROCK = dsConfiguredFeature("small_rock");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        context.register(
                SMALL_ROCK,
                new ConfiguredFeature<>(
                        Feature.BLOCK_BLOB,
                        new BlockBlobConfiguration(
                                Blocks.SMOOTH_SANDSTONE.defaultBlockState(),
                                BlockPredicate.matchesTag(TagKey.create(
                                        Registries.BLOCK,
                                        mirageLoc("desert_rock_can_place_on")
                                ))
                        )
                )
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> dsConfiguredFeature(String id) {
        return mirConfiguredFeature(DesertSurface.DS_ID + "/" + id);
    }
}
