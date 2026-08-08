package dev.perxenic.mirage.datagen.desert_underground;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

import static dev.perxenic.mirage.datagen.helpers.FactoryHelper.factoryWithLookup;

//TODO: Add gold geode to datagen, maybe migrate to feature?
public class DesertUndergroundDataGenerators {
    public static void serverSideData(
            DataGenerator.PackGenerator packGenerator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        packGenerator.addProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Adds underground desert generation")
        ));
        packGenerator.addProvider(factoryWithLookup(DesertUndergroundDatapackProvider::new, lookupProvider));
    }
}
