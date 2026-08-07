package dev.perxenic.mirage.content.features;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.joml.Vector3f;

public class UndergroundBlobFeature extends Feature<UndergroundBlobConfiguration> {
    public UndergroundBlobFeature(Codec<UndergroundBlobConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<UndergroundBlobConfiguration> context) {
        var config = context.config();
        var random = context.random();
        var origin = context.origin();
        var level = context.level();

        var radX = config.size().sample(random);
        var radY = config.size().sample(random);
        var radZ = config.size().sample(random);

        var negCorner = origin.offset(-radX, -radY, -radZ);
        var posCorner = origin.offset(radX, radY, radZ);

        var didPlace = false;

        for (var pos : BlockPos.betweenClosed(negCorner, posCorner))
        {
            var offset = pos.subtract(origin);
            // Offset scaled to be between 0 and 1 on every axis
            var proportionalOffset = new Vector3f(offset.toMutable()).div(radX, radY, radZ);

            if (proportionalOffset.lengthSquared() > 1) continue;

            if (!config.target().test(level, pos)) continue;

            level.setBlock(
                    pos,
                    config.stateProvider().getState(level, random, pos),
                    2
            );
            markAboveForPostProcessing(level, pos);
            didPlace = true;
        }

        return didPlace;
    }
}
