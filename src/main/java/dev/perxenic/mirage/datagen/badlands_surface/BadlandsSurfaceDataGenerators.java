package dev.perxenic.mirage.datagen.badlands_surface;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

public class BadlandsSurfaceDataGenerators {
    public static void serverSideData(
            DataGenerator.PackGenerator generator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        generator.addProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Adds overground badlands generation")
        ));
        generator.addProvider(output -> new BadlandsSurfaceDatapackProvider(output, lookupProvider));
    }
}
