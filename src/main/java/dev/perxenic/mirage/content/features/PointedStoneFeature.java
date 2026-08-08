package dev.perxenic.mirage.content.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class PointedStoneFeature extends Feature<PointedStoneConfiguration> {
    public PointedStoneFeature(Codec<PointedStoneConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<PointedStoneConfiguration> context) {
        var config = context.config();
        var random = context.random();
        var origin = context.origin();
        var level = context.level();

        var length = config.length().sample(random);
        var direction = config.direction();
        int availableLength = 0;

        var currentPos = origin.mutable();

        while (availableLength < length && config.target().test(level, currentPos)) {
            availableLength++;
            currentPos.move(direction);
        }

        currentPos.set(origin);

        for (var i = 0; i < availableLength; i++)
        {
            BlockState stateToPlace;
            if (i == availableLength - 1) stateToPlace = config.tipBlock().getState(level, random, currentPos);
            else if (i == availableLength - 2) stateToPlace = config.frustumBlock().getState(level, random, currentPos);
            else if (i == 0) stateToPlace = config.baseBlock().getState(level, random, currentPos);
            else stateToPlace = config.middleBlock().getState(level, random, currentPos);

            level.setBlock(currentPos, stateToPlace, 2);
            currentPos.move(direction);
        }

        return availableLength > 0;
    }
}
