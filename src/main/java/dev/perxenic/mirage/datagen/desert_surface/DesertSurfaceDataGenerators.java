package dev.perxenic.mirage.datagen.desert_surface;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

public class DesertSurfaceDataGenerators {
    public static void serverSideData(
            DataGenerator.PackGenerator generator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        generator.addProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Adds overground desert generation")
        ));
        generator.addProvider(output -> new DesertSurfaceDatapackProvider(output, lookupProvider));
    }
}
