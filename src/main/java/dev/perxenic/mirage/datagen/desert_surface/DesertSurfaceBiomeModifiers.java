package dev.perxenic.mirage.datagen.desert_surface;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;

import java.util.List;

import static dev.perxenic.mirage.datagen.helpers.BiomeModifierHelper.addFeatureBiomeModifer;
import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirPlacedFeature;

public class DesertSurfaceBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        addFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                "add_desert_rock",
                "has_desert_rock",
                List.of(mirPlacedFeature("placed_desert_rock"))
        );
    }
}
