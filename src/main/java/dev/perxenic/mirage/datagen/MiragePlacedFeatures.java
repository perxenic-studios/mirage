package dev.perxenic.mirage.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MiragePlacedFeatures {
    static Holder<PlacedFeature> SINGLE_CACTUS;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        SINGLE_CACTUS = context.register(
                ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc("placed_single_cactus")),
                new PlacedFeature(
                        configuredFeatures.getOrThrow(VegetationFeatures.CACTUS),
                        List.of(
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE),
                                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
                                        Blocks.CACTUS.defaultBlockState(),
                                        Vec3i.ZERO
                                ))
                        )
                )
        );
    }
}
