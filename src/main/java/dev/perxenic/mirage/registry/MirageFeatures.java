package dev.perxenic.mirage.registry;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.content.features.UndergroundBlobConfiguration;
import dev.perxenic.mirage.content.features.UndergroundBlobFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MirageFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, Mirage.MIRAGE_ID);

    public static Supplier<Feature<UndergroundBlobConfiguration>> UNDERGROUND_BLOB = FEATURES.register(
            "underground_blob",
            () -> new UndergroundBlobFeature(UndergroundBlobConfiguration.CODEC)
    );

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
