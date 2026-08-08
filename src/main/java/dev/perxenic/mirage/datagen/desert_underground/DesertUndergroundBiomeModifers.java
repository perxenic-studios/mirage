package dev.perxenic.mirage.datagen.desert_underground;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Arrays;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class DesertUndergroundBiomeModifers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        addFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.UNDERGROUND_ORES,
                "add_sandy_stone",
                "has_sandy_stone",
                "placed_sandy_stone"
        );
        addFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                "add_pointed_sandstone",
                "has_sandy_stone",
                "placed_pointed_sandstone",
                "placed_floor_pointed_sandstone"
        );
        addFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                "add_underground_dry_grass",
                "has_sandy_stone",
                "placed_underground_dry_grass"
        );
    }

    private static void addFeatureBiomeModifer(
            BootstrapContext<BiomeModifier> context,
            HolderGetter<Biome> biomes,
            HolderGetter<PlacedFeature> placedFeatureLookup,
            GenerationStep.Decoration generationStep,
            String name,
            String biomeTag,
            String... placedFeatures
    ) {
        var features = Arrays.stream(placedFeatures).map(feature ->
                placedFeatureLookup.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, mirageLoc(feature)))
        ).toList();

        context.register(
                ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, mirageLoc(name)),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(TagKey.create(Registries.BIOME, mirageLoc(biomeTag))),
                        HolderSet.direct(features),
                        generationStep
                )
        );
    }
}
