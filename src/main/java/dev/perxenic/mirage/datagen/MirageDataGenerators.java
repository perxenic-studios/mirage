package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.datagen.badlands_surface.BadlandsSurfaceDataGenerators;
import dev.perxenic.mirage.datagen.desert_surface.DesertSurfaceDataGenerators;
import dev.perxenic.mirage.datagen.desert_underground.DesertUndergroundDataGenerators;
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
import java.util.function.BiConsumer;

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

        addBuiltInPack("badlands_surface", generator, lookupProvider, BadlandsSurfaceDataGenerators::serverSideData);
        addBuiltInPack("desert_surface", generator, lookupProvider, DesertSurfaceDataGenerators::serverSideData);
        addBuiltInPack("desert_underground", generator, lookupProvider, DesertUndergroundDataGenerators::serverSideData);
    }

    private static void clientSideData(
            DataGenerator generator,
            PackOutput packOutput
    ) {
        generator.addProvider(true, new MirageAtlasProvider(packOutput));
        generator.addProvider(true, new MirageEquipmentAssetProvider(packOutput));
        generator.addProvider(true, new MirageModelProvider(packOutput));
    }

    private static void addBuiltInPack(
            String packId,
            DataGenerator generator,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            BiConsumer<DataGenerator.PackGenerator, CompletableFuture<HolderLookup.Provider>> packData
    ) {

        var packGenerator = generator.getBuiltinDatapack(true, Mirage.MIRAGE_ID, packId);

        packData.accept(packGenerator, lookupProvider);
    }
}
