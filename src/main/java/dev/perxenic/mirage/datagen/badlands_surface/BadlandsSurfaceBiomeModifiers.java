package dev.perxenic.mirage.datagen.badlands_surface;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;

import java.util.List;

import static dev.perxenic.mirage.Mirage.mcLoc;
import static dev.perxenic.mirage.datagen.helpers.BiomeModifierHelper.*;
import static dev.perxenic.mirage.datagen.helpers.ResourceKeyHelper.mirPlacedFeature;

//TODO: Move all badlands surface features to datagen
public class BadlandsSurfaceBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        addFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                "add_random_badlands_patches",
                "has_badlands_patches",
                List.of(BadlandsSurfacePlacedFeatures.RANDOM_PATCH)
        );
        removeFeatureBiomeModifer(
                context, biomes, placedFeatures,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                "remove_vanilla_badlands_foliage",
                "has_badlands_patches",
                mcLoc("patch_cactus_decorated"),
                mcLoc("patch_dead_bush_badlands"),
                mcLoc("patch_dry_grass_badlands")
        );
    }
}
