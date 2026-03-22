package dev.perxenic.mirage.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class MirageDataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new MirageBlockTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MirageItemTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new MirageRecipeProvider.Runner(packOutput, lookupProvider));

        generator.addProvider(true, new MirageEquipmentAssetProvider(packOutput));
        generator.addProvider(true, new MirageModelProvider(packOutput));
    }
}
