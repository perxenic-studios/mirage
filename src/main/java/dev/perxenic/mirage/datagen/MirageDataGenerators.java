package dev.perxenic.mirage.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class MirageDataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        serverSideData(generator, packOutput, lookupProvider);
        clientSideData(generator, packOutput);
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        serverSideData(generator, packOutput, lookupProvider);
    }

    private static void serverSideData(
            DataGenerator generator,
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        generator.addProvider(true, new MirageBlockTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MirageItemTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new LootTableProvider(
                packOutput,
                Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(
                                MirageBlockLootSubProvider::new,
                                LootContextParamSets.BLOCK
                        )
                ),
                lookupProvider
        ));

        // Update lookup provider after registering the armor trim patterns
        lookupProvider = generator.addProvider(true, new MirageDatapackProvider(packOutput, lookupProvider))
                .getRegistryProvider();

        generator.addProvider(true, new MirageRecipeProvider.Runner(packOutput, lookupProvider));
    }

    private static void clientSideData(
            DataGenerator generator,
            PackOutput packOutput
    ) {
        generator.addProvider(true, new MirageAtlasProvider(packOutput));
        generator.addProvider(true, new MirageEquipmentAssetProvider(packOutput));
        generator.addProvider(true, new MirageModelProvider(packOutput));
    }
}
