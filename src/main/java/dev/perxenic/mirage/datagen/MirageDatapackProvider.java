package dev.perxenic.mirage.datagen;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.content.MirageArmorTrimPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MirageDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.PLACED_FEATURE, MiragePlacedFeatures::bootstrap)
            .add(Registries.TRIM_PATTERN, MirageArmorTrimPatterns::bootstrap);

    public MirageDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Mirage.MIRAGE_ID));
    }
}
