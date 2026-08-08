package dev.perxenic.mirage.datagen.desert_underground;

import dev.perxenic.mirage.content.features.PointedStoneConfiguration;
import dev.perxenic.mirage.content.features.UndergroundBlobConfiguration;
import dev.perxenic.mirage.registry.MirageBlocks;
import dev.perxenic.mirage.registry.MirageFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class DesertUndergroundConfiguredFeatures {
    static Holder<ConfiguredFeature<?, ?>> SANDY_STONE;
    static Holder<ConfiguredFeature<?, ?>> POINTED_SANDSTONE;
    static Holder<ConfiguredFeature<?, ?>> FLOOR_POINTED_SANDSTONE;

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        SANDY_STONE = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_sandy_stone")),
                new ConfiguredFeature<>(
                        MirageFeatures.UNDERGROUND_BLOB.get(),
                        new UndergroundBlobConfiguration(
                                new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(MirageBlocks.SANDY_STONE.get().defaultBlockState(), 5)
                                        .add(Blocks.SAND.defaultBlockState(), 1)
                                        .add(Blocks.STONE.defaultBlockState(), 2)
                                ),
                                BlockPredicate.matchesTag(TagKey.create(
                                        Registries.BLOCK,
                                        mirageLoc("sandy_stone_replaceable")
                                )),
                                UniformInt.of(4, 7)
                        )
                )
        );

        POINTED_SANDSTONE = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_pointed_sandstone")),
                new ConfiguredFeature<>(
                        MirageFeatures.POINTED_STONE.get(),
                        PointedStoneConfiguration.simple(
                                new UniformInt(2, 6),
                                Direction.DOWN,
                                MirageBlocks.POINTED_SANDSTONE.get()
                        )
                )
        );
        FLOOR_POINTED_SANDSTONE = context.register(
                ResourceKey.create(Registries.CONFIGURED_FEATURE, mirageLoc("configured_floor_pointed_sandstone")),
                new ConfiguredFeature<>(
                        MirageFeatures.POINTED_STONE.get(),
                        PointedStoneConfiguration.simple(
                                new BiasedToBottomInt(1, 5),
                                Direction.UP,
                                MirageBlocks.POINTED_SANDSTONE.get()
                        )
                )
        );
    }
}
