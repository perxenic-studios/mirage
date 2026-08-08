package dev.perxenic.mirage.datagen.desert_underground;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

public class DesertUndergroundDataGenerators {
    public static void serverSideData(
            DataGenerator.PackGenerator generator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        generator.addProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Adds underground desert generation")
        ));
        generator.addProvider(output -> new DesertUndergroundDatapackProvider(output, lookupProvider));
    }
}
