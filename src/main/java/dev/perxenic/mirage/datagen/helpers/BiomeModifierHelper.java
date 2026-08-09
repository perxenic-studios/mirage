package dev.perxenic.mirage.datagen.helpers;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class BiomeModifierHelper {
    public static void addFeatureBiomeModifer(
            BootstrapContext<BiomeModifier> context,
            HolderGetter<Biome> biomes,
            HolderGetter<PlacedFeature> placedFeatureLookup,
            GenerationStep.Decoration generationStep,
            String name,
            String biomeTag,
            List<ResourceKey<PlacedFeature>> placedFeatures
    ) {
        var features = placedFeatures.stream().map(placedFeatureLookup::getOrThrow).toList();

        context.register(
                ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, mirageLoc(name)),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(TagKey.create(Registries.BIOME, mirageLoc(biomeTag))),
                        HolderSet.direct(features),
                        generationStep
                )
        );
    }

    public static void removeFeatureBiomeModifer(
            BootstrapContext<BiomeModifier> context,
            HolderGetter<Biome> biomes,
            HolderGetter<PlacedFeature> placedFeatureLookup,
            GenerationStep.Decoration generationStep,
            String name,
            String biomeTag,
            Identifier... placedFeatures
    ) {
        var features = Arrays.stream(placedFeatures).map(feature ->
                placedFeatureLookup.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, feature))
        ).toList();

        context.register(
                ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, mirageLoc(name)),
                new BiomeModifiers.RemoveFeaturesBiomeModifier(
                        biomes.getOrThrow(TagKey.create(Registries.BIOME, mirageLoc(biomeTag))),
                        HolderSet.direct(features),
                        Set.of(generationStep)
                )
        );
    }
}
