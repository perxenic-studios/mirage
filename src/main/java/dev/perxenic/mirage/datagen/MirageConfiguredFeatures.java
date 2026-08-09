package dev.perxenic.mirage.datagen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static dev.perxenic.mirage.Mirage.mirageLoc;
import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirConfiguredFeature;

public class MirageConfiguredFeatures {
    static ResourceKey<ConfiguredFeature<?, ?>> NO_OP = mirConfiguredFeature("configured_no_op");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        context.register(
                NO_OP,
                new ConfiguredFeature<>(
                       Feature.NO_OP,
                       NoneFeatureConfiguration.INSTANCE
                )
        );
    }
}
