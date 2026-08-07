package dev.perxenic.mirage.content.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record UndergroundBlobConfiguration(
        BlockStateProvider stateProvider,
        BlockPredicate target,
        IntProvider size
) implements FeatureConfiguration {
    public static final Codec<UndergroundBlobConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(UndergroundBlobConfiguration::stateProvider),
                    BlockPredicate.CODEC.fieldOf("target").forGetter(UndergroundBlobConfiguration::target),
                    IntProviders.CODEC.fieldOf("size").forGetter(UndergroundBlobConfiguration::size)
            ).apply(instance, UndergroundBlobConfiguration::new)
    );
}
