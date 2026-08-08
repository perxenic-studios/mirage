package dev.perxenic.mirage.content.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record PointedStoneConfiguration(
        IntProvider length,
        Direction direction,
        BlockStateProvider baseBlock,
        BlockStateProvider middleBlock,
        BlockStateProvider frustumBlock,
        BlockStateProvider tipBlock,
        BlockPredicate target
) implements FeatureConfiguration {
    public static final Codec<PointedStoneConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    IntProviders.CODEC.fieldOf("length").forGetter(PointedStoneConfiguration::length),
                    Direction.CODEC.fieldOf("direction").forGetter(PointedStoneConfiguration::direction),
                    BlockStateProvider.CODEC.fieldOf("base_block").forGetter(PointedStoneConfiguration::baseBlock),
                    BlockStateProvider.CODEC.fieldOf("middle_block").forGetter(PointedStoneConfiguration::middleBlock),
                    BlockStateProvider.CODEC.fieldOf("frustum_block").forGetter(PointedStoneConfiguration::frustumBlock),
                    BlockStateProvider.CODEC.fieldOf("tip_block").forGetter(PointedStoneConfiguration::tipBlock),
                    BlockPredicate.CODEC.fieldOf("target").forGetter(PointedStoneConfiguration::target)
            ).apply(instance, PointedStoneConfiguration::new)
    );
}
