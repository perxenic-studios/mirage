package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.datagen.badlands_surface.BadlandsSurfaceDataGenerators;
import dev.perxenic.mirage.datagen.desert_surface.DesertSurfaceDataGenerators;
import dev.perxenic.mirage.datagen.desert_underground.DesertUndergroundDataGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static dev.perxenic.mirage.datagen.helpers.FactoryHelper.*;

@EventBusSubscriber
public class MirageDataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        serverSideData(generator, lookupProvider);
        clientSideData(generator);
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        serverSideData(generator, lookupProvider);
    }

    private static void serverSideData(
            DataGenerator generator,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        generator.addProvider(true, factoryWithLookup(MirageBlockTagProvider::new, lookupProvider));
        generator.addProvider(true, factoryWithLookup(MirageItemTagProvider::new, lookupProvider));
        generator.addProvider(true, factoryWithLookup((output, lookup) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(
                                MirageBlockLootSubProvider::new,
                                LootContextParamSets.BLOCK
                        )
                ),
                lookup
        ), lookupProvider));

        // Update lookup provider after registering the armor trim patterns
        lookupProvider = generator.addProvider(true, factoryWithLookup(MirageDatapackProvider::new, lookupProvider))
                .getRegistryProvider();

        generator.addProvider(true, factoryWithLookup(MirageRecipeProvider.Runner::new, lookupProvider));

        addBuiltInPack("badlands_surface", generator, lookupProvider, BadlandsSurfaceDataGenerators::serverSideData);
        addBuiltInPack("desert_surface", generator, lookupProvider, DesertSurfaceDataGenerators::serverSideData);
        addBuiltInPack("desert_underground", generator, lookupProvider, DesertUndergroundDataGenerators::serverSideData);
    }

    private static void clientSideData(
            DataGenerator generator
    ) {
        generator.addProvider(true, factory(MirageAtlasProvider::new));
        generator.addProvider(true, factory(MirageEquipmentAssetProvider::new));
        generator.addProvider(true, factory(MirageModelProvider::new));
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
