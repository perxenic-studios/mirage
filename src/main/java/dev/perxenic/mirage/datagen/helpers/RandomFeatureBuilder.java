package dev.perxenic.mirage.datagen.helpers;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;

public class RandomFeatureBuilder {
    private final HolderGetter<PlacedFeature> placedFeatureLookup;
    private final Holder<PlacedFeature> defaultFeature;

    private final ArrayList<WeightedPlacedFeature> featureList = new ArrayList<>();

    public RandomFeatureBuilder(
            HolderGetter<PlacedFeature> placedFeatureLookup,
            ResourceKey<PlacedFeature> defaultFeature
    ) {
        this.placedFeatureLookup = placedFeatureLookup;
        this.defaultFeature = placedFeatureLookup.getOrThrow(defaultFeature);
    }

    public RandomFeatureBuilder add(ResourceKey<PlacedFeature> feature, float chance) {
        featureList.add(new WeightedPlacedFeature(
                placedFeatureLookup.getOrThrow(feature),
                chance
        ));

        return this;
    }

    public ConfiguredFeature<RandomFeatureConfiguration, Feature<RandomFeatureConfiguration>> build() {
        return new ConfiguredFeature<>(
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(featureList, defaultFeature)
        );
    }
}
