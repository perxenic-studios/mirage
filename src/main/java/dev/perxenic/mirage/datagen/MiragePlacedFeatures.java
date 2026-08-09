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

import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirPlacedFeature;

public class MiragePlacedFeatures {
    public static ResourceKey<PlacedFeature> NO_OP = mirPlacedFeature("placed_no_op");
    public static ResourceKey<PlacedFeature> SINGLE_CACTUS = mirPlacedFeature("placed_single_cactus");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                NO_OP,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(MirageConfiguredFeatures.NO_OP),
                        List.of()
                )
        );

        context.register(
                SINGLE_CACTUS,
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
