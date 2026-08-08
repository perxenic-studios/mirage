package dev.perxenic.mirage.content.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.perxenic.mirage.content.blocks.PointedStoneBlock;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.MatchingBlockTagPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

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

    public static PointedStoneConfiguration simple(
            IntProvider length,
            Direction direction,
            PointedStoneBlock block
    ) {
        return new PointedStoneConfiguration(
                length,
                direction,
                SimpleStateProvider.simple(block.defaultBlockState()
                        .setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.BASE)
                        .setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
                        .setValue(PointedDripstoneBlock.WATERLOGGED, false)
                ),
                SimpleStateProvider.simple(block.defaultBlockState()
                        .setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.MIDDLE)
                        .setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
                        .setValue(PointedDripstoneBlock.WATERLOGGED, false)
                ),
                SimpleStateProvider.simple(block.defaultBlockState()
                        .setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.FRUSTUM)
                        .setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
                        .setValue(PointedDripstoneBlock.WATERLOGGED, false)
                ),
                SimpleStateProvider.simple(block.defaultBlockState()
                        .setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.TIP)
                        .setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
                        .setValue(PointedDripstoneBlock.WATERLOGGED, false)
                ),
                MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE
        );
    }
}
