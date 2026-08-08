package dev.perxenic.mirage.datagen.desert_underground;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;

import java.util.List;

import static dev.perxenic.mirage.datagen.helpers.BiomeModifierHelper.addFeatureBiomeModifer;

public class DesertUndergroundBiomeModifers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        addFeatureBiomeModifer(
                context, biomes,
                GenerationStep.Decoration.UNDERGROUND_ORES,
                "add_sandy_stone",
                "has_sandy_stone",
                List.of(DesertUndergroundPlacedFeatures.SANDY_STONE)
        );
        addFeatureBiomeModifer(
                context, biomes,
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                "add_pointed_sandstone",
                "has_sandy_stone",
                List.of(
                        DesertUndergroundPlacedFeatures.POINTED_SANDSTONE,
                        DesertUndergroundPlacedFeatures.FLOOR_POINTED_SANDSTONE
                )
        );
        addFeatureBiomeModifer(
                context, biomes,
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                "add_underground_dry_grass",
                "has_sandy_stone",
                List.of(DesertUndergroundPlacedFeatures.UNDERGROUND_DRY_GRASS)
        );
    }
}
