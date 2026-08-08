package dev.perxenic.mirage.datagen.helpers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FactoryHelper {
    public static <T extends DataProvider> DataProvider.Factory<T> factory(
            Function<PackOutput, T> dataProvider
    ) {
        return dataProvider::apply;
    }

    public static <T extends DataProvider> DataProvider.Factory<T> factoryWithLookup(
            BiFunction<PackOutput, CompletableFuture<HolderLookup.Provider>, T> dataProvider,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        return output -> dataProvider.apply(output, lookupProvider);
    }
}
