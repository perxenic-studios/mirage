package dev.perxenic.mirage.datagen.desert_underground;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import static dev.perxenic.mirage.datagen.helpers.BiomeModifierHelper.addFeatureBiomeModifer;

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
}
