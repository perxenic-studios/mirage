package dev.perxenic.mirage.datagen.desert_surface;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

//TODO: Move all desert surface features to datagen
public class DesertSurfaceDataGenerators {
    public static void serverSideData(
            DataGenerator.PackGenerator packGenerator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        packGenerator.addProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Adds overground desert generation")
        ));
        packGenerator.addProvider(output -> new DesertSurfaceDatapackProvider(output, lookupProvider));
    }
}
