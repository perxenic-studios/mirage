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

        var height = config.height().sample(random);
        int availableHeight = 0;

        while (availableHeight < height && config.target().test(level, origin.below(availableHeight))) {
            availableHeight++;
        }

        for (var i = 0; i < availableHeight; i++)
        {
            var pos = origin.below(i);

            BlockState stateToPlace;
            if (i == availableHeight - 1) stateToPlace = config.tipBlock().getState(level, random, pos);
            else if (i == availableHeight - 2) stateToPlace = config.frustumBlock().getState(level, random, pos);
            else if (i == 0) stateToPlace = config.baseBlock().getState(level, random, pos);
            else stateToPlace = config.middleBlock().getState(level, random, pos);

            level.setBlock(pos, stateToPlace, 2);
        }

        return availableHeight > 0;
    }
}
