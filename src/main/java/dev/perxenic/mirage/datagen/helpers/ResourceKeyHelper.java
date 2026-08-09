package dev.perxenic.mirage.datagen.helpers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class ResourceKeyHelper {
    public static ResourceKey<PlacedFeature> mirPlacedFeature(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc(id));
    }
}
